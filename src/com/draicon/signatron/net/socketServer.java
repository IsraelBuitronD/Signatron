package com.draicon.signatron.net;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;


public class socketServer {
   
    public static void main(String[] args) {
	  try{
		ServerSocket s = new 	ServerSocket(9999);
		String str;
		while (true){
		  Socket c = s.accept();
		  InputStream i =   c.getInputStream();
		  OutputStream o =  c.getOutputStream();
          DataInputStream dis = new DataInputStream(i);
         DataOutputStream dos = new DataOutputStream(o);
		  do{
		    byte[] line = new byte[100];
            dos.writeUTF("Entro al Socket");
            dos.writeUTF("Que operación deseas?");
            dos.writeUTF("1.Suma 2. Resta 3. Multiplicación 4.División");
            dos.writeUTF("Opción:");
             int opcion = dis.readInt();
             System.out.println(opcion);
             dos.writeUTF("Dame número 1:");
            int p = dis.readInt();
            dos.writeUTF("Dame número 2:");
            int q = dis.readInt();
            int res = 0;
            switch(opcion){
                case 1:
                    res = p +q;
                    break;
                case 2:
                    res = p -q;
                    break;
                case 3:
                        res =p*q;
                        break;
                case 4:
                    if (q!=0){
                    res= p/q;

                    }else{
                        res = -999999;
                    }
                    break;
                default:
                    res = -888888;
            }
            dos.writeUTF("Resultado:");
            System.out.println(res);
            dos.writeInt(res);

            dos.writeUTF("¿Quieres continuar?(Y/N)");
            str = dis.readUTF();
            if(str.equals("N")){
                break;
            }
             
           
	      }while ( !str.trim().equals("bye") );
	       c.close();
	    }
    }catch (Exception err){
       System.err.println(err);
    }
  }

}
