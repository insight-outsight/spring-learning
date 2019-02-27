package org.springlearning.annotation.value;

import org.springframework.stereotype.Component;

@Component("dynamicConfigService")
public class DynamicConfigService {

    public int count() {
        return 10;
    }

    public int max(int size) {
        int count = count();
        return count > size ? count : size;
    }
}