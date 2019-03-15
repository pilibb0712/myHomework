package service;

import entity.*;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.Part;
import java.util.ArrayList;
import java.util.HashMap;

public interface CoursingService {
    public ArrayList<Coursing> getAllCoursing();
    //这是给Admin审核开课的方法，显示课程状态，为审核的要给Admin审核的
    public void checkCoursing(String ccid);

    public void endCoursing(String ccid);

    public void createCoursing(Coursing coursing);
    public ArrayList<Coursing> getCoursingOfTerm(String term);
    //记得根据时间更新所有coursing状态（除了已经结课的），然后显示所有这学期的课(除了还没审核通过的,结课的也会显示）

    public ArrayList<Coursing> getCoursingOfTid(String tid);
    public Coursing getCoursingOfId(String id);

    public void uploadScoreRecordOfCoursing(Part scoreRecord,String ccid,String servletPath);
    //upload and save filename
    public String downloadScoreRecordOfCoursing(String ccid,String servletPath);

}
