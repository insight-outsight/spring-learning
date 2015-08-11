package org.springlearning.web.controller.avdice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springlearning.model.result.ActivityResponse;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;


@Order(1)
@ControllerAdvice(basePackages="org.springlearning.web.controller")
public class AwardAdvice implements ResponseBodyAdvice<ActivityResponse> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        logger.debug("support2 determined3 apply controller advice ");

    	return methodParameter.getMethod().getReturnType().isAssignableFrom(ActivityResponse.class);
    }

    @Override
    public ActivityResponse beforeBodyWrite(
    		ActivityResponse activityResponse, MethodParameter methodParameter, MediaType mediaType,
            Class<? extends HttpMessageConverter<?>> converterType,
            ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        logger.debug("apply2 controller3 advice {}",activityResponse.toString());
        return activityResponse;
        
    }
}