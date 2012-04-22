package br.com.training.entidades;

import java.util.List;

public class Exercicio {
	private int codigo;
	private String descricao;
	private List<GrupoExercicio> grupoxExercicio;
	
	public Exercicio(){
		super();
	}

	public Exercicio(int codigo, String descricao,
			List<GrupoExercicio> grupoxExercicio) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.grupoxExercicio = grupoxExercicio;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<GrupoExercicio> getGrupoxExercicio() {
		return grupoxExercicio;
	}

	public void setGrupoxExercicio(List<GrupoExercicio> grupoxExercicio) {
		this.grupoxExercicio = grupoxExercicio;
	}
}
