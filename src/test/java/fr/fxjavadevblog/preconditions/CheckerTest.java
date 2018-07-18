package fr.fxjavadevblog.preconditions;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class CheckerTest {

	@Test
	void testNotNull() {
		String arg = "dummy";
		String details = "should not be null";
		this.checkExceptionMessage(() -> Checker.notNull(null, "%s "+ details, arg), arg + " " + details);
		
	}
	
	@Test
	void testNotNullLambdas() {
		String msg = "dummy should not be null";
		this.checkExceptionMessage(() -> Checker.notNull(null, IllegalArgumentException::new, msg) , msg);	
	}
	
	@Test
	void testInRange() {
		this.checkExceptionMessage(() -> Checker.inRange(0, 1, 10, "%s is %d but must be between %d and %d", "dummy"), "dummy is 0 but must be between 1 and 10");	
	}
	
	@Test
	void testNotEmpty()
	{
		List <Object> emptyList = new LinkedList<>();
		this.checkExceptionMessage(() -> Checker.notEmpty(emptyList, "The collection %s must not be empty", "emptyList"), "The collection emptyList must not be empty");
	}
	
	private void checkExceptionMessage(Runnable runnable, String message)
	{
		try
		{
			runnable.run();
		}
		catch (IllegalArgumentException e) {
			log.info(e.getMessage());
			assertEquals(message, e.getMessage());
		}
		
	}

}
