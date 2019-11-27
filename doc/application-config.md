## 项目配置信息  


​    
​    
​    

### 1 端口配置  

| 项目                | 端口(默认 dev 环境) | 用途     | 备注                                                  |
| ------------------- | ------------------- | -------- | ----------------------------------------------------- |
| demo-web            | 8088                | Tomcat   | 主项目,SpringCloud 服务注册中心                       |
|                     | 7749                | Redis    |                                                       |
|                     | 5672                | rabbitMQ |                                                       |
|                     | 3306                | MySQL    |                                                       |
| cloud-zuul          | 8090                | Tomcat   | SpringCloud 子项目,依赖主项目,为 Cloud 子项目提供路由 |
| cloud-client-a      | 8091                | Tomcat   | SpringCloud 子项目,依赖主项目                         |
| cloud-client-b      | 8092                | Tomcat   | SpringCloud 子项目,依赖主项目                         |
| cloud-client-c      | 8093                | Tomcat   | SpringCloud 子项目,依赖主项目                         |
| cloud-config-server | 8094                | Tomcat   | SpringCloud 项目,依赖主项目,分布式配置服务端          |
| cloud-config-client | 8095                | Tomcat   | SpringCloud 项目,依赖主项目,分布式配置客户端          |
| demo-schedule       | 8848                | Tomcat   | 普通SpringBoot项目,不依赖主项目,定时任务              |
| demo-rest           | 8055                | Tomcat   | 普通SpringBoot项目,不依赖主项目,REST 风格模板         |
|                     | 7749                | Redis    |                                                       |
|                     | 3306                | MySQL    |                                                       |
|                     |                     |          |                                                       |
|                     |                     |          |                                                       |

