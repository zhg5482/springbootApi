package com.example.demo.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

@Slf4j
public class ControllerApiRequestMappingHandlerMapping extends RequestMappingHandlerMapping{
    /**
     * 是否打印mapping
     */
    private boolean logMappingPath = true;

    public ControllerApiRequestMappingHandlerMapping(boolean logMappingPath) {
        this.logMappingPath = logMappingPath;
    }

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo mappingInfo = super.getMappingForMethod(method, handlerType);
        Class<?> superClass = handlerType.getSuperclass();
        mappingInfo = appendParentRequestMapping(superClass,mappingInfo);
        logMapping(mappingInfo);
        return mappingInfo;
    }

    /**
     * 添加夫类的mapping
     * @param handlerType
     * @param mappingInfo
     * @return
     */
    protected RequestMappingInfo appendParentRequestMapping(Class<?> handlerType,RequestMappingInfo mappingInfo) {
        if(handlerType==null) {
            return mappingInfo;
        }
        RequestMapping parentRequestMapping = handlerType.getAnnotation(RequestMapping.class);
        if(parentRequestMapping!=null&&parentRequestMapping.value().length>0) {
            //使用path工具向前追加夫类的path
            mappingInfo = RequestMappingInfo.paths(parentRequestMapping.value()).build().combine(mappingInfo);
        }
        return appendParentRequestMapping(handlerType.getSuperclass(),mappingInfo);
    }

    /**
     * 由于spring boot2不打印mapping了，不习惯，就自己打印一下，但是有些系统mapping也不打印，有空再研究怎么打印
     * @param info
     */
    private void logMapping(RequestMappingInfo info) {
        if(!logMappingPath||info==null) {
            return;
        }
        //此处等价Logger.info
        log.info("mapping path:"+info.toString());
    }

}
