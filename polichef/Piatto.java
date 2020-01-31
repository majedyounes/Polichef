package polichef;

import java.util.*;

public class Piatto{

	int idPiatto;
	String nome;
	Concorrente concorrente;
	
	private TreeMap<String, String> mappaIngredienti = new TreeMap<String, String>();
	int numeroIngredienti = 0;
	
	public Piatto(int idPiatto, String nome, Concorrente concorrente) {
		this.idPiatto=idPiatto;
		this.nome=nome;
		this.concorrente=concorrente;
	}
	
	
	public String getNome() {
		return nome;
	}

	public String getIdConcorrente() {
		return concorrente.getId();
	}

	public int getIdPiatto() {
		return idPiatto;
	}
	
	public void aggiungiIngrediente(String ingrediente) throws EccezioneIngredienteDuplicato{
		if(!mappaIngredienti.containsKey(ingrediente)) {
			mappaIngredienti.put(ingrediente, ingrediente);
			numeroIngredienti++;
		}
		else
			throw new EccezioneIngredienteDuplicato();
	}
	
}







