package com.example.summer.utility.convertorUtility;

import com.example.summer.domain.MultiCreditProjectPO;
import com.example.summer.domain.SimpleProjectPO;
import com.example.summer.model.MultiCreditProjectModel;
import com.example.summer.model.SimpleProjectModel;

public class ProjectMapClassUtility {
    public static Class<? extends Object> getDestinationClass(Object o){
        if (o instanceof SimpleProjectModel){ return SimpleProjectPO.class;}
        if (o instanceof SimpleProjectPO){return SimpleProjectModel.class;}

        if (o instanceof MultiCreditProjectModel){return MultiCreditProjectPO.class;}
        if (o instanceof MultiCreditProjectPO){return MultiCreditProjectModel.class;}
        return null;
    }
}
