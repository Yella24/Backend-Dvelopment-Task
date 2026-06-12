package com.example.userage.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

/**
 * Injects an {@code X-Request-Id} header (generated when missing), exposes it
 * in the response, places it in the SLF4J MDC so all log lines carry it, and
 * logs request method/path/status/duration on completion.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestIdFilter extends OncePerRequestFilter {

    public static final String HEADER = "X-Request-Id";
    public static final String MDC_KEY = "requestId";

    private static final Logger log = LoggerFactory.getLogger(RequestIdFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String id = request.getHeader(HEADER);
        if (id == null || id.isBlank()) {
            id = UUID.randomUUID().toString();
        }
        MDC.put(MDC_KEY, id);
        response.setHeader(HEADER, id);

        long start = System.currentTimeMillis();
        try {
            filterChain.doFilter(request, response);
        } finally {
            long duration = System.currentTimeMillis() - start;
            log.info("http_request method={} path={} status={} duration_ms={}",
                    request.getMethod(),
                    request.getRequestURI(),
                    response.getStatus(),
                    duration);
            MDC.remove(MDC_KEY);
        }
    }
}
