package by.gsu.epamlab.filters;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.Roles;
import by.gsu.epamlab.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/courier")
public class CouriereFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req =
                (HttpServletRequest) servletRequest;
        User user=(User)req.getSession().getAttribute(Constants.USER);

        if(user!=null && user.getIdRole()<Constants.TWO ) {
            filterChain.doFilter(req, servletResponse);
        } else {
            HttpServletResponse resp =
                    (HttpServletResponse)servletResponse;
            resp.sendRedirect(req.getContextPath()+"/");
        }

    }

    @Override
    public void destroy() {

    }
}
