package br.com.training.entidades;

public class SerieExercicio {
	private ExMusculacao	exMusculacao;
	private int 			repeticao;
	private float 			peso;
	private String 			unidadeMedida;
	
	public SerieExercicio(){
		super();
	}
	public SerieExercicio(ExMusculacao exMusculacao, int repeticao, float peso, String unidadeMedida) {
		super();
		this.exMusculacao	= exMusculacao;
		this.repeticao 		= repeticao;
		this.peso 			= peso;
		this.unidadeMedida 	= unidadeMedida;
	}
	public ExMusculacao getMusculacao() {
		return exMusculacao;
	}
	public void setMusculacao(ExMusculacao exMusculacao) {
		this.exMusculacao = exMusculacao;
	}
	public int getRepeticao() {
		return repeticao;
	}
	public void setRepeticao(int repeticao) {
		this.repeticao = repeticao;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public String gertunidadeMedida(){
		return this.unidadeMedida;
	}
	public void setunidadeMedida(String unidadeMedida){
		this.unidadeMedida = unidadeMedida;
	}
	
}
