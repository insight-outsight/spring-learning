package org.springlearning.aop.dynamic_data_source;
import java.io.IOException;  
import java.util.HashMap;  
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;  
import java.util.TreeMap;
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
  

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;  
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.ChildBeanDefinition;  
import org.springframework.beans.factory.support.DefaultListableBeanFactory;  
import org.springframework.context.ApplicationContext;  
import org.springframework.context.ApplicationContextAware;  
import org.springframework.context.ApplicationEvent;  
import org.springframework.context.ApplicationListener;  
import org.springframework.context.event.ContextRefreshedEvent;  
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.google.common.collect.Lists;
import com.jolbox.bonecp.BoneCPDataSource;  
  
  
public class DynamicBoneCPDataSource extends AbstractRoutingDataSource implements ApplicationContextAware,ApplicationListener<ApplicationEvent> {  
  
	private static final Logger LOG = LoggerFactory.getLogger(DynamicBoneCPDataSource.class);

    private ApplicationContext applicationContext;  
    

	@Override
	protected Object determineCurrentLookupKey() {
		return ContextDataSourceKeyHolder.getDataSourceKey();    
	}  
	
    @Override  
    public void setApplicationContext(ApplicationContext applicationContext)  
            throws BeansException {  
        this.applicationContext = applicationContext;  
    }  
    
    public void onApplicationEvent(ApplicationEvent event) {    
        //如果是容器刷新事件    
        if(event instanceof ContextRefreshedEvent ){   
        	LOG.info("{}接收到事件{}！",this.getClass().getSimpleName(),event.getClass().getSimpleName()); 
            try {  
                registerDynamicDataSourceBean();  
            } catch (Exception e) { 
            	System.out.println(e.getMessage()+" 错误!!");  
                e.printStackTrace();  
            }  
        	LOG.info("{}处理 事件{}完毕！",this.getClass().getSimpleName(),event.getClass().getSimpleName()); 
        }
        
    }    
  
    private void registerDynamicDataSourceBean() throws Exception{  
        // 解析属性文件，得到数据源Map  
        Map<String, DataSourceInfo> mapDataSource = parsePropertiesFile(Constants.dataSourcesInfoFileName);  
        // 把数据源bean注册到容器中  
        addDataSourceBeanToSpringContainer(mapDataSource); 
        // 初始化sharding信息
        ShardingInfoHolder.init();
    }
    
    /** 
     * 功能说明：根据DataSource创建bean并注册到容器中 
     * @param acf 
     * @param mapDataSource 
     */  
    private void addDataSourceBeanToSpringContainer(Map<String, DataSourceInfo> mapDataSource) {  
        DefaultListableBeanFactory acf = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();  
        BeanDefinition beanDefinition;
        String parentDynamicBoneCPDataSourceId = "parentDynamicBoneCPDataSource";
        BeanDefinitionBuilder beanDefinitionBuilder; 
        Iterator<String> iterator = mapDataSource.keySet().iterator(); 
        Map<Object,Object> targetDataSources = new LinkedHashMap<Object, Object>(); 
        List<String> dataSourcesListArg = Lists.newArrayList();
        
        //创建parent bean定义
        beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition("com.jolbox.bonecp.BoneCPDataSource");  
        beanDefinitionBuilder.getBeanDefinition().setAttribute("id", parentDynamicBoneCPDataSourceId);  
        beanDefinitionBuilder.addPropertyValue("driverClass", "com.mysql.jdbc.Driver");  
        beanDefinitionBuilder.addPropertyValue("connectionTimeoutInMs", 5000);  
        beanDefinitionBuilder.addPropertyValue("maxConnectionAge", 0);  
        beanDefinitionBuilder.addPropertyValue("idleMaxAge", 240);  
        beanDefinitionBuilder.addPropertyValue("idleConnectionTestPeriod", 60);  
        beanDefinitionBuilder.addPropertyValue("partitionCount", 3);  
        beanDefinitionBuilder.addPropertyValue("maxConnectionsPerPartition", 30);  
        beanDefinitionBuilder.addPropertyValue("minConnectionsPerPartition", 10); 
        acf.registerBeanDefinition(parentDynamicBoneCPDataSourceId, beanDefinitionBuilder.getBeanDefinition()); 
        LOG.info("{}",beanDefinitionBuilder.getBeanDefinition());
        LOG.info("register ParentDynamicDataSource,key:{},value:{}",parentDynamicBoneCPDataSourceId,acf.getBean(parentDynamicBoneCPDataSourceId));
        int i=0;
        while(iterator.hasNext()){  
            String beanKey = iterator.next();
            
/*            // 得到Bean定义，并添加到容器中  
            beanDefinition = new ChildBeanDefinition("dataSource");  
            // 注意：必须先注册到容器中，再得到Bean进行修改，否则数据源属性不能有效修改  .
            //acf不用判断容器中是否已经有相应名字的bean，spring会在该名称的bean存在时覆盖，不存在时添加。
            acf.registerBeanDefinition(beanKey, beanDefinition);  
            
            // 再得到数据源Bean定义，并修改连接相关的属性  
            BoneCPDataSource ds = (BoneCPDataSource )applicationContext.getBean( beanKey);;  
            ds.setJdbcUrl(mapDataSource.get(beanKey).connUrl);  
            ds.setUser(mapDataSource.get(beanKey).userName);  */
       
            //  创建bean  
            beanDefinitionBuilder = BeanDefinitionBuilder.childBeanDefinition(parentDynamicBoneCPDataSourceId);
            beanDefinitionBuilder.getBeanDefinition().setAttribute("id", beanKey);   
            beanDefinitionBuilder.addPropertyValue("jdbcUrl", mapDataSource.get(beanKey).connUrl);  
            beanDefinitionBuilder.addPropertyValue("username", mapDataSource.get(beanKey).userName);  
            beanDefinitionBuilder.addPropertyValue("password", mapDataSource.get(beanKey).password);  
//            if(i++==1){
//                beanDefinitionBuilder.addPropertyValue("connectionTimeoutInMs", 12000);  
//            }
            LOG.info("{}",beanDefinitionBuilder.getBeanDefinition());
            //  注册bean  
            acf.registerBeanDefinition(beanKey, beanDefinitionBuilder.getBeanDefinition());  
              
            //  放入map中，注意一定是刚才创建bean对象  
            targetDataSources.put(beanKey, acf.getBean(beanKey));  
            dataSourcesListArg.add(beanKey);
            LOG.info("register DynamicDataSource,key:{},value:{}",beanKey,acf.getBean(beanKey));
        }
        //  将创建的map对象set到 targetDataSources；  
        setTargetDataSources(targetDataSources);         
        //  必须执行此操作，才会重新初始化AbstractRoutingDataSource 中的 resolvedDataSources，也只有这样，动态切换才会起效  
        afterPropertiesSet(); 
        
		ContextDataSourceKeyHolder.setDataSourceKeyList(dataSourcesListArg);
    }  
    /** 
     * 功能说明：解析属性文件，得到数据源Map 
     * @return 
     * @throws IOException 
     */  
    private Map<String, DataSourceInfo> parsePropertiesFile(String fileName) throws IOException {  
        // 属性文件  
        ResourcePropertySource props =  new ResourcePropertySource(fileName);  
          
        Matcher matcher;  
        Pattern pattern = Pattern.compile("^dds\\.(eagle2\\.\\w+)\\.jdbc\\.(jdbcUrl|user|password)$");  
          
        Map<String, DataSourceInfo> mapDataSource = new TreeMap<String,DataSourceInfo>();  
        // 根据配置文件解析数据源  
        for(String keyProp : props.getPropertyNames())  
        {  
            matcher = pattern.matcher(keyProp);  
            if(matcher.find()){  
                String dsName = matcher.group(1);
                String dsPropName = matcher.group(2);  
                DataSourceInfo dsi;  
                  
                if(mapDataSource.containsKey(dsName)){  
                    dsi = mapDataSource.get(dsName);  
                }  
                else{  
                    dsi = new DataSourceInfo();  
                }  
                // 根据属性名给数据源属性赋值  
                if("jdbcUrl".equals(dsPropName)){  
                    dsi.connUrl = (String)props.getProperty(keyProp);  
                }else if("user".equals(dsPropName)){  
                    dsi.userName = (String)props.getProperty(keyProp);  
                }else if("password".equals(dsPropName)){  
                    dsi.password = (String)props.getProperty(keyProp);  
                }  
                mapDataSource.put(dsName, dsi);  
            }  
        }  
        return mapDataSource;  
    }  
      
    private class DataSourceInfo{  
        public String connUrl;  
        public String userName;  
        public String password;  
          
        public String toString(){        
            return "(JcbcUrl:"+connUrl+", user:"+userName+", password:"+password+")";  
        }  
    }

}  