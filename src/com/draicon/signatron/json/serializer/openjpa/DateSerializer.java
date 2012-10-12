package com.draicon.signatron.json.serializer.openjpa;


import java.util.Date;

import org.apache.openjpa.util.java$sql$Timestamp$proxy;
import org.jabsorb.serializer.MarshallException;
import org.jabsorb.serializer.SerializerState;
import org.json.JSONException;
import org.json.JSONObject;



/**
 * Serialises date and time values
 */
public class DateSerializer extends org.jabsorb.serializer.impl.DateSerializer {
  /**
   * Unique serialisation id.
   */
  private final static long serialVersionUID = 2;

  /**
   * Classes that this can serialise.
   */
  private static Class[] _serializableClasses = new Class[] { 
	  java$sql$Timestamp$proxy.class
  };

  /**
   * Classes that this can serialise to.
   */
  private static Class[] _JSONClasses = new Class[] { JSONObject.class };

  public Class[] getJSONClasses()
  {
    return _JSONClasses;
  }

  public Class[] getSerializableClasses()
  {
    return _serializableClasses;
  }

  public Object marshall(SerializerState state, Object p, Object o)
      throws MarshallException
  {
    long time;
    if (o instanceof Date)
    {
      time = ((Date) o).getTime();
    }
    else
    {
      throw new MarshallException("cannot marshall date using class "
          + o.getClass());
    }
    JSONObject obj = new JSONObject();
    try
    {
      
      obj.put("javaClass", "java.sql.Timestamp");
      obj.put("time", time);
    }
    catch (JSONException e)
    {
      throw new MarshallException(e.getMessage(), e);
    }
    return obj;
  }

}

