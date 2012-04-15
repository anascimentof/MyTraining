package br.com.training.entidades;

public class ExMusculacao {
	private Exercicio		exercicio;
	private Equipamento 	equipamento[];
	private Musculo			musculos[];
	private SerieExercicio 	serie[];
	private String 			velocidade;
	private int 			tempoDescanso;
	private String 			modoExecucao; 
	private String 			tipoPegada;
	private String 			posicaoBanco;
	private String 			amplitude;
	
	public ExMusculacao(Equipamento[] equipamento, Musculo[] musculos,
			SerieExercicio[] serie, String velocidade, int tempoDescanso,
			String modoExecucao, String tipoPegada, String posicaoBanco,
			String amplitudade, String tipoExercicio) {
		this.equipamento = equipamento;
		this.musculos = musculos;
		this.serie = serie;
		this.velocidade = velocidade;
		this.tempoDescanso = tempoDescanso;
		this.modoExecucao = modoExecucao;
		this.tipoPegada = tipoPegada;
		this.posicaoBanco = posicaoBanco;
		this.amplitude = amplitudade;
	}

	public Equipamento[] getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento[] equipamento) {
		this.equipamento = equipamento;
	}

	public Musculo[] getMusculos() {
		return musculos;
	}

	public void setMusculos(Musculo[] musculos) {
		this.musculos = musculos;
	}

	public SerieExercicio[] getSerie() {
		return serie;
	}

	public void setSerie(SerieExercicio[] serie) {
		this.serie = serie;
	}

	public String getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(String velocidade) {
		this.velocidade = velocidade;
	}

	public int getTempoDescanso() {
		return tempoDescanso;
	}

	public void setTempoDescanso(int tempoDescanso) {
		this.tempoDescanso = tempoDescanso;
	}

	public String getModoExecucao() {
		return modoExecucao;
	}

	public void setModoExecucao(String modoExecucao) {
		this.modoExecucao = modoExecucao;
	}

	public String getTipoPegada() {
		return tipoPegada;
	}

	public void setTipoPegada(String tipoPegada) {
		this.tipoPegada = tipoPegada;
	}

	public String getPosicaoBanco() {
		return posicaoBanco;
	}

	public void setPosicaoBanco(String posicaoBanco) {
		this.posicaoBanco = posicaoBanco;
	}

	public String getAmplitudade() {
		return amplitude;
	}

	public void setAmplitudade(String amplitudade) {
		this.amplitude = amplitudade;
	}
	
}
