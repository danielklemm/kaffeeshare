/*******************************************************************************
 * Copyright 2013 Jens Breitbart, Daniel Klemm, Dennis Obermann
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package de.kaffeeshare.server.exception;

/**
 * Thrown if a reserved namespace is supposed to be used
 */
public class ReservedNamespaceException extends RuntimeException {

	private static final long serialVersionUID = 2826030527124116551L;
	
	/**
	 * Void constructor.
	 */
	public ReservedNamespaceException() {
		super("Trying to use a reserved namespace!");
	}

}
