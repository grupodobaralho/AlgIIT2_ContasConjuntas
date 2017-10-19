package Jaguar;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class App {
    private static int qntContas;
    
	public static void main(String[] args) {
		load("casos/caso01.txt");
		load("casos/caso02.txt");
		load("casos/caso03.txt");
		load("casos/caso04.txt");
		load("casos/caso05.txt");
		load("casos/caso06.txt");
		load("casos/caso07.txt");
		load("casos/caso08.txt");
		load("casos/caso09.txt");
		load("casos/caso10.txt");
		
	}
    
	public static void load(String arquivo) {
		Grafo G = new Grafo();
		Path path = Paths.get(arquivo);
		try (Scanner sc = new Scanner(Files.newBufferedReader(path, Charset.forName("utf8")))) {
			qntContas = Integer.parseInt(sc.next());
			String nroConta, nome1, nome2;
			int cont = qntContas;
			while (cont>0) {
				nroConta = sc.next();
				nome1 = sc.next();
				nome2 = sc.next();
				G.montaGrafo(nroConta, nome1, nome2);
				cont--;				
			}
			String nodoInicial = sc.next();
			String nodoFinal = sc.next();
			System.out.println("#################### " + arquivo + " ####################");
			System.out.println("De " + nodoInicial + " para " + nodoFinal);
			G.bfs(nodoInicial, nodoFinal);	
		
		} catch (IOException e) {
			System.out.println("FALHOU");
			e.printStackTrace();
		} catch (Throwable e1) {
			System.out.println("A app apresentou o seguinte erro:");
			e1.printStackTrace();
		}

	}
}
