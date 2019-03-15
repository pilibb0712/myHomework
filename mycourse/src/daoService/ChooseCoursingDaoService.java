package daoService;

import java.util.ArrayList;

public interface ChooseCoursingDaoService {

    public void addChooseCoursing(String ccid,String sid);
    public void deleteChooseCoursing(String ccid,String sid);
    public ArrayList<String> findChoosenStudentsOfCoursing(String ccid);
    public Boolean ifChoosedCoursingByStudent(String ccid,String sid);
    //just of current term, for choose coursing

}
