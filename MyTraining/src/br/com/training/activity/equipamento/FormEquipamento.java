package br.com.training.activity.equipamento;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import br.com.training.activity.R;
import br.com.training.components.ConsistenciaMSG;
import br.com.training.dao.EquipamentoDAO;
import br.com.training.dao.MarcaEquipamentoDAO;
import br.com.training.entidades.Equipamento;
import br.com.training.entidades.MarcaEquipamento;

public class FormEquipamento extends Activity {
	private Equipamento 			equipamento;
	private EditText				telaDescricao;
	private Spinner					comboMarca;
	private ConsistenciaMSG			textoConsistencia;
	private List<MarcaEquipamento>	listaCombo;
	private MarcaEquipamento 		meSelecionado;
	private int 					posicao;			
	
	@Override
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
		setContentView(R.layout.formequipamento);
				
		//CRIAR OS OBJETOS DOS COMPONENTES DO FORM.
		textoConsistencia	= (ConsistenciaMSG) findViewById(R.formEquipamento.textoconsistencia);
		telaDescricao		= (EditText) findViewById(R.formEquipamento.equipamento);
		comboMarca			= (Spinner)  findViewById(R.formEquipamento.combomarca);
		comboMarca.setPrompt("Marca");
		carregarComboMarca();
		
		//RECEBER O EQUIPAMENTO ALTERADO DA TELA DE LISTA EQUIPAMENTO
		equipamento		= (Equipamento) getIntent().getSerializableExtra("eqSelecionado");
		
		if(equipamento == null)	{
			equipamento = new Equipamento();
		}else{
			//CARREGAR OS CAMPOS DO FORM. PARA ALTERAÇÃO
			telaDescricao.setText(equipamento.getDescricao());
			comboMarca.setSelection( retornaPosicaoCombo( equipamento.getMarca() ) );
		}
		
		//ROTINA DE CLIQUE PARA SELECIONAR A MARCA NO COMBOMARCA
		comboMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				meSelecionado = (MarcaEquipamento) arg0.getSelectedItem() ;
			}
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		
		
		//ROTINA DE CLIQUE DO BOTÃO SALVAR
		ImageButton btSalvar = (ImageButton) findViewById(R.formEquipamento.salvar);
		btSalvar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(validar()){
					equipamento.setDescricao(telaDescricao.getEditableText().toString().trim());
					equipamento.setMarca(meSelecionado);
									
					EquipamentoDAO equipamentodao = new EquipamentoDAO(FormEquipamento.this);
					equipamentodao.salvar(equipamento);
					
					telaDescricao.setText("");
					Toast.makeText(FormEquipamento.this, "Equipamento salvo com sucesso!", Toast.LENGTH_SHORT).show();
				}
				telaDescricao.setFocusable(true);
			}
		});
		
		//ROTINA DE CLIQUE DO BOTÃO CANCELAR
		ImageButton btCancelar = (ImageButton) findViewById(R.formEquipamento.cancelar);
		btCancelar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();				
			}
		});
	}

	//METODO DE VALIDAÇÃO DOS CAMPOS DO FORM
	private boolean validar(){
		textoConsistencia.setText("");
		if (telaDescricao.getText().toString().trim().equals("") ){
			textoConsistencia.mensagem( getString(R.string.msg_Campo_Descricao_em_branco) , ConsistenciaMSG.ERRO);
			return false ;
		}
		if (meSelecionado==null){
			Toast.makeText(FormEquipamento.this, "Selecione uma Marca para o equipamento.", Toast.LENGTH_LONG).show();
			return false ;
		}
		//textoConsistencia.setText("");
		return true;
	}
	
	//METODO DE CARREGAR AS MARCAS NO COMBOMARCA DO FORM
	private void carregarComboMarca(){
		MarcaEquipamentoDAO marcaDAO = new MarcaEquipamentoDAO(FormEquipamento.this);
		listaCombo = marcaDAO.listar() ;
		if (!listaCombo.isEmpty()){
			ArrayAdapter<MarcaEquipamento> adaptador = new ArrayAdapter<MarcaEquipamento>(FormEquipamento.this , 
					android.R.layout.simple_spinner_item, 
					listaCombo );
			adaptador.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
			comboMarca.setAdapter(adaptador);
		}
	}
	//METODO DE RETORNAR A POSICAO DE UM OBJETO MARCA NO COMBOMARCA 
	@SuppressWarnings("unchecked")
	private int retornaPosicaoCombo(MarcaEquipamento me){
		posicao = -1;
		ArrayAdapter<MarcaEquipamento> comboAdapter = (ArrayAdapter<MarcaEquipamento>) comboMarca.getAdapter(); 
		for (int i = 0; i < comboAdapter.getCount(); i++) {
			if(comboAdapter.getItem(i).getCodigo() == me.getCodigo()){
				posicao = i;
				break;
			}
		}
		return posicao;
	}
}
