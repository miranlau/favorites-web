/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.favorites.session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * The purpose of this class is to configure Spring Session to use.
 */
@EnableRedisHttpSession
public class SessionConfig {

    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private int redisPort;
    @Value("${spring.redis.password}")
    private String redisPwd;

    @Bean
    public LettuceConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory(redisHost, redisPort);
    }

}
