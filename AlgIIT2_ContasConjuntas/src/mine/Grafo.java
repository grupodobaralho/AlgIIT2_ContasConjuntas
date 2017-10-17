package mine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Grafo {

	public Map<String, List<Aresta>> adj;
	//public Map<String, Vertice> vertices;

	public Grafo() {
		adj = new HashMap<>();
	}

	public void addAresta(String idConta, String cliente1, String cliente2) {
		Vertice v1 = new Vertice(cliente1, Integer.MAX_VALUE);
		Vertice v2 = new Vertice(cliente2, Integer.MAX_VALUE);
		Aresta novaAresta = new Aresta(idConta, v1, v2, 1);

		if (adj.containsKey(cliente1)) {
			adj.get(cliente1).add(novaAresta);
		} else {
			adj.put(cliente1, new ArrayList<Aresta>());
			adj.get(cliente1).add(novaAresta);
			//vertices.put(cliente1, v1);
		}

		if (adj.containsKey(cliente2)) {
			adj.get(cliente2).add(novaAresta);
		} else {
			adj.put(cliente2, new ArrayList<Aresta>());
			adj.get(cliente2).add(novaAresta);
			//vertices.put(cliente2, v2);
		}

	}

	public List<String> dijkstra(String inicio, String fim) {
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
			if(menor.getCliente().equals(fim)) {
				List<String> caminho = new ArrayList<String>();
				while(anterior.get(menor.getCliente()) != null) {
					caminho.add(menor.getCliente());
					menor = anterior.get(menor.getCliente());
				}
				return caminho;
			}
			
			if (distancia.get(menor.getCliente()) == Integer.MAX_VALUE) {
				break;
			}
			
			for(Aresta a : adj.get(menor.getCliente())) {
				Vertice vizinho;
				if(a.getVerticeIda().getCliente().equals(menor.getCliente()))
					vizinho = a.getVerticeSaida();
				else
					vizinho = a.getVerticeIda();
				int alt = distancia.get(menor.getCliente()) + vizinho.getDistancia();
				if(alt < distancia.get(vizinho.getCliente())) {
					distancia.put(vizinho.getCliente(), alt);
					anterior.put(vizinho.getCliente(), menor);
					
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
		return new ArrayList<String>(distancia.keySet());
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

	public void printaPrioridade() {
		// System.out.println(heap);
	}

}
