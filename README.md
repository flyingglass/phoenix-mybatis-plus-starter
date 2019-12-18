# phoenix-mybatis-plus-starter

## 简介
该项目主要利用SpringBoot的自动化配置特性来实现快速的将Mybatis-Plus + Phoenix引入Springboot项目中，简化原生Mybatis-Plus整合Phoenix使用。

- [完整使用样例Demo参考](https://github.com/FlyingGlass/backend-starter-mybatis-demo)

欢迎使用和star支持，如使用中碰到问题，可以提出Issue，我会尽力完善该Starter

### 版本基础
- Mybatis-Plus: 3.2.0
- Spring-Boot: 2.2.2.RELEASE

### Quick-Start

轻松引入SpringBoot工程中，需要如下步骤:

- 在`pom.xml`中引入依赖:
```xml
<dependency>
    <groupId>com.github.flyingglass</groupId>
    <artifactId>phoenix-mybatis-plus-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

- 在应用主类中增加`@MapperScan(basePackages = "com.xxx.mapper")`注解，用于扫描`Mapper`的`Interface`，并且排除`DruidDataSourceAutoConfigure`

```java
@MapperScan(basePackages = "com.xxx.mapper")
@SpringBootApplication(
        exclude = DruidDataSourceAutoConfigure.class
)
public class Bootstrap {

    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }

}
```

- 配置`application.yml`


#### 配置`phoenix`数据源，已针对`phoenix-core`进行`shaded repackage`解决`Springboot`兼容问题
- 配置`pom.xml`
```xml
<dependency>
    <groupId>com.github.flyingglass</groupId>
    <artifactId>phoenix-core-shaded</artifactId>
    <version>1.0.0</version>
    <exclusions>
        <exclusion>
            <groupId>org.apache.phoenix</groupId>
            <artifactId>phoenix-core</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

- 配置`application.yml`
```yml
spring:
  datasource:
    dynamic:
      primary: master
      datasource:
        master:
          username:
          password:
          url: jdbc:phoenix:znode01,znode02,znode03:2181
          driver-class-name: org.apache.phoenix.jdbc.PhoenixDriver
          druid:
            filters: stat
            connection-properties:
              schema: "\"TEST\""
```

#### 自定义特性
- 通过`Mybatis-plus`的`SqlInjector`注入`PhoenixUpsert`的`upsert`方法适应`phoenix`插入方法
- 自定义`IPhoenixService`，用户的`Service`继承自`PhoenixServiceImpl`即可复用`Mybatis-plus`的`ServiceImpl<M, T>`所有方法
- 关于`Phoenix`和`HBase`的版本兼容问题，对于包冲突，需要额外`shaded repackage`，参考`https://github.com/FlyingGlass/hbase-phoenix-shaded`
