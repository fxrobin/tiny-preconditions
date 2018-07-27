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