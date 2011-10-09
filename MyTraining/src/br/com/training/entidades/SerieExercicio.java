package br.com.training.entidades;

public class SerieExercicio {
	private Musculacao musculacao;
	private int repeticao;
	private float peso;
	
	public SerieExercicio(Musculacao musculacao, int repeticao, float peso) {
		this.musculacao = musculacao;
		this.repeticao = repeticao;
		this.peso = peso;
	}
	public Musculacao getMusculacao() {
		return musculacao;
	}
	public void setMusculacao(Musculacao musculacao) {
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
