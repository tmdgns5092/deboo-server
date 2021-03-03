package com.restapi.deboo.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimplePBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEncryptableProperties
public class PropertyEncyptConfiguration {

    @Bean("jasyptStringEncryptor")
    public StandardPBEStringEncryptor PropertyEncyptConfiguration() {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        SimplePBEConfig config = new SimplePBEConfig();
        config.setPassword("deboo");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPoolSize(1);
        standardPBEStringEncryptor.setConfig(config);

        return standardPBEStringEncryptor;
    }
}
