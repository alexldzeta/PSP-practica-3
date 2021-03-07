package Practica3_0;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class AtiendeServidor extends Thread {
	
	private Socket socket;

    public AtiendeServidor(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
    	
        try {
        	
            while (true) {
            	
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                System.out.println(dataInputStream.readUTF());
                
            }
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	

}
