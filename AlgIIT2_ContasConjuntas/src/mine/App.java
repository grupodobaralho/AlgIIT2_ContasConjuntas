package mine;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

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
		load("enunciadoTeste.txt");
		Stack<Aresta> caminho = g.dijkstra(nodoInicial, nodoFinal);
		while(!caminho.isEmpty()) {
			System.out.println(caminho.pop());
		}
		//g.printaMap();
		System.out.println("finalizou");
	}

	public static void load(String arquivo) {
		Path path = Paths.get(arquivo);
		try (Scanner sc = new Scanner(Files.newBufferedReader(path, Charset.forName("ISO-8859-1")))) {
			tam = Integer.parseInt(sc.next());
			//System.out.println(tam);
			String idConta = "", cliente1 = "", cliente2 = "";
			int cont = tam;
			while (cont>0) {
				idConta = sc.next();
				cliente1 = sc.next();
				cliente2 = sc.next();
				//System.out.println(nConta + " " + p1 + " " + p2); //PRINT
				cont--;
				g.addAresta(idConta, cliente1, cliente2);
			}
			nodoInicial = sc.next();
			nodoFinal = sc.next();
			//System.out.println(nodoInicial + " " + nodoFinal); //PRINT
		} catch (IOException e) {
			System.out.println("FALHOU");
			e.printStackTrace();
		} catch (Throwable e1) {
			System.out.println("A app apresentou o seguinte erro:");
			e1.printStackTrace();
		}

	}

}
