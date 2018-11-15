package org.springlearning.aop.dynamic_data_source.aspect.exception;

public class ServiceException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -7288210006732136618L;

    public ServiceException(String string, Throwable throwable) {
        super(string,throwable);
    }

}
