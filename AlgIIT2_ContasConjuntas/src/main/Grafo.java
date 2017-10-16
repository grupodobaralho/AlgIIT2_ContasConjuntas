package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Grafo {

	private final Map<String, List<Vertice>> vertices;

	public Grafo() {
		this.vertices = new HashMap<String, List<Vertice>>();
	}

	public void addVertice(String String, List<Vertice> Vertice) {
		this.vertices.put(String, Vertice);
	}

	/**
	 * Retorna a lista de vértices que um determinado nodo se conecta,
	 * ou Nulo se não se conectar com ninguém
	 * @param vertice
	 * @return
	 */
	public List<Vertice> getArestas(String vertice) {
		if (!vertices.get(vertice).isEmpty())
			return vertices.get(vertice);
		else
			return null;
	}

	public List<String> getShortestPath(String start, String finish) {
		final Map<String, Integer> distances = new HashMap<String, Integer>();
		final Map<String, Vertice> previous = new HashMap<String, Vertice>();
		PriorityQueue<Vertice> nodes = new PriorityQueue<Vertice>();

		for (String Vertice : vertices.keySet()) {
			if (Vertice == start) {
				distances.put(Vertice, 0);
				nodes.add(new Vertice(Vertice, 0));
			} else {
				distances.put(Vertice, Integer.MAX_VALUE);
				nodes.add(new Vertice(Vertice, Integer.MAX_VALUE));
			}
			previous.put(Vertice, null);
		}

		while (!nodes.isEmpty()) {
			Vertice smallest = nodes.poll();
			if (smallest.getId() == finish) {
				final List<String> path = new ArrayList<String>();
				while (previous.get(smallest.getId()) != null) {
					path.add(smallest.getId());
					smallest = previous.get(smallest.getId());
				}
				return path;
			}

			if (distances.get(smallest.getId()) == Integer.MAX_VALUE) {
				break;
			}

			for (Vertice neighbor : vertices.get(smallest.getId())) {
				Integer alt = distances.get(smallest.getId()) + neighbor.getDistance();
				if (alt < distances.get(neighbor.getId())) {
					distances.put(neighbor.getId(), alt);
					previous.put(neighbor.getId(), smallest);

					forloop: for (Vertice n : nodes) {
						if (n.getId() == neighbor.getId()) {
							nodes.remove(n);
							n.setDistance(alt);
							nodes.add(n);
							break forloop;
						}
					}
				}
			}
		}

		return new ArrayList<String>(distances.keySet());
	}

}
