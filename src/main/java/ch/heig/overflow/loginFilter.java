package ch.heig.overflow;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "MyFilter", urlPatterns = "/*")
public class loginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(false);

        // Accès à la page de login n'a pas de restriction
        if(isPublicRessource(request.getRequestURI())){
            chain.doFilter(req, resp);
            return;
        }

        boolean isLogged = (session != null && session.getAttribute("currentUser") != null);

        if(!isLogged){
            String targetUrl = request.getRequestURI();
            if(request.getQueryString() != null){
                targetUrl += "?" + request.getQueryString();
            }
            request.getSession().setAttribute("targetUrl", targetUrl);

            ((HttpServletResponse) resp).sendRedirect("/OverFlow-1.0-SNAPSHOT/login");
            return;
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

    boolean isPublicRessource(String URI){
        // Seulement la page de login est accessible pour tout le monde.
        return URI.startsWith("/OverFlow-1.0-SNAPSHOT/login");
    }

}
