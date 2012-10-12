package com.draicon.util;

import java.util.Calendar;
import java.util.Date;

public class Formatter {

	public static double redondea2Decimales(double num){
		//System.out.println("num:" + num);
		num = Math.rint(( num ) * 100) / 100;
		//System.out.println("num:" + num);
		return num;
	}
	
	public static String formateaFechaMMDDAAAA(String cadena){
		//recibe el formato de dd/mm/aaaa y lo debe de cambiar a mm/dd/aaaa
		String mes = cadena.substring(0, cadena.indexOf("/"));
		cadena =cadena.substring(cadena.indexOf("/") + 1 , cadena.length());
		String dia = cadena.substring(0,cadena.indexOf("/"));
		String anio = cadena.substring(cadena.indexOf("/") + 1 , cadena.length());
		
		return ( dia  + "/" +  mes + "/" + anio);
	}
	
	public static String getStringFromDate(Date date){
		if(date==null)
			return "";

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day=cal.get(Calendar.DAY_OF_MONTH);
		int month=cal.get(Calendar.MONTH);
		int year=cal.get(Calendar.YEAR);
		
		return (day<10?"0"+day:day)+"/" + ((month+1)<10?"0"+(month+1):month+1) +"/" + +year;
	}
}
