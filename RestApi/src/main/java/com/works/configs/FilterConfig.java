package com.works.configs;

import com.works.entities.Info;
import com.works.repositories.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class FilterConfig implements Filter {

    final InfoRepository infoRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        String roles = auth.getAuthorities().toString();
        String url = req.getRequestURI();
        String sessionID = req.getSession().getId();
        String userAgent = req.getHeader("user-agent");
        String time = ""+System.currentTimeMillis();
        String ip = req.getRemoteAddr();
        Info i = new Info(username, roles, url, sessionID, userAgent, time, ip);
        infoRepository.save(i);

        filterChain.doFilter(req, res);
    }

}
