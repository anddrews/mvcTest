package by.gsu.epamlab.filters;


import by.gsu.epamlab.constants.Constants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/login/*")
public class LoginAndCreate implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req =
                (HttpServletRequest) servletRequest;

        if(req.getSession().getAttribute(Constants.USER)==null) {
            filterChain.doFilter(req, servletResponse);
        } else {
            HttpServletResponse resp =
                    (HttpServletResponse)servletResponse;
            resp.sendRedirect(Constants.HOME_PAGE);
        }

    }

    @Override
    public void destroy() {

    }
}
