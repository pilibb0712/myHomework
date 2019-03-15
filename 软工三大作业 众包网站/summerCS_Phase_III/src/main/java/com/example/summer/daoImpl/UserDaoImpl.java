package com.example.summer.daoImpl;

import com.example.summer.dao.UserDao;
import com.example.summer.dao.WorkerDao;
import com.example.summer.domain.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    private MongoTemplate mongoTemplate;
    private WorkerDao workerDao;
    @Autowired
    public UserDaoImpl(MongoTemplate mongoTemplate, WorkerDao workerDao){
        this.workerDao = workerDao;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public boolean isValidUser(String logStr, String password) {
        //邮箱
        Query queryMail = new Query(Criteria.where("email.address").is(logStr).and("password").is(password));
        boolean isUserExist = mongoTemplate.exists(queryMail, UserPO.class);
        //用户名
        Query queryName = new Query(Criteria.where("username").is(logStr).and("password").is(password));
        isUserExist |= mongoTemplate.exists(queryName, UserPO.class);

        return isUserExist;
    }

    @Override
    public boolean isExistedUserByUserName(String username) {
        Query queryName = new Query(Criteria.where("username").is(username));
        boolean isUserExist = mongoTemplate.exists(queryName, UserPO.class);
        return isUserExist;
    }

    @Override
    public boolean isExistedUserByEmail(String emailAddress) {
        //邮箱
        Query queryMail = new Query(Criteria.where("email.address").is(emailAddress));
        boolean isUserExist = mongoTemplate.exists(queryMail, UserPO.class);
        return isUserExist;
    }

    @Override
    public boolean register(UserPO po) {
        if (isExistedUserByUserName(po.getUsername())){
            return false;
        }
        if (isExistedUserByEmail(po.getEmail().getAddress())){
            return false;
        }
        mongoTemplate.save(po);
        workerDao.saveWorker(po.getUsername());
        return true;
    }

    @Override
    public UserPO queryUserByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        UserPO po = mongoTemplate.findOne(query, UserPO.class);
        return po;
    }

    @Override
    public List<UserPO> listAllUsers() {
       List<UserPO> pos = mongoTemplate.findAll(UserPO.class);
       return pos;
    }

    @Override
    public void deleteUser(String username) {

    }
}
