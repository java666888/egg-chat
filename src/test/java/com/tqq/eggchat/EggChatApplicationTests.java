package com.tqq.eggchat;

import com.tqq.eggchat.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EggChatApplicationTests {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
        boolean falg = redisUtil.set("你好", "123", 100);
        System.out.println("写入完毕"+falg);
    }

}
