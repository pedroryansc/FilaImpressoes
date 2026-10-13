package redeImpressoras;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Main {
	public static void main(String[] args) {
		Impressao impressao1 = new Impressao("arquivo.pdf", 5, "Pedro");
		Impressao impressao2 = new Impressao("fichaDnD", 3, "Vinícius");
		Impressao impressao3 = new Impressao("Prova Métodos Numéricos", 13, "Milton");
		Impressao impressao4 = new Impressao("teste.pdf", 1, "Igor");
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		System.out.println("\n=== LOG DAS IMPRESSORAS ===\n");
		
		executor.execute(impressao1);
		executor.execute(impressao2);
		executor.execute(impressao3);
		executor.execute(impressao4);
		
		executor.shutdown();
	}
}