package polichef;

public class Concorrente {

	String iD;
	String nome;
	String cognome;
	String professione;
	
	public Concorrente(String iD, String nome, String cognome, String professione) {
		this.iD=iD;
		this.nome=nome;
		this.cognome=cognome;
		this.professione=professione;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getProfessione() {
		return professione;
	}

	public String getId() {
		return iD;
	}
	
}
