package com.example.summer.model;

import com.example.summer.dao.ImageFlowDao;
import com.example.summer.daoImpl.ImageFlowDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;

@Component
public class ImageFlowModel {

    @Autowired
    private ImageFlowDao imageFlowDaoIn;

    private static ImageFlowDao imageFlowDao;
    @PostConstruct
    public void init(){
        imageFlowDao = this.imageFlowDaoIn;
    }

    /**
     * 存储project的一批图片
     * */
    public static boolean saveProjectImages(MultipartFile picData, String projectId){
        return imageFlowDao.saveProjectImages(picData, projectId);
    }
}
