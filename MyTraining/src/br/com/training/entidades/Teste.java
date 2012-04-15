package br.com.training.entidades;

import java.io.Serializable;

public class Teste implements Serializable {

	private static final long serialVersionUID = 1L;
	private int cdTeste;
	private String dsTeste;
	
	public Teste(){
		super();
	}
	
	public Teste(int cdTeste, String dsTeste){
		this.cdTeste = cdTeste;
		this.dsTeste = dsTeste;
	}

	public int getCdTeste() {
		return cdTeste;
	}

	public String getCdTesteString(){
		return String.valueOf(this.cdTeste);
	}
	
	public void setCdTeste(int cdTeste) {
		this.cdTeste = cdTeste;
	}

	public String getDsTeste() {
		return dsTeste;
	}

	public void setDsTeste(String dsTeste) {
		this.dsTeste = dsTeste;
	}

	public String toString(){
		return getDsTeste();
	}
	
}
