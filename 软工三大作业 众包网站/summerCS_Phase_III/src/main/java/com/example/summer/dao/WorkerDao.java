package com.example.summer.dao;

import com.example.summer.domain.WorkerPO;

public interface WorkerDao {
    void saveWorker(String username);
    void updateWorker(WorkerPO po);
    WorkerPO queryWorker(String username);
}
