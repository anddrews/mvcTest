package by.gsu.epamlab.filters;


import by.gsu.epamlab.constants.Constants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@WebFilter (urlPatterns ={"/loginPage/login","/createPage/create"})
public class FilterEmpty implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        boolean isEmpty=false;
        HttpServletRequest req=(HttpServletRequest)servletRequest;
        Map<String,String[]> params=req.getParameterMap();

        for (Map.Entry<String,String[]> tmp:params.entrySet())
        {
            for (String param:tmp.getValue())
            {
                if(param==null || param.equals(Constants.EMPTY_STRING))
                {
                    isEmpty=true;
                }
            }
        }
        if(!isEmpty)
        {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else
        {
            HttpServletResponse resp =
                    (HttpServletResponse)servletResponse;
            resp.sendRedirect(req.getContextPath()+"/");
        }

    }

    @Override
    public void destroy() {

    }
}
