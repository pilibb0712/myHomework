package tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

public class CheckSessionTag extends BodyTagSupport {
    //因为sevlet控制jsp的跳转，检查session不应该放在jsp里，而是应该放在servlet
    // 在sevlet检查完了，决定跳转到哪个界面
    // 我写了一个过滤器用来检查session，没有使用这个tag
    private PageContext pageContext;

    @Override
    public void setPageContext(PageContext pageContext) {
        this.pageContext=pageContext;
    }

    public int doStartTag() throws JspException {
        HttpServletRequest httpRequest = (HttpServletRequest) pageContext.getRequest();
        HttpSession session = httpRequest.getSession(false);
        if (session.getAttribute("name") == null) {
            try {
                HttpServletResponse httpResponse = (HttpServletResponse) pageContext.getResponse();
                httpResponse.sendRedirect("/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.doStartTag();
    }
}
