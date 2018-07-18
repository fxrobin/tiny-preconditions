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
 * cette classe est responsable du test de validité d'arguments en fonction de
 * contraintes.
 * 
 * @author fxjavadevblog
 *
 */
public final class Checker
{
	/**
	 * vérifie que la référence n'est pas nulle. Si elle l'est, une exception de
	 * type "IllegalArgumentException" est levée avec le message (format et
	 * arguments) passé en paramètres.
	 * 
	 * @param arg
	 *            référence à tester
	 * @param format
	 *            chaine de formatage
	 * @param vals
	 *            valeurs à injecter dans la chaine de formatage
	 * 
	 * @see String#format(String, Object...)
	 * @see IllegalArgumentException
	 */
	public static void notNull(Object arg, String format, Object... vals)
	{
		if (arg == null)
		{
			throw new IllegalArgumentException(String.format(format, vals));
		}
	}

	/**
	 * vérifie que la référence n'est pas nulle. Si elle l'est, le supplier
	 * d'IllegalArgumentException est invoqué, et le message est fourni à la
	 * construction de l'exception. Enfin, l'exception est levée.
	 * 
	 * @param arg
	 *            référence à tester
	 * @param exceptionSupplier
	 *            supplier d'IllegalArgumentException
	 * @param argName
	 *            message à fournir à l'exception
	 * 
	 * @see IllegalArgumentException
	 */
	public static void notNull(Object arg, Function<String, IllegalArgumentException> exceptionSupplier, String argName)
	{
		if (arg == null)
		{
			throw exceptionSupplier.apply(argName);
		}
	}


	/**
	 * vérifie que l'argument se situe bien sans une plage de valeurs Integer.
	 * 
	 * @param arg
	 * 		 argumenter à tester
	 * @param min
	 * 		valeur minimale incluse
	 * @param max
	 * 		valeur maximale incluse
	 * @param msgRangePattern
	 *  	format message en cas d'erreur (voir String.format)
	 * @param argName
	 * 		 nom de l'argument testé
	 */
	public static void inRange(Integer arg, int min, int max, String msgRangePattern, String argName)
	{
		if (arg == null || arg < min || arg > max)
		{
			throw new IllegalArgumentException(String.format(msgRangePattern, argName, arg, min, max));
		}
	}

	/**
	 * vérifie que l'argument se situe bien sans une plage de valeurs Integer.
	 * Cette méthode permet de désigner une exception, pour la démo (à compléter dans les autres méthodes).
	 * 
	 * @param arg
	 * 		 argumenter à tester
	 * @param min
	 * 		valeur minimale incluse
	 * @param max
	 * 		valeur maximale incluse
	 * @param function
	 * 		function lambda prenant une chaine de caractères et un entier 
	 * 		et retournant une instance de RuntimeException. Permet de désigner une exception
	 * 		notamment avec l'un des ses constructeurs. 
	 * @param argumentName
	 */
	public static void inRange(Integer arg, 
								 int min, 
								 int max, 
								 BiFunction<String, Integer, ? extends RuntimeException> function, 
								 String argumentName)
	{
		if (arg == null || arg < min || arg > max)
		{
			throw function.apply(argumentName, arg);
		}
	}

	/**
	 * vérifie qu'une collection n'est ni nulle, ni vide.
	 * 
	 * @param competences
	 *            collection à tester
	 * @param format
	 *            format message en cas d'erreur (voir String.format)
	 * @param vals
	 *            valeurs à injecter dans le format de message
	 * 
	 * @see String#format(String, Object...)
	 * @see IllegalArgumentException
	 */
	public static void notEmpty(Collection<?> competences, String format, Object... vals)
	{
		if (competences == null || competences.isEmpty())
		{
			throw new IllegalArgumentException(String.format(format, vals));
		}
	}

	/**
	 * vérifie qu'une chaine de caractères respecte bien une expression
	 * régulière définie dans un Pattern.
	 * 
	 * @param data
	 *            chaine à tester
	 * @param pattern
	 *            expression régulière
	 * @param format
	 *            format message en cas d'erreur (voir String.format)
	 * @param vals
	 *            valeurs à injecter dans le format de message
	 * 
	 * @see String#format(String, Object...)
	 * @see IllegalArgumentException
	 */

	public static void respects(String data, Pattern pattern, String format, Object... vals)
	{
		if (!pattern.matcher(data).matches())
		{
			throw new IllegalArgumentException(String.format(format, vals));
		}
	}

	/**
	 * vérifie que l'expression booléen "predicate" est bien à true, sinon une
	 * exception "IllegalException" est levée avec le message fourni.
	 * 
	 * @param predicate
	 *            expression booléenne.
	 * @param format
	 *            format message en cas d'erreur (voir String.format)
	 * @param vals
	 *            valeurs à injecter dans le format de message
	 * 
	 * @see String#format(String, Object...)
	 * @see IllegalArgumentExceptions
	 */
	public static void respects(boolean predicate, String format, Object... vals)
	{
		if (!predicate)
		{
			throw new IllegalArgumentException(String.format(format, vals));
		}
	}

	/**
	 * vérifie que l'expression booléenne évaluée a posteriori par le
	 * BooleanSupplier fourni en paramètre retorune bien à true, sinon une
	 * exception "IllegalException" est levée avec le message fourni.
	 * 
	 * @param predicate
	 *            supplier d'expression booléene.
	 * @param format
	 *            format message en cas d'erreur (voir String.format)
	 * @param vals
	 *            valeurs à injecter dans le format de message
	 * 
	 * @see String#format(String, Object...)
	 * @see IllegalArgumentExceptions
	 */
	public static void respects(BooleanSupplier predicate, String format, Object... vals)
	{
		if (!predicate.getAsBoolean())
		{
			throw new IllegalArgumentException(String.format(format, vals));
		}
	}

	/**
	 * vérifie que la référence, confrontée au prédicat, retourne true, sinon
	 * une exception "IllegalException" est levée avec le message fourni.
	 * 
	 * @param predicate
	 *            prédicat prenant un type T et qui contient la logique "true"
	 *            ou "false".
	 * @param format
	 *            format message en cas d'erreur (voir String.format)
	 * @param vals
	 *            valeurs à injecter dans le format de message
	 * 
	 * @see String#format(String, Object...)
	 * @see IllegalArgumentExceptions
	 */
	public static <T> void respects(T t, Predicate<T> predicate, String format, Object... vals)
	{
		if (!predicate.test(t))
		{
			throw new IllegalArgumentException(String.format(format, vals));
		}
	}
	
	/**
	 * vérifie que la référence, confrontée au prédicat, retourne true, sinon
	 * une exception "IllegalException" est levée avec le message fourni.
	 * 
	 * @param predicate
	 *            prédicat prenant un type T et qui contient la logique "true"
	 *            ou "false"
	 * @param function
	 * 			function lambda prenant le nom de l'argument testé et un T 
	 * 			qui retourne une instance de RuntimeException. Permet de désigner une exception
	 * 			notamment avec l'un des ses constructeurs. 
	 * 			
	 * @param argumentName
	 */
	
	public static <T> void respects(T t, Predicate<T> predicate,  BiFunction<String, T, ? extends RuntimeException> function, String argumentName)
	{
		if (!predicate.test(t))
		{
			throw function.apply(argumentName, t);
		}
	}

	/**
	 * vérifie qu'aucune valeur de la Map ne fasse référence à "null", sinon une
	 * exception de type IllegalArgumentException est levée avec le message
	 * fourni.
	 * 
	 * Le message devra prendre forcément un "%s" en entrée pour faire référence
	 * à la clé de la Map dont la valeur associée est nulle.
	 * 
	 * @param arguments
	 *            map à tester
	 * 
	 * @param format
	 *            chaine de formatage du message.
	 * 
	 */
	public static void notAnyNullValue(Map<?, ?> arguments, String format)
	{
		for (Entry<?, ?> e : arguments.entrySet())
		{
			if (e.getValue() == null)
			{
				throw new IllegalArgumentException(String.format(format, e.getKey()));
			}
		}
	}

}
