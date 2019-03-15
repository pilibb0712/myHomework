package serviceImpl;


import daoService.CoursewareDaoService;
import daoService.CoursingDaoService;
import entity.Courseware;
import entity.Coursing;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import service.CoursewareService;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

@Repository
public class CoursewareServiceImpl implements CoursewareService {
    private static CoursewareDaoService coursewareDaoService;
    private static CoursingDaoService coursingDaoService;

    public CoursewareServiceImpl(){
        ApplicationContext appliationContext=new ClassPathXmlApplicationContext("DaoApplicationContext.xml");
        coursewareDaoService=(CoursewareDaoService)appliationContext.getBean("CoursewareDaoService");
        coursingDaoService=(CoursingDaoService)appliationContext.getBean("CoursingDaoService");
    }
    @Override
    public void uploadCourseware(Part coursewarePart, String cid,String servletPath) {
        String filename = coursewarePart.getSubmittedFileName();//获取上传文件名
        String dirPath = servletPath+"/courseware";
        String fileUrl=dirPath+ "/" + filename;
        System.out.println(fileUrl);
        File dir=new File(dirPath);
        if(!dir.exists()){
            dir.mkdir();//如果目录不存在，则创建
        }
        try {
            coursewarePart.write(fileUrl);//将文件写入服务器*/
        }catch (IOException e){
            e.printStackTrace();
        }

        //upadate courseware
        Courseware courseware=new Courseware();
        int num=coursewareDaoService.numOfCoursewares();
        num++;
        String id="cware"+String.valueOf(num);
        courseware.setId(id);
        courseware.setCid(cid);
        courseware.setFilename(filename);
        courseware.setFile_url(fileUrl);
        coursewareDaoService.addCourseware(courseware);
    }

    @Override
    public String downloadCourseware(String cwid) {
        Courseware courseware=coursewareDaoService.findCoursewareById(cwid);
        String fileUrl=courseware.getFile_url();
        String fileName=courseware.getFilename();
        File file = new File(fileUrl);
        if(file.exists()){
            return fileName;
        }
        return null;
    }

    @Override
    public ArrayList<Courseware> getCoursewareOfCourse(String cid) {
        return coursewareDaoService.findCoursewareByCid(cid);
    }

    @Override
    public ArrayList<Courseware> getCoursewareOfCoursing(String ccid) {
        Coursing coursing=coursingDaoService.findCouringById(ccid);
        String cid=coursing.getCid();
        return coursewareDaoService.findCoursewareByCid(cid);
    }
    /*
    public static void main(String args[]) {
        ApplicationContext serviceAppliationContext = new ClassPathXmlApplicationContext("ServiceApplicationContext.xml");
        CoursewareService coursewareService = (CoursewareService) serviceAppliationContext.getBean("CoursewareService");
        ArrayList<Courseware> coursewares = coursewareService.getCoursewareOfCoursing("cc1");
        System.out.println(coursewares.size() + "  " + coursewares.get(0).getFilename());
    }*/

    }
