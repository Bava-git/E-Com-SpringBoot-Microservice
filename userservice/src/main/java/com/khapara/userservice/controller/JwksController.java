package com.khapara.userservice.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class JwksController {

    @GetMapping("/.well-known/jwks.json")
    public Map<String, Object> getJwks() throws Exception {
        // Load public certificate
        InputStream is = getClass().getResourceAsStream("/jwt_public.cer");
        CertificateFactory fact = CertificateFactory.getInstance("X.509");
        X509Certificate cert = (X509Certificate) fact.generateCertificate(is);
        RSAPublicKey publicKey = (RSAPublicKey) cert.getPublicKey(); // Build JWKS
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .keyUse(com.nimbusds.jose.jwk.KeyUse.SIGNATURE)
                .algorithm(com.nimbusds.jose.JWSAlgorithm.RS256)
                .keyID("jwtkey") // must match kid in JWT header
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return jwkSet.toJSONObject();
    }


}
