package br.com.training.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Musculo implements Serializable{

	private static final long serialVersionUID = 1L;
	private int codigo;
	private String descricao;
	private List<CategoriaMuscular> categoriaMuscular = new ArrayList<CategoriaMuscular>();
	
	public Musculo(){
		super();
	}
	public Musculo(int codigo, String descricao, List<CategoriaMuscular> categMuscular) {
		this.codigo				= codigo;
		this.descricao			= descricao;
		this.categoriaMuscular	= categMuscular;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getCodigoString(){
		return String.valueOf(this.codigo);
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<CategoriaMuscular> getCategMuscular() {
		return categoriaMuscular;
	}
	public void setCategMuscular(List<CategoriaMuscular> categMuscular) {
		this.categoriaMuscular = categMuscular;
	}
	public String toString(){
		return getDescricao();
	}
	public void adicionarCategoria(CategoriaMuscular categoria){
		if (categoria != null ){
			this.categoriaMuscular.add(categoria);
		}
	}
	public void limparCategorias(){
		if (categoriaMuscular!=null){
			categoriaMuscular.clear();
		}
	}
}
