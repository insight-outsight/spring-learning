package org.springlearning.ioc.autowiredBeanListAndMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BeanClient {

    @Autowired
    private List<BeanInterface> list;

    @Autowired
    private Map<String, BeanInterface> map;

    /**
     * @Autowired默认为byType的所以有两个相同类型的bean如果不使用 @Qualifier指定具
     * 体的bean就会抛出异常 private BeanInterface beaninterface;
     */
    @Autowired
    @Qualifier("beanImplOne")
    private BeanInterface beaninterface;

    public void showName() {
        System.out.println("list...");
        if (null != list && 0 != list.size()) {
            for (BeanInterface bean : list) {
                System.out.println("---------list item-------------");
                System.out.println(bean.getClass().getName());
                bean.showName();
            }

        } else {
            System.out.println("List<BeanInterface> list is null !!!!");
        }
        System.out.println();
        System.out.println("map...");
        if (null != map && 0 != map.size()) {
            for (Map.Entry<String, BeanInterface> m : map.entrySet()) {
                System.out.println("---------map item-------------");
                System.out.println(m.getKey() + "    " + m.getValue().getClass().getName());
                m.getValue().showName();
            }
        } else {
            System.out.println("Map<String,BeanInterface> map is null !!!!");

        }
        System.out.println("-------------------------");
        if (null != beaninterface) {
            System.out.println(beaninterface.getClass().getName());
            beaninterface.showName();
        } else {
            System.out.println("beaninterface is null !!!");
        }
    }

}