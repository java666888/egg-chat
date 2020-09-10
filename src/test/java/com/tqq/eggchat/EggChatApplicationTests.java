package com.tqq.eggchat;

import com.tqq.eggchat.util.JwtTokenUtil;
import com.tqq.eggchat.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class EggChatApplicationTests {



    @Test
    void contextLoads() {
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("qq123456"));
    }

}
