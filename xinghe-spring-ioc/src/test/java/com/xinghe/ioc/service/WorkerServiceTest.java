package com.xinghe.ioc.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class WorkerServiceTest {

    @Test
    public void findJob() {
        WorkerService workerService = new WorkerService();
        workerService.findJob();
    }
}