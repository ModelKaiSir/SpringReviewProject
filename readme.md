# spring mvc 复习项目
## web.xml
指定从xml文件中获取spring配置  
content-param -> contextConfigLocation  
> 指定xml文件资源地址    

指定从配置类中获取spring配置
content-param -> contextClass  
> 设置applicationContext为AnnotationConfigWebApplicationContext  
    
content-param -> contextConfigLocation  
> 指定配置类路径
#### IOC
##### Resource文件资源
Resource代表一个文件资源，根据不同实现可以分为ClassPathResource、FileSystemResource、UrlResource等。  
不同实现寻找文件的方式不同。  

使用ResourceLoader通过文件地址表达式和ant表达式来获取文件资源。  

**文件地址表达式：**
- classpath: -> ClassPathResource
- file: -> FileResource
- http:// -> UrlResource
- ftp:// -> UrlResource  

**ant表达式在文件地址表达式基础上可以使用通配符：**  
- ?  匹配一个字符，比如classpath:com/t?st.xml
- \* 匹配任意字符，比如classpath:com/*.xml
- ** 匹配路径下所有包含的字符 比如classpath:com/**/test.xml，com子目录及其子孙目录下的test.xml都会被找到。  

**tips: 读取文件内容时，尽量使用流的方式。以免在jar中无法使用getFile方法。**  

##### BeanFactory Bean工厂
**beanFactory**  
ResourcePatternResolver + DefaultListableBeanFactory获得BeanFactory  

```
ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
Resource resource = resolver.getResource("classpath:beans.xml");
DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
reader.loadBeanDefinitions(resource);
```  

**ApplicationContext**  
ApplicationContext由beanFactory派生而来  
 
**BeanFactory中bean的生命周期(bean级别)：**  
- ② 构造函数
- ⑤ 设置bean的属性值
- setBeanName (实现BeanNameAware)
- setBeanFactory (实现BeanFactoryAware)
- afterPropertiesSet (实现InitializingBean)
- init (bean配置中指定init-method)
- destroy (实现DisposableBean)
- custom destroy (bean配置中指定destroy-method)  

**容器级别的生命周期**  

装载容器级别生命周期处理器，继承InstantiationAwareBeanPostProcessorAdapter和实现BeanPostProcessor  
- ① 在getBean之前会触发before Instance
- ③ bean实例化后会触发after instance
- ④ 在设置bean属性之前会触发property values
- 在bean的afterPropertiesSet之前触发before init，然后触发BeanPostProcessor的before init
- 在bean的afterPropertiesSet之后触发after init，然后触发BeanPostProcessor的after init
- 继续bean剩下的生命周期

> Spring中通常使用注解@PostConstruct和@Destroy来处理初始化和销毁这两个生命周期步骤，效果相同。 来自InitDestroyAnnotationBeanPostProcessor装配器 ApplicationContext默认装配  

**ApplicationContext中Bean的生命周期**  

在ApplicationContext中Bean的生命周期基本一致，context还提供ApplicationContextAware接口，可以获得applicationContext实例。setApplicationContext()

**bean的作用域：**  
- singleton 单例 (默认)
- prototype 每次获取都返回一个新实例
- request 在一次请求中存活
- session 在一个会话期间存活，不同会话使用不同的bean
- globalSession 在一个全局Session中共享  

**Spring加载配置创建Bean的流程，工作机制**  

ResourceLoader加载spring配置文件，得到表示spring配置文件的Resource。  
BeanDefinitionReader解析配置文件，每一个bean的信息会被解析成BeanDefinition对象，保存到BeanDefinitionRegistry中。  
BeanDefinitionRegistry对BeanDefinition进行加工，通过PropertyEditorRegistry注册自定义的属性编辑器。  
最后通过InstantiationStrategy和BeanWrapper来实例化Bean并设置Bean属性。  

**Spring的MessageResource**
MessageResource可以用于国际化。通过java的MessageFormat来格式化内容。  
ResourceBundleMessageSource是messageResource的标准实现，仅需要使用getMessage()方法来实现国际化。  
ReloadableResourceBundleMessageSource支持定时刷新资源文件，通过属性cacheSeconds来指定刷新周期，单位秒。  
ApplicationContext本身也实现了MessageResource接口，只需要注册一个MessageResource的Bean，context会自动加载这个bean到context本身，即可实现容器级别的国际化。  

**Spring的事件机制**  

事件ApplicationEvent  
- ApplicationContextEvent
    - ContextCloseEvent
    - ContextRefreshEvent
    - ContextStartEvent
    - ContextStopEvent
- RequestHandleEvent
    - ServletRequestHandleEvent
- 自定义事件，继承ApplicationEvent  

监听器ApplicationListener  

事件广播器ApplicationEventMulticaster，装配实现了ApplicationEventMulticaster的bean，即可实现自定义事件广播器。  

事件由容器统一管理，要产生事件，需要通过context.pushEvent(event)方法。
#### AOP

aop由切点和增强来描述在哪里进行aop和具体逻辑，一个完整的切面是包含了切点和增强信息的。  

切点 Pointcut  
增强 Advice