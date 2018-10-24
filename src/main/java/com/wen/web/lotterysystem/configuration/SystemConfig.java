package com.wen.web.lotterysystem.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 * @date 2018-10-29 11:49
 */
@Configuration
@ConfigurationProperties(value = "systemconfig")
public class SystemConfig {
    private String systemName;
    private String pageName;

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
}
