package br.com.training.entidades;

public class SerieExercicio {
	private ExMusculacao musculacao;
	private int repeticao;
	private float peso;
	
	public SerieExercicio(){
		super();
	}
	
	public SerieExercicio(ExMusculacao musculacao, int repeticao, float peso) {
		this.musculacao = musculacao;
		this.repeticao = repeticao;
		this.peso = peso;
	}
	public ExMusculacao getMusculacao() {
		return musculacao;
	}
	public void setMusculacao(ExMusculacao musculacao) {
		this.musculacao = musculacao;
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
	
}
