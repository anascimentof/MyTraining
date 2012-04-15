package br.com.training.entidades;

import java.io.Serializable;
import java.util.Timer;

public class Treino implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String cdPlano;
	private String cdTreino;
	private String descricao;
	private ExMusculacao exMuscular[];
	private Timer tempoDuracao;

	public Treino(){
		super();
	}

	public Treino(String codigo, String descricao, ExMusculacao[] exMuscular,
			Timer tempoDuracao) {
		this.cdTreino = codigo;
		this.descricao = descricao;
		this.exMuscular = exMuscular;
		this.tempoDuracao = tempoDuracao;
	}

	public String getCodigo() {
		return cdTreino;
	}

	public void setCodigo(String codigo) {
		this.cdTreino = codigo;
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

	public String getCdPlano() {
		return cdPlano;
	}

	public void setCdPlano(String cdPlano) {
		this.cdPlano = cdPlano;
	}
}
