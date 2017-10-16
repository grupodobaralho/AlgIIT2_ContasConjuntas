package main;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class App {

	public static String tam="a";
	public static Grafo g = new Grafo();

	public static void main(String[] args) {
		// Grafo g = new Grafo();
		// g.addVertice("A", Arrays.asList(new Vertice("B", 7), new Vertice("C", 8)));
		// g.addVertice("B", Arrays.asList(new Vertice("A", 7), new Vertice("F", 2)));
		// g.addVertice("C", Arrays.asList(new Vertice("A", 8), new Vertice("F", 6), new
		// Vertice("G", 4)));
		// g.addVertice("D", Arrays.asList(new Vertice("F", 8)));
		// g.addVertice("E", Arrays.asList(new Vertice("H", 1)));
		// g.addVertice("F", Arrays.asList(new Vertice("B", 2), new Vertice("C", 6), new
		// Vertice("D", 8), new Vertice("G", 9), new Vertice("H", 3)));
		// g.addVertice("G", Arrays.asList(new Vertice("C", 4), new Vertice("F", 9)));
		// g.addVertice("H", Arrays.asList(new Vertice("E", 1), new Vertice("F", 3)));
		// System.out.println(g.getShortestPath("A", "H"));

		load("tinyG.txt");
		System.out.println("a?");
	}

	public static void load(String arquivo) {
		Path path = Paths.get(arquivo);
		try (Scanner sc = new Scanner(Files.newBufferedReader(path, Charset.forName("utf8")))) {
			//sc.useDelimiter("[\\S*\n]");			
			tam = sc.next();
			System.out.println(tam);
			String nConta, p1 = "a", p2 = "a";
			while (sc.hasNext()) {
				nConta = sc.next();
				if (sc.hasNext())
				p1 = sc.next();
				if (sc.hasNext())
					p2 = sc.next();
				System.out.println(nConta + " " + p1 + " " + p2);
			}
		} catch (IOException e) {
			System.out.println("FALHOU");
			e.printStackTrace();
		} catch (Throwable e1) {
			System.out.println("A app apresentou o seguinte erro:");
			e1.printStackTrace();
		}

	}

}
