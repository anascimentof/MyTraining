package br.com.training.entidades;

import java.io.Serializable;
import java.util.Date;

public class Agenda implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date dataTreino;
	private Treino treino;
	private ExMusculacao exmusculacao;
	private int sequencial;
	
	public Agenda(){
		super();
	}
	
	public Agenda(Date dataTreino, Treino treino, ExMusculacao exmusculacao, int sequencial){
		this.dataTreino 	= dataTreino;
		this.treino			= treino;
		this.exmusculacao	= exmusculacao;
		this.sequencial		= sequencial;
	}
	
	public Date getDataTreino() {
		return dataTreino;
	}
	public void setDataTreino(Date dataTreino) {
		this.dataTreino = dataTreino;
	}
	public Treino getTreino() {
		return treino;
	}
	public void setTreino(Treino treino) {
		this.treino = treino;
	}
	public ExMusculacao getExmusculacao() {
		return exmusculacao;
	}
	public void setExmusculacao(ExMusculacao exmusculacao) {
		this.exmusculacao = exmusculacao;
	}
	public int getSequencial() {
		return sequencial;
	}
	public void setSequencial(int sequencial) {
		this.sequencial = sequencial;
	}
	@Override
	public String toString() {
		return getDataTreino().toString();
	}
}
