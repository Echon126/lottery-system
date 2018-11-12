@Configuration
用于定义配置类，可替换xml配置文件备注街的类内部包含一个或者说个被@Bena注解的方法，
这些方法将被AnnotatinConfigApplicationContext或者AnnotationConfigWebApplicationContext类进行扫描，
并拥有构建Bean定义，初始化Spring容器。

@Configuration中所有带着@Bean注解的方法都会被动态代理，因此调用该方法返回的都是同一个实例。

注意事项
1.@Configuration不可以是final类型
2.@Configuration不可以是匿名类
3.嵌套的configurtion必须是静态类


二、@Configuration加载Spring方法
@Configuration配置在spring并启动Spring容器
@Configuration标注在类上面，相当于把该类作为spring的xml配置文件中的<beans>作用为：配置spring容器（应用上下文）。

```java
   package com.dxz.demo.configuration;
   
   import org.springframework.context.annotation.Configuration;
   
   @Configuration
   public class TestConfiguration {
       public TestConfiguration() {
           System.out.println("TestConfiguration容器启动初始化。。。");
       }
   }
```  
类似
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context" xmlns:jdbc="http://www.springframework.org/schema/jdbc"  
    xmlns:jee="http://www.springframework.org/schema/jee" xmlns:tx="http://www.springframework.org/schema/tx"
    xmlns:util="http://www.springframework.org/schema/util" xmlns:task="http://www.springframework.org/schema/task" xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd
        http://www.springframework.org/schema/jdbc http://www.springframework.org/schema/jdbc/spring-jdbc-4.0.xsd
        http://www.springframework.org/schema/jee http://www.springframework.org/schema/jee/spring-jee-4.0.xsd
        http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.0.xsd
        http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-4.0.xsd
        http://www.springframework.org/schema/task http://www.springframework.org/schema/task/spring-task-4.0.xsd" default-lazy-init="false">


</beans>

三、@Configuration启动容器+@Component注册Bean
```java
package com.dxz.demo.configuration;

import org.springframework.stereotype.Component;

//添加注册bean的注解
@Component
public class TestBean {

    private String username;
    private String url;
    private String password;

    public void sayHello() {
        System.out.println("TestBean sayHello...");
    }

    public String toString() {
        return "username:" + this.username + ",url:" + this.url + ",password:" + this.password;
    }

    public void start() {
        System.out.println("TestBean 初始化。。。");
    }

    public void cleanUp() {
        System.out.println("TestBean 销毁。。。");
    }
}

```
```java
package com.dxz.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
//添加自动扫描注解，basePackages为TestBean包路径
@ComponentScan(basePackages = "com.dxz.demo.configuration")
public class TestConfiguration {
    public TestConfiguration() {
        System.out.println("TestConfiguration容器启动初始化。。。");
    }

    /*// @Bean注解注册bean,同时可以指定初始化和销毁方法
    // @Bean(name="testNean",initMethod="start",destroyMethod="cleanUp")
    @Bean
    @Scope("prototype")
    public TestBean testBean() {
        return new TestBean();
    }*/
}

```
四、如何处理@Configuration注释的类
在Spring容器启动的时候，会加载一些默认的PostPRocessor，其中就有ConfigurationClassPostProcessor，
这个后置处理程序专门处理带有 @Configuration 注解的类，这个程序会在 bean 定义加载完成后，在 bean 初始化前进行处理，
主要处理的过程就是使用 cglib 动态代理增强类，而且是对其中带有 @Bean 注解的方法进行处理。