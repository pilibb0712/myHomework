package daoService;

import entity.Courseware;
import java.util.ArrayList;

public interface CoursewareDaoService {
    public void addCourseware(Courseware courseware);
    public int numOfCoursewares();
    public Courseware findCoursewareById(String id);
    public ArrayList<Courseware> findCoursewareByCid(String cid);
}
