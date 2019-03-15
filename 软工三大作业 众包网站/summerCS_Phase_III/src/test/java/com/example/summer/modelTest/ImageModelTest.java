package com.example.summer.modelTest;


import com.example.summer.domain.MarkPO;
import com.example.summer.domain.RecGroupMarksPO;
import com.example.summer.model.ImageModel;
import com.example.summer.utility.json.JsonStringUitility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ImageModelTest {
    ImageModel image;
    @Before
    public void initData(){
        image=ImageModel.queryImageModelByImageId("2018-04-15_20-31-47--0");
    }

    @Test
    public void testCanAddNewMarker(){
        Assert.assertTrue(image.canAddNewMarker());
    }
    @Test
    public void testMarkerAlreadyIn(){
        Assert.assertTrue(image.markerAlreadyIn("beibei"));
        Assert.assertTrue(image.markerAlreadyIn("bei"));
    }

    @Test
    public void testMark(){
        ArrayList<MarkPO> markPOS = new ArrayList<>();
        MarkPO markPO = new MarkPO();
        markPO.setRecGroupMark(new RecGroupMarksPO());
        ImageModel imageModel = new ImageModel();
        System.out.println(JsonStringUitility.toString(image));
    }
}
