package com.redis.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * redis集群配置文件
 *
 * # Redis服务器地址
 #spring.redis.host=10.100.50.23
 # Redis服务器连接端口
 #spring.redis.port=6379
 # Redis服务器连接密码（默认为空）
 spring.redis.password=
 # 连接池最大连接数（使用负值表示没有限制）
 spring.redis.pool.maxActive=8
 # 连接池最大阻塞等待时间（使用负值表示没有限制）
 spring.redis.pool.maxWait=-1
 # 连接池中的最大空闲连接
 spring.redis.pool.maxIdle=8
 # 连接池中的最小空闲连接
 spring.redis.pool.minIdle=0
 # 连接超时时间（毫秒）
 spring.redis.timeout=0
 #重连次数
 spring.redis.maxAttempts=5
 spring.redis.commandTimeout=5000

 # redis.cluster
 spring.redis.cluster.nodes=10.100.50.23:6380,10.100.50.23:6381,10.100.50.23:6382,10.100.50.23:6383,10.100.50.23:6384,10.100.50.23:6385
 *
 */
//@Component
//@Configuration
//@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {
    /** redis集群节点 */
    @Value("${spring.redis.cluster.nodes}")
    private String nodes;
    /** 连接超时时间 */
    @Value("${spring.redis.timeout}")
    private int timeout;
    /** 重连次数 */
    @Value("${spring.redis.maxAttempts}")
    private int maxAttempts;

    /**连接池中的最大空闲连接*/
    @Value("${spring.redis.pool.maxIdle}")
    private int maxIdle;

    /**连接池最大阻塞等待时间（使用负值表示没有限制）*/
    @Value("${spring.redis.pool.maxWait}")
    private int maxWaitMillis;

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(int maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }
}
