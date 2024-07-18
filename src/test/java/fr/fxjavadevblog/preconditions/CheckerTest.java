package fr.fxjavadevblog.preconditions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class CheckerTest
{

	@Test
	void testNotNull()
	{
		Executable executable = () -> Checker.notNull("dummy", null);
		assertThrows(IllegalArgumentException.class, executable);
	}

	@Test
	void testNotNullLambdas()
	{
		Executable executable = () -> Checker.notNull("dummy", null, IllegalArgumentException::new);
		assertThrows(IllegalArgumentException.class, executable);
	}

	@Test
	void testInRange()
	{
		Executable executable = () -> Checker.inRange("dummy", 0, 1, 10);
		assertThrows(IllegalArgumentException.class, executable);
	}

	@Test
	void testNotEmpty()
	{
		List<Object> emptyList = new LinkedList<>();
		Executable executable = () -> Checker.notEmpty("emptyList", emptyList);
		assertThrows(IllegalArgumentException.class, executable);
	}

	@Test
	void testMapNotContainsNull()
	{
		Map<String, String> argMap = new HashMap<>();
		argMap.put("key-00", "hello");
		argMap.put("key-01", "world");
		argMap.put("key-02", null);
		Executable executable = () -> Checker.notAnyNullValue("argMap", argMap);
		assertThrows(IllegalArgumentException.class, executable);
	}

}
