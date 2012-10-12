package com.draicon.signatron.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import jxl.write.DateTime;

import com.draicon.signatron.db.Conexion;
import com.sun.jmx.snmp.Timestamp;

public class DBTest {


	public static void main(String[] args) {

		System.out.println(" Prueba de query estadistico, " +
				"para indicar el tiempo  encendido/apagado de un vehiculo ");
		
		String query ="";

		Long tiempo = 0l;
		Integer motivo = null;

		Long tiempoAnt =0l;
		Integer motivoAnt = null ;
		
		Boolean bTInicial = true;
		Long tiempoApagado=0l;
		Long tiempoEncendido=0l;
		Long tiempoInicial =0l;
		Long tiempoFinal =0l;
		Long tiempoTotal =0l;
		
		
		
		
		query = "SELECT * FROM posiciones where id_vehiculo =342" +
				"and (id_motivo = 11 or id_motivo =12)" +
				"order by id_posicion asc;;";
		
		ResultSet rs = Conexion.getConexion().consultaSql(query);
	

		try {
			if (rs.next()){
				tiempoAnt = rs.getTimestamp("fecha").getTime();
				motivoAnt = rs.getInt("id_motivo");
				if (motivoAnt == 11 ){
					tiempoInicial = rs.getTimestamp("fecha").getTime();
					bTInicial = false;
				}
			}

			while(rs.next()){
				tiempo = rs.getTimestamp("fecha").getTime();
				motivo = rs.getInt("id_motivo");
				//System.out.println("time: " + tiempo +" motivo = "+motivo );
				
				if(motivo != motivoAnt){
					if (motivo == 11){
						tiempoApagado += (tiempo - tiempoAnt);
						
						if (bTInicial == true){
							tiempoInicial = tiempo;
							bTInicial = false;
						}
						else{
							tiempoFinal = tiempo;
						}
					}
					
					motivoAnt = motivo;
					tiempoAnt = tiempo;
					
				}
				
				
			}
			rs.close();
		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		tiempoTotal = tiempoFinal -tiempoInicial;
		tiempoEncendido = tiempoTotal-tiempoApagado;

		//System.out.println("tiempos:" + tiempoFinal + ", " + tiempoInicial  +" total: " + deltaTime(tiempoTotal));
		//System.out.println("tiempoEnc/Apa:" + tiempoEncendido + ", " + deltaTime(tiempoApagado));		
		System.out.println("Tiempo Apagado " + deltaTime(tiempoApagado));
		System.out.println("Tiempo Encendido " + deltaTime(-1*(tiempoEncendido)));
		System.out.println("Tiempo Total  " + deltaTime(tiempoTotal));
		/*
		Time horaTotal = new Time(tiempoTotal);
		System.out.println("Tiempo total:  " + horaTotal.get + " " + horaTotal.getHours()+":"+horaTotal.getMinutes());
		
		Time horaApagado = new Time(tiempoApagado);
		System.out.println("Tiempo apagado:  " + horaApagado.getDay() + " " + horaApagado.getHours()+":"+horaApagado.getMinutes());
		
		Time horaEncendido = new Time(tiempoEncendido);
		System.out.println("Tiempo encendido:  " + horaEncendido.getDay() + " " + horaEncendido.getHours()+":"+horaEncendido.getMinutes());
		*/
				
		
		
		
		
	}

	private static String deltaTime(Long tiempoTranscurrido){
	
	    tiempoTranscurrido = tiempoTranscurrido /1000;
		
	    Long segundos= 0l, minutos = 0l, horas = 0l , dias =0l;
	    String sttiempoTranscurrido;
        
		segundos = tiempoTranscurrido % 60;
		minutos = (tiempoTranscurrido % 3600) /60;         
		horas = (tiempoTranscurrido % 86400)/ 3600;
		dias = tiempoTranscurrido/ 86400;
		
		if (dias > 0 ) 
  		  sttiempoTranscurrido = dias.toString() +" dias " + horas.toString() + " hr " + minutos.toString() + " min " + segundos.toString() + " seg ";
  	  	else
            sttiempoTranscurrido = horas.toString() + " hr " + minutos.toString() + " min " + segundos.toString()+ " seg ";

		
		return sttiempoTranscurrido;
	}
	
	
	
}
