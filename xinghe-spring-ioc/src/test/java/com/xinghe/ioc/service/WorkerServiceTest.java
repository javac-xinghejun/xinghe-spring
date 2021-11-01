package com.xinghe.ioc.service;

import static org.junit.Assert.*;

import com.xinghe.ioc.dao.WorkerDao;
import com.xinghe.ioc.dao.impl.JavaWorkerDaoImpl;
import com.xinghe.ioc.dao.impl.WorkerDaoImpl;
import org.junit.Test;

public class WorkerServiceTest {

    @Test
    public void findJob() {
        WorkerService workerService = new WorkerService();
        WorkerDao workerDao = new WorkerDaoImpl();
        workerService.setWorkerDao(workerDao);
        workerService.findJob();
    }
}