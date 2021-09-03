package com.liquor.japidocsdemo.config;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author: Liquor.Huang
 * @Date 2021/9/3 16:46
 */
@Configuration
public class JApiDocsConfig {

    @Bean
    public DocsConfig getDocsConfig(){
        DocsConfig docsConfig = new DocsConfig();
        //项目根目录
        docsConfig.setProjectPath("G:\\Program\\project\\data-resources\\java\\docs\\JApiDocs\\japidocs-demo");
        //项目名字
        docsConfig.setProjectName("JApiDocs-demo");
        //声明该API版本
        docsConfig.setApiVersion("0.0.1");
        //生成API 所在文档目录
        docsConfig.setDocsPath("G:\\docs");
        //配置自动生成
        docsConfig.setAutoGenerate(Boolean.TRUE);
        //执行生成文档
        Docs.buildHtmlDocs(docsConfig);
        return docsConfig;
    }
}
