package com.example.summer.daoTest;

import com.example.summer.dao.UserDao;
import com.example.summer.domain.EmailPO;
import com.example.summer.domain.UserPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserCreaterTest {
    @Autowired
    private UserDao userDao;
    @Test
    public void addNewUsers(){
        Random random = new Random();
        for (int j =10;j<31;j++){
            for (int i = 0; i<random.nextInt(3); i++){
                UserPO po = new UserPO();
                po.setUsername("a"+j+""+i);
                po.setPassword("123456");
                po.setEmail(new EmailPO("651415"+i+""+j+"@qq.com"));
                po.setRegisterTime("2018-05-"+j);
                userDao.register(po);
            }
        }
//        for (int i=0; i<15; i++){
//            UserPO po = new UserPO();
//            po.setUsername("b"+i);
//            po.setEmail(new EmailPO("65536"+i+"@qq.com"));
//            po.setPassword("123456");
//            po.setRegisterTime("2018-05-04");
//            userDao.register(po);
//        }

        for (int j =10;j<31;j++){
            for (int i = 0; i<random.nextInt(6); i++){
                UserPO po = new UserPO();
                po.setUsername("c"+j+""+i);
                po.setPassword("123456");
                po.setEmail(new EmailPO("65126"+i+""+j+"@qq.com"));
                po.setRegisterTime("2018-06-"+j);
                userDao.register(po);
            }
        }
        for (int j =1;j<10;j++){
            for (int i = 0; i<random.nextInt(8); i++){
                UserPO po = new UserPO();
                po.setUsername("d"+j+""+i);
                po.setPassword("123456");
                po.setEmail(new EmailPO("65126452"+i+""+j+"@qq.com"));
                po.setRegisterTime("2018-07-"+j);
                userDao.register(po);
            }
        }
    }
}
