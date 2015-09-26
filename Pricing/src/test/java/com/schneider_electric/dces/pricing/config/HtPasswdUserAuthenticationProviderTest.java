package com.schneider_electric.dces.pricing.config;

import com.schneider_electric.dces.pricing.config.HtPasswdUserAuthenticationProvider;
import com.schneider_electric.dces.pricing.i18n.I18nProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.xnap.commons.i18n.I18nFactory;

import java.util.Locale;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HtPasswdUserAuthenticationProviderTest {

    @Mock
    private UserDetailsService userDetailsService;

    @Before
    public void setUp() {
        I18nProvider.set(I18nFactory.getI18n(getClass(), Locale.ENGLISH));
    }

    @Test
    public void shouldAuthenticate_usingHtpasswdFile() {
        HtPasswdUserAuthenticationProvider authenticationProvider = new HtPasswdUserAuthenticationProvider(userDetailsService, new ClassPathResource("htpassword-test"));
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("pace");
        authenticationProvider.additionalAuthenticationChecks(userDetails, new UsernamePasswordAuthenticationToken("pace", "pacepwd"));
    }

    @Test(expected = BadCredentialsException.class)
    public void shouldAuthenticationFails_usingHtpasswdFile_withWrongPassword() {
        HtPasswdUserAuthenticationProvider authenticationProvider = new HtPasswdUserAuthenticationProvider(userDetailsService, new ClassPathResource("htpassword-test"));
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("pace");
        authenticationProvider.additionalAuthenticationChecks(userDetails, new UsernamePasswordAuthenticationToken("pace", "pacepwdwrong"));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void shouldAuthenticationFails_usingProperties_IfHtpasswdFileNotFound_andUserUnknown() {
        HtPasswdUserAuthenticationProvider authenticationProvider = new HtPasswdUserAuthenticationProvider(userDetailsService, new ClassPathResource("htpassword-unknown"));
        UserDetails userDetails = mock(UserDetails.class);
        authenticationProvider.additionalAuthenticationChecks(userDetails, new UsernamePasswordAuthenticationToken("pace3", "paceProp"));
    }

}