/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.ui.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter", urlPatterns = "/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(false);

        // access to login page has no restriction
        if (isPublicRessource(request.getRequestURI(), request.getContextPath())) {
            chain.doFilter(req, resp);
            return;
        }

        boolean isLogged = session != null && session.getAttribute("currentUser") != null;

        if (!isLogged) {
            String targetUrl = request.getRequestURI();
            if (request.getQueryString() != null) {
                targetUrl += "?" + request.getQueryString();
            }
            request.getSession().setAttribute("targetUrl", targetUrl);

            ((HttpServletResponse) resp).sendRedirect(request.getContextPath() + "/login");
        } else {
            chain.doFilter(req, resp);
        }
    }

    boolean isPublicRessource(String uri, String prefix) {
        // only the login page is open to everybody
        return uri.startsWith(prefix + "/login") ||
                uri.startsWith(prefix + "/register.do") ||
                uri.startsWith(prefix + "/questions") ||
                uri.equals(prefix + "/") ||
                uri.equals(prefix) ||
                uri.startsWith(prefix + "/ArquillianServletRunner") ||
                uri.startsWith(prefix + "/assets");
    }

}
