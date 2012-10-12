package com.draicon.signatron.rpc.secure;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.draicon.signatron.data.CatMotivo;
import com.draicon.signatron.data.Comandos;
import com.draicon.signatron.data.Gps;
import com.draicon.signatron.data.Setup;
import com.draicon.signatron.data.Vehiculos;
import com.draicon.signatron.rpc.em.DAOEntityManagerFactory;
import com.draicon.signatron.security.exception.NonAuthorizedCallException;
import com.draicon.util.Hexadecimal;

public class ComandosDAO extends BaseCommonDAO {
	private static Log logger = LogFactory.getLog(ComandosDAO.class);
	
	private final static String ENTITY_ID = "idComando";
	private final static String ENTITY_NAME = "Comandos";
	private final static Class<?> ENTITY_CLASS = Comandos.class;
	
	private static final String NO_COMMAND = "NC"; 
	private static final String ESTATUS_ENVIADO = "S"; 
	private static final String ESTATUS_ERROR = "E"; 

	public ComandosDAO() {
	}
	
	@Override
	protected String getPersistenceEntityIdField() {
		logger.debug("getPersistenceEntityIdField: " + ENTITY_ID);
		return ENTITY_ID;
	}

	@Override
	protected String getPersistenceEntityName() {
		logger.debug("getPersistenceEntityName: " + ENTITY_NAME);
		return ENTITY_NAME;
	}

	@Override
	protected Class<?> getPersistenceEntityClass() {
		logger.debug("getPersistenceEntityClass: "
				+ ENTITY_CLASS.getCanonicalName());
		return ENTITY_CLASS;
	}
	
	public List<CatMotivo> getCommands(String authToken) {
		List<CatMotivo> results = null;
		
		EntityTransaction tx = null;
		EntityManager em = null;

		try {
			em = DAOEntityManagerFactory.createEntityManager();
			tx = em.getTransaction();

			tx.begin();
			Query q = em.createQuery("SELECT p FROM CatMotivo p "
					+ "WHERE p.iscommand=1 "
					+ "ORDER BY p.motivo");
			
			results = q.getResultList();
		} catch (Exception e) {
			logger.error("Problema en getCommands", e);
			throw new PersistenceException(e);
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			em.close();
		}
		
		return results;
	}
	
	public Comandos sendCommand(String authToken, String idVehiculo, String commandClient, String telefono, String message) 
		throws NonAuthorizedCallException, PersistenceException {
		String command = NO_COMMAND; 
		String tipoSocket = "";
		Comandos result = new Comandos();
		logger.info(ComandosDAO.class + " - sendCommand(" + commandClient + "," + telefono + "," + message + ")");
		
		
		// Obtenemos el IMEI del vehículo
		VehiculosDAO vehiculoDAO = new VehiculosDAO();
		Vehiculos vehiculo = (Vehiculos)vehiculoDAO.getDataById(authToken, Integer.parseInt(idVehiculo));
		//String imei = vehiculo.getIdGps().getImei();

		GpsDAO gpsDAO = new GpsDAO();
		Gps gps = (Gps)gpsDAO.getDataByIdVehiculo(authToken, Integer.parseInt(idVehiculo));
		String imei = gps.getImei();

		CatMotivoDAO catMotivoDAO = new CatMotivoDAO();
		CatMotivo comando = (CatMotivo)catMotivoDAO.getDataById(authToken, Integer.parseInt(commandClient));
		command = comando.getCodigo();
		
/*
		if(commandClient.equals("1")){
			command = "A"; // 0x41
		}else if(commandClient.equals("2")){
			command = "O"; // 0x4F;
		}else if(commandClient.equals("3")){
			command = "D"; // 0x44
		}else if(commandClient.equals("4")){
			command = "C"; // 0x43
		}else if(commandClient.equals("5")){
		}else if(commandClient.equals("6")){
		}else if(commandClient.equals("7")){
		}else if(commandClient.equals("8")){
		}else if(commandClient.equals("9")){
			command = "B"; // 0x42
		}else if(commandClient.equals("10")){
			//command = "F"; // 0x46
		}else if(commandClient.equals("11")){
		}else if(commandClient.equals("12")){
		}
*/
		
		result.setIdVehiculo(vehiculo);
		result.setComando(command);
		result.setFechaComando(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		result.setMensaje(message);
		if(!telefono.equals("")) {
			try {
				result.setTelefono(new BigDecimal(telefono));
			}
			catch(NumberFormatException e) {
				result.setTelefono(new BigDecimal(0));
			}
		}
		
		// TODO Guardar el usuario que hace el envío del comando
		//result.setIdUser(idUser);
		
		
		if(!command.equals(NO_COMMAND)){
			// Obtenemos los datos de configuracion de la app
			SetupDAO setupDAO = new SetupDAO();
			Setup setup = null;
			
			// http://forums.sun.com/thread.jspa?threadID=5400416
			String word = ">"; // 0x3E
			//String host = "draicon-desk1"; //"draicon-server1"; // "192.168.1.73";
			//int port = 6003;
			
			if(imei.length()==12){
				String hexWord = Hexadecimal.convertStringToHexString(word).toUpperCase();
				//String hexCommand = Hexadecimal.convertStringToHexString(command);
				String hexCommand = command;
				String hexEnviar = hexWord + imei + hexCommand;
				
				if(command.equals("42")) { // Si es comando de mensaje y el mensaje es diferente de vacío
					if(!message.trim().equals("")) {
						//System.out.println("ComandosDAO.sendCommand() >>>>>>>>>>>>>>>>>> MENSAJE");
						//System.out.println(message.trim().replace("\r", " ").replace("\n", " ").replace("\t", " "));
						hexEnviar+= Hexadecimal.convertStringToHexString(message.trim().replace("\r", " ").replace("\n", " ").replace("\t", " ")).toUpperCase();
					}
					hexEnviar+= "0D";
				} else if (command.equals("50")){
					if(!telefono.trim().equals("")){
						//System.out.println("ComandosDAO.sendCommand() >>>>>>>>>>>>>>>>>> TELEFONO");
						//System.out.println(telefono.trim().replace("\r", " ").replace("\n", " ").replace("\t", " "));
						hexEnviar+= Hexadecimal.convertStringToHexString(telefono.trim().replace("\r", " ").replace("\n", " ").replace("\t", " ")).toUpperCase();
					}
					hexEnviar+= "1F0D0A";
				}
				
				byte[] dataSend = Hexadecimal.convertHexStringToByteArray(hexEnviar);
				
				System.out.println("ComandosDAO.sendCommand(). hexEnviar=" + hexEnviar + " - dataSend=" + dataSend);
				result.setStatus(ESTATUS_ERROR);
				DatagramSocket s = null;
				DatagramPacket data = null;
				
				Socket skt = null;
				//InputStream inputStream = null;
				OutputStream outputStream = null;
				//String responseSocket = null;
				
				try {
					setup = (Setup)setupDAO.getDataById(authToken, 1);
					
					//if(imei.length()==imei.length()) {
					if(setup.getIsTcp()==1) {
						tipoSocket = "TCP";
						skt = new Socket(setup.getServer(), setup.getPortOut());
						//inputStream = skt.getInputStream();
						outputStream = skt.getOutputStream();
						
						outputStream.write(dataSend);
						outputStream.flush();
						
						//responseSocket = "" + inputStream.read();
						
						//System.out.println("ComandosDAO.sendCommand(): responseSocket = " + responseSocket);
						//inputStream.close();
						outputStream.close();
						skt.close();
					} else {
						tipoSocket = "UDP";
						s = new DatagramSocket();
						//byte[] ipServerSignatron = new byte[] {(byte)200, (byte)67, (byte)207, (byte)190}; 
						//byte[] ipServerSignatron = new byte[] {(byte)ip[0], (byte)60, (byte)9, (byte)3};
						//data = new DatagramPacket(dataSend, dataSend.length, InetAddress.getByAddress(ipServerSignatron), 6010);
						data = new DatagramPacket(dataSend, dataSend.length, InetAddress.getByName(setup.getServer()), setup.getPortOut());
						s.send(data);
						s.close();
					}
					result.setStatus(ESTATUS_ENVIADO);
					result.setAlerta("OK");
				} catch (NullPointerException e) {
					result.setAlerta("ERROR!  Null pointer exception. No hay registro en SETUP.");
					System.out.println("ComandosDAO.sendCommand() - ERROR!!!!  Null pointer exception. No hay registro en SETUP.");
					e.printStackTrace();
				} catch (UnknownHostException e) {
					result.setAlerta("ERROR!  Host desconocido: " + setup.getServer());
					System.out.println("ComandosDAO.sendCommand() - ERROR!!!!  Host desconocido: " + setup.getServer());
					e.printStackTrace();
				} catch (SocketException e) {
					result.setAlerta("ERROR!  SocketException. Host: " + setup.getServer() + ". Port: " + setup.getPortOut());
					System.out.println("ComandosDAO.sendCommand() - ERROR!!!!  SocketException. Host: " + setup.getServer() + ". Port: " + setup.getPortOut());
					e.printStackTrace();
				} catch (IOException e) {
					result.setAlerta("ERROR!  No se pudo obtener el I/O para la conexión al host: " + setup.getServer() + ". port: " + setup.getPortOut());
					System.out.println("ComandosDAO.sendCommand() - ERROR!!!!  No se pudo obtener el I/O para la conexión al host: " + setup.getServer() + ". port: " + setup.getPortOut());
					e.printStackTrace();
				} catch (Exception e) {
					result.setAlerta("ERROR!  No se pudo enviar el comando: " + setup.getServer() + ". port: " + setup.getPortOut());
					System.out.println("ComandosDAO.sendCommand() - ERROR!!!!  No se pudo enviar el comando: " + setup.getServer() + ". port: " + setup.getPortOut());
					e.printStackTrace();
				} finally {
					if (s!=null && !s.isClosed()) {
						s.close();
					}
//					if (inputStream!=null) {
//						try {
//							inputStream.close();
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					}
					if (outputStream!=null){
						try {
							outputStream.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			} else {
				result.setStatus(ESTATUS_ERROR);
				result.setAlerta("No se encontró el código IMEI o no tiene longitud de 12 caracteres. (IMEI:" + imei + ")");
			}
		} else {
			result.setStatus(ESTATUS_ERROR);
			result.setAlerta("No existe un comando asociado a la acción. Verificar catálogo.");
		}
		if(!tipoSocket.equals("")){
			result.setAlerta(result.getAlerta() + " (" + tipoSocket + ")");
		}
		int idResult = (Integer)(this.add(authToken, result));
		logger.info("Comando registrado en la base de datos. Devuelve = " + idResult);
		return result;
	}
}