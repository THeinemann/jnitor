/*
 *    Copyright 2016 Thomas Heinemann
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */


package com.github.theinemann.jnitor.exceptions;

public class JnitorRuntimeException extends RuntimeException {

	/**
	 * UID for serialization format
	 */
	private static final long serialVersionUID = -5265092237812770733L;

	public JnitorRuntimeException() {
	}

	public JnitorRuntimeException(String what) {
		super(what);
	}

	public JnitorRuntimeException(Throwable cause) {
		super(cause);
	}

	public JnitorRuntimeException(String what, Throwable cause) {
		super(what, cause);
	}

	public JnitorRuntimeException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
