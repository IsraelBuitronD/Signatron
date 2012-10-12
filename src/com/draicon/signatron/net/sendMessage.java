package com.draicon.signatron.net;

public class sendMessage {
	public static void main(String[] args) {
	
		String codigo = null;
		String word = "3e";
		String IMEI = "153110010175";
		
		if (IMEI.length()==12){
			System.out.println("IMEI "+ IMEI);
		}else{
			System.out.println("Error IMEI incorrecto");
		}
		word = word + IMEI;
	if(codigo == "41" ){word = word + "41";}
	else if (codigo == "42" ){word = word + "42";}
	else if (codigo == "43" ){word = word + "43";}
	else if (codigo == "44" ){word = word + "44";}
	else if (codigo == "46" ){word = word + "46";}
	else if (codigo == "48" ){word = word + "48";}
	else if (codigo == "4d" ){word = word + "4d";}
	else if (codigo == "4e" ){word = word + "4e";}
	else if (codigo == "4f" ){word = word + "4f";}

	
	
	System.out.println("word "+ word);
	}
	
	public static String comandos(){
		
		return comandos();
	}
}
