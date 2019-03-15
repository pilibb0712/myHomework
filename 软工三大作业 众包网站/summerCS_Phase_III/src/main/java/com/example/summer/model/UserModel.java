package com.example.summer.model;

import com.example.summer.dao.UserDao;
import com.example.summer.domain.UserPO;
import com.example.summer.utility.dataUtility.InitTimeUtility;
import com.example.summer.utility.dateUtility.DateUtility;
import com.example.summer.utility.dozerSingletonUtility.DozerMapSingleton;
import com.example.summer.utility.json.deserialzer.EmailModelDeserializer;
import com.example.summer.utility.json.serializer.EmailModelSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

/*
 * @program: summerCS_Phase_I
 * @description: User的model模型
 * @author: 161250193
 * @create: 2018/3/17
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Component
public class UserModel implements Model{
    @JsonIgnore
    private String registerTime;

    private String id;

    private int totalCredit;

    private String username;

    private String password;

    @JsonSerialize(using = EmailModelSerializer.class)
    @JsonDeserialize(using = EmailModelDeserializer.class)
    private EmailModel email;

    /**
     * 验证用户是否合法
     * @param logStr 登陆的用户名或者密码
     * @param password 登陆的密码
     * */
    public static boolean isValidUser(String logStr, String password){

        return userDao.isValidUser(logStr, password);
    }

    /**
     * 删除用户（管理员）
     * @author Mr.Wang
     * @param username username
     */
    public static void deleteUser(String username){
        userDao.deleteUser(username);
    }

    @JsonIgnore
    private static UserDao userDao ;
    @JsonIgnore
    @Autowired
    private UserDao userDaoIn;

    @PostConstruct
    public void init(){
        userDao = this.userDaoIn;
    }

    /**
     *
     * */
    public boolean register(){
        UserPO po = DozerMapSingleton.getInstance().map(this, UserPO.class);
        po.setRegisterTime(InitTimeUtility.getCurrentDay());
        //System.out.println(JSONObject.fromObject(po).toString());
        return userDao.register(po);
    }

    public static ArrayList<UserModel> listAllUsers(){
        ArrayList<UserPO> pos = (ArrayList<UserPO>) userDao.listAllUsers();
        ArrayList<UserModel> models = new ArrayList<>();
        pos.forEach(e->models.add(DozerMapSingleton.getInstance().map(e, UserModel.class)));
        return models;
    }

    /**
     * 返回一定时间段内每天的注册人数
     * @author Mr.Wang
     * @param beginTime 开始时间, yyyy-MM-DD
     * @param endTime 结束时间, yyyy-MM-DD
     * @return java.util.ArrayList<java.lang.Integer>
     */
    public static ArrayList<Integer>  listRegisterNum(String beginTime, String endTime) {
        if (beginTime == null || endTime == null) {
            return null;
        }

        ArrayList<UserModel> users = listAllUsers();
        if(users == null) {
            return null;
        }

        ArrayList<Integer> result = new ArrayList<>();
        endTime = DateUtility.getNextDay(endTime);
        while (!beginTime.equals(endTime)) {
            int i = 0;
            for(UserModel user : users) {
                if (user.registerTime != null) {
                    if (user.registerTime.equals(beginTime)){
                        i++;
                    }
                }
            }
            result.add(i);
            beginTime = DateUtility.getNextDay(beginTime);
        }
        return result;
    }
}
