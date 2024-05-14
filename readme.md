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