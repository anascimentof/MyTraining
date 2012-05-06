package br.com.training.entidades;

import java.io.Serializable;

import android.text.format.Time;

public class Treino implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String 			codigo;
	private String 			descricao;
	private ExMusculacao 	exMuscular[];
	private Time			tempoDuracao;

	public Treino(String codigo, String descricao, ExMusculacao[] exMuscular,
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

	public ExMusculacao[] getExMuscular() {
		return exMuscular;
	}

	public void setExMuscular(ExMusculacao[] exMuscular) {
		this.exMuscular = exMuscular;
	}

	public Time getTempoDuracao() {
		return tempoDuracao;
	}

	public void setTempoDuracao(Time tempoDuracao) {
		this.tempoDuracao = tempoDuracao;
	}
	
	public String toString(){
		return this.descricao;
	}
	
}
