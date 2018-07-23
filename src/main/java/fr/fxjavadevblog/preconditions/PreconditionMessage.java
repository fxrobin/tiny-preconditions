package fr.fxjavadevblog.preconditions;

import java.text.MessageFormat;

public enum PreconditionMessage
{
	SHOULD_NOT_BE_NULL, 
	SHOULD_NOT_BE_EMPTY, 
	SHOULD_BE_BETWEEN, 
	SHOULD_MATCH_REGEXP, 
	SHOULD_RESPECT_BOOLEAN_CONDITION, 
	MAP_SHOULD_NOT_CONTAIN_ANY_NULL_REFERENCE;

	private MessageFormat getMessageFormat()
	{
		return new MessageFormat(Checker.bundle.getString(this.toString()));
	}

	public String format(Object... objects)
	{
		return this.getMessageFormat().format(objects);
	}
}