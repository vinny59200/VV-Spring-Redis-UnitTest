package com.vv.redistesting.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@TestConfiguration
public class TestRedisConfiguration {

    @Value( "${spring.redis.port:6370}" )
    private int redisPort;
    private RedisServer redisServer;

    public TestRedisConfiguration( ) {
    }

    @PostConstruct
    public void postConstruct() {
        this.redisServer = new RedisServer(this.redisPort);
        redisServer.start();
    }

    @PreDestroy
    public void preDestroy() {
        redisServer.stop();
    }
}
