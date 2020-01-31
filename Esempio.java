import java.util.*;

import polichef.*;

public class Esempio {

	public static void main(String[] args) throws EccezioneIngredienteDuplicato{
			
		Trasmissione t = new Trasmissione();
		
		System.out.println("/***********************************/");
		System.out.println("/**        R1. CONCORRENTI        **/");
		System.out.println("/***********************************/\n");
		
		System.out.println("Iscritto nuovo concorrente");
		
		Concorrente c1 = t.iscriviConcorrente("Mario", "Rossi", "Insegnante");

		System.out.println(" "+c1.getNome()+" "+c1.getCognome()+" (professione "+c1.getProfessione()+")");

		System.out.println("\nIdentificativo assegnato");
		String idc1 = c1.getId();
		System.out.println(" "+idc1);

		System.out.println("\nDefinito altro concorrente");
		
		Concorrente c2 = t.iscriviConcorrente("Luca", "Neri", "Artigiano");
		String idc2 = c2.getId();

		System.out.println(" "+c2.getNome()+" "+c2.getCognome()+" (professione "+c2.getProfessione()+")");

		System.out.println("\nIdentificativo assegnato:");
		System.out.println(" "+idc2);
		
		
		System.out.println("\nIscritti altri concorrenti\n");
		t.iscriviConcorrente("Gianni", "Verdi", "Artigiano");
		t.iscriviConcorrente("Luisa", "Blu", "Veterinaria");
		t.iscriviConcorrente("Luigi", "Azzurri", "Agricoltore");

		System.out.println("Ricerca concorrente 'Gianni V.' \n");
		
		Concorrente c = t.cercaConcorrente("Gianni V.");
		System.out.println("Concorrente trovato\n "+c.getNome()+" "+c.getCognome()+" (professione "+c.getProfessione()+")");

		System.out.println("\nElenco concorrenti (ordine alfabetico nome e cognome)");
		LinkedList<Concorrente> listaConcorrenti = new LinkedList<Concorrente>(t.elencoConcorrenti());
		for(Concorrente ctemp : listaConcorrenti)
			System.out.println(" "+ctemp.getNome()+" "+ctemp.getCognome()+" (professione "+ctemp.getProfessione()+")");

		System.out.println("\nElenco concorrenti di professione 'Artigiano' (ordine di iscrizione)");
		listaConcorrenti = new LinkedList<Concorrente>(t.elencoConcorrenti("Artigiano"));
		for(Concorrente ctemp : listaConcorrenti)
			System.out.println(" "+ctemp.getNome()+" "+ctemp.getCognome()+" (professione "+ctemp.getProfessione()+")");
		
		System.out.println("\n/***********************************/");
		System.out.println("/**           R2. PIATTI          **/");
		System.out.println("/***********************************/\n");
		
		System.out.println("Registrazione piatto primo concorrente");
		int idp1 = t.registraPiattoConcorrente("Tonno vitellato", idc1);
		
		System.out.println("\nRicerca piatto aggiunto");
		Piatto p = t.cercaPiatto(idp1);
		
		System.out.println(" "+p.getIdPiatto()+" "+p.getNome()+" (concorrente "+p.getIdConcorrente()+")");
		
		System.out.println("\nAggiunti ingredienti al piatto");

		try{
			t.aggiungiIngredientePiatto(idp1, "Tonno");
			t.aggiungiIngredientePiatto(idp1, "Tonno");
			t.aggiungiIngredientePiatto(idp1, "Salsa di vitello");
		}
		catch(EccezioneIngredienteDuplicato eid) {
			System.out.println("\nIndividuato ingrediente duplicato");
		}
		
		System.out.println("\nAggiunto piatto secondo concorrente");

		int idp2 = t.registraPiattoConcorrente("Tiratisu", idc2);
		try {
			t.aggiungiIngredientePiatto(idp2, "Uovo");
			t.aggiungiIngredientePiatto(idp2, "Pavesini");
			t.aggiungiIngredientePiatto(idp2, "Rum");
		}
		catch(EccezioneIngredienteDuplicato eid) {
			System.out.println("\nIndividuato ingrediente duplicato");
		}
		
		System.out.println("\nElenco piatti (ordine alfabetico per nome)");
		LinkedList<Piatto> listaPiatti = new LinkedList<Piatto>(t.elencoPiattiPerNome());
		for(Piatto ptemp : listaPiatti)
			System.out.println(" "+ptemp.getIdPiatto()+" "+ptemp.getNome()+" (concorrente "+ptemp.getIdConcorrente()+")");
			
		System.out.println("\nElenco piatti (per numero ingredienti crescente)");
		listaPiatti = new LinkedList<Piatto>(t.elencoPiattiPerNumeroIngredienti());
		for(Piatto ptemp : listaPiatti)
			System.out.println(" "+ptemp.getIdPiatto()+" "+ptemp.getNome()+" (concorrente "+ptemp.getIdConcorrente()+")");
		
		System.out.println("\n/***********************************/");
		System.out.println("/**        R3. FASI E SFIDE       **/");
		System.out.println("/***********************************/");
		
		t.definisciFase(1, "Prima fase", 40);
		
		System.out.println("\nAggiunti primi due concorrenti alla fase 1");

		t.assegnaConcorrenteFase(1, idc1);
		t.assegnaConcorrenteFase(1, idc2);

		System.out.println("\nDefinita sfida tra i primi due concorrenti della fase 1");

		t.definisciSfidaFase(1, c1.getId(), idp1, idc2, idp2, "3-1");
		
		System.out.println("\nSfide della fase 1");
		String descrizioneSfideFase = t.descriviSfideFase(1);
		System.out.println(""+descrizioneSfideFase);
		
		t.definisciFase(2, "Seconda fase", 20);

		System.out.println("Aggiunto concorrente alla fase 2");
		t.assegnaConcorrenteFase(2, idc1);

		System.out.println("\nSfide di tutte fasi");
		String descrizioneSfide = t.descriviSfide();
		System.out.println(""+descrizioneSfide);

		System.out.println("Vincitore sfida tra i primi due concorrenti iscritti");
		String idVincitore = t.determinaVincitoreSfida(idc1, idc2);
		System.out.println(" "+idVincitore);
		
		System.out.println("\n/***********************************/");
		System.out.println("/**     R4. LETTURA DA FILE       **/");
		System.out.println("/***********************************/");

		System.out.println("\nLettura dati da file e costruzione struttura dati");
		t.leggiDaFile("input.txt");
	
		System.out.println("\nElenco concorrenti (ordine alfabetico per nome e cognome)");
		listaConcorrenti = new LinkedList<Concorrente>(t.elencoConcorrenti());
		for(Concorrente ctemp : listaConcorrenti)
			System.out.println(" "+ctemp.getNome()+" "+ctemp.getCognome()+" (professione "+ctemp.getProfessione()+")");

		System.out.println("\nElenco piatti (ordine alfabetico per nome)");
		listaPiatti = new LinkedList<Piatto>(t.elencoPiattiPerNome());
		for(Piatto ptemp : listaPiatti)
			System.out.println(" "+ptemp.getIdPiatto()+" "+ptemp.getNome()+" (concorrente "+ptemp.getIdConcorrente()+")");

		
	}

}
