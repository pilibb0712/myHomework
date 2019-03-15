package tag;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
public class CheckIfVisitedTag extends BodyTagSupport {
    //用来检查是否已经访问过这个网站，如果已访问过，再次进入index.jsp（登录界面）将不会计入在线人数
    private PageContext pageContext;

    @Override
    public void setPageContext(PageContext pageContext) {
        this.pageContext=pageContext;
    }

    public int doStartTag() throws JspException {
        HttpServletRequest httpRequest = (HttpServletRequest) pageContext.getRequest();
        HttpSession session = httpRequest.getSession(false);
        if (session.getAttribute("hasVisited") == null) {
            session.setAttribute("hasVisited",true);
            ServletContext context=httpRequest.getServletContext();
            Integer onlineNum = (Integer) context.getAttribute("onlineNum");
            int count = onlineNum.intValue( );
            System.out.println(count);
            onlineNum = new Integer(count + 1);
            context.setAttribute("onlineNum",onlineNum);
        }

        return super.doStartTag();
    }
}
