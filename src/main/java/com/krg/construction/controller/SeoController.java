package com.krg.construction.controller;

import com.krg.construction.service.BaseUrlResolver;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SeoController {

    private final BaseUrlResolver baseUrlResolver;

    public SeoController(BaseUrlResolver baseUrlResolver) {
        this.baseUrlResolver = baseUrlResolver;
    }

    @GetMapping(value = "/robots.txt", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String robots(HttpServletRequest request) {
        String base = baseUrlResolver.resolveBaseUrl(request);
        return "User-agent: *\n" +
               "Allow: /\n\n" +
               "Sitemap: " + base + "/sitemap.xml\n";
    }

    @GetMapping(value = "/sitemap.xml", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String sitemap(HttpServletRequest request) {
        String base = baseUrlResolver.resolveBaseUrl(request);
        return """
            <?xml version="1.0" encoding="UTF-8"?>
            <urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
              <url>
                <loc>%s/</loc>
                <lastmod>2026-11-05</lastmod>
                <changefreq>weekly</changefreq>
                <priority>1.0</priority>
              </url>
              <url>
                <loc>%s/#about</loc>
                <changefreq>monthly</changefreq>
                <priority>0.8</priority>
              </url>
              <url>
                <loc>%s/#manufacturing</loc>
                <changefreq>monthly</changefreq>
                <priority>0.8</priority>
              </url>
              <url>
                <loc>%s/#products</loc>
                <changefreq>weekly</changefreq>
                <priority>0.9</priority>
              </url>
              <url>
                <loc>%s/#delivery</loc>
                <changefreq>monthly</changefreq>
                <priority>0.8</priority>
              </url>
              <url>
                <loc>%s/#contact</loc>
                <changefreq>monthly</changefreq>
                <priority>0.9</priority>
              </url>
            </urlset>
            """.formatted(base, base, base, base, base, base).trim() + "\n";
    }
}

