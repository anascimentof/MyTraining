package br.com.training.entidades;

public class Musculacao extends Exercicio {
	private Equipamento equipamento[];
	private Musculo musculos[];
	private SerieExercicio serie[];
	private String velocidade;
	private int tempoDescanso;
	private String modoExecução;
	private String tipoPegada;
	private String posicaoBanco;
	private String amplitudade;
	
	public Musculacao(){
		super();
	}
	
	public Musculacao(int codigo, String descricao, String obs , Equipamento[] equipamento, Musculo[] musculos,
			SerieExercicio[] serie, String velocidade, int tempoDescanso,
			String modoExecução, String tipoPegada, String posicaoBanco,
			String amplitudade, String tipoExercicio) {
		super();
		setCodigo(codigo);
		setDescricao(descricao);
		setObs(obs);
		setTipoExercicio(tipoExercicio);
		this.equipamento = equipamento;
		this.musculos = musculos;
		this.serie = serie;
		this.velocidade = velocidade;
		this.tempoDescanso = tempoDescanso;
		this.modoExecução = modoExecução;
		this.tipoPegada = tipoPegada;
		this.posicaoBanco = posicaoBanco;
		this.amplitudade = amplitudade;
	}

	public void setCodigo(int codigo){
		super.setCodigo(codigo);
	}
	
	public int getCodigo(){
		return super.getCodigo();
	}
	
	public void setDescricao(String descricao){
		super.setDescricao(descricao);
	}
	
	public void setTipoExercicio(String tipoExercicio){
		super.setTipoExercicio(tipoExercicio);
	}
	
	public String getDescricao(){
		return super.getDescricao();
	}
	
	public void setObs(String obs){
		super.setObs(obs);
	}
	
	public String getObs(){
		return super.getObs();
	}
	
	public Equipamento[] getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento[] equipamento) {
		this.equipamento = equipamento;
	}

	public Musculo[] getMusculos() {
		return musculos;
	}

	public void setMusculos(Musculo[] musculos) {
		this.musculos = musculos;
	}

	public SerieExercicio[] getSerie() {
		return serie;
	}

	public void setSerie(SerieExercicio[] serie) {
		this.serie = serie;
	}

	public String getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(String velocidade) {
		this.velocidade = velocidade;
	}

	public int getTempoDescanso() {
		return tempoDescanso;
	}

	public void setTempoDescanso(int tempoDescanso) {
		this.tempoDescanso = tempoDescanso;
	}

	public String getModoExecução() {
		return modoExecução;
	}

	public void setModoExecução(String modoExecução) {
		this.modoExecução = modoExecução;
	}

	public String getTipoPegada() {
		return tipoPegada;
	}

	public void setTipoPegada(String tipoPegada) {
		this.tipoPegada = tipoPegada;
	}

	public String getPosicaoBanco() {
		return posicaoBanco;
	}

	public void setPosicaoBanco(String posicaoBanco) {
		this.posicaoBanco = posicaoBanco;
	}

	public String getAmplitudade() {
		return amplitudade;
	}

	public void setAmplitudade(String amplitudade) {
		this.amplitudade = amplitudade;
	}
	
}
