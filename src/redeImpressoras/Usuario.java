package redeImpressoras;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Usuario {
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		
		try(Socket cliente = new Socket("127.0.0.1", 1234)) {
			ObjectOutputStream envio = new ObjectOutputStream(cliente.getOutputStream());
			
			System.out.print("Insira seu nome de usuário: ");
			String nomeUsuario = entrada.nextLine();

			boolean encerrar = false;
			
			while(!encerrar) {
				System.out.println("\nUsuário " + nomeUsuario + "\n");

				System.out.print("Digite o nome do arquivo para imprimir (ou deixe vazio para sair): ");
				String nomeArquivo = entrada.nextLine();
				
				if (nomeArquivo.isBlank()) {
					encerrar = true;
				} else {
					System.out.print("Quantidade de páginas do arquivo: ");
					int quantPaginas = Integer.parseInt(entrada.nextLine().trim());

					Impressao pedido = new Impressao(nomeArquivo, quantPaginas, nomeUsuario);
					
					envio.writeObject(pedido);
					envio.flush();
	
					System.out.println("\nPedido de impressão de '" + nomeArquivo + "' enviado.");
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO - Houve um erro na conexão com a rede de impressoras: " + e.getMessage());
		}
		
		entrada.close();
		
		System.out.println("\nFim do programa.");
	}
}