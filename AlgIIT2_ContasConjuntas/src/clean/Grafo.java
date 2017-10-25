package clean;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Esta classe é responsável por criar todos os vertices e arestas do grafo,
 * fazer o caminhamento em largura do mesmo e, ainda, criar a String de sáida
 * do caminhamento.
 * 
 * Esta classe utiliza uma estrutura de dados de Hash Map para representar o grafo.
 * Para realizar o caminhamento utilizou-se outras tres HashMaps.
 * 
 * Para a analise da classe, tenha em mente que foi utilizado um método chamado
 * capturaVertice para garantir que toda Key dos HashMaps seja exatamente a mesma. 
 * 
 * @author Hercilio Martins Ortiz
 *
 */

public class Grafo {
    private Map<Vertice, List<Aresta>> vertices = new HashMap<>();
    private final int INFINITY = Integer.MAX_VALUE;
	Map<Vertice, Integer> marca = new HashMap<>();
    Map<Vertice, Integer> distancia = new HashMap<>();
    Map<Vertice, ArrayList<Object>> anterior = new HashMap<>();
    String caminho = "";
    Vertice n1, n2;
    Aresta As, Ad;
    

	//Classe responsável por criar toda a estrutura do grafo
	public void montaGrafo(String nroConta, String nome1, String nome2) {
		n1 = new Vertice(nome1);
		n2 = new Vertice(nome2);
		As =  new Aresta(nroConta, n2);
		Ad =  new Aresta(nroConta, n1);
		
		if(capturaVertice(n1.getNome()) == null) {
			vertices.put(n1, new ArrayList<Aresta>());
			vertices.get(n1).add(As);			
		} else {
			vertices.get(capturaVertice(n1.getNome())).add(As);
		}
		
		if(capturaVertice(n2.getNome()) == null) {
			vertices.put(n2, new ArrayList<Aresta>());
			vertices.get(n2).add(Ad);
			
		} else {
			vertices.get(capturaVertice(n2.getNome())).add(Ad);
		}
		
		
	}
	
	//Classe que pega o a mesma Key para determinado vertice
	public Vertice capturaVertice(String nome) {
		Vertice v = new Vertice(nome);
		//Esta função é responsável por pegar a Key Objeto, com base em uma string
		//Caso não ecista tal objeto, retorna null
		Vertice key = vertices.entrySet()
                .stream()                       
                .filter(e -> e.getKey().getNome().equals(nome))
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(null);
		return key;
	}
	
  
	public void bfs(String nodoInicial, String nodoFinal) {
		//Adapta as tres HashMaps para o caminhamento
		Vertice s = capturaVertice(nodoInicial);
		Vertice f = capturaVertice(nodoFinal);
		Iterator<Vertice> keys = vertices.keySet().iterator();
		
		for(int i=0; i < vertices.size(); i++) {
			String nome = keys.next().toString();
			Vertice v = capturaVertice(nome);
			//Como HashMap n permite value booleano, utilizou-se um inteiro
			marca.put(v, 0);
			distancia.put(v, INFINITY);
			anterior.put(v, new ArrayList<>());
		}	
		bfs(s,f);
		System.out.println(imprimeCaminho(f));
	}


	private void bfs(Vertice s, Vertice f) {
		//Este metodo irá realziar o caminhamento em largura do grafo
    	Queue<Vertice> q = new Queue<Vertice>();
    	distancia.put(s, 0);
    	marca.put(s, 1);
        q.enqueue(s);

        while (!q.isEmpty()) {
        	//quando o vertice alvo for atinjido, o caminhamento para.
        	if(hasPathTo(f)) return;
            Vertice v = q.dequeue();
            for (Aresta w1 : vertices.get(v)) {
            	Vertice w = capturaVertice(w1.getDestino().getNome());
                if (marca.get(w) == 0) {            	
                    anterior.get(w).add(v);
                    anterior.get(w).add(w1);
                    distancia.put(w, distancia.get(v) + 1);
                    marca.put(w, marca.get(w) + 1);
                    q.enqueue(w);
                } 
            }
        } 
	}
	
	//Metodo responsavel por verificar se o vertici já foi analisado.
	public boolean hasPathTo(Vertice v) {
		int bool = marca.get(v);
        if(bool == 0) return false;
        return true;
    }
    
	//Método que verifica a distancia do determinado vertici com base no original
    public int distTo(Vertice v) {
        return distancia.get(v);
    }
    
    //Cria uma string q representa um caminhamento
    public String imprimeCaminho(Object v) {
    	if(distTo((Vertice) v) == 0) return caminho = anterior.get(v).toString();
    	String verticeAtual = anterior.get(v).get(0).toString() + " "
    						+ anterior.get(v).get(1).toString();
    	imprimeCaminho(anterior.get(v).get(0));
    	return caminho+= "\n-> " + verticeAtual;
    }
}
