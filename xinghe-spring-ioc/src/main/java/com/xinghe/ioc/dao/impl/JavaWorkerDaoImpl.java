package com.xinghe.ioc.dao.impl;

import com.xinghe.ioc.dao.WorkerDao;

/**
 * do sth.
 *
 * @author 星河君❀❀ on 2021/11/1 - 3:12 下午
 * @see [相关类/方法]
 * @since [版本号]
 */
public class JavaWorkerDaoImpl implements WorkerDao {

    /**
     * 找工作
     */
    @Override
    public void findJob() {
        System.out.println("find java worker!");
    }
}
