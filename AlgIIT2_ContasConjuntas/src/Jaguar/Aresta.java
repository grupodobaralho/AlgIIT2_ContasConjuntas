package Jaguar;

public class Aresta {
	public String nroConta;
	public Vertice destino;

	public Aresta(String idConta, Vertice destino) {
		this.nroConta = idConta;
		this.destino = destino;
	}

	public String getNroConta() {
		return nroConta;
	}

	public Vertice getDestino() {
		return destino;
	}

	@Override
	public String toString() {
		return nroConta+ " " + destino;
	}
}
