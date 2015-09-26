package com.schneider_electric.dces.bom;

import com.schneider_electric.dces.bom.rest.ExtendBOM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

/**
 * Created by porcher-g on 09/12/2014.
 */
public class SSLUtils {

    protected final static Logger LOG = LoggerFactory.getLogger(SSLUtils.class);

    public static KeyStore getTruststore(String truststorePath) {
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
