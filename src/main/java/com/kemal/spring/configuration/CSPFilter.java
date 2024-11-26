package com.kemal.spring.configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

@WebFilter("/*")
public class CSPFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(CSPFilter.class.getName());


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Relaxed Content Security Policy (CSP) to allow resources from trusted sources
        httpResponse.setHeader("Content-Security-Policy",
                "default-src 'self'; " +                          // Allow content from the same origin
                        "script-src 'self' 'unsafe-inline' https://apis.google.com https://cdnjs.cloudflare.com; " + // Allow inline scripts and trusted CDNs
                        "style-src 'self' 'unsafe-inline' https://fonts.googleapis.com https://cdnjs.cloudflare.com; " + // Allow inline styles and trusted CDNs
                        "img-src 'self' data: https://trusted-images.com; " + // Allow images from same origin and trusted sources
                        "font-src 'self' https://fonts.gstatic.com; " + // Allow fonts from the same origin and Google Fonts
                        "connect-src 'self' https://api.example.com; " + // Allow API connections from trusted origin
                        "frame-src 'none'; " +                         // Disallow frames
                        "object-src 'none'; " +                        // Disallow object embedding
                        "form-action 'self'; " +                       // Only allow form submissions to the same origin
                        "base-uri 'self';");                            // Restrict <base> element to the same origin

        LOGGER.info("Added CSP Header: " + httpResponse.getHeader("Content-Security-Policy"));
        chain.doFilter(request, response);
    }


    @Override
    public void destroy() {
    }
}

