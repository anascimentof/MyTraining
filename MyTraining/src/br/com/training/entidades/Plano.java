package br.com.training.entidades;

import java.util.Date;

public class Plano {

	private String codigo;
	private Treino treinos[];
	private String nivel;
	private String objetivo;
	private Date dataInclusao;
	private int sessaoAtual;
	private int sessaoFinal;
	private int cicloAtual;
	private int cicloFinal;
	private String professor;
	private Date dataInicio;
	private Date dataFim;
	private String meta;
	private String status;

	public Plano(){
		
	}

	public Plano(String codigo, Treino[] treinos, String nivel,
			String objetivo, Date dataInclusao, int sessaoAtual,
			int sessaoFinal, int cicloAtual, int cicloFinal, String professor,
			Date dataInicio, Date dataFim, String meta, String status) {

		this.codigo = codigo;
		this.treinos = treinos;
		this.nivel = nivel;
		this.objetivo = objetivo;
		this.dataInclusao = dataInclusao;
		this.sessaoAtual = sessaoAtual;
		this.sessaoFinal = sessaoFinal;
		this.cicloAtual = cicloAtual;
		this.cicloFinal = cicloFinal;
		this.professor = professor;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.meta = meta;
		this.status = status;
	}



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}
	
	public Treino[] getTreinos() {
		return treinos;
	}

	public void setTreinos(Treino[] treinos) {
		this.treinos = treinos;
	}

	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public int getSessaoAtual() {
		return sessaoAtual;
	}

	public void setSessaoAtual(int sessaoAtual) {
		this.sessaoAtual = sessaoAtual;
	}

	public int getSessaoFinal() {
		return sessaoFinal;
	}

	public void setSessaoFinal(int sessaoFinal) {
		this.sessaoFinal = sessaoFinal;
	}

	public int getCicloAtual() {
		return cicloAtual;
	}

	public void setCicloAtual(int cicloAtual) {
		this.cicloAtual = cicloAtual;
	}

	public int getCicloFinal() {
		return cicloFinal;
	}

	public void setCicloFinal(int cicloFinal) {
		this.cicloFinal = cicloFinal;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}
	
}
