package com.example.demo.config;

import com.example.demo.handler.ControllerApiRequestMappingHandlerMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


@Configuration
//这个注解很重要啊，不写不生效
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebMvcConfiguration implements WebMvcRegistrations{
    //是否打印mapping？2.0不自己打印了，可以通过mapping.showpath配置控制
    @Value("${mapping.showpath:true}")
    private boolean showMappingPath;

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new ControllerApiRequestMappingHandlerMapping(showMappingPath);
    }

}
