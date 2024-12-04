package com.zjw.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 监听器
 *
 * @author 朱俊伟
 * @since 2024/12/04 13:45
 */
@Component
public class ApplicationStartupListener implements ApplicationListener<ApplicationReadyEvent> {

    private final Environment environment;
    private final Logger logger = LoggerFactory.getLogger(ApplicationStartupListener.class);

    public ApplicationStartupListener(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        String port = environment.getProperty("server.port");
        if (port != null) {
            logger.info("Application started on port: {}", port);
            logger.info("http://127.0.0.1:{}", port);
        } else {
            logger.info("Could not determine the port.");
        }
    }
}