package br.com.training.activity.exercicio;

import br.com.training.dao.ExercicioDAO;
import android.app.ListActivity;
import android.os.Bundle;

public class ListaExercicio extends ListActivity {
	private final static int NOVO 		= 0;
	private final static int VOTLAR 	= 1;
	private final static int ALTERAR	= 0;
	private final static int DELETAR	= 1;
	private ExercicioDAO exerciciodao 	= new ExercicioDAO(ListaExercicio.this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	
	
	
}
