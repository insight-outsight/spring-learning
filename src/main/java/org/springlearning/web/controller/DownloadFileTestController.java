package org.springlearning.web.controller;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * 测试springMVC下载文件相关技术
 * 
 * 
 * <mvc:message-converters register-defaults="true">
		   	<bean class="org.springframework.http.converter.ByteArrayHttpMessageConverter"/> 
			<bean class="org.springframework.http.converter.StringHttpMessageConverter"/> 
			<bean class="org.springframework.http.converter.ResourceHttpMessageConverter"/> 
			<bean class="org.springframework.http.converter.xml.SourceHttpMessageConverter "/> 
			<bean class="org.springframework.http.converter.xml.XmlAwareFormHttpMessageConverter"/> 
			<bean class="org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter"/> 
			<bean class="org.springframework.http.converter.json.MappingJacksonHttpMessageConverter" 
			p:supportedMediaTypes="*"/>
	</mvc:message-converters>
	或者	 
 * <bean class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter">  
    <property name="messageConverters">  
        <list>  
            //把ByteArray加在Json前面  
            <bean class="org.springframework.http.converter.ByteArrayHttpMessageConverter"/>  
            <bean id="jsonHttpMessageConverter" class="org.springframework.http.converter.json.MappingJacksonHttpMessageConverter" >  
                <property name = "supportedMediaTypes">  
                    <list>  
                        <value>text/plain;charset=UTF-8</value>  
                    </list>  
                </property>  
            </bean>  
        </list>  
    </property>  
</bean> 
 *
 *参考：http://sishuok.com/forum/posts/list/5351.html
 */
@Controller
public class DownloadFileTestController {
	
	private static final Logger logger = Logger.getLogger(DownloadFileTestController.class);
	
	@RequestMapping("download")  
	public ResponseEntity<byte[]> download() throws IOException {  
	    HttpHeaders headers = new HttpHeaders();  
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
	    headers.setContentDispositionFormData("attachment", "dict.txt");
	    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(getFile()),  
	                                      headers, HttpStatus.CREATED);  
	}

	private File getFile() {
		return null;
	} 

}
