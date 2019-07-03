package org.springlearning.ioc;

public class HelloClient {

    private String version;
    private IHello helloService;
    
    public void showVersion() {
        System.out.println("Version is " + getVersion());
    }

    public void textHello() {
        helloService.sayHello();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public IHello getHelloService() {
        return helloService;
    }

    public void setHelloService(IHello helloService) {
        this.helloService = helloService;
    }

}
