package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter("/Login")

@WebFilter(filterName= "myFilter",
        urlPatterns= { "/welcome.jsp","/billing.jsp"})
public class FilterRequest implements Filter {
    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // System.out.println("Good");
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();//create session if none exist
        if(req.getSession().getAttribute("userName") != null)
            filterChain.doFilter(servletRequest, servletResponse);
        else {
            session.setAttribute("msg", " Login required!");
            resp.sendRedirect("login.jsp");

        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}

