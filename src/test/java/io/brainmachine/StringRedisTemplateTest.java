package io.brainmachine;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = Application.class)
public class StringRedisTemplateTest {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void whenSavingStringThenAvailableOnRetrieval() throws Exception {
		final String key = "adhoc:" + UUID.randomUUID().toString();
		final String value = "STRING VALUE";

		final ValueOperations<String, String> opsForValue = this.stringRedisTemplate.opsForValue();
		opsForValue.append(key, value);
		assertEquals(value, opsForValue.get(key));
	}
}
