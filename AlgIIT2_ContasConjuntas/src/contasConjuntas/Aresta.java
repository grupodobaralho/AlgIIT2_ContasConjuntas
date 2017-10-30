package contasConjuntas;

public class Aresta {
	
	/**
	 * @author1 Israel Deorce @author2 Hercilio Ortiz
	 * Uma aresta possui:
	 * Um ID de uma conta conjunta;
	 * Um vertice de saida (STRING=NOME DE CLIENTE);
	 * Um vertice de chegada (STRING=NOME DE CLIENTE).
	 */
	public String idConta;
	public String verticeSaida;
	public String verticeChegada;

	public Aresta(String idConta, String verticeSaida, String verticeChegada) {
		this.idConta = idConta;
		this.verticeSaida = verticeSaida;
		this.verticeChegada = verticeChegada;
	}

	public String getIdConta() {
		return idConta;
	}

	public String getVerticeSaida() {
		return verticeSaida;
	}

	public String getVerticeChegada() {
		return verticeChegada;
	}

	@Override
	public String toString() {
		return idConta+ " (" + verticeSaida + "----" + verticeChegada + ")";
	}

}
