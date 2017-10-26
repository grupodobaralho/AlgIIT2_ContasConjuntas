package clean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class Grafo {
	/** @author1 Israel Deorce @author2 Hercilio Ortiz */
	private Map<String, List<Aresta>> adj;			//Estrutura Hash para consulta de Arestas
	private final int INFINITO = Integer.MAX_VALUE;	//Constante de valor m�ximo de um int

	public Grafo() {
		adj = new HashMap<>();
	}

	/**
	 * Adiciona as arestas na estrutura Hash de arestas "adj". Cada aresta �
	 * referente � uma conta conjunta e possui dois verticies que representam um
	 * cliente cada
	 */
	public void addAresta(String idConta, String cliente1, String cliente2) {
		Aresta novaAresta = new Aresta(idConta, cliente1, cliente2);
		if (adj.containsKey(cliente1)) {
			adj.get(cliente1).add(novaAresta);
		} else {
			adj.put(cliente1, new ArrayList<Aresta>());
			adj.get(cliente1).add(novaAresta);
		}

		if (adj.containsKey(cliente2)) {
			adj.get(cliente2).add(novaAresta);
		} else {
			adj.put(cliente2, new ArrayList<Aresta>());
			adj.get(cliente2).add(novaAresta);
		}
	}

	/**
	 * Algoritmo de busca de menor caminho de um nodoInicial � um final.
	 * Ele � uma adapta��o do Breadth-first search
	 */
	public Stack<Aresta> shortestPathBFS(String nodoInicial, String nodoFinal) {
		Map<String, Integer> mark = new HashMap<>();	//Marca os nodos ja visitados
		Map<String, Integer> distTo = new HashMap<>();	//Armazena a distancia (nao foi utilizada na proposta do trab)
		Map<String, String> edgeTo = new HashMap<>();	//Armazena o nodo anterior mais proximo (menor caminho)
		Queue<String> queue = new Queue<String>();		//Fila auxiliar para o BFS
		Set<String> vEnchergados = new HashSet<>();		//Set para evitar problemas com loops (diferencial do BFS comum)
		boolean achouCaminho = false;					//Flag para sinalizar a existencia de um caminho (economia)

		//Popula as estruturas inicializadas acima. Assumimos distancia infinita
		//para todos os vertices, tirando o inicial que possui distancia 0 para si.
		for (String v : adj.keySet()) {
			if (v.equals(nodoInicial)) {
				distTo.put(v, 0);
			} else {
				distTo.put(v, INFINITO);
			}
			mark.put(v, 0);
			edgeTo.put(v, null);
		}

		distTo.put(nodoInicial, 0);
		queue.enqueue(nodoInicial);

		//o BFS termina somente quando a fila estiver vazia (
		while (!queue.isEmpty()) {
			String vProximo = queue.dequeue();
			vEnchergados.add(vProximo);
			mark.put(vProximo, 1);
			for (Aresta aresta : adj.get(vProximo)) {
				String vS = aresta.verticeSaida;
				String vC = aresta.verticeChegada;
				if (!vEnchergados.contains(vS) || !vEnchergados.contains(vC)) {
					if (vProximo.equals(vS) && mark.get(vC) == 0) {
						distTo.put(vC, distTo.get(vProximo) + 1);
						edgeTo.put(vC, vProximo);
						queue.enqueue(vC);
						if (vC.equals(nodoFinal)) {
							achouCaminho = true;
							vProximo = nodoFinal;
							break;						}
					} else if (mark.get(vS) == 0) {
						distTo.put(vS, distTo.get(vProximo) + 1);
						edgeTo.put(vS, vProximo);
						queue.enqueue(vS);
						if (vS.equals(nodoFinal)) {
							achouCaminho = true;
							vProximo = nodoFinal;
							break;
						}
					}
				}
				vEnchergados.add(vS);
				vEnchergados.add(vC);
			}
			
			//Se encontramos um caminho, voltamos o edgeTo e devolvemos uma pilha com o resultado
			if (achouCaminho) {
				Stack<Aresta> caminho = new Stack<>();
				while (edgeTo.get(vProximo) != null) {
					for (Aresta aresta2 : adj.get(vProximo)) {
						String nChegada = edgeTo.get(vProximo);
						if (aresta2.getVerticeChegada().equals(nChegada)
								|| aresta2.getVerticeSaida().equals(nChegada)) {
							caminho.push(aresta2);
							vProximo = nChegada;
							break;
						}
					}
				}
				return caminho;
			}
		}
		return null;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (Map.Entry<String, List<Aresta>> entry : adj.entrySet()) {
			String key = entry.getKey();
			List<Aresta> value = entry.getValue();
			str.append(key + "->| ");
			str.append(value + "\n");
		}
		return str.toString();
	}
	
    public void fazImagem(String source, String end, Stack<Aresta> caminho) {
    	String styleSheet =
	            "node {" +
	            "	fill-color: black;" +
	            "   text-size: 20;" +
	            "}" +
	            "node.marked {" +
	            "	fill-color: red;" +
	            "}" +
	            "node.start {" +
	            "   fill-color: green;" +
	            "   text-size: 30;"+
	            "}" +
	            "node.end {" +
	            "   fill-color: blue;" +
	            "   text-size: 30;"+
	            "}" ;
    	styleSheet += "edge { arrow-shape: none; size: 2;}";
		   	
    	
		// Pego todos os vertices do grafo
    	Iterator<String> keys = adj.keySet().iterator();
    	//Cria o grafo que sera utilizado
		Graph g = new SingleGraph("Print");
		
		//Copia para o grafo g os vertices do grafo PAI
		for(int v=0; v< adj.size(); v++) {
			String vertice = keys.next();
			Node n = g.addNode(vertice);
			n.addAttribute("ui.label", vertice);
			if(adj.get(vertice) != null)
				n.addAttribute("ui.class", "marked");	
			//n.setAttribute("ui.style", "size: 30;");
		}
		
        Set<String> set = new HashSet<>();
		Node n = g.getNode(source);
		n.setAttribute("ui.class", "start");
				
		Iterator<String> keys2 = adj.keySet().iterator();
		
		//Copia para o grafo g as arestas do grafo PAI
		while(keys2.hasNext()) {
			
			String v = keys2.next();
			
			for(Aresta a: adj.get(v)) {
				
				String v1 = a.getVerticeSaida();
				String v2 = a.getVerticeChegada();
				String w = a.getIdConta();
				String id1 = v1+v2;
				String id2 = v2+v1;
				
				if(!(!set.isEmpty() && (set.contains(id1) || set.contains(id2)))) {
					Edge ne = g.addEdge(id1, v1, v2, true);
					set.add(id1);
					set.add(id2);					
					ne.addAttribute("ui.label", w);
				}	
			}
		}
		Node nEnd = g.getNode(end);
		nEnd.setAttribute("ui.class", "end");
		
		//Pinta de vermelho as arestas que formam o caminho
		for(int i=0; i<caminho.size(); i++) {
			String saida = caminho.get(i).getVerticeSaida();
			String chegada = caminho.get(i).getVerticeChegada();
			Edge e = g.getEdge(saida+chegada);
			e.addAttribute("ui.style", "size: 3px; fill-color: rgb(255,255,0);");	
		}
		
		// Solicita alta qualidade no desenho
		g.addAttribute("ui.quality");
		//g.addAttribute("ui.antialias");
		
		// Caso se queira utilizar todos os recursos de CSS do visualizador avançado:
		//System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		
		// Informa a style sheet a ser utilizada (se for o caso)
	    g.addAttribute("ui.stylesheet", styleSheet);
	    
	    // Exibe o grafo
		g.display();
    }
}
