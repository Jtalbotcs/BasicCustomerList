package com.talbot.customerList.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:customerList-servlet.xml"})
public class CalculatorTest {
	@Test
	public void testAbs()
	{
		assertEquals(1,1);
	}
}
