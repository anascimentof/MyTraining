package br.com.training.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Exercicio implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int codigo;
	private String descricao;
	private List<GrupoExercicio> grupoxExercicio = new ArrayList<GrupoExercicio>();
	
	public Exercicio(){
		super();
	}

	public Exercicio(int codigo, String descricao, List<GrupoExercicio> grupoxExercicio) {
		super();
		this.codigo 			= codigo;
		this.descricao 			= descricao;
		this.grupoxExercicio 	= grupoxExercicio;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public String getCodigoString(){
		return String.valueOf(this.codigo);
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
	
	public String toString(){
		return getDescricao();
	}
	
	public void adicionarGrupoExercicio(GrupoExercicio grupo){
		if(grupo != null){
			this.grupoxExercicio.add(grupo);
		}
	}
	
	public void limparGrupoExercicio(){
		if(this.grupoxExercicio != null){
			this.grupoxExercicio.clear();
		}
	}
}
