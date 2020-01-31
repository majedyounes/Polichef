package polichef;

import java.util.*;

public class Fase {

	int numeroFase;
	String nome;
	int numeroMassimoConcorrenti;
	
	LinkedList<Sfida> listaSfide = new LinkedList<Sfida>();
	
	TreeMap<String, Concorrente> mappaConcorrenti = new TreeMap<String, Concorrente>();
	
	public Fase(int numeroFase, String nome, int numeroMassimoConcorrenti) {
		this.numeroFase = numeroFase;
		this.nome = nome;
		this.numeroMassimoConcorrenti = numeroMassimoConcorrenti;
	}

	public int getNumeroFase() {
		return numeroFase;
	}

	public String getNome() {
		return nome;
	}

	public int getNumeroMassimoConcorrenti() {
		return numeroMassimoConcorrenti;
	}
	
	public void assegnaConcorrente(Concorrente concorrente) {
		if(!mappaConcorrenti.containsKey(concorrente.getId()))
			mappaConcorrenti.put(concorrente.getId(), concorrente);
	}
}
