package redeImpressoras;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Servidor {
	public static void main(String[] args) {
		try {
			ServerSocket servidor = new ServerSocket(1234);
			
			System.out.println("Endereço do servidor: " + servidor.getInetAddress().getHostAddress());
			System.out.println("Porta do servidor: " + servidor.getLocalPort());
			System.out.println("Está vinculado a um endereço? " + servidor.isBound());
			
			ExecutorService executor = Executors.newFixedThreadPool(2);
			
			System.out.println("\n=== LOG DAS IMPRESSORAS ===\n");
			
			while(true) {
				Socket cliente = servidor.accept();
				System.out.println("Usuário conectado");
				
				ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
				Impressao impressao = (Impressao) entrada.readObject();
				
				System.out.println("\n=== Pedido de impressão recebido === \n"
						+ "Usuário: " + impressao.getNomeUsuario() + "\n"
						+ "Arquivo: " + impressao.getNomeArquivo() + " (" + impressao.getQuantPaginas() + " páginas)\n");
				
				executor.execute(impressao);
				
				entrada.close();
			}
		} catch(Exception e) {
			System.out.println("\nERRO - Houve um erro na rede de impressoras: " + e.getMessage() + "\n");
		}
	}
}