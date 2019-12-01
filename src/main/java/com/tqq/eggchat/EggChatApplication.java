package com.tqq.eggchat;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Description:  蛋蛋聊天室后台启动类
 * @Author: Tan
 * @Date: 2019/11/30
 **/
@MapperScan("com.tqq.eggchat.dao")
@SpringBootApplication
public class EggChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(EggChatApplication.class, args);
    }

    /**
     * @Description: 启用mybatis plus 分页插件支持
     * @Author: Tan
     * @Date: 2019/11/30
     * @return: com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
     **/
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * @Description: 启用websocket支持
     * @Author: Tan
     * @Date: 2019/11/20
     * @return: org.springframework.web.socket.server.standard.ServerEndpointExporter
     **/
  @Bean
  public ServerEndpointExporter serverEndpoint(){
      return new ServerEndpointExporter();
    }


}
