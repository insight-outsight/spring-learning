package org.springlearning.aop.dynamic_data_source;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.core.io.support.ResourcePropertySource;


public class ShardingInfoHolder {

	private static Map<String,ShardingInfo> dataMap = new HashMap<String,ShardingInfo>();

	
	public static ShardingInfo getShardingInfoByMapperClassName(String className) {
		return dataMap.get(className);
	}

	public static void init() throws Exception {
        // 属性文件  
        ResourcePropertySource props =  new ResourcePropertySource(Constants.shardingInfoFileName);  
          
        Matcher matcher;  
        /*  dds.indexUserPhone.mapperClassName=com.yanke.backend.api.dal.dao.IndexUserPhoneMapper
			dds.indexUserPhone.modelClassName=com.yanke.backend.api.dal.model.IndexUserPhone
			dds.indexUserPhone.modelExampleClassName=com.yanke.backend.api.dal.model.IndexUserPhoneExample
			dds.indexUserPhone.shardingFieldName=mobile_phone_number
         */
        Pattern pattern = Pattern.compile("^dds\\.(\\w+)\\.(mapperClassName|modelClassName|modelExampleClassName|shardingFieldName)$");  
        Map<String,ShardingInfo> dataMapTemp = new HashMap<String,ShardingInfo>();
        ShardingInfo shardingInfo; 
        for(String keyProp : props.getPropertyNames()){  
            matcher = pattern.matcher(keyProp);  
            if(matcher.find()){  
                String mapperGroupName = matcher.group(1);
                String propName = matcher.group(2);  
                 
                if(dataMapTemp.get(mapperGroupName)!=null){  
                	shardingInfo = dataMapTemp.get(mapperGroupName);  
                }  
                else{  
                	shardingInfo = new ShardingInfo();  
                }  
                // 根据属性名给数据源属性赋值  
                if("mapperClassName".equals(propName)){ 
                	shardingInfo.setMapperClassName((String)props.getProperty(keyProp));
                }else if("modelClassName".equals(propName)){  
                	shardingInfo.setModelClassName((String)props.getProperty(keyProp));
                }else if("modelExampleClassName".equals(propName)){  
                	shardingInfo.setModelExampleClassName((String)props.getProperty(keyProp));
                }else if("shardingFieldName".equals(propName)){  
                	shardingInfo.setShardingFieldName((String)props.getProperty(keyProp));
                }  
                dataMapTemp.put(mapperGroupName, shardingInfo);  
            }  
        }
        for(String key:dataMapTemp.keySet()){
        	shardingInfo = dataMapTemp.get(key);
        	shardingInfo.setModelClass(ShardingInfoHolder.class.getClassLoader().loadClass(shardingInfo.getModelClassName()));
        	shardingInfo.setModelExampleClass(ShardingInfoHolder.class.getClassLoader().loadClass(shardingInfo.getModelExampleClassName()));
        	dataMap.put(shardingInfo.getMapperClassName(), shardingInfo);
        }

	}

	public static void main(String[] args) {
		try {
			init();
	        for(String key:dataMap.keySet()){
	        	System.out.println("key:"+key+",value:"+dataMap.get(key));
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
