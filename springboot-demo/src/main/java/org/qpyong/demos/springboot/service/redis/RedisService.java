package org.qpyong.demos.springboot.service.redis;

public interface RedisService {

    boolean exists(String key);

    boolean exists(String key, String field);

    void put(String key, String field, String value);

    void set(String key, String value);

    String get(String key, String field);

    String get(String key);


}
