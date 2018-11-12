## Security
 ### WebSecurityConfigurerAdapter配置中心
 
## SecurityContextHolder
   #### 是最基本的对象，负责存储当前安全上下文的信息，即保存这当前用户是什么，是否已经通过认证，拥有哪些权限，etc.
   #### securityContextHolder 默认使用ThreadLocal策略来存储认证信息，意味着这是一种与线程绑定的策略.
   #### 获取当前用户的信息 
   ```Java
   public class GetContextHolder{
    public Object getPrincipal(){
         Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         if(principal instanceof UserDetails){
             String userName = ((UserDetails)principal).getUsername();
         }else{
             String userName = principal.toString();
         }
    }
   }

   ```
## Authentication 
    继承与java.security包的Principal接口，而本身是Spring.Security包中的接口，也就是说，Authentication是Security中最高界别的认证
    getCredentials：密码信息，由用户输入的密码凭证，认证之后会移除来保证安全性。
    getDetails：细节信息，web应用中一般是访问者的ip地址和sessionid
    getPrincipal：最重要的身份信息，一般返回UserDetails的实现类
    getAuthorities：权限列表，通常是代表权限的字符串列表
    
## UserDetails 和UserDetailsService
    UserDetails 接口代表了最相信的用户信息，这个接口涵盖了一些必要的用户信息字段，具体的实现类对他进行扩展。
    该接口与Authentication类似，都包含了用户名、密码以及权限信息，而区别是Authentication中的getCredenntials来源于用户提交的密码凭证，
    而UserDetails中的getPasword取到是用户正确的密码信息，认证的第一步就是比较两者是否相同，除此之外，
    Authentication中的getAuthorities是认证用户名和密码成功之后，由UserDetails中的getAuthorities传递而来。
    而Authentication中的getDetails信息是经过了AuthenticationProvider认证之后填充的。
    
    
## UserDetailsService
   ```Java
            public interface UserDetailsService{
                UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
             }
             
   ```
   该接口只有一个方法，就是从特定的地方(一般是数据库)中加载用户信息，仅此而已，常用的实现类由JdbcDaoImpl和InMermoryUserDetailsManager,
   前者是从数据库中加载信心，后者是从内存中，这两者都满足不了是可是自己实现UserDetailsService
   
##AuthenticationManager
  ```java
    public interface AuthenticationManager{
        Authentication authenticate(Autentication autentication) throws AuthenticationException;
    }
  ```
  接口描述：该接口是全局唯一身份管理器，AuthenticatonManager是一个用来处理认证(Authentication)的接口，其中只定义了一个方法
  authenticate(),该方法只接受一个代表认证请求的Authenticationdui对象作为参数，如果认证成功，则会返回一个封装了当前用户权限
  等信息的Authentication对象作为返回。 
  
  该接口只包含一个方法，那就是认证，它是认证相关的核心接口，也是发起认证的出发点。实际的业务中可能根据不同的信息进行认证
  所以可以通过实现该接口来自定义自己的认证方式，spring提供了默认的实现，ProviderManager.
   
  ProviderManager与认证的相关代码：
  ```java
       public class ProviderManager implements AuthenticationManager,MessageSourceAware,InitializingBean{
        // 维护一个AuthenticationProvider列表 
        private List<AuthenticationProvider>providers = Collectors.emptyList();
            
            
        public Authentication authenticate(Authentication authentication) throws  AuthenticationException{
            Class<? extends Authentication>toTest = authentication.getClass();
            AuthenticationException lastException = null;
            Autentication result =null;
            
            for(AUthenticationProvider provider :getProviders()){
                if(!provider.supports(toTest)){
                    continue;
                }
                
                try {
                         result = provider.authenticate(authentication);
                
                             if (result != null) {
                                copyDetails(authentication, result);
                                break;
                             }
                          } catch (AuthenticationException e) {
                             lastException = e;
                          }
              
            }
        }
        // 如果有Authentication信息，则直接返回
               if (result != null) {
                    if (eraseCredentialsAfterAuthentication
                            && (result instanceof CredentialsContainer)) {
                         //移除密码
                        ((CredentialsContainer) result).eraseCredentials();
                    }
                    //发布登录成功事件
                    eventPublisher.publishAuthenticationSuccess(result);
                    return result;
               }
                
               //执行到此，说明没有认证成功，包装异常信息
               if (lastException == null) {
                  lastException = new ProviderNotFoundException(messages.getMessage(
                        "ProviderManager.providerNotFound",
                        new Object[] { toTest.getName() },
                        "No AuthenticationProvider found for {0}"));
               }
               prepareException(lastException, authentication);
               throw lastException;
            }
        
       }
  ```  
其实ProviderManager不是自己处理身份验证请求，它将委托给配置的AuthenticationProvider列表，
按照顺序进行依次认证，每个provider都会尝试认证，或者通过简单地返回null来跳过验证。如果所有实现都返回null，
那么ProviderManager将抛出一个ProviderNotFoundException。
 
 
##AuthenticationProvider
```java
public interface AuthenticationProvider {
    Authentication authenticate(Authentication var1) throws AuthenticationException;

    boolean supports(Class<?> var1);
}
```  
AuthenticationProvider接口提供了两个方法，一个是真正的认证，另一个是满足什么样的身份信息才进行如上认证。

这里我们基于最常用的DaoAuthenticationProvider来详细解释一下：
```java
public class DaoAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    //密码加解密算法
    private PasswordEncoder passwordEncoder;
    //用户信息dao
    private UserDetailsService userDetailsService;

    //检查用户名和密码是否匹配
    protected void additionalAuthenticationChecks(UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"));
        }
        //用户提交的密码凭证
        String presentedPassword = authentication.getCredentials().toString();
        //比较两个密码
        if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
                throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"));
        }
    }


    //获取用户信息
    protected final UserDetails retrieveUser(String username,
            UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
            UserDetails loadedUser = this.getUserDetailsService().loadUserByUsername(username);
    }
}

``` 
在Spring Security中。提交的用户名和密码，被封装成了UsernamePasswordAuthenticationToken，
而根据用户名加载用户的任务则是交给了UserDetailsService，在DaoAuthenticationProvider中，
对应的方法便是retrieveUser，虽然有两个参数，但是retrieveUser只有第一个参数起主要作用，返回一个UserDetails。
还需要完成UsernamePasswordAuthenticationToken和UserDetails密码的比对，
这便是交给additionalAuthenticationChecks方法完成的，如果这个void方法没有抛异常，则认为比对成功。
比对密码的过程，用到了PasswordEncoder和SaltSource。



#### SpringSecurity 中的过滤器分析


     
     
     
     
     
     
     
     
     
     
     
     
#####@Configuration 注解
用于定义配置类，可替换XML配置文件，被注解的类内部包括了一个或者更多个被@Bean注解的方法，这些方法将被AnnotationConfigApplicationContext
或者AnnotationConfigWebApplicationContext类进行扫描并用于构建bean定义，初始化为Spring容器 

当@Configuration类作为输入提供时，@Configuration类本身被注册为bean定义，类中所有声明的@Bean方法也被注册为bean定义。 

     
     
package org.springframework.web;  
public interface WebApplicationInitializer {
    void onStartup(ServletContext var1) throws ServletException;
}
    
     
     
     
     
   
   
Spring容器启动简述
WebApplicationContext需要ServletContext实例，也是是说必须在拥有Web容器的前提下才能完成启动工作。
Spring提供了用于启动WebApplicationContext的Servlet和Web容器的监听器
      org.springframework.web.context.ContextLoaderServlet；
      org.springframework.web.context.ContextLoaderListener；
     
Spring内嵌了两种基础DI容器，即Beanfactory 和ApplicationContext(ApplicationContext) 
(1)BeanFactory主要用于内存、cpu资源受限的场合，比如Applet,手持设备
(2)https://img-blog.csdn.net/20140124171918109?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvY2hlbnh1ZWd1aTEyMzQ=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center


     
     
     
     
     
     
     
     
    
    
    
    
    
    
    
    
    
    
    
    