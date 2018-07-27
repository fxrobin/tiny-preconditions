/* (C) Copyright 2018  François-Xavier Robin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors: François-Xavier Robin
 */

package fr.fxjavadevblog.preconditions;



import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
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

	/**
	 * check if the BooleanSupplier predicate returns true.
	 * 
	 * @param argumentName
	 *            name of the argument
	 * @param predicate
	 *            predicate which must return true or false
	 * @param message
	 *            message included in the exception message
	 * 
	 * @throws IllegalArgumentException
	 *             if the BooleanSupplier does not return "true"
	 */
	public static void respects(String argumentName, BooleanSupplier predicate, String message)
	{
		if (!predicate.getAsBoolean())
		{
			throw new IllegalArgumentException(PreconditionMessage.SHOULD_RESPECT_BOOLEAN_CONDITION.format(argumentName, message));
		}
	}

	/**
	 * check if the argument passed to the predicate returns true.
	 * 
	 * @param argumentName
	 *            name of the argument
	 * @param t
	 *            argument to check
	 * @param predicate
	 *            predicate to apply on the argument
	 * @param message
	 *            message included in the exception message
	 * 
	 * @throws IllegalArgumentException
	 *             if the predicate does not return "true"
	 */
	public static <T> void respects(String argumentName, T t, Predicate<T> predicate, String message)
	{
		if (!predicate.test(t))
		{
			throw new IllegalArgumentException(PreconditionMessage.SHOULD_RESPECT_BOOLEAN_CONDITION.format(argumentName, message));
		}
	}

	/**
	 * check if the argument passed to the predicate returns true.
	 * 
	 * @param argumentName
	 *            name of the argument
	 * @param t
	 *            argument to check
	 * @param predicate
	 *            predicate to apply on the argument
	 * @param function
	 *            function which returns any RuntimeException and takes a String
	 *            message and T in one of its constructors. To be used with
	 *            method reference of Java 8.
	 * 
	 * @throws RuntimeException
	 *             returned by the function if the predicate does not return
	 *             "true"
	 */

	public static <T> void respects(String argumentName, T t, Predicate<T> predicate, BiFunction<String, T, ? extends RuntimeException> function)
	{
		if (!predicate.test(t))
		{
			throw function.apply(argumentName, t);
		}
	}

	/**
	 * check if the Map does not contain any null references in its values.
	 * 
	 * @param argumentName
	 *            name of the argument
	 * @param map
	 *            map to test, should not be null
	 * 
	 * @throws IllegalArgumentException
	 *             if the map contains any null references in its values
	 */
	public static void notAnyNullValue(String argumentName, Map<?, ?> map)
	{
		Checker.notNull("map", map);
		for (Entry<?, ?> e : map.entrySet())
		{
			if (e.getValue() == null)
			{
				throw new IllegalArgumentException(PreconditionMessage.MAP_SHOULD_NOT_CONTAIN_ANY_NULL_REFERENCE.format(argumentName, e.getKey()));
			}
		}
	}
}
