package redeImpressoras;

import java.net.ServerSocket;

public class Servidor {
	public static void main(String[] args) {
		try {
			ServerSocket servidor = new ServerSocket(5000);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
