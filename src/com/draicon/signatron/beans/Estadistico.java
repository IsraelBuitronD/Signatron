package com.draicon.signatron.beans;

import java.util.Date; 
import java.util.List;

import com.draicon.signatron.data.Posiciones;

public class Estadistico {

	private Date fechaInicial;
	private Date fechaFinal;
	private String fechaInicialStr;
	private String fechaFinalStr;
	private double distanciaRecorrida;
	private double velocidadPromedio;
	private double velocidadMaxima;
	private double altitudPromedio;
	private int id_vehiculo;
	private String tiempoApagadoStr;
	private String tiempoPrendidoStr;
	private String tiempoTotalStr;
	private double tiempoApagado;
	private double tiempoPrendido;
	private double tiempoTotal;
	private List<Posiciones> posiciones;
	
	public double getTiempoApagado() {
		return tiempoApagado;
	}
	public void setTiempoApagado(double tiempoApagado) {
		this.tiempoApagado = tiempoApagado;
	}
	public double getTiempoPrendido() {
		return tiempoPrendido;
	}
	public void setTiempoPrendido(double tiempoPrendido) {
		this.tiempoPrendido = tiempoPrendido;
	}
	public double getTiempoTotal() {
		return tiempoTotal;
	}
	public void setTiempoTotal(double tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}
	public String getTiempoApagadoStr() {
		return tiempoApagadoStr;
	}
	public void setTiempoApagadoStr(String tiempoApagadoStr) {
		this.tiempoApagadoStr = tiempoApagadoStr;
	}
	public String getTiempoPrendidoStr() {
		return tiempoPrendidoStr;
	}
	public void setTiempoPrendidoStr(String tiempoPrendidoStr) {
		this.tiempoPrendidoStr = tiempoPrendidoStr;
	}
	public String getTiempoTotalStr() {
		return tiempoTotalStr;
	}
	public void setTiempoTotalStr(String tiempoTotalStr) {
		this.tiempoTotalStr = tiempoTotalStr;
	}
	public List<Posiciones> getPosiciones() {
		return posiciones;
	}
	public void setPosiciones(List<Posiciones> posiciones) {
		this.posiciones = posiciones;
	}
	public int getId_vehiculo() {
		return id_vehiculo;
	}
	public void setId_vehiculo(int idVehiculo) {
		id_vehiculo = idVehiculo;
	}
	public double getVelocidadPromedio() {
		return velocidadPromedio;
	}
	public void setVelocidadPromedio(double velocidadPromedio) {
		this.velocidadPromedio = velocidadPromedio;
	}
	public double getVelocidadMaxima() {
		return velocidadMaxima;
	}
	public void setVelocidadMaxima(double velocidadMaxima) {
		this.velocidadMaxima = velocidadMaxima;
	}
	public String getFechaInicialStr() {
		return fechaInicialStr;
	}
	public void setFechaInicialStr(String fechaInicialStr) {
		this.fechaInicialStr = fechaInicialStr;
	}
	public String getFechaFinalStr() {
		return fechaFinalStr;
	}
	public void setFechaFinalStr(String fechaFinalStr) {
		this.fechaFinalStr = fechaFinalStr;
	}
	public Date getFechaInicial() {
		return fechaInicial;
	}
	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public double getDistanciaRecorrida() {
		return distanciaRecorrida;
	}
	public void setDistanciaRecorrida(double distanciaRecorrida) {
		this.distanciaRecorrida = distanciaRecorrida;
	}
	public double getAltitudPromedio() {
		return altitudPromedio;
	}
	public void setAltitudPromedio(double altitudPromedio) {
		this.altitudPromedio = altitudPromedio;
	}	
}
