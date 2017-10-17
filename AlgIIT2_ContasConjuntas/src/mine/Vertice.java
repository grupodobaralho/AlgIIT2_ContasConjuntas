package mine;

public class Vertice implements Comparable<Vertice> {

	public String cliente;
	public int distancia;

	public Vertice(String cliente, int distancia) {
		this.cliente = cliente;
		this.distancia = distancia;
	}

	public String getCliente() {
		return cliente;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	@Override
	public int compareTo(Vertice v) {
		if (this.distancia < v.distancia)
			return -1;
		else if (this.distancia > v.distancia)
			return 1;
		else
			return 0;
	}
	
	@Override
	public String toString() {
		return cliente;
	}

}
