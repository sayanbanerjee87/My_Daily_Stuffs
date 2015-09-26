package com.schneider_electric.dces.pricing.config;

import com.schneider_electric.dces.pricing.i18n.I18nProvider;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.xnap.commons.i18n.I18n;

import java.io.FileInputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

/**
 * User: FDU3285
 * Date: 20/11/2014
 * Time: 10:12
 */
public class HtPasswdUserAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private static final Logger log = LoggerFactory.getLogger(HtPasswdUserAuthenticationProvider.class);
    private final UserDetailsService userDetailsService;
    private final Map<String, String> htUsers = newHashMap();

    public HtPasswdUserAuthenticationProvider(UserDetailsService userDetailsService, Resource htpasswdFile) {
        super();
        this.userDetailsService = userDetailsService;
        read(htpasswdFile);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        I18n i18n = I18nProvider.get();
        if (userDetails == null || userDetails.getUsername() == null) {
            throw new UsernameNotFoundException(i18n.tr("Unknown user"));
        }

        String password = authentication.getCredentials().toString();
        if (htUsers.containsKey(userDetails.getUsername())) {
            String encryptedPwd = htUsers.get(userDetails.getUsername());
            String passwd64 = Base64.encodeBase64String(DigestUtils.sha(password));
            if (encryptedPwd.substring("{SHA}".length()).equals(passwd64) ) {
                logger.debug("Unsalted SHA-1 encoded password matched for user '" + userDetails.getUsername() + "'");
                return;
            }
            throw new BadCredentialsException(i18n.tr("Authentication failed for user {0}", userDetails.getUsername()));
        }
        throw new UsernameNotFoundException(i18n.tr("Unknown user {0}", userDetails.getUsername()));
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        return userDetailsService.loadUserByUsername(username);
    }

    /**
     * Reads the htpasswd file and builds the in-memory lookup map.
     * @param htpasswdFile
     */
    private void read(Resource htpasswdFile)
    {
        log.info("Reading resource file " + htpasswdFile.getFilename());
        if (htpasswdFile.exists()) {
            Pattern entry = Pattern.compile("^([^:]+):(.+)");

            Scanner scanner = null;
            try {
                scanner = new Scanner(new FileInputStream(htpasswdFile.getFile()));
                while( scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    if ( !line.isEmpty() &&  !line.startsWith("#") ) {
                        Matcher m = entry.matcher(line);
                        if ( m.matches() ) {
                            htUsers.put(m.group(1), m.group(2));
                        }
                    }
                }
            } catch (Exception e) {
                log.error("Failed to read {}", htpasswdFile, e);
            }
            finally {
                if (scanner != null) scanner.close();
            }
        }
    }
}
