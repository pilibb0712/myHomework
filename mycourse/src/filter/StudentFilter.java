package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class StudentFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        HttpSession session = httpRequest.getSession(false);
        if((session.getAttribute("userType")==null)||(!(session.getAttribute("userType")).equals("Student"))){
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.sendRedirect("/mycourse");
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }

        //filterChain.doFilter(servletRequest, servletResponse);
        //直到最后一个filter过滤完，就跳到目标url，只要filterChain.doFilter这一步存在，就一定会跳转到原来的目标，无论是否重定向
    }
}
