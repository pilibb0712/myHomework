package serviceImpl;


import daoService.CourseworkDaoService;
import daoService.StudentworkDaoService;
import entity.Coursework;
import entity.Studentwork;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import service.CourseworkService;
import utils.TimeUtil;

import javax.servlet.http.Part;
import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Repository
public class CourseworkServiceImpl implements CourseworkService {
    private static CourseworkDaoService courseworkDaoService;
    private static StudentworkDaoService studentworkDaoService;

    public CourseworkServiceImpl(){
        ApplicationContext appliationContext=new ClassPathXmlApplicationContext("DaoApplicationContext.xml");
        courseworkDaoService=(CourseworkDaoService)appliationContext.getBean("CourseworkDaoService");
        studentworkDaoService=(StudentworkDaoService)appliationContext.getBean("StudentworkDaoService");
    }
    @Override
    public void createCoursework(Coursework coursework) {
        int num=courseworkDaoService.numOfCourseworks();
        num++;
        String id="cwork"+String.valueOf(num);
        coursework.setId(id);
        String time= TimeUtil.getCurrentTime();
        coursework.setTime(time);
        courseworkDaoService.addCoursework(coursework);
    }
/*
    public static void main(String args[]) {
        ApplicationContext serviceAppliationContext = new ClassPathXmlApplicationContext("ServiceApplicationContext.xml");
        CourseworkService courseworkService = (CourseworkService) serviceAppliationContext.getBean("CourseworkService");
        Coursework coursework=new Coursework();

        coursework.setCcid("cc1");
        coursework.setTopic("lab2 of j2ee");
        coursework.setContent("use servlet");
        coursework.setDdl("2019-12-31 00:00:00");
        coursework.setMax_size("5M");
        courseworkService.createCoursework(coursework);
    }*/

        @Override
    public ArrayList<Coursework> getCourseworkOfCoursing(String ccid) {
        return courseworkDaoService.findCourseworkByCcid(ccid);
    }

    @Override
    public Coursework getCoursework(String cwid) {
        return courseworkDaoService.findCourseworkById(cwid);
    }

    @Override
    public void uploadScoreRecordOfCoursework(Part scoreRecord, String cwid, String servletPath) {
        String filename = scoreRecord.getSubmittedFileName();//获取上传文件名
        String dirPath = servletPath+"/coursingworkScore";
        String fileUrl=dirPath+ "/" + filename;
        File dir=new File(dirPath);
        if(!dir.exists()){
            dir.mkdir();//如果目录不存在，则创建
        }
        try {
            scoreRecord.write(fileUrl);//将文件写入服务器*/
        }catch (IOException e){
            e.printStackTrace();
        }

        //upadate coursework
        Coursework coursework=courseworkDaoService.findCourseworkById(cwid);
        coursework.setScorefile_name(filename);
        courseworkDaoService.updateCoursework(coursework);
    }

    @Override
    public String downloadScoreRecordOfCoursework(String cwid,String servletPath) {
        Coursework coursework=courseworkDaoService.findCourseworkById(cwid);
        String fileName=coursework.getScorefile_name();
        String dirPath = servletPath+"/coursingworkScore";
        String fileUrl=dirPath+ "/" + fileName;
        File file = new File(fileUrl);
        if(file.exists()){
            return fileName;
        }
        return null;
    }

    @Override
    public void uploadStudentwork(Part studentworkPart, String cwid,String sid, String servletPath) {
        String filename = studentworkPart.getSubmittedFileName();//获取上传文件名
        String dirPath = servletPath+"/studentwork";
        String fileUrl=dirPath+ "/" + filename;
        File dir=new File(dirPath);
        if(!dir.exists()){
            dir.mkdir();//如果目录不存在，则创建
        }
        try {
            studentworkPart.write(fileUrl);//将文件写入服务器*/
        }catch (IOException e){
            e.printStackTrace();
        }

        //upadate studentwork
        Studentwork studentwork=new Studentwork();
        int num=studentworkDaoService.numOfStudentworks();
        num++;
        String id="sw"+String.valueOf(num);
        studentwork.setId(id);
        studentwork.setCwid(cwid);
        studentwork.setSid(sid);
        studentwork.setFile_url(fileUrl);
        studentwork.setFilename(filename);
        studentworkDaoService.addStudentwork(studentwork);
    }

    @Override
    public ArrayList<Studentwork> getStudentworkOfStudent(String cwid,String sid) {
        return studentworkDaoService.findStudentworkByCwidAndSid(cwid,sid);
    }

    @Override
    public ArrayList<Studentwork> getStudentworkOfCoursework(String cwid) {
        return studentworkDaoService.findStudentworkByCwid(cwid);
    }

   /* public static void main(String[] args){
           // zipStudentworks("cwork1","D:\\Homework\\J2EE与中间件\\lab9\\mycourse\\out\\artifacts\\lab1_war_exploded");
    }*/

    public String  zipStudentworks(String cwid,String servlertPath){
        ApplicationContext appliationContext=new ClassPathXmlApplicationContext("DaoApplicationContext.xml");
        courseworkDaoService=(CourseworkDaoService)appliationContext.getBean("CourseworkDaoService");
        studentworkDaoService=(StudentworkDaoService)appliationContext.getBean("StudentworkDaoService");
            ArrayList<Studentwork> studentworks=studentworkDaoService.findStudentworkByCwid(cwid);
            String zipPath=servlertPath+"/studentwork/";
            String zipName=cwid+"Studentwork.zip";
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            FileOutputStream fos = null;
            ZipOutputStream zos = null;
            File zipFile = new File(zipPath+zipName);
        if(zipFile.exists()){
            System.out.println(zipName+" exist");
            System.out.println(zipPath+zipName);
        }else{
            if(studentworks!=null){
                try{
                fos = new FileOutputStream(zipFile);
                zos = new ZipOutputStream(new BufferedOutputStream(fos));
                for(int i=0;i<=studentworks.size()-1;i++){
                    byte[] bufs = new byte[1024*1024];
                    Studentwork studentwork=studentworks.get(i);
                    String fileName=studentwork.getFilename();
                    String fileUrl=studentwork.getFile_url();
                    System.out.println(fileUrl);
                    File file=new File(fileUrl);
                    ZipEntry zipEntry = new ZipEntry(fileName);
                    zos.putNextEntry(zipEntry);
                    //读取待压缩的文件并写进压缩包里
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis, 1024*1024);
                    int len = 0;
                    while((len=fis.read(bufs)) != -1){
                        zos.write(bufs,0,len);
                    }
                    fis.close();
                }
                zos.close();
            }catch (FileNotFoundException e1){
                    e1.printStackTrace();
                }catch (IOException e2){
                    e2.printStackTrace();
                }
        }else{
                System.out.println("studentworks are null");
            }
        }
        return zipName;
    }
}
