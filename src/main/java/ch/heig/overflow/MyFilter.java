package ch.heig.overflow;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "MyFilter", urlPatterns = "/flow")
public class MyFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        resp.getWriter().println("From filter");
        chain.doFilter(req, resp);
        resp.getWriter().println("From filter 2");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
