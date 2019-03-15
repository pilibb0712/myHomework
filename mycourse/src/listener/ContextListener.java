package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent cse) {
        ServletContext servletContext= cse.getServletContext();
        servletContext.setAttribute("loginNum", 0);
        servletContext.setAttribute("onlineNum", -2);
        //因为启动tomcat时index.jsp中的代码（onlineNum+1）会执行3遍。。。
    }

}
