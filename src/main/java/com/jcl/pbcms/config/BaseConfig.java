package com.jcl.pbcms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BaseConfig {

    @Value("${com.jcl.pb.config.allauth}")
    private String allauth;

    public String getAllauth() {
        return allauth;
    }

    public void setAllauth(String allauth) {
        this.allauth = allauth;
    }
}
