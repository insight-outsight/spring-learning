package org.springlearning.web.controller.avdice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;
import org.springlearning.model.result.ActivityResponse;

//@Order(2)
//@ControllerAdvice(basePackages = "org.springlearning.web.controller")
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
    public JsonpAdvice() {
        super("callback", "jsonp"); //指定jsonpParameterNames
    	logger.debug("jsonp advice instantiated..................");
    }
    
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        logger.debug("support jsonp advice ");

    	return methodParameter.getMethod().getReturnType().isAssignableFrom(ActivityResponse.class);
    }
    
    
}