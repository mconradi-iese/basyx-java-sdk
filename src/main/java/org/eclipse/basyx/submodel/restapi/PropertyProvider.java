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
package org.eclipse.basyx.submodel.restapi;

import java.util.Map;

import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.Property;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.valuetype.ValueTypeHelper;
import org.eclipse.basyx.vab.exception.provider.MalformedRequestException;
import org.eclipse.basyx.vab.exception.provider.ProviderException;
import org.eclipse.basyx.vab.modelprovider.VABPathTools;
import org.eclipse.basyx.vab.modelprovider.api.IModelProvider;

/**
 * Handles properties according to AAS meta model
 *
 * @author schnicke
 *
 */
public class PropertyProvider implements IModelProvider {

	private IModelProvider proxy;

	public PropertyProvider(IModelProvider proxy) {
		this.proxy = proxy;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getValue(String path) throws ProviderException {
		path = VABPathTools.stripSlashes(path);

		// Handle "/value" path
		if (path.equals(Property.VALUE)) {
			// return value
			Map<String, Object> p = (Map<String, Object>) proxy.getValue("");
			return p.get(Property.VALUE);

		} else if (path.isEmpty()) {
			// Handle "" path by returning complete property
			return proxy.getValue("");
		} else {
			throw new MalformedRequestException("Unknown path: " + path);
		}
	}

	@Override
	public void setValue(String path, Object newValue) throws ProviderException {
		path = VABPathTools.stripSlashes(path);
		// Only handle "/value" paths
		if (path.equals(Property.VALUE)) {
			// Set value and type
			proxy.setValue(Property.VALUE, newValue);
			proxy.setValue(Property.VALUETYPE, ValueTypeHelper.getType(newValue).toString());
		} else {
			throw new MalformedRequestException("Given Set path '" + path + "' does not end in /value");
		}
	}

	@Override
	public void createValue(String path, Object newEntity) throws ProviderException {
		throw new MalformedRequestException("Create not allowed at path '" + path + "'");
	}

	@Override
	public void deleteValue(String path) throws ProviderException {
		throw new MalformedRequestException("Delete not allowed at path '" + path + "'");
	}

	@Override
	public void deleteValue(String path, Object obj) throws ProviderException {
		throw new MalformedRequestException("Delete not allowed at path '" + path + "'");
	}

	@Override
	public Object invokeOperation(String path, Object... parameter) throws ProviderException {
		throw new MalformedRequestException("Invoke not allowed at path '" + path + "'");
	}

}
