package main;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
 * @author Israel
 *
 */
public class App {

	public static int tam;
	public static String nodoInicial, nodoFinal;
	public static Grafo g = new Grafo();

	public static void main(String[] args) {
		// Grafo g = new Grafo();
		// g.addVertice("A", Arrays.asList(new Vertice("B", 7), new Vertice("C",
		// 8)));
		// g.addVertice("B", Arrays.asList(new Vertice("A", 7), new Vertice("F",
		// 2)));
		// g.addVertice("C", Arrays.asList(new Vertice("A", 8), new Vertice("F",
		// 6), new
		// Vertice("G", 4)));
		// g.addVertice("D", Arrays.asList(new Vertice("F", 8)));
		// g.addVertice("E", Arrays.asList(new Vertice("H", 1)));
		// g.addVertice("F", Arrays.asList(new Vertice("B", 2), new Vertice("C",
		// 6), new
		// Vertice("D", 8), new Vertice("G", 9), new Vertice("H", 3)));
		// g.addVertice("G", Arrays.asList(new Vertice("C", 4), new Vertice("F",
		// 9)));
		// g.addVertice("H", Arrays.asList(new Vertice("E", 1), new Vertice("F",
		// 3)));
		// System.out.println(g.getShortestPath("A", "H"));

		load("enunciadoTeste.txt");
		System.out.println("finalizou");
	}

	public static void load(String arquivo) {
		Path path = Paths.get(arquivo);
		try (Scanner sc = new Scanner(Files.newBufferedReader(path, Charset.forName("ISO-8859-1")))) {
			tam = Integer.parseInt(sc.next());
			System.out.println(tam);
			String nConta = "", p1 = "", p2 = "";
			int cont = tam;
			while (cont>0) {
				nConta = sc.next();
				p1 = sc.next();
				p2 = sc.next();
				System.out.println(nConta + " " + p1 + " " + p2); //PRINT
				cont--;
				g.addVertice("p1", Arrays.asList(new Vertice("B", 1), new Vertice("C", 1)));
			}
			nodoInicial = sc.next();
			nodoFinal = sc.next();
			System.out.println(nodoInicial + " " + nodoFinal); //PRINT
		} catch (IOException e) {
			System.out.println("FALHOU");
			e.printStackTrace();
		} catch (Throwable e1) {
			System.out.println("A app apresentou o seguinte erro:");
			e1.printStackTrace();
		}

	}
	public static void adicionaAresta(String p1, String p2){
		if(g.getArestas(p1)==null){
			List<Vertice> listaV = new ArrayList<>();
			listaV.add(new Vertice(p1,1));
			g.addVertice(p1, listaV);
		}
	}

}
