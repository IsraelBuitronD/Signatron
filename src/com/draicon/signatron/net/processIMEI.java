package com.draicon.signatron.net;

public class processIMEI {
	

	public static void main(String[] args) {
		 String Data, word;
			Data = " 0d 20 3e 23 26 80 03 38 36 49 17 06 81 12 61 95 09 69 90 38 7c 62 a0 3f 55 00 0d 20 3e 23 26 80 03 37 83 83 17 06 81 12 71 93 53 19 91 90 1c 29 80 67 43 00 0d 20 3e 23 26 80 03 38 25 75 17 06 81 13 91 93 40 39 92 02 0c 00 80 0d 4a 00 0d 20 3e 23 26 80 03 38 36 49 17 06 81 14 31 95 05 69 90 40 4c 60 a0 3f 43 00 0d 20 00 00 20 3e 23 26 80 03 38 36 49 17 06 81 14 31 95 05 69 90 40 4c 60 a0 3f 43 00 0d 20 20 3e 23 26 80 03 38 36 49 17 06 81 14 31 95 05 69 90 40 4c 60 a0 3f 43 00 0d 20 20 3e 23 26 80 03 38 36 49 17 06 81 14 31 95 05 69 90 40 4c 60 a0 3f 43 00 0d 20";
//		    Data = "  012345678901234567890012345678901234567890012345678901234567890012345678901234567890";
			int Data2 = 0x3e23;
			int bitmask = 0x00FF;
			int val = 0x2222;
	  System.out.println( Data2 & bitmask);  
	  Data = Data.trim();
	  Data = Data.replaceAll(" ", "");


	  for (int i = 0; i < Data.length(); i++){}
		  int inicio = Data.indexOf("3e23");
		  int fin = Data.indexOf("0d", inicio + 2);
		  word = Data.substring(inicio + 4, fin);

		 
		  System.out.println(word);
//		  System.out.println(Data.substring(inicio));
		  		  
		 

		  // IMEI decimal
		  String IMEI = word.substring(0 , 12);
		  System.out.println("IMEI: " + IMEI);
		  for (int i = 0; i < IMEI.length()/2; i++ ){
			  int par = 2 + 2 * i;
			  String dupla =IMEI.substring(par -2 , par);
			  int decdupla= Integer.parseInt(dupla,16);
			  int[] dIMEI = new int[12];
			  dIMEI[i]=decdupla;
			  }
		  
		  // Fecha
		  String fecha = word.substring(12 , 16);
		  System.out.println("fecha: " + fecha);
		   for (int i = 0; i<fecha.length()/2; i++){
			  int par =2+2*i;
			  String dupla=fecha.substring(par-2, par);
			  int decdupla = Integer.parseInt(dupla, 16);
			  int[] dfecha = new int[2];
			  dfecha [i]= decdupla;
			  System.out.println("dfecha: " + dfecha[i]);
		   }
		  
		  //segundos
		   	  String segundos = word.substring(16 , 21);
			  System.out.println("segundos: " + segundos);
			   for (int i = 0; i<segundos.length(); i++){
				  String bit=segundos.substring(i, i+1);
				  int decbit = Integer.parseInt(bit, 16);
				  int[] dsegundos = new int[5];
				  dsegundos [i]= decbit;
//				  System.out.println("dsegundos: " + dsegundos[i]);
			   	}
		   
		  //Lat
			    String lat = word.substring(21 , 27);
			    int ilat = Integer.parseInt(lat);
			    double dlat = (double)ilat;
			    dlat = dlat * 0.0001;
			    System.out.println("lat: " + dlat);
			    
		  //Long
			    String longi = word.substring(27 , 33);
			    int ilong = Integer.parseInt(longi);
			    double dlong = (double)ilong;
			    dlong = dlong * 0.0001;
			    System.out.println("long: " + dlong);
		   
		   
		  //Calidad
			    String cal = word.substring(33,34);
			    int dcal = Integer.parseInt(cal, 16);
			    String scal = Integer.toBinaryString(dcal); 
			    if (scal.length()< 4){
			    	while (scal.length()<4){scal ="0"+ scal;}
			    }
			    System.out.println("cal "+scal);
			    String calidad = String.valueOf(scal.substring(0, 2));
			    System.out.println("calidad"+calidad);
			    if (calidad == "11"){System.out.println("excelente");}
			    else if (calidad == "10") {System.out.println("buena");}
			    else if (calidad == "01" && calidad == "00") {System.out.println("mala");}	
			
			    
			    
		  //Velocidad
			    String vel = word.substring(33,36);
			    int dvel = Integer.parseInt(vel, 16);
			    String bvel = Integer.toBinaryString(dvel); 
			    if (bvel.length()< 12){
			    	while (bvel.length()<12){bvel ="0"+ bvel;}
			    }
			    bvel = bvel.substring(2,12);
			    String cvel  = bvel.substring(0,2);
			    String decvel  = bvel.substring(2,6);
			    String uvel  = bvel.substring(6,10);
				int icvel = (Integer.parseInt(cvel,2));
				int idecvel = (Integer.parseInt(decvel,2));
				int iuvel = (Integer.parseInt(uvel,2));
				int velocidad = icvel*100 + idecvel *10 + iuvel;
				System.out.println("velocidad "+velocidad);
			    
			    
//			    System.out.println("velocidad: " + vel);
//			    try {
//					byte[] bytes = vel.getBytes("US-ASCII");
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
//			    int ivel = Integer.parseInt(vel);
//			    int bitmask_vel = 0x00FF;
//				System.out.println( bytes & bitmask_vel);  

	
			    
		  //GPS
			  String gps = word.substring(36,37);
			  int dgps = Integer.parseInt(gps, 16);
			    String bgps = Integer.toBinaryString(dgps); 
			    if (bgps.length()< 4){
			    	while (bgps.length()<4){bgps ="0"+ bgps;}
			    }
			  gps = bgps.substring(0,1);   
			  System.out.println("GPS "+gps);
			 
		   
		  //Direccion
			  String dir = word.substring(36,39);
			  int ddir = Integer.parseInt(dir, 16);
			  String bdir = Integer.toBinaryString(ddir);
			  if (bdir.length()< 12){
			    	while (bdir.length()<12){bdir ="0"+ bdir;}
			    }
			  bdir = bdir.substring(1,12);
			  String cdir  = bdir.substring(0,3);
			  String decdir  = bdir.substring(3,7);
			  String udir  = bdir.substring(7,11);
			  int icdir = (Integer.parseInt(cdir,2));
			  int idecdir = (Integer.parseInt(decdir,2));
			  int iudir = (Integer.parseInt(udir,2));
			  int direccion = icdir*100 + idecdir *10 + iudir;
			  System.out.println("direccion grados "+direccion);
			  
	  
		  //S1 S2 S3 S4 S5 S6 
			  String sens = word.substring(39,41);
			  int dsens = Integer.parseInt(sens, 16);
			  String bsens = Integer.toBinaryString(dsens);
			  if (bsens.length()< 8){
			    	while (bsens.length()<8){bsens ="0"+ bsens;}
			    }
			  bsens = bsens.substring(0,6);
			  String s1  = bsens.substring(0,1);
			  String s2  = bsens.substring(1,2);
			  String s3  = bsens.substring(2,3);
			  String s4  = bsens.substring(3,4);
			  String s5  = bsens.substring(4,5);
			  String s6  = bsens.substring(5,6);
//			  System.out.println("Sensores1 " + s1);
//			  System.out.println("Sensores2 " + s2);
//			  System.out.println("Sensores3 " + s3);
//			  System.out.println("Sensores4 " + s4);
//			  System.out.println("Sensores5 " + s5);
//			  System.out.println("Sensores6 " + s6);
//			  System.out.println("Sensores " + bsens);
			  

		   
		  //Motivo del reporte
		   
			  String repor = word.substring(40,42);
			  int drepor = Integer.parseInt(repor, 16);
			  String brepor = Integer.toBinaryString(drepor);
			  if (brepor.length()< 8){
			    	while (brepor.length()<8){brepor ="0"+ brepor;}
			    }
			  brepor = brepor.substring(2,8);
			  int irepor = Integer.parseInt(brepor, 2);
			  String reporte = Integer.toHexString(irepor);
			  repor = String.valueOf(brepor);
			  System.out.println("reporte " + reporte);
			  if(reporte == "15"){System.out.println("velocidad maxima sobrepasada");}
			  else if (reporte == "18"){System.out.println("Comando Apertura de Pta. Trasera");}
			
		   
		  // Display
		   
		   
		  // Mensaje 
		  
		  
		  
		  
//		  //separa la palabra
//		  int i= Integer.parseInt("F",16);
//		  String binary = Integer.toBinaryString(i);
//		  System.out.println(i);
//		  System.out.println(binary);
    }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
