package com.liquor.springdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.OrderComparator;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@SpringBootTest
class SpringDemoApplicationTests {

    @Test
    void contextLoads() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        beanDefinition.setBeanClass(User.class);
        //设置懒加载
        beanDefinition.setLazyInit(true);
        //设置初始化方法
        beanDefinition.setInitMethodName("init");
        //设置作用域
        beanDefinition.setScope("singleton");
        //设置销毁方法
        beanDefinition.setDestroyMethodName("destroy");
        //注册到spring容器
        applicationContext.registerBeanDefinition("user", beanDefinition);

        System.out.println(applicationContext.getBean("user"));

        User user = (User) applicationContext.getBean("user");
        //销毁bean
        user.destroy();
    }

    @Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        //BeanDefinition读取器
        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(context);
        annotatedBeanDefinitionReader.register(User.class);
        System.out.println(context.getBean("user"));
    }

    @Test
    public void test2() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(annotationConfigApplicationContext);
        xmlBeanDefinitionReader.loadBeanDefinitions("spring.xml");
        System.out.println(annotationConfigApplicationContext.getBean("user"));
    }

    @Test
    public void test3() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //扫描器
        ClassPathBeanDefinitionScanner classPathBeanDefinitionScanner = new ClassPathBeanDefinitionScanner(annotationConfigApplicationContext);
        classPathBeanDefinitionScanner.scan("com.liquor.springdemo");
        System.out.println(annotationConfigApplicationContext.getBean("userService"));
    }

    @Test
    public void test4() {
        //BeanFactory,ApplicationContext也是一个BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        beanDefinition.setBeanClass(User.class);
        beanFactory.registerBeanDefinition("user", beanDefinition);
        System.out.println(beanFactory.getBean("user"));
    }

    @Test
    public void test5() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //国际化
        String message = applicationContext.getMessage("test", null, new Locale("en_CN"));
        System.out.println(message);
    }

    @Test
    public void test6() throws IOException {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        Resource resource = annotationConfigApplicationContext.getResource("file://G:\\Program\\project\\data-resources\\java\\spring\\spring-demo\\src\\main\\java\\com\\liquor\\springdemo\\User.java");
        System.out.println(resource.contentLength());

        Resource resource1 = annotationConfigApplicationContext.getResource("http://www.baidu.com");
        System.out.println(resource1.contentLength());

        Resource resource2 = annotationConfigApplicationContext.getResource("classpath:spring.xml");
        System.out.println(resource2.contentLength());

        Resource[] resources = annotationConfigApplicationContext.getResources("classpath:com/liquor/springdemo/*.class");
        for (Resource resource3 : resources) {
            System.out.println(resource3.contentLength());
            System.out.println(resource3.getFilename());
        }
    }

    @Test
    public void test7() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Map<String, Object> systemEnvironment = context.getEnvironment().getSystemEnvironment();
        System.out.println(systemEnvironment);
        System.out.println("============");
        Map<String, Object> systemProperties = context.getEnvironment().getSystemProperties();
        System.out.println(systemProperties);
        System.out.println("==============");
        MutablePropertySources propertySources = context.getEnvironment().getPropertySources();
        System.out.println(propertySources);
        System.out.println("===============");

        System.out.println(context.getEnvironment().getProperty("NO_PROXY"));
        System.out.println(context.getEnvironment().getProperty("sun.jnu.encoding"));
        System.out.println(context.getEnvironment().getProperty("port"));
    }

    @Test
    public void test8() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        //发布事件
        context.publishEvent("kkkk");
    }

    @Test
    public void test9() {
        StringToUserPropertyEditor propertyEditor = new StringToUserPropertyEditor();
        propertyEditor.setAsText("1");
        User value = (User) propertyEditor.getValue();
        System.out.println(value);

    }

    @Test
    public void test10() {
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new StringToUserConverter());
        User value = conversionService.convert("1", User.class);
        System.out.println(value);
    }

    @Test
    public void test11() {
        SimpleTypeConverter typeConverter = new SimpleTypeConverter();
        typeConverter.registerCustomEditor(User.class, new StringToUserPropertyEditor());
        //typeConverter.setConversionService(conversionService);
        User value = typeConverter.convertIfNecessary("1", User.class);
        System.out.println(value);
        System.out.println(value.getName());

    }

    @Test
    public void test12() {
        A a = new A(); // order=3
        B b = new B(); // order=2
        OrderComparator comparator = new OrderComparator();
        System.out.println(comparator.compare(a, b)); // 1
        List list = new ArrayList<>();
        list.add(a);
        list.add(b);
        // 按order值升序排序
        list.sort(comparator);
        System.out.println(list); // B，A
    }

    @Test
    public void test13() {
        C c = new C();
        D d = new D();
        AnnotationAwareOrderComparator comparator = new AnnotationAwareOrderComparator();
        System.out.println(comparator.compare(c, d)); // 1
        List list = new ArrayList<>();
        list.add(c);
        list.add(d);
        // 按order值升序排序
        list.sort(comparator);
        System.out.println(list); // D,C
    }

    @Test
    public void test14() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig2.class);
        UserService userService = annotationConfigApplicationContext.getBean("userService", UserService.class);
        System.out.println(userService);
    }

    @Test
    void test15() throws IOException {
        SimpleMetadataReaderFactory simpleMetadataReaderFactory = new
                SimpleMetadataReaderFactory();
        // 构造一个MetadataReader
        MetadataReader metadataReader =
                simpleMetadataReaderFactory.getMetadataReader("com.liquor.springdemo.UserService");
        // 得到一个ClassMetadata，并获取了类名
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        System.out.println(classMetadata.getClassName());
        // 获取一个AnnotationMetadata，并获取类上的注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        for (String annotationType : annotationMetadata.getAnnotationTypes()) {
            System.out.println(annotationType);
        }
    }
}
