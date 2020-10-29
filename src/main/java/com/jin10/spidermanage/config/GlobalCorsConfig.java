package com.jin10.spidermanage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置全局跨域请求
 *
 */
@Configuration
public class GlobalCorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")    //添加映射路径，“/**”表示对所有的路径实行全局跨域访问权限的设置
                        .allowedOrigins("http://admins.jin10.com", "*")    //开放哪些ip、端口、域名的访问权限
                        .allowCredentials(true)  //是否允许发送Cookie信息
                        .allowedMethods("GET","POST", "PUT", "DELETE")     //开放哪些Http方法，允许跨域访问
                        .allowedHeaders("*", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")     //允许HTTP请求中的携带哪些Header信息
                        .exposedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", "Access-Control-Allow-Origin","Access-Control-Allow-Credentials");   //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）

            }
        };
    }

}
