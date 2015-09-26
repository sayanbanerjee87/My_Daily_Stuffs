package com.schneider_electric.dces.pricing.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;

import javax.inject.Singleton;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import com.schneider_electric.dces.pricing.exception.UserNotFoundException;
import com.schneider_electric.dces.pricing.i18n.I18nProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schneider_electric.dces.pricing.model.FederatedIdWrapper;
import org.xnap.commons.i18n.I18n;

@Singleton
@Service
public class UserServiceImpl implements UserService {
	
	@Value(value = "${truststore.path}")
	private String truststorePath;
	
	@Value(value = "${https.proxy.host}")
	private String httpsProxyHost;
	
	@Value(value = "${https.proxy.port}")
	private String httpsProxyPort;
	
	@Value(value = "${authentication.url}")
	private String authenticationUrl;
	
	protected final static Log LOG = LogFactory.getLog(UserServiceImpl.class);

    public String getFederatedId(String authorization) {
        FederatedIdWrapper federatedIdWrapper = doGetFederatedId(authorization);

        if (federatedIdWrapper == null || federatedIdWrapper.getId() == null) {
            I18n i18n = I18nProvider.get();
            throw new UserNotFoundException(i18n.tr("Unable to retrieve user"));
        }

        return federatedIdWrapper.getId();
    }

	private FederatedIdWrapper doGetFederatedId(String authorization) {
		System.setProperty ("https.proxyHost", httpsProxyHost);
	    System.setProperty ("https.proxyPort", httpsProxyPort);
		
		Client client = ClientBuilder.newBuilder().trustStore(getTruststore()).build();
		
		String rsp = client.target(authenticationUrl)
		        .request(MediaType.APPLICATION_JSON)
		        .header("Authorization", authorization)
		        .get(String.class);
		
		FederatedIdWrapper federatedIdWrapper = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			federatedIdWrapper = mapper.readValue(rsp, FederatedIdWrapper.class);
		} catch (IOException e) {
			LOG.warn("Unable to get the userId");
		}

		if (LOG.isInfoEnabled()) {
            LOG.info("federatedId is : " + federatedIdWrapper);
        }
		
		return federatedIdWrapper;
	}
	
	private KeyStore getTruststore() {
        try {
            
            LOG.info("Loading truststore from " + truststorePath);
            InputStream truststoreInputStream = null;
            
            try {
                truststoreInputStream = new FileInputStream(truststorePath);
            } catch (Exception e) {
                LOG.info("Load truststore from " + truststorePath + " failed, trying to look in the classpath");
            }
            if (truststoreInputStream == null) {
                truststoreInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(truststorePath);
            }
            if (truststoreInputStream != null) {
                LOG.info("Truststore found");
                try {

                    KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());

                    ks.load(truststoreInputStream, null);

                    return ks;
                } finally {
                    truststoreInputStream.close();
                }
            } else {
                LOG.warn("No truststore found for URL " + truststorePath); 
                return null;
            }

        } catch (Exception e) {
            LOG.error("Error while loading connection factory", e);
            return null;
        }
    }

}
