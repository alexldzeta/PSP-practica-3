package Practica3_0;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) throws IOException {

		// try-with-resource
		try (ServerSocket serverSocket = new ServerSocket(1999)) {

			ComunHilos comunHilos = new ComunHilos();

			System.out.println("SERVIDOR INICIADO");

			while (true) {
					
				Socket cliente = serverSocket.accept();
				System.out.println("***Cliente conectado al servidor***");

				AtiendeCliente clienteThread = new AtiendeCliente(cliente, comunHilos);
				clienteThread.start();
			}
		}
	}
	
	
}
