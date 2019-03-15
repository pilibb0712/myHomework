package servlet;



import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.deploy.security.ValidationState;
import com.sun.imageio.plugins.jpeg.JPEG;
import entity.User;
import netscape.javascript.JSObject;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet("/test")
@MultipartConfig
public class TestServlet extends HttpServlet {
    public TestServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HashMap<String,Integer> map=new HashMap<String,Integer>();
        map.put("t1",20);
        map.put("t2",30);
        map.put("t3",10);
        map.put("t4",40);
        map.put("t5",50);
        map.put("t6",35);
        Gson gson=new Gson();
        String result=gson.toJson(map);
        out.print(result);

        /*
        String url="D:\\\\Homework\\\\J2EE与中间件\\\\lab9\\\\mycourse\\\\out\\\\artifacts\\\\lab1_war_exploded\\pic.bmp";
        out.print(url);*/

/*
        Part part=request.getPart("file");
        String filename = part.getSubmittedFileName();//获取上传文件名
        String realPath = this.getServletContext().getRealPath("/");//获取web目录的真实物理路径，文件将保存在upload文件夹下
        System.out.println(realPath);
        File dir=new File(realPath);
        if(!dir.exists()){
            dir.mkdir();//如果目录不存在，则创建
            	}
        part.write(realPath + "/" + filename);//将文件写入磁盘*/


//下载文件
       /* String name="pic.bmp";//request.getParameter("coursewareId");
        File f = new File("D:\\Homework\\J2EE与中间件\\lab9\\mycourse\\out\\artifacts\\lab1_war_exploded\\"+name);
        if(f.exists()) {
            System.out.println("file exists");
            FileInputStream fis = new FileInputStream(f);
            String filename = URLEncoder.encode(f.getName(), "utf-8"); //解决中文文件名下载后乱码的问题
            byte[] b = new byte[fis.available()];
            fis.read(b);
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + filename + "");
            //获取响应报文输出流对象
            ServletOutputStream out = response.getOutputStream();
            //输出
            out.write(b);
            out.flush();
            out.close();
        }*/



/*接受ajax的对象，传给ajax对象集
        PrintWriter out = response.getWriter();
        String paramter=request.getParameter("user");
        Gson gson=new Gson();
        User user=gson.fromJson(paramter,User.class);
        System.out.println(user.getName());

        User user1=new User();
        user1.setName("bei2");
        user1.setMoney(200);
        user1.setPassword("1123");

        User user2=new User();
        user2.setName("bei3");
        user2.setMoney(200);
        user2.setPassword("1123");

        ArrayList<User> users=new ArrayList<User>();
        users.add(user1);
        users.add(user2);

        String infor=gson.toJson(users);
        System.out.println(infor);
        out.println(infor);*/

/*显示图片
        String imagePath = "D:\\Homework\\J2EE与中间件\\lab9\\mycourse\\out\\artifacts\\lab1_war_exploded\\微信图片_20181226173010.bmp";
        String mimeType = "image/jpg";
         //设置content类型
        response.setContentType(mimeType);
        //设置大小
        File file = new File(imagePath);
        response.setContentLength((int) file.length());
        //打开文件并输出
        FileInputStream inputStream = new FileInputStream(file);
        OutputStream out = response.getOutputStream();
        //把文件复制到输出流
        byte[] data = new byte[1024];
        int count = 0;
        while ((count = inputStream.read(data)) >= 0) {
            out.write(data, 0, count);
        }
        inputStream.close();
        out.close();*/
    }

        public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
