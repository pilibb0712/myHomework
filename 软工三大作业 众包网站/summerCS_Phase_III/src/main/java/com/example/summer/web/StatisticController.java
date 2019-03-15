package com.example.summer.web;

import com.example.summer.model.*;
import com.example.summer.service.RankService;
import com.example.summer.utility.enums.MarkTypeEnum;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatisticController {

    /**
     * 返回用户准确率用来画雷达图
     * @return 返回[{labelCnt, labelAcc},{singleRecCnt, singleRecAcc},{MultiRecCnt, MultiRecAcc}]
     * 其中labelCnt代表现在标记了多少个label, labelAcc代表标记label的准确率
     * */
    @RequestMapping("stat/userAccRadar")
    public List<AccuracyStatModel> statUserAccRadar(@RequestParam("username") String username){
        return AccuracyStatModel.generate(username);
    }

    /**
     * 用来绘制项目内username用户在projectId对应的项目下得分的list
     * @return  某个工人在某个项目内所有得分的list,如[3,2,2,2,1]
     * */
    @RequestMapping("stat/userCreditsBox")
    public  List<Integer> statUserCreditsBox(@RequestParam("projectId") String projectId
            , @RequestParam("username") String username){
        return UserCreditsInProjectStatModel.queryUserCreditsInProj(projectId, username);
    }

    /**
     * 项目内所有工人准确率的盒装图
     * @return 项目内所有用户的准确率, 如[0.91, 0.45, 0.99]
     */
    @RequestMapping("stat/userAccBox")
    public List<Double> statUserAccsBox(@RequestParam("projectId") String projectId){
        MarkTypeEnum type = ProjectModel.queryProjectById(projectId).getMarkType();
        return AllWorkerAccInProjectStatModel.queryWorkerAccs(projectId, type);
    }

    /**
     * @return 项目内不同工人得分占比 比如[{3:15},{2:45},{1:45}]
     * */
    @RequestMapping("stat/userCreditsInProjPolar")
    public List<ProjCreditStatModel> statUserCreditsInProjPolar(@RequestParam("projectId") String projectId
            , @RequestParam("username") String username){
        return ProjCreditStatModel.generate(projectId, username);
    }

    /**
     * 查询发放积分区间内项目个数
     * @param min 最少发放积分
     * @param max 最多发放积分
     * @return 区间内项目个数
     * */
    @RequestMapping("stat/numOfTheCredit")
    public int getNumOfTheCredit(@RequestParam("minCredit")int min
            , @RequestParam("maxCredit") int max){
        return ProjectNumOfCreditModel.getNumOfTheCredit(min,max);
    }

    /**
     * 查询给定项目size（图片张数）区间和标记用户人数 项目完成需要的时间
     *
     * @param minSize
     * @param maxSize
     * @param minWorkers
     * @param maxWorkers
     * @return ProjectNeedTimeModel
     * 该model内包含最少时间，最多时间（分10个区间），每个时间区间内项目个数（用大小为10的） List保存
     */
    @RequestMapping("stat/projectNeedTime")
    public ProjectNeedTimeModel getProjectNeedTime(@RequestParam("minSize")int minSize, @RequestParam("maxSize")int maxSize, @RequestParam("minWorkers")int minWorkers, @RequestParam("maxWorkers")int maxWorkers) {
        ProjectNeedTimeModel model=new ProjectNeedTimeModel();
        return model.getProjectNeedTime(minSize,maxSize,minWorkers,maxWorkers);
    }
}
