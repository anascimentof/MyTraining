package br.com.training.entidades;

import java.util.Date;

public class AgendaExercicio {

	private Date dateInicio;
	private int codigoPlano;
	private Treino treinos[];
	private boolean realizado;
	
	public AgendaExercicio(){
	
	}

	public AgendaExercicio(Date dateInicio, int codigoPlano, Treino[] treinos,
			boolean realizado) {
		super();
		this.dateInicio = dateInicio;
		this.codigoPlano = codigoPlano;
		this.treinos = treinos;
		this.realizado = realizado;
	}

	public Date getDateInicio() {
		return dateInicio;
	}

	public void setDateInicio(Date dateInicio) {
		this.dateInicio = dateInicio;
	}

	public int getCodigoPlano() {
		return codigoPlano;
	}

	public void setCodigoPlano(int codigoPlano) {
		this.codigoPlano = codigoPlano;
	}

	public Treino[] getTreinos() {
		return treinos;
	}

	public void setTreinos(Treino[] treinos) {
		this.treinos = treinos;
	}

	public boolean isRealizado() {
		return realizado;
	}

	public void setRealizado(boolean realizado) {
		this.realizado = realizado;
	}
}
