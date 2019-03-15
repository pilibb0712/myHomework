package com.example.summer.daoTest;

import com.example.summer.dao.ImageDao;
import com.example.summer.daoImpl.ImageDaoImpl;
import com.example.summer.domain.ImageFlowPO;
import com.example.summer.domain.ImagePO;
import com.example.summer.domain.MarkerInfoPO;
import com.example.summer.model.ImageListModel;
import com.example.summer.stub.ImagePOStub;
import com.example.summer.utility.json.JsonStringUitility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageDaoTest {
    @Autowired
    private ImageDao imageDao;

    String projectId = "123456789";

    //@Test
    public void testSaveProjectImages(){
        List<ImagePO> images = new ArrayList<>();
        for (int i=0; i<5; i++){
            ImagePO po = ImagePO.builder()
                    .fileName("picture"+i)
                    .imageId("20180613"+i)
                    .projectId(projectId)
                    .upUserLimit(i+1)
                    .build();
            images.add(po);
        }

        imageDao.saveProjectImages(projectId, images);
    }

    //@Test
    public void testQueryImageByImageId(){
        ImagePO po = imageDao.queryImageByImageId("201806130");
        Assert.assertEquals("201806130", po.getImageId());
        System.out.println(JsonStringUitility.toString(po));
    }

    //@Test
    public void testListByProjectId(){
        List<ImagePO> images = imageDao.listImagesByProjectId(projectId);
        for (ImagePO po : images){
            Assert.assertEquals(projectId, po.getProjectId());
            System.out.println(JsonStringUitility.toString(po));
        }
    }

    //@Test
    public void testUpdate(){
        ArrayList<MarkerInfoPO> markers = new ArrayList<>();
        markers.add(new MarkerInfoPO("zhangao",4, false));
        markers.add(new MarkerInfoPO("abc",84, true));
        markers.add(new MarkerInfoPO("edf",44, false));

        ImagePO po = ImagePO.builder()
                .fileName("picture"+2)
                .imageId("20180613"+2)
                .projectId(projectId)
                .upUserLimit(1+2)
                .markers(markers)
                .build();
        imageDao.updateImage(po);
    }

    //@Test
    public void testListImagesByProjectIdAndUserId(){
        List<ImagePO> imagePOS = imageDao.listImagesByProjectIdAndUserId(projectId,"zhangao");
        System.out.println(JsonStringUitility.toString(imagePOS));
    }

    //@Test
    public void listImagesByProjectIdAndUserId(){
        List<ImagePO> images = imageDao
                .listImagesByProjectIdAndUserId(projectId, "zhangao", 1, 1);
        System.out.println(JsonStringUitility.toString(images));
    }

    @Test
    public void testListCurrentImagesByProjectIdAndUserId(){
        List<ImagePO> images = imageDao
                .listCurrentImagesByProjectIdAndUserId(projectId, "zhangao");
        System.out.println(JsonStringUitility.toString(images));
    }
}
