package redeImpressoras;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor {
	private static final int QUANT_IMPRESSORAS = 2;

	public static void main(String[] args) {
		BlockingQueue<Impressora> impressorasLivres = new ArrayBlockingQueue<>(QUANT_IMPRESSORAS);
		
		for(int i = 1; i <= QUANT_IMPRESSORAS; i++) {
			impressorasLivres.add(new Impressora("Impressora " + i));
		}

		// Pool das impressoras
		ExecutorService poolImpressoras = Executors.newFixedThreadPool(QUANT_IMPRESSORAS);

		// Pool das conexões (uma thread para cada cliente conectado)
		ExecutorService poolConexoes = Executors.newCachedThreadPool();

		try(ServerSocket servidor = new ServerSocket(1234)) {
			System.out.println("Servidor executando na porta " + servidor.getLocalPort() + "");
			System.out.println("\n=== LOG DAS IMPRESSORAS ===\n");

			while(true) {
				Socket cliente = servidor.accept();
				System.out.println("Usuário conectado: " + cliente.getRemoteSocketAddress() + "\n");

				poolConexoes.execute(() -> atenderCliente(cliente, poolImpressoras, impressorasLivres));
			}
		} catch(IOException e) {
			System.out.println("\n[ERRO] - Houve um erro na rede de impressoras: " + e.getMessage() + "\n");
		}
	}

	private static void atenderCliente(Socket cliente, ExecutorService poolImpressoras, BlockingQueue<Impressora> impressorasLivres) {
		try(ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream())) {
			while(true) {
				Impressao impressao = (Impressao) entrada.readObject();

				System.out.println("=== Pedido de impressão recebido === \nUsuário: " + impressao.getNomeUsuario() + "\nArquivo: "
						+ impressao.getNomeArquivo() + " (" + impressao.getQuantPaginas() + " pág.)\n");

				poolImpressoras.execute(new TarefaImpressao(impressao, impressorasLivres));
			}
		} catch(Exception e) {
			System.out.println("Erro na conexão com o usuário: " + e.getMessage() + "\n");
		}
	}
}