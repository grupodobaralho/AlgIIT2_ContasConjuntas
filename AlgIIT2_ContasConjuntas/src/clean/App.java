package clean;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Stack;

/** @author1 Israel Deorce @author2 Hercilio Ortiz 
 * @email {israel.deorce, hercilio.ortiz}@acad.pucrs.br 
 * @professor Marcelo Cohen
 * @disciplina Algoritmos e Estruturas de Dados II
 * @data 01 de Novembro de 2017
 */
public class App {
	
    private static int qntContas;
    private static String nodoInicial, nodoFinal;
    private static Grafo g;
    
    /**
     * O Main passa como parametro os arquivos de teste para o metodo
     * que faz a leitura dos arquivos e finaliza o trabalho.
     * @param args
     */
	public static void main(String[] args) {
		
		//Passando o arquivo a ser carregado como parametro, 
		//e uma flag para printar o grafo em uma interface grafica
		load("casos/caso01.txt", false);
		load("casos/caso02.txt", false);
		load("casos/caso03.txt", false);
		load("casos/caso04.txt", false);
		load("casos/caso05.txt", false);
		load("casos/caso06.txt", false);
		load("casos/caso07.txt", false);
		load("casos/caso08.txt", false);
		load("casos/caso09.txt", false);
		load("casos/caso10.txt", false);
		load("casos/enunciadoTeste.txt", true);
		load("casos/meuCaso.txt", true);
		
		System.out.println("\n----------------------------------------------------------------");
		System.out.println("Programa concluidodo");
		System.out.println("Autores:");
		System.out.println("@Israel Deorce - israel.deorce@acad.pucrs.br");
		System.out.println("@Hercilio Ortiz - hercilio.ortiz@acad.pucrs.br");
		System.out.println("Em: 18/10/2017");
		
	}
	
	/**
	 * Metodo auxiliar que verifica se um caminho existe vai apresentando
	 * os resultados enquanto desempilha a pilha de caminho.
	 * @param caminho
	 */
	public static void printa(Stack<Aresta> caminho) {
		System.out.println("@De: " + nodoInicial + " @Para: " + nodoFinal);
		if (caminho == null) {
			System.out.println("Nao existe conexao");
		} else {
			while (!caminho.isEmpty()) {
				System.out.println(caminho.pop());
			}
		}
	}
    
	/**
	 * Metodo que carrega e popula o grafo com os dados de um arquivo 
	 * passado como parametro.
	 * @param arquivo
	 */
	public static void load(String arquivo, boolean mostrarGrafico) {
		g = new Grafo();
		System.out.println("######################## " + arquivo.substring(6) + " #############################");		
		Path path = Paths.get(arquivo);
		try (Scanner sc = new Scanner(Files.newBufferedReader(path, Charset.forName("ISO-8859-1")))) {
			qntContas = Integer.parseInt(sc.next());
			String nroConta, nome1, nome2;
			int cont = qntContas;
			while (cont>0) {
				nroConta = sc.next();
				nome1 = sc.next();
				nome2 = sc.next();
				cont--;					
				g.addAresta(nroConta, nome1, nome2);
			}
			nodoInicial = sc.next();
			nodoFinal = sc.next();	
		} catch (IOException e) {
			System.err.println("Erro de IO: ");
			e.printStackTrace();
			System.exit(1);
		} catch (Throwable e1) {
			System.out.println("A app apresentou o seguinte erro: ");
			e1.printStackTrace();
			System.exit(1);
		}
		
		Stack<Aresta> caminho = g.shortestPathBFS(nodoInicial, nodoFinal);
		if(mostrarGrafico)
			g.fazImagem(nodoInicial, nodoFinal, caminho);
		printa(caminho);


	}
}
