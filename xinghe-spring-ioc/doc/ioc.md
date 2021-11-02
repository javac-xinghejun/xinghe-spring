## 理解IOC
学习Spring，IOC是个绕不过去的话题。作为Spring的基础，IOC有太多博客和教程了。这里做个简单的梳理，以找工作为例，谈谈我对IOC的理解。

在经典的MVC架构这，一般会有Dao、Service和Controller层，我们用Dao和Service举例，项目结构如图：
![项目结构](https://cdn.jsdelivr.net/gh/filess/img19@main/2021/11/01/1635772952626-a51cf15c-645c-4c34-b013-b74d6add88b4.png)

其中WorkerService调用WorkDao的实现，代码如下：

```java
public class WorkerService {

    private WorkerDao workerDao = new WorkerDaoImpl();

    /**
     * 找工作
     */
    public void findJob(){
        workerDao.findJob();
    }
}
```

WorkerDaoImpl代码如下：

```java
public class WorkerDaoImpl implements WorkerDao {

    /**
     * 找工作
     */
    @Override
    public void findJob() {
        System.out.println("find job default");
    }
}
```

测试代码：

```java
public class WorkerServiceTest {

    @Test
    public void findJob() {
        WorkerService workerService = new WorkerService();
        workerService.findJob();
    }
}
```

到目前为止没有问题，运行起来看下：
![运行结果](https://cdn.jsdelivr.net/gh/filess/img7@main/2021/11/01/1635772999459-a57b9bb7-bb8a-47cb-a85f-06ee173a3a10.png)

类图如下：

![类图](https://cdn.jsdelivr.net/gh/filess/img7@main/2021/11/01/1635773036950-2b3a8e5e-4d29-4df0-8ff9-dcda10ab3e91.png)


现在问题来了。我想要一个固定类型的工作，比如Java开发的工作。先添加一个对应的实现：

```java
public class JavaWorkerDaoImpl implements WorkerDao {

    /**
     * 找工作
     */
    @Override
    public void findJob() {
        System.out.println("find java worker!");
    }
}
```

同样，对应的WorkerService也修改一下：

```java
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

```

可以看出，此时只需要把WorkerDao的实现改成JavaWorkerDaoImpl类就行了。运行：

![](https://cdn.jsdelivr.net/gh/filess/img13@main/2021/11/01/1635773071741-c07b6c07-2146-4246-a19c-8bd9f37053c4.png)

同样，如果需要其他的工作，只需要不同的实现就行了。**唯一的问题是，需要改动业务代码，来兼容客户端的需求。**

接下来开始做一点修改。先把WorkerService做一点改造：

```java
public class WorkerService {

//    private WorkerDao workerDao = new WorkerDaoImpl();
    private WorkerDao workerDao;
    /**
     * 找工作
     */
    public void findJob(){
        workerDao.findJob();
    }

    public WorkerDao getWorkerDao() {
        return workerDao;
    }

    public void setWorkerDao(WorkerDao workerDao) {
        this.workerDao = workerDao;
    }
}
```

这样客户端的测试代码就成了这样：

```java
public class WorkerServiceTest {

    @Test
    public void findJob() {
        WorkerService workerService = new WorkerService();
        WorkerDao workerDao = new WorkerDaoImpl();
        workerService.setWorkerDao(workerDao);
        workerService.findJob();
    }
}
```

这个时候，workderDao实例化时，只需要客户端指定就行了，客户端可以按照自己想要的来set对应的WorkerDao的实现。这样一来，对于客户端的需求变化，业务层不需要做任何修改。
看下此时的类图：

![](https://cdn.jsdelivr.net/gh/filess/img14@main/2021/11/01/1635773102498-c66a03c8-88b3-4556-abe9-406ab0132719.png)

这里有四个角色：WorkerDao及其实现类、WorkerService业务类、Test类（客户端）。以目前这种方式解耦了业务层和客户端，业务类的代码不会根据客户端的需求来改变。对比上面的图可以看出，业务类WorkerService并不直接管理WorkerDao，业务更内聚。

现在回到IOC。**所谓控制反转，就是要把对象的管理权交出去。** 最开始的实现UserService类需要自己来new WorkerDaoImpl()来实现WorkerDao，这样管理WorkerDao就成了WorkerService来做的了。后续的实现则是把WorkerDao的实现交给客户端来完成，把控制权交给了客户端。

- 控制：谁来创建对象，谁就有控制权
- 反转：对象的创建不再由业务控制，业务类只需要被动地接收客户端set进来的类



