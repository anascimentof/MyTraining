package br.com.training.entidades;

public class Exercicio {
	private int codigo;
	private String descricao;
	private String obs;
	private String tipoExercicio;
	
	protected Exercicio(){
		
	}
	
	protected int getCodigo() {
		return codigo;
	}
	protected void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	protected String getDescricao() {
		return descricao;
	}
	protected void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	protected String getObs() {
		return obs;
	}
	protected void setObs(String obs) {
		this.obs = obs;
	}
	
	protected String getTipoExercicio() {
		return tipoExercicio;
	}

	protected void setTipoExercicio(String tipoExercicio) {
		this.tipoExercicio = tipoExercicio;
	}
	
}
