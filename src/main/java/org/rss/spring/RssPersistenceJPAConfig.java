package org.rss.spring;

import org.common.spring.PersistenceJPACommonConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import(PersistenceJPACommonConfig.class)
@ComponentScan({ "org.rss.persistence" })
@ImportResource("classpath*:rssPersistenceConfig.xml")
@PropertySource({ "classpath:persistence-${persistenceTarget:prod}.properties" })
public class RssPersistenceJPAConfig {

    public RssPersistenceJPAConfig() {
        super();
    }

}