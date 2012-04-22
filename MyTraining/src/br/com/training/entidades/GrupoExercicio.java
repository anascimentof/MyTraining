package br.com.training.entidades;

import java.io.Serializable;

public class GrupoExercicio implements Serializable {

	private static final long serialVersionUID = 1L;

	private int codigo;
	private String descricao;
	
	public GrupoExercicio(){
		super();
	}

	public GrupoExercicio(int codigo, String descricao) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getCodigoStrign(){
		return String.valueOf(codigo);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String toString(){
		return getDescricao();
	}
	
}
