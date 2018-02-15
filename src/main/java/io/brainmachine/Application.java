package io.brainmachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Same References:
 * 
 * 	https://projects.spring.io/spring-data-redis
 * 	http://www.baeldung.com/spring-data-redis-tutorial
 * 	https://github.com/spring-projects/spring-data-examples/tree/master/redis/repositories
 * 	https://medium.com/@MatthewFTech/spring-boot-cache-with-redis-56026f7da83a
 * 
 * @author falvojr
 */
@SpringBootApplication
public class Application {

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		final JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
		jedisConFactory.setHostName("localhost");
		jedisConFactory.setPort(6379);
		return jedisConFactory;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		final RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
