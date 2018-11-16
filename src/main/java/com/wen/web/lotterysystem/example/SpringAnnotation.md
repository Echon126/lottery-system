###注解(Annotation)提供了一种安全的类似注解的机制，为我们在代码中添加信息提供了一种形式化的方法，使我们可以再稍某个时刻
方便的使用这些数据，用来将任何的信息或者元数据与程序元素进行关联，其实就是更加直观更加明了的说明，这些说明信息与程序业务没有关系
并且是提供指定的工具或者框架使用的，
Annotation像是一种修饰符一样，应用于包，类型、构造器方法、方法、成员变量、参数及本地变量的生命语句中。

Spring注解原理的详细剖析与实现
https://blog.csdn.net/u010987379/article/details/52152795













### @Configuration注解
用于定义配置类，可替换xml配置文件备注街的类内部包含一个或者说个被@Bena注解的方法，
这些方法将被AnnotatinConfigApplicationContext或者AnnotationConfigWebApplicationContext类进行扫描，
并拥有构建Bean定义，初始化Spring容器。

@Configuration中所有带着@Bean注解的方法都会被动态代理，因此调用该方法返回的都是同一个实例。

注意事项
1.@Configuration不可以是final类型
2.@Configuration不可以是匿名类
3.嵌套的configurtion必须是静态类


二、@Configuration加载Spring方法
@Configuration配置spring并启动Spring容器
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




### @Import注解
1.从另一配置中加载@Bean定义
2.把用到的Bean导入到IOC容器中
3.在java-base-config中的作用
 可以合并多个@Configuration注解配置的类来简化容器的实例化
