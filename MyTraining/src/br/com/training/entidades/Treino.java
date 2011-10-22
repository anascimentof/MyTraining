package br.com.training.entidades;

import java.util.Timer;

public class Treino {

	private String codigo;
	private String descricao;
	private ExMusculacao exMuscular[];
	private Timer tempoDuracao;

	public Treino(){
		super();
	}

	public Treino(String codigo, String descricao, ExMusculacao[] exMuscular,
			Timer tempoDuracao) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.exMuscular = exMuscular;
		this.tempoDuracao = tempoDuracao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ExMusculacao[] getExMuscular() {
		return exMuscular;
	}

	public void setExMuscular(ExMusculacao[] exMuscular) {
		this.exMuscular = exMuscular;
	}

	public Timer getTempoDuracao() {
		return tempoDuracao;
	}

	public void setTempoDuracao(Timer tempoDuracao) {
		this.tempoDuracao = tempoDuracao;
	}
	
}
