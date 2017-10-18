package mine;

public class Aresta {

	public String idConta;
	public Vertice verticeIda;
	public Vertice verticeSaida;

	public Aresta(String idConta, Vertice verticeIda, Vertice verticeSaida) {
		this.idConta = idConta;
		this.verticeIda = verticeIda;
		this.verticeSaida = verticeSaida;
	}

	public String getIdConta() {
		return idConta;
	}

	public Vertice getVerticeIda() {
		return verticeIda;
	}

	public Vertice getVerticeSaida() {
		return verticeSaida;
	}

	@Override
	public String toString() {
		return idConta+ " (" + verticeIda + "----" + verticeSaida + ")";
	}

}
