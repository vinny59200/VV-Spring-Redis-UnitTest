package com.vv.redistesting;

import com.vv.redistesting.configuration.TestRedisConfiguration;
import com.vv.redistesting.configuration.VVConfiguration;
import com.vv.redistesting.service.VVService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@Slf4j
@RunWith( SpringRunner.class )
@SpringBootTest( classes = { TestRedisConfiguration.class, VVConfiguration.class } )
public class SimpleRedisTest {

    @Autowired
    private VVService theService;

    @Test
    public void shouldSaveStringtoRedis() {
        UUID id = UUID.randomUUID();
        String saved = theService.save( id.toString() );
        log.error( saved );
        assertNotNull( saved );
    }
}
