# 框架整体说明
## 模块说明

模块名 | 功能说明
---|---
hope-api | 框架API入口，里面的hope-biz-api是业务的http restful请求入口
hope-basic | 框架的基础支撑(数据库：hope-db,缓存:hope-cache)
hope-client | 框架的客户端支撑(接口提供者：hope-biz-cliente，对象实体:hope-entity)
hope-dependencies | 框架的所有依赖，核心依赖：hope-core，dubbo的依赖：hope-dubboe，单元测试的依赖：hope-teste，工具类的依赖:hope-util
hope-parent | 框架的父模块，统一管理，统一打包，编译等
hope-service | 框架的服务模块,它包括了服务的实现hope-biz-service
hope-support | 框架的支持，现在有整个接口内的测试


## 日志说明
为了方便日志管理及以后的日志采集，我们采用两种日志，一种普通的，一种JSON格式的

具体配置：

```
logging:
  config: classpath:logback-spring.xml
  path: D:/logs/hope/api/biz
  level:
        org.hopeframework.biz.api: info
```
具体文件：

```
<?xml version="1.0" encoding="UTF-8"?>
<configuration>

    <!-- spring boot logging 的基础配置 -->
    <include resource="org/springframework/boot/logging/logback/defaults.xml"/>

    <springProperty scope="context" name="springAppName" source="spring.application.name"/>

    <!-- 文件保存路径配置 -->
    <property name="LOG_PATH" value="${LOG_PATH:-${LOG_TEMP:-${java.io.tmpdir:-/tmp}}}" />

    <!-- Appender to log to console -->
    <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <!-- Minimum logging level to be presented in the console logs-->
            <level>INFO</level>
        </filter>
        <encoder>
            <pattern>${CONSOLE_LOG_PATTERN}</pattern>
            <charset>utf8</charset>
        </encoder>
    </appender>

    <!-- Appender to log to file -->
    <appender name="flatfile" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${LOG_PATH}/normal/${springAppName}.%d{yyyy-MM-dd}.log</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} ${PID:- } [${springAppName:-},%X{X-B3-TraceId:-},%X{X-B3-SpanId:-},%X{X-Span-Export:-}] %-5level [%thread] %logger{36} - %msg%n</pattern>
            <charset>utf8</charset>
        </encoder>
    </appender>

    <!-- Appender to log to file in a JSON format -->
    <appender name="logstash" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${LOG_PATH}/json/${springAppName}.%d{yyyy-MM-dd}.json</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder class="net.logstash.logback.encoder.LoggingEventCompositeJsonEncoder">
            <providers>
                <timestamp>
                    <timeZone>UTC</timeZone>
                </timestamp>
                <pattern>
                    <pattern>
                        {
                        "severity": "%level",
                        "service": "${springAppName:-}",
                        "trace": "%X{X-B3-TraceId:-}",
                        "span": "%X{X-B3-SpanId:-}",
                        "exportable": "%X{X-Span-Export:-}",
                        "pid": "${PID:-}",
                        "thread": "%thread",
                        "class": "%logger{40}",
                        "rest": "%message"
                        }
                    </pattern>
                </pattern>
            </providers>
        </encoder>
    </appender>

    <root level="INFO">
        <appender-ref ref="console"/>
        <appender-ref ref="logstash"/>
        <appender-ref ref="flatfile"/>
    </root>

</configuration>
```


## 数据库管理
具体配置：

```
 datasource:
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://118.178.238.210:3306/td_business?autoCommit=false&useUnicode=true&characterEncoding=utf-8
    username: td_business
    password: td_business!@#123456
    hikari:
      connection-test-query: SELECT 1
      maximum-pool-size: 30
      minimum-idle: 3
      max-lifetime: 60000
      validation-timeout: 60000
      idle-timeout: 60000    
```

## dubbo + zookeeper + 缓存管理
具体配置：

```
spring:
  dubbo:
    scan: org.hopeframework.biz.api
    application:
      name: hope-biz-api
      owner: hope
    registry:
      address: zookeeper://114.55.30.32:2181
    protocol:
          name: dubbo
          port: 20881
    provider:
      timeout: 120000
      retries: -1
  redis:
    host: 114.55.30.32
    port: 34587
    password: tuodao!@#321
    database: 1
    pool:
      max-wait: 50
```

## mybatis 管理
具体配置：

```
mybatis:
  mapper-locations: classpath*:xml/**/*Mapper.xml
```


## 流程介绍

```
graph LR

httpRequest-->API
API-->Controller
Controller-->Service
Service-->cache(db)

```
## 应答响应

```
sequenceDiagram
httpRequest->>API: httprestful
API->>Controller: validate
Controller->>Service: logic!
Service->>cache(db): operate data!
cache(db)->>Service: return data!
Service->>Controller: return data!
Controller->>API: return data!
API->>httpRequest: return data!
```


