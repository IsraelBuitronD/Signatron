package com.draicon.signatron.net;


import java.net.*;
import java.io.*;
public class socketCliente{
   public static void main(String[] args) {
     try{
        Socket s = new Socket("127.0.0.1", 9999);
        InputStream i = s.getInputStream();
	    OutputStream o = s.getOutputStream();
        DataInputStream dis = new DataInputStream(i);
        DataOutputStream dos = new DataOutputStream(o);
	    String str;
        String pet;
	    do{
            byte[] line = new byte[100];
            System.out.println(dis.readUTF());
            System.out.println(dis.readUTF());
            System.out.println(dis.readUTF());
            
            //o.write(line);
            System.out.println(dis.readUTF());
            //opcion
            System.in.read(line);
            pet = new String(line);
            dos.writeInt(Integer.parseInt(pet.trim()));
            
            System.out.println(dis.readUTF());
            System.in.read(line);
            pet = new String(line);
            dos.writeInt(Integer.parseInt(pet.trim()));

            System.out.println(dis.readUTF());
            System.in.read(line);
            pet = new String(line);
            dos.writeInt(Integer.parseInt(pet.trim()));
            
            System.out.println(dis.readUTF());
            int resp = dis.readInt();

            if(resp == -999999){
                System.out.println("La división por cero no esta definida");
            }else{
                if(resp == -888888){
                System.out.println("La opción no es válida");
            }else{
                 System.out.println(resp);
            }
            }

            System.out.println(dis.readUTF());
            System.in.read(line);
            pet = new String(line);
            dos.writeUTF(pet.trim());
            
		}while ( !pet.trim().equals("bye") && !pet.trim().equals("N") );
            s.close();
        }catch (Exception err){
            System.err.println(err);
        }
    }
}