package br.com.training.entidades;

import java.io.Serializable;
import java.util.List;

public class ExMusculacao implements Serializable {

	private static final long serialVersionUID = 1L;
	private Exercicio 				exercicio;
	private List<Equipamento> 		equipamento;
	private List<Musculo>		 	musculos;
	private List<SerieExercicio> 	serie;
	private String 					velocidade;
	private int 					tempoDescanso;
	private String 					modoExecucao;
	private String 					tipoPegada;
	private String 					posicaoBanco;
	private String 					amplitudade;
	
	public ExMusculacao(){
		super();
	}
	
	public ExMusculacao(Exercicio exercicio , List<Equipamento> equipamento, List<Musculo> musculos,
			List<SerieExercicio> serie, String velocidade, int tempoDescanso,
			String modoExecucao, String tipoPegada, String posicaoBanco,
			String amplitudade, String tipoExercicio) {
		super();
		this.exercicio		= exercicio;
		this.equipamento 	= equipamento;
		this.musculos 		= musculos;
		this.serie			= serie;
		this.velocidade		= velocidade;
		this.tempoDescanso 	= tempoDescanso;
		this.modoExecucao 	= modoExecucao;
		this.tipoPegada 	= tipoPegada;
		this.posicaoBanco 	= posicaoBanco;
		this.amplitudade 	= amplitudade;
	}

	public Exercicio getExercicio() {
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	public List<Equipamento> getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(List<Equipamento> equipamento) {
		this.equipamento = equipamento;
	}

	public List<Musculo> getMusculos() {
		return musculos;
	}

	public void setMusculos(List<Musculo> musculos) {
		this.musculos = musculos;
	}

	public List<SerieExercicio> getSerie() {
		return serie;
	}

	public void setSerie(List<SerieExercicio> serie) {
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
		return amplitudade;
	}

	public void setAmplitudade(String amplitudade) {
		this.amplitudade = amplitudade;
	}
	
}
