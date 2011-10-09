package br.com.training.entidades;

import java.io.Serializable;

public class CategoriaMuscular implements Serializable {

	private static final long serialVersionUID = 1L;
	private int codigo;
	private String descricao;
	
	public CategoriaMuscular(){
		super();
	}
	
	public CategoriaMuscular(int codigo, String descricao) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
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

	@Override
	public String toString() {
		return getDescricao();
	}
}