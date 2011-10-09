package br.com.training.entidades;

import java.io.Serializable;

public class Equipamento implements Serializable {

	private static final long serialVersionUID = 1L;
	private int codigo;
	private String descricao;
	private MarcaEquipamento marca;
	
	public Equipamento(){
		super();
	}
	
	public Equipamento(int codigo, String descricao, MarcaEquipamento marca) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.marca = marca;
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
	public MarcaEquipamento getMarca() {
		return marca;
	}
	public void setMarca(MarcaEquipamento marca) {
		this.marca = marca;
	}
	public String toString(){
		return getDescricao() + " - " + getMarca().getDescricao();
	}
	
	
}
