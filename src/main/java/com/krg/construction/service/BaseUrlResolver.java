package com.krg.construction.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BaseUrlResolver {

    private final String configuredBaseUrl;

    public BaseUrlResolver(@Value("${app.base-url:}") String configuredBaseUrl) {
        this.configuredBaseUrl = configuredBaseUrl;
    }

    public String resolveBaseUrl(HttpServletRequest request) {
        if (configuredBaseUrl != null && !configuredBaseUrl.isBlank()) {
            return configuredBaseUrl.replaceAll("/+$", "");
        }
        if (request == null) {
            return "";
        }
        String proto = request.getHeader("X-Forwarded-Proto");
        if (proto == null || proto.isBlank()) {
            proto = request.getScheme();
        }
        String host = request.getHeader("X-Forwarded-Host");
        if (host == null || host.isBlank()) {
            host = request.getHeader("Host");
        }
        if (host == null || host.isBlank()) {
            int port = request.getServerPort();
            boolean isStandardPort = (proto.equalsIgnoreCase("http") && port == 80)
                    || (proto.equalsIgnoreCase("https") && port == 443);
            host = request.getServerName() + (isStandardPort ? "" : ":" + port);
        }
        return proto + "://" + host;
    }
}

