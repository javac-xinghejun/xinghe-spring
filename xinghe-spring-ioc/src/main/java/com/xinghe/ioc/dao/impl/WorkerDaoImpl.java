package com.xinghe.ioc.dao.impl;

import com.xinghe.ioc.dao.WorkerDao;

/**
 * do sth.
 *
 * @author 星河君❀❀ on 2021/10/20 - 3:18 下午
 * @see [相关类/方法]
 * @since [版本号]
 */
public class WorkerDaoImpl implements WorkerDao {

    /**
     * 找工作
     */
    @Override
    public void findJob() {
        System.out.println("find job default");
    }
}
