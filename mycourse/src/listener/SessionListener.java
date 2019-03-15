package listener;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionEvent;

public class SessionListener implements HttpSessionAttributeListener,HttpSessionListener {
    //when seesionAttribut-username create: log in successfully
    //只有在第一次set属性的时候调用
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /*ServletContext ctx = sbe.getSession( ).getServletContext( );
        Integer loginNum = (Integer) ctx.getAttribute("loginNum");
        System.out.println(loginNum);
        if (loginNum == null) {
            loginNum = new Integer(0);
        }
        else {
            int count = loginNum.intValue( );
            loginNum = new Integer(count + 1);
        }
        ctx.setAttribute("loginNum", loginNum);
        不用这个方法的主要原因是tomcat启动时，一旦setAttribute，这个方法会被执行2次？！！*/
    }

    //when seesionAttribut-username remove: log out
    //在删除属性时调用，没有属性就不会调用？
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /*ServletContext ctx=sbe.getSession().getServletContext();
        Integer loginNum = (Integer)ctx.getAttribute("loginNum");
        Integer onlineNum = (Integer)ctx.getAttribute("onlineNum");
        int loginCount = loginNum.intValue();
        loginNum = new Integer(loginCount - 1);
        int onlineCount = loginNum.intValue();
        onlineNum = new Integer(onlineCount - 1);
        ctx.setAttribute("loginNum", loginNum);
        ctx.setAttribute("onlineNum", onlineNum);
        不用这个方法的主要原因是销毁session时，这个方法会被执行3次？！！
        */
    }
}