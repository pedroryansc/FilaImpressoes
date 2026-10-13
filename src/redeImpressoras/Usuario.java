package redeImpressoras;

import java.util.Scanner;

public class Usuario {
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		
		System.out.print("Insira seu nome de usuário: ");
		String nomeUsuario = entrada.next();
		
		boolean encerrar = false;
		
		while(!encerrar) {
			System.out.println("\nUsuário " + nomeUsuario + "\n");
			
			System.out.print("Digite o nome do arquivo para imprimir: ");
			String nomeArquivo = entrada.next();
			
			System.out.println("Quantidade de páginas do arquivo: ");
			int quantPaginas = entrada.nextInt();
			
			Impressao impressao = new Impressao(nomeArquivo, quantPaginas, nomeUsuario);
			
			System.out.println("\nPedido de impressão de '" + nomeArquivo + "' enviado.");
		}
	}
}