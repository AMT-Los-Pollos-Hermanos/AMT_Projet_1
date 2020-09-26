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

        // Accès à la page de login n'a pas de restriction
        if (isPublicRessource(request.getRequestURI())) {
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

            ((HttpServletResponse) resp).sendRedirect("/overflow/login");
        } else {
            chain.doFilter(req, resp);
        }
    }

    boolean isPublicRessource(String uri) {
        // Seulement la page de login est accessible pour tout le monde.
        return uri.startsWith("/overflow/login") ||
                uri.startsWith("/overflow/register.do") ||
                uri.startsWith("/overflow/assets");
    }

}
