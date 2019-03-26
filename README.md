# distributed-tool

#### 项目介绍
这是一个分布式常用工具组件。其中包括但不限于：<br/>
分布式List、分布式Set、分布式Long、分布式信号量、分布式缓存、分布式锁、分布式序列号生成器、分布式订阅发布模式等常用工具类<br/>
目前微服务开发的系统很多，这个就涉及到分布式集群问题。像以前如果是单机，那么这些解决方案都可以使用JVM的API来实现<br/>
但在分布式环境下，这些API都不可用，所以急需一套分布式的API来支持这种常用操作，那么我来提供了，后面持续添加各种分布式常用工具类<br/>
注意：所有组件目前都是线程不安全，如果多线程请使用多实例，或者自己加同步锁<br/>

#### 软件架构
目前项目分两个module，distributed-tool和distributed-tool-test。前者是核心源码。后者是测试代码。<br/>
distributed-tool主要分如下模块：分布式List、分布式Set、分布式Long、分布式信号量等<br/>
1. core 提供基本分布式组件。（V1.3支持）<br/>
2. cache 简单的分布式缓存模块。（V1.3支持）<br/>
3. lock  分布式锁。（V1.3支持）<br/>
4. pubsub 分布式订阅发布组件。（V1.3支持）<br/>
5. sequence 分布式序列号生成器。（V1.3支持）<br/>

#### 版本更新

##### V1.0版本<br/>
* 新增client模块（所有工具组件的核心）
* 新增cache模块（分布式缓存工具）
* 新增sequence模块（分布式序列号生成工具）

##### V1.1版本<br/>
* 新增pubsub模块（订阅发布模块）

##### V1.2版本<br/>
* 新增queue模块（分布式队列）
* 重构底层DtClient模块，该模块线程不安全，如果多线程操作，需要自己同步锁，或者新建不同实例
* 新增全局单例Dt类，初始化一次后，后面使用各组件不用在设置DtClient的工厂类

##### V1.3版本<br/>
* 重构底层实现，时用户使用更加的方便
* 新增分布式锁
* 新增分布式信号量

##### V1.4版本<br/>
* 支持Redis的主备模式+哨兵部署

#### Maven引用
<pre><code>
&lt;dependency>
    &lt;groupId>com.xuanner&lt;/groupId>
    &lt;artifactId>distributed-tool&lt;/artifactId>
    &lt;version>1.3&lt;/version>
&lt;/dependency>
</pre></code>

#### 使用教程

初始化（目前底层使用了Redis来实现，所以使用Redis方式初始化）
<pre><code>
Dt.getInstance().initJedis("xxx", 8380, "xxx");
</pre></code>

销毁（注意，在应用的结束生命周期上加上销毁代码，例如Spring的destroy周期）
<pre><code>
Dt.getInstance().getDefaultJedisFactory().destroy();
</pre></code>

一个分布式List使用例子
<pre><code>
DtList list = Dt.newDtList("listName");
list.pushLeft(new String[] { "a", "b" });
list.popLeft();
list.close();//使用完毕记得close
</pre></code>

一个分布式缓存锁使用例子
<pre><code>
DtLock lock = Dt.newDtLock("lockName");
String kId = lock.tryLock();
//你的临界资源操作逻辑
lock.unLock(kId);
lock.close();//使用完毕记得close
</pre></code>

更多使用教程：
https://gitee.com/xuan698400/distributed-tool/wikis/

#### 后续支持功能
1. 分布式限流工具

#### 联系方式
1. 姓名：徐安
2. 邮箱:javaandswing@163.com
3. QQ：349309307
4. 个人博客：xuanner.com
5. 交流群：813221731（群名称：xsequence交流，群备注：分布式开发交流）