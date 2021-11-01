package com.xinghe.ioc.service;

import com.xinghe.ioc.dao.WorkerDao;
import com.xinghe.ioc.dao.impl.JavaWorkerDaoImpl;
import com.xinghe.ioc.dao.impl.WorkerDaoImpl;

/**
 * do sth.
 *
 * @author 星河君❀❀ on 2021/10/20 - 3:19 下午
 * @see [相关类/方法]
 * @since [版本号]
 */
public class WorkerService {

//    private WorkerDao workerDao = new WorkerDaoImpl();
    private WorkerDao workerDao = new JavaWorkerDaoImpl();
    /**
     * 找工作
     */
    public void findJob(){
        workerDao.findJob();
    }
}

