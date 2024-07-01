package com.reactive.router;

import com.reactive.handler.DepartmentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
public class DepartmentRouter {

    @Autowired
    private DepartmentHandler departmentHandler;

    @Bean
    public RouterFunction<ServerResponse> departmentRouterFunction() {
        return RouterFunctions.route()
                .GET("/dept/{departmentId}", departmentHandler::getDepartmentById)
                .GET("/dept", departmentHandler::getAllDepartments)
                .build();
    }
}
