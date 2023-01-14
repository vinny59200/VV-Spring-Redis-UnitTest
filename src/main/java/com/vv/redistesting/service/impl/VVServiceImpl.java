package com.vv.redistesting.service.impl;

import com.vv.redistesting.service.VVService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;

@RequiredArgsConstructor
public class VVServiceImpl implements VVService {

    //install a localhost redis on Windows👇
    //https://redis.io/docs/getting-started/installation/install-redis-on-windows/
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public String save( final String str ) {
        redisTemplate.opsForValue()
                     .set( str + "-key", str + "-value" );
        return str + "-saved";
    }

    @Override
    public String get( final String str ) {
        String result = redisTemplate.opsForValue()
                     .get( str );
        return result;
    }
}
