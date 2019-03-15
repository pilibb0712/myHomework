package com.example.summer.daoImpl;

import com.example.summer.configInfo.EstimatorConfig;
import com.example.summer.dao.WorkerDao;
import com.example.summer.domain.WorkerPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class WorkerDaoImpl implements WorkerDao {
    private MongoTemplate mongoTemplate;

    @Autowired
    public WorkerDaoImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void saveWorker(String username) {
        WorkerPO po = new WorkerPO();
        po.setCredit(0);
        po.setUsername(username);
        po.setCvectorX(EstimatorConfig.cvector_rec_x);
        po.setCvectorY(EstimatorConfig.cvector_rec_y);
        po.setCvectorH(EstimatorConfig.cvector_rec_h);
        po.setCvectorW(EstimatorConfig.cvector_rec_w);
        mongoTemplate.save(po);
    }

    @Override
    public void updateWorker(WorkerPO po) {
        po.setCredit(0);
        Query query = new Query(Criteria.where("username").is(po.getUsername()));
        mongoTemplate.remove(query, WorkerPO.class);
        mongoTemplate.save(po);
    }

    @Override
    public WorkerPO queryWorker(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        WorkerPO po = mongoTemplate.findOne(query,WorkerPO.class );
        return po;
    }
}
