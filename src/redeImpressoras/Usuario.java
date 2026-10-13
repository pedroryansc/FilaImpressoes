package redeImpressoras;

import java.util.Scanner;
import java.net.Socket;
import java.io.ObjectOutputStream;

public class Usuario {
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		
		try {
			Socket cliente = new Socket("127.0.0.1", 1234);
			
			ObjectOutputStream envio = new ObjectOutputStream(cliente.getOutputStream());
			
			System.out.print("Insira seu nome de usuário: ");
			String nomeUsuario = entrada.nextLine();
			
			boolean encerrar = false;
			
			while(!encerrar) {
				System.out.println("\nUsuário " + nomeUsuario + "\n");
				
				System.out.print("Digite o nome do arquivo para imprimir: ");
				String nomeArquivo = entrada.nextLine();
				
				System.out.print("Quantidade de páginas do arquivo: ");
				int quantPaginas = entrada.nextInt();
				
				Impressao impressao = new Impressao(nomeArquivo, quantPaginas, nomeUsuario);
				
				envio.writeObject(impressao);
				envio.flush();
				
				System.out.println("\nPedido de impressão de '" + nomeArquivo + "' enviado.");
				
				entrada.nextLine();
			}
			
			entrada.close();
			envio.close();
			cliente.close();
		} catch(Exception e) {
			System.out.println("ERRO - Houve um erro na conexão com a rede de impressoras: " + e.getMessage());
		}
	}
}