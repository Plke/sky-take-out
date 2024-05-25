# 苍穹外卖
此项目为planck学习JAVA的项目，源于黑马

## 项目结构
|模块|说明|
|----|----|
|sky-take-out|maven父工程，统一管理依赖版本，聚合其他子模块|
|sky-common|存放公共类，例如：工具类、常量类、异常类等|
|sky-pojo|存放实体类、VO、DTO等|
|sky-server|后端服务，存放配置文件、Controller、Service、Mapper等|

### sky-common

|名称|说明|
|----|----|
|constant|存放相关常量类|
|context|存放上下文类|
|enumeration|项目的枚举类存储|
|exception|存放自定义异常类|
|json|处理json转换的类|
|properties|存放SpringBoot相关的配置属性类|
|result|返回结果类的封装|
|utils|常用工具类|

### sky-pojo

|名称|说明|
|----|----|
|Entity|实体，通常和数据库中的表对应|
|DTO|数据传输对象，通常用于程序中各层之间传递数据|
|VO|视图对象，为前端展示数据提供的对象|
|POJO|普通Java对象，只有属性和对应的getter和setter|

### sky-server

|名称|说明|
|----|----|
|config|存放配置类|
|controller|存放controller类|
|interceptor|存放拦截器类|
|mapper|存放mapper接口|
|service|存放service类|
|SkyApplication|启动类|



## 注意事项
记录我在写代码过程中的问题

### PageHelper插件
在进行分页查询的时候
这个插件先查询记录条数
如果设置的**页数x每页记录数**大于记录条数，则不会执行查询语句
bug复现
1. 先进行一次页数为2的查询，得到结果后，
2. 在这个基础上进行多个条件的查询，导致第二页显示的科数据为0，最终只进行数量查询，不进行内容查询

修改
设置pagehelper.reasonable=true时，pageNum<=0 时会查询第一页， pageNum>pages（超过总数时），会查询最后一页
也就是即使传入页数大于总页数，仍然会返回数据


## Bean
对于部分记得使用`Bean`对象交给`IOC`管理，如`RedisTemplate`配置,否则使用默认的，配置会出错

## 事务管理
启动类上添加`@EnableTransactionManagement` 开启注解方式的事务管理
在`SpringBoot`中，事务管理使用`@Transactional`注解，在需要开启事务的方法上添加该注解即可

## 缓存
启动类上添加`@EnableCaching`

## spring task
启动类上添加`@EnableScheduling`
cron表达式其实就是一个字符串，通过cron表达式可以定义任务触发的时间