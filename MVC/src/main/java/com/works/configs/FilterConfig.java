package com.works.configs;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class FilterConfig implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String url = req.getRequestURI();
        String[] freeUrls = {"/", "/login", "/userLogin", "/signup"};
        boolean loginStatus = true;
        for ( String item : freeUrls ) {
            if ( item.equals(url) ) {
                loginStatus = false;
                break;
            }
        }

        // Global Control
        if (loginStatus) {
            // session control
            boolean status = req.getSession().getAttribute("user") == null;
            if (status) {
                // session user not found
                res.sendRedirect("/login");
            }else  {
                filterChain.doFilter(req, res);
            }
        }else {
            filterChain.doFilter(req, res);
        }


    }

}
