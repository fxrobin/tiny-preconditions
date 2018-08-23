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

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * represents a validation message error which can be modified (supports i18n) thanks to the file "tiny-preconditions.properties".
 * 
 * @author fxjavadevblog
 *
 */
public enum PreconditionMessage
{
	SHOULD_NOT_BE_NULL, 
	SHOULD_NOT_BE_EMPTY, 
	SHOULD_BE_BETWEEN, 
	SHOULD_MATCH_REGEXP, 
	SHOULD_RESPECT_BOOLEAN_CONDITION, 
	MAP_SHOULD_NOT_CONTAIN_ANY_NULL_REFERENCE;

	/**
	 * i18n message bundle.
	 */
	private static ResourceBundle bundle = ResourceBundle.getBundle("fr.fxjavadevblog.preconditions.tiny-preconditions");

	private MessageFormat getMessageFormat()
	{
		return new MessageFormat(PreconditionMessage.bundle.getString(this.toString()));
	}

	/**
	 * returns the full message injected with the given arguments.
	 * 
	 * @param objects
	 * 		arguments which will replace defined parameters in the message format String
	 * @return
	 * 		full message
	 */
	public String format(Object... objects)
	{
		return this.getMessageFormat().format(objects);
	}
}