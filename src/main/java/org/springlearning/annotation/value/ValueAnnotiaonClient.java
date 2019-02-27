package org.springlearning.annotation.value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ValueAnnotiaonClient {

    @Value("${app.version.num}")
    private String appVersionNum;
    
    @Value("${app.type.num}")
    private String appTypeNum;
    
    @Value("#{factoryBeanConfig['app.name']}")
    private String appName;
    
    @Value("${literal.foo}")
    private String literalFoo;
    
    @Value("#{utilPropertiesConfig['bar1']}")
    private String bar1;

    @Value("#{dynamicConfigService.count()}")
    private int count;
      
    @Value("#{dynamicConfigService.max(${app.type.num})}")
    private int max;
    
    public String getAppVersionNum() {
        return appVersionNum;
    }

    public void setAppVersionNum(String appVersionNum) {
        this.appVersionNum = appVersionNum;
    }

    public String getAppTypeNum() {
        return appTypeNum;
    }

    public void setAppTypeNum(String appTypeNum) {
        this.appTypeNum = appTypeNum;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getLiteralFoo() {
        return literalFoo;
    }

    public void setLiteralFoo(String literalFoo) {
        this.literalFoo = literalFoo;
    }

    public String getBar1() {
        return bar1;
    }

    public void setBar1(String bar1) {
        this.bar1 = bar1;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
    
}
