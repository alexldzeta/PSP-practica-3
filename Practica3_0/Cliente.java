package Practica3_0;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	
	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		// try-with-resource
		try (Socket socket = new Socket("localhost", 1999)) {

			DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

			System.out.println("Nombre de usuario?");
			String usuario = teclado.nextLine();
			dataOutputStream.writeUTF(usuario);

			AtiendeServidor threadServer = new AtiendeServidor(socket);
			threadServer.start();

			String mensage = teclado.nextLine();

			while (!mensage.equals("Salir")) {
				
				dataOutputStream.writeUTF(mensage);
				mensage = teclado.nextLine();
				
			}

			dataOutputStream.writeUTF(mensage);
			System.out.println("Se ha desconectado del chat");
			
		}
	}
	

}
