package com.example.summer.daoTest;

import com.example.summer.dao.UserDao;
import com.example.summer.daoImpl.UserDaoImpl;
import com.example.summer.domain.EmailPO;
import com.example.summer.domain.UserPO;
import org.junit.Test;

import java.util.ArrayList;

public class UserDaoTest {

//    @Test
//    public void testRegister(){
//        UserDao dao=new UserDaoImpl();
//
//        UserPO userPO1=new UserPO();
//        userPO1.setUsername("kaikai");
//        EmailPO emailPO=new EmailPO();
//        emailPO.setAddress("161250194@qq.com");
//        userPO1.setEmail(emailPO);
//        userPO1.setPassword("1998");
//        userPO1.setTotalCredit(80);
//
//        dao.register(userPO1);
//    }
//
//    @Test
//    public void testValid(){
//        UserDao dao=new UserDaoImpl();
//        System.out.println(dao.isValidUser("beibei","12345"));
//        System.out.println(dao.isValidUser("beibei","123456"));
//        System.out.println(dao.isExistedUserByEmail("1292155474@qq.com"));
//        System.out.println(dao.isExistedUserByEmail("1292155474@163.com"));
//        System.out.println(dao.isExistedUserByUserName("kaikai"));
//        System.out.println(dao.isExistedUserByUserName("bilai"));
//
//    }
//
//    @Test
//    public void testQuery(){
//        UserDao dao=new UserDaoImpl();
//        UserPO po=dao.queryUserByUsername("kaikai");
//        System.out.println(po.getEmail().getAddress());
//
//        ArrayList<UserPO> users=dao.listAllUsers();
//        System.out.println(users.size());
//        System.out.println(users.get(0).getUsername()+"   "+ users.get(1).getUsername());
//    }
//
//    @Test
//    public void testDelete(){
//        UserDao dao=new UserDaoImpl();
//        dao.deleteUser("beibei");
//    }

}
