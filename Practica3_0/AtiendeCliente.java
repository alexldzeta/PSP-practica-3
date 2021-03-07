package Practica3_0;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class AtiendeCliente extends Thread {
	
	private String usuario;
	private Socket cliente;
	private ComunHilos comunHilos;

	public AtiendeCliente(Socket cliente, ComunHilos comunHilos) {
		this.usuario = "";
		this.cliente = cliente;
		this.comunHilos = comunHilos;
	}

	@Override
	public void run() {
		
		try {
			
			DataInputStream dataInputStream = new DataInputStream(cliente.getInputStream());

			comunHilos.añadirCliente(cliente);

			comunHilos.mostrarHistorial();

			usuario = dataInputStream.readUTF();

			String mensage = "[" + usuario + "] " + dataInputStream.readUTF();

			while (!mensage.equals("[" + usuario + "] " + "*")) {
				
				System.out.println(mensage);
				comunHilos.anadirMensaje(mensage);
				mensage = "[" + usuario + "] " + dataInputStream.readUTF();
				
			}

			comunHilos.eliminarCliente(cliente);
			System.out.println(usuario + " desconectado");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	

}
