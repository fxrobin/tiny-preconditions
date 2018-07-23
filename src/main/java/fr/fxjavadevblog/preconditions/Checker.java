package fr.fxjavadevblog.preconditions;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.function.BiFunction;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * this class offers the ability to test methods arguments, using lambdas and
 * method references.
 * 
 * Basic usage is to call sequently multiple static methods from this class in
 * order to check the argument(s) validity.
 * 
 * <pre>
 * public void yourMethodName(String arg0, int arg1)
 * {
 * 	Checker.notNull("arg0", arg0);
 * 	Checker.respects("arg0", arg0, new Pattern("[A-Z]{1,10}")); // pattern should be static
 * 	Checker.range("arg1", arg1, 10, 20,);
 * 
 * 	// all tests passed ... let's write the method body now ...
 * }
 * </pre>
 * 
 * <p>
 * For full documentation, go to [http://fxrobin.github.com/tiny-preconditions]
 * 
 * @author fxjavadevblog
 *
 */
public final class Checker
{
	private Checker()
	{
		// protecting constructor of this class.
	}

	/**
	 * i18n message bundle.
	 */
	static ResourceBundle bundle = ResourceBundle.getBundle("fr.fxjavadevblog.preconditions.tiny-preconditions");

	/**
	 * check if the argument is null and throws IllegalArugmentException if
	 * true.
	 * 
	 * @param argumentName
	 *            name of the argument
	 * @param arg
	 *            argument to test
	 * 
	 * @throws IllegalArgumentException
	 *             if the argument is null
	 */
	public static void notNull(String argumentName, Object arg)
	{
		if (arg == null)
		{
			throw new IllegalArgumentException(PreconditionMessage.SHOULD_NOT_BE_NULL.format(argumentName));
		}
	}

	/**
	 * check if the argument is null and throws the RuntimeException supplied by
	 * the function.
	 * 
	 * @param argumentName
	 *            name of the argument
	 * @param arg
	 *            argument to test
	 * @param exceptionSupplier
	 *            the function which takes a String as argument and return an
	 *            instance of any RuntimeException
	 * 
	 * @throws RuntimeException
	 *             if the argument is null throws the exception supplied
	 */
	public static void notNull(String argumentName, Object arg, Function<String, ? extends RuntimeException> exceptionSupplier)
	{
		if (arg == null)
		{
			throw exceptionSupplier.apply(argumentName);
		}
	}

	/**
	 * check if the Integer argument is between a minus and max inclusives.
	 * 
	 * @param argumentName
	 *            name of the argument
	 * @param arg
	 *            argument to test
	 * @param min
	 *            mininum value inclusive
	 * @param max
	 *            maximum value inclusive
	 * 
	 * @throws IllegalArgumentException
	 *             if the argument is not in bounds.
	 */
	public static void inRange(String argumentName, Integer arg, Integer min, Integer max)
	{
		if (arg == null || arg < min || arg > max)
		{
			throw new IllegalArgumentException(PreconditionMessage.SHOULD_BE_BETWEEN.format(argumentName, min, max));
		}
	}

	/**
	 * check if the Integer argument is between a minus and max inclusives.
	 * 
	 * @param argumentName
	 *            name of the argument
	 * @param arg
	 *            argument to test
	 * @param min
	 *            mininum value inclusive
	 * @param max
	 *            maximum value inclusive
	 * 
	 * @param exceptionSupplier
	 *            the function which takes an Integer as argument and return an
	 *            instance of any RuntimeException
	 * 
	 * @throws RuntimeException
	 *             if the argument is not in range throws the exception supplied
	 */
	public static void inRange(String argumentName, Integer arg, int min, int max, BiFunction<String, Integer, ? extends RuntimeException> exceptionSupplier)
	{
		if (arg == null || arg < min || arg > max)
		{
			throw exceptionSupplier.apply(argumentName, arg);
		}
	}

	/**
	 * check if the collection is not null nor empty.
	 * 
	 * @param argumentName
	 *            name of the argument
	 * @param collection
	 *            collection to test
	 * 
	 * @throws IllegalArgumentException
	 *             if the collection is null or empty
	 */
	public static void notEmpty(String argumentName, Collection<?> collection)
	{
		if (collection == null || collection.isEmpty())
		{
			throw new IllegalArgumentException(PreconditionMessage.SHOULD_NOT_BE_EMPTY.format(argumentName));
		}
	}

	/**
	 * check if the argument matches the regexp pattern.
	 * 
	 * @param argumentName
	 *            name of the argument
	 * @param arg
	 *            argument to test
	 * @param pattern
	 *            regexp for String testing
	 * 
	 * @throws IllegalArgumentException
	 *             if the argument does not respect the pattern
	 */
	public static void respects(String argumentName, String arg, Pattern pattern)
	{
		if (!pattern.matcher(arg).matches())
		{
			throw new IllegalArgumentException(PreconditionMessage.SHOULD_MATCH_REGEXP.format(argumentName, pattern.toString()));
		}
	}

	public static void respects(String argumentName, BooleanSupplier predicate, String message)
	{
		if (!predicate.getAsBoolean())
		{
			throw new IllegalArgumentException(PreconditionMessage.SHOULD_RESPECT_BOOLEAN_CONDITION.format(argumentName, message));
		}
	}

	public static <T> void respects(String argumentName, T t, Predicate<T> predicate, String message)
	{
		if (!predicate.test(t))
		{
			throw new IllegalArgumentException(PreconditionMessage.SHOULD_RESPECT_BOOLEAN_CONDITION.format(argumentName, message));
		}
	}

	public static <T> void respects(String argumentName, T t, Predicate<T> predicate, BiFunction<String, T, ? extends RuntimeException> function)
	{
		if (!predicate.test(t))
		{
			throw function.apply(argumentName, t);
		}
	}

	public static void notAnyNullValue(String argumentName, Map<?, ?> map)
	{
		for (Entry<?, ?> e : map.entrySet())
		{
			if (e.getValue() == null)
			{
				throw new IllegalArgumentException(PreconditionMessage.MAP_SHOULD_NOT_CONTAIN_ANY_NULL_REFERENCE.format(argumentName, e.getKey()));
			}
		}
	}
}
