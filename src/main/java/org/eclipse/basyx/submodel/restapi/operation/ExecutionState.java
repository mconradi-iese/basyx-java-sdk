/*******************************************************************************
 * Copyright (C) 2021 the Eclipse BaSyx Authors
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 * SPDX-License-Identifier: MIT
 ******************************************************************************/
package org.eclipse.basyx.submodel.restapi.operation;

import org.eclipse.basyx.submodel.metamodel.enumhelper.StandardizedLiteralEnum;
import org.eclipse.basyx.submodel.metamodel.enumhelper.StandardizedLiteralEnumHelper;

/**
 * ExecutionState for Operations
 * 
 * @author espen
 *
 */
public enum ExecutionState implements StandardizedLiteralEnum {

	/**
	 * Initial state
	 */
	INITIATED("Initiated"),
	/**
	 * State during execution
	 */
	RUNNING("Running"),
	/**
	 * Operation has been completed
	 */
	COMPLETED("Completed"),
	/**
	 * Operation has been canceled
	 */
	CANCELED("Canceled"),
	/**
	 * Operation has failed
	 */
	FAILED("Failed"),
	/**
	 * Operation has timed out
	 */
	TIMEOUT("Timeout");

	private String standardizedLiteral;

	private ExecutionState(String standardizedLiteral) {
		this.standardizedLiteral = standardizedLiteral;
	}

	@Override
	public String getStandardizedLiteral() {
		return standardizedLiteral;
	}
	
	@Override
	public String toString() {
		return standardizedLiteral;
	}

	public static ExecutionState fromString(String str) {
		return StandardizedLiteralEnumHelper.fromLiteral(ExecutionState.class, str);
	}
}
