package com.glu.smartshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
// 重点：指定读取dev配置，加载dev里的mysql、redis全部配置
@ActiveProfiles("dev")
class SmartShopApplicationTests {

    @Test
    void contextLoads() {
    }

}