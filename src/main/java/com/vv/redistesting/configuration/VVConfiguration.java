package com.vv.redistesting.configuration;

import com.vv.redistesting.service.VVService;
import com.vv.redistesting.service.impl.VVServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Slf4j
@Configuration
public class VVConfiguration {

    @Value( "${spring.redis.host:localhost}" )
    private String redisHost;

    @Value( "${spring.redis.port:6379}" )
    private int redisPort;

    @Value( "${spring.redis.password:#{''}}" )
    private String redisPassword;

    @Value( "${spring.redis.ssl:false}" )
    private boolean redisSSL;


    @Bean
    public JedisConnectionFactory redisConnectionFactory() {

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration( redisHost,
                                                                                                      redisPort );
        redisStandaloneConfiguration.setPassword( redisPassword );
        JedisClientConfiguration jedisConfig = JedisClientConfiguration.builder()
                                                                       .useSsl()
                                                                       .and()
                                                                       .usePooling()
                                                                       .build();
        if ( !redisSSL ) {
            return new JedisConnectionFactory( redisStandaloneConfiguration );
        }
        return new JedisConnectionFactory( redisStandaloneConfiguration, jedisConfig );

    }

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory( redisConnectionFactory() );
        redisTemplate.setHashValueSerializer( new StringRedisSerializer() );
        redisTemplate.setKeySerializer( new StringRedisSerializer() );
        redisTemplate.setValueSerializer( new StringRedisSerializer() );
        redisTemplate.setHashKeySerializer( new StringRedisSerializer() );
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public VVService theService( RedisTemplate<String, String> redisTemplate ) {
        VVService result =new VVServiceImpl( redisTemplate );
        log.error("VV service null? -> "+(result!=null));
        return result;
    }
}
