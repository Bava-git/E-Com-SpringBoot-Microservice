package com.khapara.userservice.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

@Component
public class KeyProvider {

    @Value("${jwt.keystore-location}")
    private String keystorePath;
    @Value("${jwt.keystore-password}")
    private String keystorePassword;
    @Value("${jwt.key-alias}")
    private String keyAlias;
    @Value("${jwt.key-password}")
    private String keyPassword;

    public PrivateKey getPrivateKey() throws Exception {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        try (InputStream is = getClass().getResourceAsStream("/jwt.jks")) {
            keyStore.load(is, keystorePassword.toCharArray());
        }
        return (PrivateKey) keyStore.getKey(keyAlias, keyPassword.toCharArray());
    }

    public PublicKey loadPublicKey() throws Exception {
        try (InputStream is = getClass().getResourceAsStream("/jwt_public.cer")) {
            CertificateFactory factory = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) factory.generateCertificate(is);
            return cert.getPublicKey();
        }
    }
}
