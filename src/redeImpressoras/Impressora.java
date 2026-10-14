package redeImpressoras;

public class Impressora {
	private static final int CAPACIDADE_PAPEL = 10;
	private static final int TEMPO_POR_PAGINA = 2000;
	private static final int TEMPO_REPOSICAO = 10000;

	private String nome;
	private int estoquePapel;

	public Impressora(String nome) {
		this.nome = nome;
		this.estoquePapel = CAPACIDADE_PAPEL;
	}

	public String getNome() {
		return nome;
	}

	// Este método é executado apenas pela impressão que adquiriu (e bloqueou) o acesso à impressora
	public void imprimir(Impressao impressao) {
		System.out.println(nome + ": Iniciando a impressão de '" + impressao.getNomeArquivo() + "' (" + impressao.getQuantPaginas()
				+ " pág.) de " + impressao.getNomeUsuario() + " | Papel restante: " + estoquePapel + " folha(s)\n");
		
		int tempoImpressao = (TEMPO_POR_PAGINA * impressao.getQuantPaginas()) / 1000;
		
		for(int pagina = 1; pagina <= impressao.getQuantPaginas(); pagina++) {
			if (estoquePapel == 0) {
				reporPapel();
				
				tempoImpressao += TEMPO_REPOSICAO / 1000;
			}

			estoquePapel--;
			
			try {
				Thread.sleep(TEMPO_POR_PAGINA);
			} catch(InterruptedException e) {
				System.out.println(
					"[ERRO] " + nome + ": Houve um erro ao imprimir '" + impressao.getNomeArquivo() + "' (Usuário " + impressao.getNomeUsuario() + ")\n"
				);
			}

			System.out.println(nome + ": Imprimindo '" + impressao.getNomeArquivo() + "' (página " + pagina + "/"
					+ impressao.getQuantPaginas() + ") | Papel restante: " + estoquePapel + " folha(s)\n");
		}

		System.out.println(nome + ": Impressão '" + impressao.getNomeArquivo() + "' (" + impressao.getNomeUsuario() + ") finalizada em "
				+ tempoImpressao + " segundos.\n");
	}

	private void reporPapel() {
		System.out.println(nome + ": Sem papel. Repondo estoque...\n");
		
		try {
			Thread.sleep(TEMPO_REPOSICAO);
		} catch(InterruptedException e) {
			System.out.println("[ERRO] " + nome + ": Houve um erro ao repor o estoque de folhas - " + e.getMessage() + "\n");
		}
		
		estoquePapel = CAPACIDADE_PAPEL;
		
		System.out.println(nome + ": Papel reposto (" + CAPACIDADE_PAPEL + " folhas)\n");
	}
}