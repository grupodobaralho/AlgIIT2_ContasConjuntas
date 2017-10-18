package mine;

public class Aresta {

	public String idConta;
	public Vertice verticeIda;
	public Vertice verticeSaida;
	public int peso;

	public Aresta(String idConta, Vertice verticeIda, Vertice verticeSaida, int peso) {
		this.idConta = idConta;
		this.verticeIda = verticeIda;
		this.verticeSaida = verticeSaida;
		if (peso <= 0)
			this.peso = 1;
		else
			this.peso = peso;
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

	public int getPeso() {
		return peso;
	}

	@Override
	public String toString() {
		return idConta+ " (" + verticeIda + "----" + verticeSaida + ")";
	}

}
