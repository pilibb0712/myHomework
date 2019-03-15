package com.example.summer.dao;
import com.example.summer.domain.ProjectRankPO;

import java.util.ArrayList;

public interface ProjectRankDao {
    boolean save(ProjectRankPO projectRank);
    boolean update(ProjectRankPO projectRank);
    ProjectRankPO queryProjectRankByProjectId(String projectId);
    ArrayList<String> getAllProjectIds();
}
