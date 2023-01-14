package com.vv.redistesting.service.impl;

import com.vv.redistesting.service.VVService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;

@RequiredArgsConstructor
public class VVServiceImpl implements VVService {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public String save( final String str ) {
        //install a localhost redis on Windows👇
        //https://redis.io/docs/getting-started/installation/install-redis-on-windows/
        redisTemplate.opsForValue()
                     .set( str+"-key", str+"-value" );
        return str+"-saved";
    }
}
