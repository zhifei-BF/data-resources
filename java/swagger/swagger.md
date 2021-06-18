# Swagger介绍

官网：https://swagger.io/

前后端分离开发模式中，api文档是最好的沟通方式。

Swagger 是一个规范和完整的框架，用于生成、描述、调用和可视化 RESTful 风格的 Web 服务。

优点：

- 及时性 (接口变更后，能够及时准确地通知相关前后端开发人员)

- 规范性 (并且保证接口的规范性，如接口的地址，请求方式，参数及响应格式和错误信息)

- 一致性 (接口信息一致，不会出现因开发人员拿到的文档版本不一致，而出现分歧)

- 可测性 (直接在接口文档上进行测试，以方便理解业务)

# 配置Swagger

项目导入相关的依赖：

```xml
<!--swagger-->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>
```

创建swagger配置类：

```java
package com.atguigu.guli.service.base.config;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket webApiConfig(){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                //只显示api路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/api/.*")))
                .build();

    }

    @Bean
    public Docket adminApiConfig(){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi")
                .apiInfo(adminApiInfo())
                .select()
                //只显示admin路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/admin/.*")))
                .build();

    }

    private ApiInfo webApiInfo(){

        return new ApiInfoBuilder()
                .title("网站-API文档")
                .description("本文档描述了网站微服务接口定义")
                .version("1.0")
                .contact(new Contact("Helen", "http://atguigu.com", "55317332@qq.com"))
                .build();
    }

    private ApiInfo adminApiInfo(){

        return new ApiInfoBuilder()
                .title("后台管理系统-API文档")
                .description("本文档描述了后台管理系统微服务接口定义")
                .version("1.0")
                .contact(new Contact("Helen", "http://atguigu.com", "55317332@qq.com"))
                .build();
    }
}
```

重启，浏览器访问：http://ip:port/swagger-ui.html

# 常见注解

1. API模型

   entity的实体类中可以添加一些自定义设置，例如：

   定义样例数据

   ```java
   @ApiModelProperty(value = "入驻时间", example = "2010-01-01")
   @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
   private Date joinDate;
   
   @ApiModelProperty(value = "创建时间", example = "2019-01-01 8:00:00")
   @TableField(fill = FieldFill.INSERT)
   private Date gmtCreate;
   
   @ApiModelProperty(value = "更新时间", example = "2019-01-01 8:00:00")
   @TableField(fill = FieldFill.INSERT_UPDATE)
   private Date gmtModified;
   ```

2. 定义接口说明和参数说明

   定义在类上：@Api

   定义在方法上：@ApiOperation

   定义在参数上：@ApiParam

   ```java
   @Api(tags = "讲师管理")
   @RestController
   @RequestMapping("/admin/edu/teacher")
   public class TeacherController {
   
       @Autowired
       private TeacherService teacherService;
   
       @ApiOperation("所有讲师列表")
       @GetMapping("list")
       public List<Teacher> list(){
           return teacherService.list();
       }
   
       @ApiOperation(value = "根据ID删除讲师", notes = "根据ID删除讲师")
       @DeleteMapping("remove/{id}")
       public boolean removeById(@ApiParam(value = "讲师ID", required = true) @PathVariable String id){
           return teacherService.removeById(id);
       }
   }
   ```

   