package clean;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class App {
	
    private static int qntContas;
    private static String nodoInicial, nodoFinal;
    private static Grafo g;
    
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
		load("casos/enunciadoTeste.txt");
		load("casos/meuCaso.txt");
		
		System.out.println("\n----------------------------------------------------------------");
		System.out.println("Programa concluído");
		System.out.println("Autores:");
		System.out.println("@Israel Deorce - israel.deorce@acad.pucrs.br");
		System.out.println("@Hercilio Ortiz - hercilio.ortiz@acad.pucrs.br");
		System.out.println("Em: 18/10/2017");
		
	}
    
	public static void load(String arquivo) {
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
				g.montaGrafo(nroConta, nome1, nome2);
			}
			nodoInicial = sc.next();
			nodoFinal = sc.next();	
			System.out.println("De " + nodoInicial + " para " + nodoFinal);
			g.bfs(nodoInicial, nodoFinal);	
		} catch (IOException e) {
			System.err.println("Erro de IO: ");
			e.printStackTrace();
			System.exit(1);
		} catch (Throwable e1) {
			System.out.println("A app apresentou o seguinte erro: ");
			e1.printStackTrace();
			System.exit(1);
		}


	}
}
