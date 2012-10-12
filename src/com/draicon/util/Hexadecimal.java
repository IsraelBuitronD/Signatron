package com.draicon.util;

public class Hexadecimal {
	static final String HEX = "0123456789ABCDEF";
	
	public Hexadecimal() {
		
	}
	
	public static String convertStringToHexString(String str){
		  char[] chars = str.toCharArray();
		  
		  StringBuffer hex = new StringBuffer();
		  for(int i = 0; i < chars.length; i++){
		    hex.append(Integer.toHexString((int)chars[i]));
		  }
	 
		  return hex.toString();
	}
	
	public static String convertHexStringToString(String hex){
		/*
		 * Se presentan problemas a partir del decimal 127, ya que es el último número decimal que puede convertir a ASCII
		 */
		  StringBuilder sb = new StringBuilder();
		  
		  //System.out.println("Hexadecimal.convertHexToString() - hex: " + hex);
		  for(int i=0, l=hex.length(); i<l-1; i+=2){
			  // Se obtienen la cadena en pares
		      String output = hex.substring(i, (i + 2));
		      
		      // El número hexadecimal se pasa a entero
		      int decimal = Integer.parseInt(output, 16);
		      // El número decimal se pasa a character
		      sb.append((char)decimal);
		      
		      //System.out.println("output="+output + " - decimal="+decimal);
		  }
		  //System.out.println("tmep = " + temp);
		  //System.out.println("Hexadecimal.convertHexToString() - FINAL = " + sb);
		  return sb.toString();
	}
	
	public static byte[] convertHexStringToByteArray(String str){
		int len = str.length();
		byte[] data = new byte[len/2];
		for(int i=0; i<len; i+=2){
			data[i/2]= (byte)((Character.digit( str.charAt(i) , 16) << 4 ) + Character.digit(str.charAt(i+1), 16));
		}
		return data;
	}
	
	public static String convertByteArrayToHexString(byte[] data) {
		if(data==null) return null;
		StringBuilder hex = new StringBuilder(2*data.length);
		for(byte b: data) {
			hex.append(HEX.charAt((b & 0xF0) >> 4))
			   .append(HEX.charAt((b & 0x0F)));
		}
		return hex.toString();
	}
}
