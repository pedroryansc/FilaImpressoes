package redeImpressoras;

import java.io.Serializable;

public class Impressao implements Serializable {
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
}