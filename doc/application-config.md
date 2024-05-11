## 项目配置信息  


​    
​    
​    

### 1 端口配置  

| 项目                                | 端口(默认 dev 环境) | 用途     | 备注                                                         |
| ----------------------------------- | ------------------- | -------- | ------------------------------------------------------------ |
| demo-web                            | 8088                | Tomcat   | **主项目**,SpringCloud 服务注册中心                          |
|                                     | 7749                | Redis    |                                                              |
|                                     | 5672                | rabbitMQ |                                                              |
|                                     | 3306                | MySQL    |                                                              |
| cloud-zuul                          | 8090                | Tomcat   | SpringCloud 子项目,依赖主项目,为 Cloud 子项目提供路由        |
| cloud-client-a                      | 8091                | Tomcat   | SpringCloud 子项目,依赖主项目                                |
| cloud-client-b                      | 8092                | Tomcat   | SpringCloud 子项目,依赖主项目                                |
| cloud-client-c                      | 8093                | Tomcat   | SpringCloud 子项目,依赖主项目                                |
| cloud-config-server                 | 8094                | Tomcat   | SpringCloud 项目,依赖主项目,分布式配置服务端                 |
| cloud-config-client                 | 8095                | Tomcat   | SpringCloud 项目,依赖主项目,分布式配置客户端                 |
| demo-schedule                       | 8848                | Tomcat   | 普通SpringBoot项目,不依赖主项目,定时任务                     |
| cloud-zookeeper-provider            | 8100                | Tomcat   | SpringCloud 项目,不依赖主项目，SpringCloud 服务注册中心，通过 Zookeeper 技术实现 |
| cloud-zookeeper-ribbon              | 8101                | Tomcat   | SpringCloud Zookeeper 子项目，不依赖主项目，通过 Ribbon 方式调用 Spring Cloud Zookeeper 服务 |
| cloud-zookeeper-feign               | 8102                | Tomcat   | SpringCloud Zookeeper 子项目，不依赖主项目，通过 Feign 方式(内置 Ribbon) 调用 Spring Cloud Zookeeper 服务 |
| cloud-zookeeper-config-server       | 8103                | Tomcat   | SpringCloud Zookeeper 子项目，不依赖主项目，Spring Cloud Zookeeper Config 分布式配置中心服务端 |
| cloud-zookeeper-config-client       | 8104                | Tomcat   | SpringCloud Zookeeper 子项目，不依赖主项目，Spring Cloud Zookeeper Config 分布式配置中心客户端 |
| demo-websocket                      | 8200                | Tomcat   | SpringBoot websocket 项目,不依赖主项目                       |
| demo-websocket-spring               | 8201                | Tomcat   | SpringBoot websocket 项目,不依赖主项目                       |
| activiti-workflow                   | 8400                | Tomcat   | SpringBoot 项目,Activiti 工作流,不依赖主项目                 |
| demo-mybatis-plus                   | 8450                | Tomcat   | SpringBoot 项目,Mybatis Plus 入门教程,不依赖主项目           |
| demo-stater-usage                   | 8500                | Tomcat   | SpringBoot 项目，不依赖主项目                                |
| demo-schedule-quartz                | 8550                | Tomcat   | SpringBoot 项目，Quart 定时任务单机模式，不依赖主项目        |
| demo-schedule-quartz-group          | 8551                | Tomcat   | SpringBoot 项目，Quartz 定时任务集群模式，不依赖主项目       |
|                                     | 8552                | Tomcat   |                                                              |
| demo-schedule-xxl-job               | 6560                | Tomcat   | SpringBoot 项目，xxl-job 定时任务调度中心，不依赖主项目，不在此仓库中 |
|                                     | 8561                | Tomcat   | SpringBoot 项目，xxl-job 定时任务执行器,不依赖主项目         |
|                                     | 8562                | Tomcat   | xxl-job 定时任务执行器                                       |
|                                     | 8566                | xxl-job  | xxl-job 定时任务执行器，执行器与调度中心通讯端口             |
|                                     | 8567                | xxl-job  | xxl-job 定时任务执行器，执行器与调度中心通讯端口             |
| cloud-alibaba-server-provider       | 8600                | Tomcat   | Spring Cloud Alibaba 项目，不依赖主项目，服务提供者示例项目  |
|                                     | 8601                | Tomcat   |                                                              |
| cloud-alibaba-server-consumer       | 8602                | Tomcat   | Spring Cloud Alibaba 子项目，不依赖主项目，服务消费者(Ribbon) |
| cloud-alibaba-server-consumer-feign | 8604                | Tomcat   | Spring Cloud Alibaba 子项目，不依赖主项目，服务消费者(Feign) |
| cloud-alibaba-sentinel-dashboard    | 8606                | Tomcat   | Spring Cloud Alibaba 子项目，不依赖主项目，Sentinel 控制台   |
| cloud-alibaba-consumer-sentinel     | 8608                | Tomcat   | Spring Cloud Alibaba 子项目，不依赖主项目，Sentinel 限流示例(Feign) |
| cloud-alibaba-gateway               | 8010                | Tomcat   | Spring Cloud Alibaba 子项目，不依赖主项目，Gateway 网关      |
| cloud-alibaba-gateway-filter        | 8612                | Tomcat   | Spring Cloud Alibaba 子项目，不依赖主项目，Gateway 网关拦截功能 |
| cloud-alibaba-config                | 8614                | Tomcat   | Spring Cloud Alibaba 子项目，不依赖主项目，Nacos 分布式配置示例 |
| demo-mongodb                        | 8650                | Tomcat   | SpringBoot 项目，SpringBoot 集成 MongoDB，不依赖主项目       |
| demo-sagger3                        | 8680                | Tomcat   | SpringBoot 项目，SpringBoot 集成 Swagger3，不依赖主项目      |
| demo-maven-assembly                 | 8700                | Tomcat   | SpringBoot 项目，SpringBoot 使用 assembly 插件打包，<br />不依赖主项目 |
| demo-rocketmq-producer              | 8750                | Tomcat   | SpringBoot 项目，SpringBoot 集成 RocketMQ，消息生产者，不依赖主项目 |
| demo-rocketmq-consumer              | 8751                | Tomcat   | SpringBoot 项目，SpringBoot 集成 RocketMQ，消息消费者，不依赖主项目 |
| demo-elasticsearch                  | 8800                | Tomcat   | SpringBoot 项目，SpringBoot 集成 Elasticsearch，不依赖主项目 |
| demo-read-write                     | 8850                | Tomcat   | SpringBoot 项目，SpringBoot 集成 MySQL 读写分离，不依赖主项目 |
| demo-kafka-producer                 | 8900                | Tomcat   | SpringBoot 项目，SpringBoot 集成 Kafka，消息生产者，不依赖主项目 |
| demo-kafka-consumer                 | 8901                | Tomcat   | SpringBoot 项目，SpringBoot 集成 Kafka，消息消费者，不依赖主项目 |
| demo-sharding-jdbc                  | 8950                | Tomcat   | SpringBoot 项目，SpringBoot 集成 Sharding-jdbc，分库分表，不依赖主项目 |
| demo-aws-iot                        | 9000                | Tomcat   | SpringBoot 项目，SpringBoot 集成 AWS IoT，不依赖主项目       |
| demo-knife4j-openapi3               | 9050                | Tomcat   | SpringBoot 项目，SpringBoot 集成 Knife4j，不依赖主项目       |
| demo-netty-server                   | 9100                | Tomcat   | SpringBoot 项目，SpringBoot 集成 Netty，不依赖主项目         |
|                                     | 9120                | Netty    | TCP 通讯端口                                                 |
|                                     | 9125                | Netty    | TCP 通讯端口                                                 |
|                                     | 9130                | Netty    | UDP 通讯端口                                                 |
| demo-opencv                         | 9211                | Tomcat   | SpringBoot 项目，SpringBoot 集成 OpenCV，不依赖主项目        |
| demo-ffmepg-media                   | 9250                | Tomcat   | SpringBoot 项目，SpringBoot 集成 FFmpeg，不依赖主项目        |

