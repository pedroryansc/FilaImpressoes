package redeImpressoras;

import java.util.concurrent.BlockingQueue;

public class TarefaImpressao implements Runnable {
	private final Impressao impressao;
	private final BlockingQueue<Impressora> impressorasLivres;

	public TarefaImpressao(Impressao impressao, BlockingQueue<Impressora> impressorasLivres) {
		this.impressao = impressao;
		this.impressorasLivres = impressorasLivres;
	}

	@Override
	public void run() {
		Impressora impressora = null;

		try {
			// Conecta-se a uma impressora disponível. Se todas estiverem ocupadas, a tarefa é bloqueada e aguarda
			impressora = impressorasLivres.take();
			impressora.imprimir(impressao);
		} catch (InterruptedException e) {
			System.out.println("Impressão de '" + impressao.getNomeArquivo() + "' interrompida\n");
			Thread.currentThread().interrupt();
		} finally {
			if (impressora != null) {
				// Independentemente se a impressão teve erro ou não, a impressora é liberada após o uso
				impressorasLivres.offer(impressora);
			}
		}
	}
}