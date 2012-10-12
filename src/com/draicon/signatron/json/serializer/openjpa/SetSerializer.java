package com.draicon.signatron.json.serializer.openjpa;

import java.util.Iterator;
import java.util.Set;

import org.apache.openjpa.util.java$util$HashSet$proxy;
import org.jabsorb.JSONSerializer;
import org.jabsorb.serializer.MarshallException;
import org.jabsorb.serializer.SerializerState;
import org.json.JSONException;
import org.json.JSONObject;


public class SetSerializer extends org.jabsorb.serializer.impl.SetSerializer {

	private static Class[] _serializableClasses = new Class[] { 
		java$util$HashSet$proxy.class
	};

	private static Class[] _JSONClasses = new Class[] { JSONObject.class };


	public Class[] getJSONClasses() {
		return _JSONClasses;
	}

	public Class[] getSerializableClasses() {
		return _serializableClasses;
	}

	public Object marshall(SerializerState state, Object p, Object o)
			throws MarshallException {
		Set set = (Set) o;

		JSONObject obj = new JSONObject();
		JSONObject setdata = new JSONObject();
		if (ser.getMarshallClassHints()) {
			try {
				obj.put("javaClass", "java.util.Set");
			} catch (JSONException e) {
				throw new MarshallException("javaClass not found!", e);
			}
		}
		try {
			obj.put("set", setdata);
			state.push(o, setdata, "set");
		} catch (JSONException e) {
			throw new MarshallException("Could not set 'set': "
					+ e.getMessage(), e);
		}
		Object key = null;
		Iterator i = set.iterator();

		try {
			while (i.hasNext()) {
				key = i.next();
				String keyString = key.toString(); // only support String keys
				Object json = ser.marshall(state, setdata, key, keyString);

				// omit the object entirely if it's a circular reference or
				// duplicate
				// it will be regenerated in the fixups phase
				if (JSONSerializer.CIRC_REF_OR_DUPLICATE != json) {
					setdata.put(keyString, json);
				}
			}
		} catch (MarshallException e) {
			throw new MarshallException("set key " + key + e.getMessage(), e);
		} catch (JSONException e) {
			throw new MarshallException("set key " + key + e.getMessage(), e);
		} finally {
			state.pop();
		}
		return obj;
	}

}
