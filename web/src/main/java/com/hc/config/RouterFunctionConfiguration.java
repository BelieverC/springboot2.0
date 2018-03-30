package com.hc.config;

import com.hc.domain.User;
import com.hc.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import java.util.Collection;


/**
 * 路由器函数配置，可以理解成一个java版的xml配置
 */
@Configuration
public class RouterFunctionConfiguration {

    /**
     * Servlet
     * 请求接口：ServletRequest或者HttpServletRequest
     * 相应接口：ServletReponse或者HttpServletReponse
     * Spring 5.0 重新定义服务请求和相应接口
     * 请求接口：ServerRequest
     * 相应接口：ServerResponse
     * 即可支持Servlet，也可支持自定义，比如Netty(Web Server)
     * 以本例：
     * 定义GET请求，返回所有用户对象
     * Flux是0-N个对象集合
     * Mono是0-1个对象集合
     * Reactive中的Flux和Mono异步处理(非堵塞)
     * 集合对象上的处理基本是同步处理(堵塞)
     * Flux和Mono都是一个publisher
     */
    @Bean
    @Autowired
    public RouterFunction<ServerResponse> findAllUser(UserRespository userRespository){
        return RouterFunctions.route(RequestPredicates.GET("/user/findAll"),request->{
            Collection<User> users = userRespository.findAll();
            Flux<User> userFlux = Flux.fromIterable(users);
            return ServerResponse.ok().body(userFlux,User.class);
        });
    }
}
