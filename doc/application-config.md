## 项目配置信息  


​    
​    
​    

### 1 端口配置  

| 项目                          | 端口(默认 dev 环境) | 用途     | 备注                                                         |
| ----------------------------- | ------------------- | -------- | ------------------------------------------------------------ |
| demo-web                      | 8088                | Tomcat   | **主项目**,SpringCloud 服务注册中心                          |
|                               | 7749                | Redis    |                                                              |
|                               | 5672                | rabbitMQ |                                                              |
|                               | 3306                | MySQL    |                                                              |
| cloud-zuul                    | 8090                | Tomcat   | SpringCloud 子项目,依赖主项目,为 Cloud 子项目提供路由        |
| cloud-client-a                | 8091                | Tomcat   | SpringCloud 子项目,依赖主项目                                |
| cloud-client-b                | 8092                | Tomcat   | SpringCloud 子项目,依赖主项目                                |
| cloud-client-c                | 8093                | Tomcat   | SpringCloud 子项目,依赖主项目                                |
| cloud-config-server           | 8094                | Tomcat   | SpringCloud 项目,依赖主项目,分布式配置服务端                 |
| cloud-config-client           | 8095                | Tomcat   | SpringCloud 项目,依赖主项目,分布式配置客户端                 |
| demo-schedule                 | 8848                | Tomcat   | 普通SpringBoot项目,不依赖主项目,定时任务                     |
| cloud-zookeeper-provider      | 8100                | Tomcat   | SpringCloud 项目,不依赖主项目，SpringCloud 服务注册中心，通过 Zookeeper 技术实现 |
| cloud-zookeeper-ribbon        | 8101                | Tomcat   | SpringCloud Zookeeper 子项目，不依赖主项目，通过 Ribbon 方式调用 Spring Cloud Zookeeper 服务 |
| cloud-zookeeper-feign         | 8102                | Tomcat   | SpringCloud Zookeeper 子项目，不依赖主项目，通过 Feign 方式(内置 Ribbon) 调用 Spring Cloud Zookeeper 服务 |
| cloud-zookeeper-config-server | 8103                | Tomcat   | SpringCloud Zookeeper 子项目，不依赖主项目，Spring Cloud Zookeeper Config 分布式配置中心服务端 |
| cloud-zookeeper-config-client | 8104                | Tomcat   | SpringCloud Zookeeper 子项目，不依赖主项目，Spring Cloud Zookeeper Config 分布式配置中心客户端 |
| demo-websocket                | 8200                | Tomcat   | SpringBoot websocket 项目,不依赖主项目                       |
| demo-websocket-spring         | 8201                | Tomcat   | SpringBoot websocket 项目,不依赖主项目                       |
|                               |                     |          |                                                              |

