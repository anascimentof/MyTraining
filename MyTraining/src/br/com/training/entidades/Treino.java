package br.com.training.entidades;

import android.text.format.Time;

public class Treino {

	private String codigo;
	private String descricao;
	private Musculacao exMuscular[];
	private Time tempoDuracao;

	public Treino(){
		
	}

	public Treino(String codigo, String descricao, Musculacao[] exMuscular,
			Time tempoDuracao) {
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

	public Musculacao[] getExMuscular() {
		return exMuscular;
	}

	public void setExMuscular(Musculacao[] exMuscular) {
		this.exMuscular = exMuscular;
	}

	public Time getTempoDuracao() {
		return tempoDuracao;
	}

	public void setTempoDuracao(Time tempoDuracao) {
		this.tempoDuracao = tempoDuracao;
	}
	
}
