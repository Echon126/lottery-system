package com.wen.web.lotterysystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LotterySystemApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	AsyncService asyncService;

	@Test
	public void testAsync() {
		asyncService.async1();
		asyncService.async2();
	}

}
