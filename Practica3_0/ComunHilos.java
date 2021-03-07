package Practica3_0;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ComunHilos {
	
	private ArrayList<String> historial = new ArrayList<>();
    private ArrayList<Socket> sockets = new ArrayList<>();

    public void anadirMensaje(String mensage) throws IOException {
    	historial.add(mensage);

        for (Socket socket : sockets) {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(mensage);
        }
    }

    public void mostrarHistorial() throws IOException {
    	
        for (Socket socket : sockets) {
        	
            for (String h : historial) {
            	
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF(h);
                
            }
        }
    }

    public synchronized void añadirCliente(Socket cliente) throws IOException {

        DataOutputStream dataOutputStream = new DataOutputStream(cliente.getOutputStream());

        while (sockets.size() == 2) {
        	
            try {
            	
            	dataOutputStream.writeUTF("El servidor está lleno");
                wait();
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            } 
        }

        sockets.add(cliente);
        dataOutputStream.writeUTF("El usuario: " + cliente + "se ha unido al chat");   
        notifyAll();
    }

    public synchronized void eliminarCliente(Socket cliente) {
    	
        sockets.remove(cliente);
        notifyAll();
    }
	
	
	
	
	
	
}
