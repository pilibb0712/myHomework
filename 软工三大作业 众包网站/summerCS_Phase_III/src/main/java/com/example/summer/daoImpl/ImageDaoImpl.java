package com.example.summer.daoImpl;

import com.example.summer.dao.ImageDao;
import com.example.summer.domain.ImagePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ImageDaoImpl implements ImageDao{


    private MongoTemplate mongoTemplate;

    @Autowired
    public ImageDaoImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public ImagePO queryImageByImageId(String imageId) {
        Query query = new Query(Criteria.where("imageId").is(imageId));
        ImagePO imagePO = mongoTemplate.findOne(query, ImagePO.class);
        return imagePO;
    }

    @Override
    public List<ImagePO> listImagesByProjectId(String projectId) {
        Query query = new Query(Criteria.where("projectId").is(projectId));
        List<ImagePO> images = mongoTemplate.find(query, ImagePO.class);
        return images;
    }

    @Override
    public List<ImagePO> listImagesByProjectIdAndUserId(String projectId, String userId) {
        Query query1 = new Query(Criteria.where("projectId").is(projectId)
                .and("markers.username").is(userId));
        List<ImagePO> images = mongoTemplate.find(query1, ImagePO.class);
        return images;
    }

    @Override
    public List<ImagePO> listImagesByProjectIdAndUserId(String projectId, String userId, int begin, int num) {
        Query query1 = new Query(Criteria.where("projectId").is(projectId)
                .and("markers.username").is(userId))
                .limit(num).skip(begin);
        List<ImagePO> images = mongoTemplate.find(query1, ImagePO.class);
        return images;
    }

    @Override
    public List<ImagePO> listCurrentImagesByProjectIdAndUserId(String projectId, String userId) {
        Query query1 = new Query(Criteria.where("projectId").is(projectId)
                .and("markers")
                .elemMatch(Criteria.where("username").is(userId).and("currentImg").is(true)));
        List<ImagePO> images = mongoTemplate.find(query1, ImagePO.class);
        return images;
    }

    @Override
    public List<ImagePO> listCurrentImagesByProjectIdAndUserId(String projectId, String userId, int begin, int num) {
        Query query1 = new Query(Criteria.where("projectId").is(projectId)
                .and("markers")
                .elemMatch(Criteria.where("username").is(userId).and("currentImg").is(true)))
                .limit(num).skip(begin);
        List<ImagePO> images = mongoTemplate.find(query1, ImagePO.class);
        return images;
    }

    @Override
    public boolean updateImage(ImagePO po) {
        Query query = new Query(Criteria.where("imageId").is(po.getImageId()));
//        Update update = new Update().set("markers", po.getMarkers())
//                .set("marks", po.getMarks());
        mongoTemplate.remove(query,  ImagePO.class);
        mongoTemplate.save(po);
        return true;
    }

    @Override
    public boolean saveProjectImages(String projectId, List<ImagePO> images) {
        mongoTemplate.insert(images, ImagePO.class);
        return true;
    }

    @Override
    public List<ImagePO> listLauncherImagesByProjectId(String projectId, int begin, int num) {
        Query query = new Query(Criteria.where("projectId").is(projectId))
                .limit(num).skip(begin);
        List<ImagePO> images = mongoTemplate.find(query, ImagePO.class);
        return images;
    }

}
