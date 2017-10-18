package mine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

public class Grafo {

	public Map<String, List<Aresta>> adj;

	public Grafo() {
		adj = new HashMap<>();
	}

	public void addAresta(String idConta, String cliente1, String cliente2) {
		Vertice v1 = new Vertice(cliente1, 1);
		Vertice v2 = new Vertice(cliente2, 1);
		Aresta novaAresta = new Aresta(idConta, v1, v2);

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

	public Stack<Aresta> dijkstra(String inicio, String fim) {
		Map<String, Integer> distancia = new HashMap<>();
		Map<String, Vertice> anterior = new HashMap<>();
		PriorityQueue<Vertice> heap = new PriorityQueue<>();

		for (String v : adj.keySet()) {
			if (v.equals(inicio)) {
				distancia.put(v, 0);
				heap.add(new Vertice(v, 0));
			} else {
				distancia.put(v, Integer.MAX_VALUE);
				heap.add(new Vertice(v, Integer.MAX_VALUE));
			}
			anterior.put(v, null);
		}

		while (!heap.isEmpty()) {
			
			Vertice menor = heap.poll();
			
			//Se chegou ao fim, junta o caminho de arestas e retorna
			if(menor.getCliente().equals(fim)) {
				Stack<Aresta> caminho = new Stack<>();
				while(anterior.get(menor.getCliente()) != null) {
					for(Aresta a : adj.get(menor.getCliente())) {						
						if(a.getVerticeIda().getCliente().equals(anterior.get(menor.getCliente()).getCliente()) ||
								a.getVerticeSaida().getCliente().equals(anterior.get(menor.getCliente()).getCliente())) {
							caminho.push(a);
							menor = anterior.get(menor.getCliente());		
							break;
						}
					}
				}
				//System.out.println("dist: "+ distancia.get(fim));
				return caminho;
			}
			
			if (distancia.get(menor.getCliente()) == Integer.MAX_VALUE) {
				break;
			}
			
			//para cada aresta que liga o nodo, ele pega o nodo vizinho em que ela chega
			//e atualiza a distancia dele e da onde ele veio
			for(Aresta a : adj.get(menor.getCliente())) {
				Vertice vizinho;
				
				//O vizinho é o vértice da aresta que não é o "menor"
				if(a.getVerticeIda().getCliente().equals(menor.getCliente()))
					vizinho = a.getVerticeSaida();
				else
					vizinho = a.getVerticeIda();
				
				//soma a distancia do menor para o nodo inicial com a distancia do vizinho "1"
				int alt = distancia.get(menor.getCliente()) + vizinho.getDistancia();
				
				//Caso encontrou uma rota melhor, atualiza...
				if(alt < distancia.get(vizinho.getCliente())) {
					
					//A nova distancia do vertice vizinho para o nodo inicial
					distancia.put(vizinho.getCliente(), alt);
					//Este vertice vizinho veio do menor
					anterior.put(vizinho.getCliente(), menor);
					
					//Procura no heap pelo vizinho, tira ele, atualiza a distancia e
					//poe de volta para reordenar o heap
					for(Vertice v : heap) {
						if(v.getCliente().equals(vizinho.getCliente())) {
							heap.remove(v);
							v.setDistancia(alt);
							heap.add(v);
							break;
						}
					}
				}
			}
			
		}			
		return null;
	}

	public void printaMap() {
		StringBuilder str = new StringBuilder();
		for (Map.Entry<String, List<Aresta>> entry : adj.entrySet()) {
			String key = entry.getKey();
			List<Aresta> value = entry.getValue();
			str.append(key + "->| ");
			str.append(value + "\n");
		}
		System.out.println(str.toString());
	}
}
