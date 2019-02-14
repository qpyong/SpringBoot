package org.qpyong.demos.springboot.service.redis;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

@Service("redisService")
public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean exists(final String key) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {

            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                Jedis jedis = (Jedis) connection.getNativeConnection();
                return jedis.exists(key);
            }
        });
    }

    @Override
    public boolean exists(final String key, final String field) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {

            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                Jedis jedis = (Jedis) connection.getNativeConnection();
                return jedis.hexists(key, field);
            }
        });
    }

    @Override
    public void put(final String key, final String field, final String value) {
        redisTemplate.execute(new RedisCallback<Void>() {

            @Override
            public Void doInRedis(RedisConnection connection) throws DataAccessException {
                Jedis jedis = (Jedis) connection.getNativeConnection();
                jedis.hset(key, field, value);
                return null;
            }
        });
    }

    @Override
    public void set(String key, String value) {

    }

    @Override
    public String get(final String key, final String field) {
        return redisTemplate.execute(new RedisCallback<String>() {

            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                Jedis jedis = (Jedis) connection.getNativeConnection();
                return jedis.hget(key, field);
            }
        });
    }

    @Override
    public String get(String key) {
        return null;
    }


}
