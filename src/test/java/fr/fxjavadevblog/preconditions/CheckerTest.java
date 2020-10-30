package fr.fxjavadevblog.preconditions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class CheckerTest
{

	@Test
	void testNotNull()
	{
		this.checkException(() -> Checker.notNull("dummy", null));
	}

	@Test
	void testNotNullLambdas()
	{
		this.checkException(() -> Checker.notNull("Exception : dummy should not be null", null, IllegalArgumentException::new));
	}

	@Test
	public void testInRange()
	{
		this.checkException(() -> Checker.inRange("dummy", 0, 1, 10));
	}

	@Test
	public void testNotEmpty()
	{
		List<Object> emptyList = new LinkedList<>();
		this.checkException(() -> Checker.notEmpty("emptyList", emptyList));
	}

	@Test
	void testMapNotContainsNull()
	{
		Map<String, String> argMap = new HashMap<>();
		argMap.put("key-00", "hello");
		argMap.put("key-01", "world");
		argMap.put("key-02", null);
		this.checkException(() -> Checker.notAnyNullValue("argMap", argMap));
	}

	private void checkException(Runnable runnable)
	{
		try
		{
			runnable.run();
			Assert.fail("Exception should have occured !");
		}
		catch (IllegalArgumentException e)
		{
			log.info(e.getMessage());
		}
	}
}
