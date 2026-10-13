package redeImpressoras;

import java.io.Serializable;

public class Impressao implements Runnable, Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nomeArquivo;
	private int quantPaginas;
	private String nomeUsuario;
	
	public Impressao(String nomeArquivo, int quantPaginas, String nomeUsuario) {
		this.nomeArquivo = nomeArquivo;
		this.quantPaginas = quantPaginas;
		this.nomeUsuario = nomeUsuario;
	}
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	
	public int getQuantPaginas() {
		return quantPaginas;
	}
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	
	public void run() {
		String nomeImpressora = Thread.currentThread().getName();
		
		System.out.println("Usuário " + nomeUsuario + " conectado à Impressora " + nomeImpressora + "\n");
		
		System.out.println("Impressora X: Iniciando a impressão de '" + nomeArquivo + "' - " + quantPaginas + " página(s)\n");
		
		int tempoImpressao = quantPaginas * 2000;
		
		try {
			Thread.sleep(tempoImpressao);
		} catch(InterruptedException e) {
			System.out.println("Erro na Impressora X: Ocorreu um erro ao imprimir '" + nomeArquivo + "' (Usuário " + nomeUsuario + ")\n");
			e.printStackTrace();
			
			Thread.currentThread().interrupt();
		}
		
		System.out.println("Impressora X: Impressão de '" + nomeArquivo + "' (Usuário " + nomeUsuario + ") finalizada em " + (tempoImpressao / 1000) + "s\n");
	}
}