package com.baikja.advisor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.socurites.jive.example.konal.web.KonalWebApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {KonalWebApplication.class})
public class AdvisorApplicationTests {

	@Test
	public void contextLoads() {
	}

}
