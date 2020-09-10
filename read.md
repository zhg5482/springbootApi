#springboot 
######目录结构:
######├── pom.xml 
######├── read.md
######├── src  主程序目录
######│   ├── main
######│   │   ├── java
######│   │   │   └── com
######│   │   │       └── example
######│   │   │           └── demo
######│   │   │               ├── DemoApplication.java  应用启动程序
######│   │   │               ├── config  配置文件相关
######│   │   │               │   ├── AuthConfig.java  授权配置
######│   │   │               │   ├── RedisConfig.java  redis配置
######│   │   │               │   ├── SwaggerConfig.java  swagger配置
######│   │   │               │   └── WebMvcConfiguration.java  controller父类requestMapping域名配置
######│   │   │               ├── constant 常量文件相关
######│   │   │               │   ├── Constants.java  系统常量
######│   │   │               │   └── ResponseCode.java 返回码常量
######│   │   │               ├── controller  控制器相关
######│   │   │               │   ├── ApiController.java 控制器父类
######│   │   │               │   ├── AuthorityController.java 授权控制器
######│   │   │               │   ├── UserController.java 用户相关控制器
######│   │   │               │   └── WebTestController.java web测试控制器
######│   │   │               ├── entity 实体类相关
######│   │   │               │   └── User.java user实体类
######│   │   │               ├── handler 拦截器
######│   │   │               │   ├── AuthInterceptor.java  授权拦截器
######│   │   │               │   ├── ControllerApiRequestMappingHandlerMapping.java 控制器父类域名映射
######│   │   │               │   └── LoginRequired.java 登录控制器
######│   │   │               ├── mapper dao层相关
######│   │   │               │   └── UserMapper.java userDao层
######│   │   │               ├── schedule 计划任务相关
######│   │   │               ├── StaticScheduleTask.java 静态定时任务测试
######│   │   │               ├── service  service层
######│   │   │               │   ├── AuthorityService.java 授权service层
######│   │   │               │   ├── BaseService.java  父类
######│   │   │               │   ├── UserService.java 
######│   │   │               │   └── impl service 实现
######│   │   │               │       └── UserServiceImpl.java
######│   │   │               └── util 工具类
######│   │   │                   ├── BodyReaderHttpServletRequestWrapper.java
######│   │   │                   ├── CommonUtil.java
######│   │   │                   ├── HttpUtils.java
######│   │   │                   ├── RedisUtil.java
######│   │   │                   ├── ResultUtil.java
######│   │   │                   ├── ServletUtil.java
######│   │   │                   ├── SignUtil.java
######│   │   │                   └── Status.java
######│   │   └── resources 资源类
######│   │       ├── application.properties
######│   │       ├── mapper
######│   │       │   └── UserMapper.xml
######│   │       ├── static
######│   │       │   ├── css
######│   │       │   │   ├── bootstrap-grid.css
######│   │       │   │   ├── bootstrap-grid.css.map
######│   │       │   │   ├── bootstrap-grid.min.css
######│   │       │   │   ├── bootstrap-grid.min.css.map
######│   │       │   │   ├── bootstrap-reboot.css
######│   │       │   │   ├── bootstrap-reboot.css.map
######│   │       │   │   ├── bootstrap-reboot.min.css
######│   │       │   │   ├── bootstrap-reboot.min.css.map
######│   │       │   │   ├── bootstrap.css
######│   │       │   │   ├── bootstrap.css.map
######│   │       │   │   ├── bootstrap.min.css
######│   │       │   │   ├── bootstrap.min.css.map
######│   │       │   │   └── style.css
######│   │       │   ├── image
######│   │       │   │   └── logo.jpeg
######│   │       │   └── js
######│   │       │       ├── bootstrap.bundle.js
######│   │       │       ├── bootstrap.bundle.js.map
######│   │       │       ├── bootstrap.bundle.min.js
######│   │       │       ├── bootstrap.bundle.min.js.map
######│   │       │       ├── bootstrap.js
######│   │       │       ├── bootstrap.js.map
######│   │       │       ├── bootstrap.min.js
######│   │       │       ├── bootstrap.min.js.map
######│   │       │       └── show.js
######│   │       └── templates
######│   │           └── show.html
######│   └── test
######│       └── java
######│           └── com
######│               └── example
######│                   └── demo
######│                       └── DemoApplicationTests.java
######└── target
######    ├── classes
######    │   ├── application.properties
######    │   ├── com
######    │   │   └── example
######    │   │       └── demo
######    │   │           ├── DemoApplication.class
######    │   │           ├── config
######    │   │           │   ├── AuthConfig.class
######    │   │           │   ├── RedisConfig.class
######    │   │           │   ├── SwaggerConfig.class
######    │   │           │   └── WebMvcConfiguration.class
######    │   │           ├── constant
######    │   │           │   ├── Constants.class
######    │   │           │   └── ResponseCode.class
######    │   │           ├── controller
######    │   │           │   ├── ApiController.class
######    │   │           │   ├── AuthorityController.class
######    │   │           │   ├── UserController.class
######    │   │           │   └── WebTestController.class
######    │   │           ├── entity
######    │   │           │   └── User.class
######    │   │           ├── handler
######    │   │           │   ├── AuthInterceptor.class
######    │   │           │   ├── ControllerApiRequestMappingHandlerMapping.class
######    │   │           │   └── LoginRequired.class
######    │   │           ├── mapper
######    │   │           │   └── UserMapper.class
######    │   │           ├── service
######    │   │           │   ├── AuthorityService.class
######    │   │           │   ├── BaseService.class
######    │   │           │   ├── UserService.class
######    │   │           │   └── impl
######    │   │           │       └── UserServiceImpl.class
######    │   │           └── util
######    │   │               ├── BodyReaderHttpServletRequestWrapper$1.class
######    │   │               ├── BodyReaderHttpServletRequestWrapper.class
######    │   │               ├── CommonUtil.class
######    │   │               ├── HttpUtils.class
######    │   │               ├── RedisUtil.class
######    │   │               ├── ResultUtil.class
######    │   │               ├── ServletUtil.class
######    │   │               ├── SignUtil.class
######    │   │               ├── Status$ExpireEnum.class
######    │   │               └── Status.class
######    │   ├── mapper
######    │   │   └── UserMapper.xml
######    │   ├── static
######    │   │   ├── css
######    │   │   │   ├── bootstrap-grid.css
######    │   │   │   ├── bootstrap-grid.css.map
######    │   │   │   ├── bootstrap-grid.min.css
######    │   │   │   ├── bootstrap-grid.min.css.map
######    │   │   │   ├── bootstrap-reboot.css
######    │   │   │   ├── bootstrap-reboot.css.map
######    │   │   │   ├── bootstrap-reboot.min.css
######    │   │   │   ├── bootstrap-reboot.min.css.map
######    │   │   │   ├── bootstrap.css
######    │   │   │   ├── bootstrap.css.map
######    │   │   │   ├── bootstrap.min.css
######    │   │   │   ├── bootstrap.min.css.map
######    │   │   │   └── style.css
######    │   │   ├── image
######    │   │   │   └── logo.jpeg
######    │   │   └── js
######    │   │       ├── bootstrap.bundle.js
######    │   │       ├── bootstrap.bundle.js.map
######    │   │       ├── bootstrap.bundle.min.js
######    │   │       ├── bootstrap.bundle.min.js.map
######    │   │       ├── bootstrap.js
######    │   │       ├── bootstrap.js.map
######    │   │       ├── bootstrap.min.js
######    │   │       ├── bootstrap.min.js.map
######    │   │       └── show.js
######    │   └── templates
######    │       └── show.html
######    ├── generated-sources
######    │   └── annotations
######    ├── generated-test-sources
######    │   └── test-annotations
######    └── test-classes
######        └── com
######            └── example
######                └── demo
######                    └── DemoApplicationTests.class
#java 8 在线api 中文版
http://www.matools.com/api/java8
#SpringBoot 中常用注解
https://www.cnblogs.com/superslow/p/9113551.html
#Thymeleaf
https://www.jianshu.com/p/a842e5b5012e
https://www.jianshu.com/p/908b48b10702
#Spring Boot 静态资源处理
https://blog.csdn.net/catoop/article/details/50501706
#Springboot+Thymeleaf中常用的th标签
https://blog.csdn.net/haojiagou/article/details/92072969
#api父类
https://blog.csdn.net/zhanghaishan/article/details/103863258/
#redis
https://www.jianshu.com/p/5596c3a4978d
#Swagger
https://www.jianshu.com/p/be1e772b089a
https://www.cnblogs.com/97guoxiang/p/12595793.html
#接口文档
http://localhost:8999/swagger-ui.html
#jpa
https://www.cnblogs.com/huhx/p/13228766.html
#拦截器
https://www.cnblogs.com/jtlgb/p/12372758.html
https://recomm.cnblogs.com/blogpost/11550891
https://www.cnblogs.com/zhangpeng8888/p/12702305.html
#字节数组转16进制字符串
https://my.oschina.net/u/347386/blog/182717
#定时任务
https://www.cnblogs.com/mmzs/p/10161936.html
