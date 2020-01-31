package polichef;

import java.io.*;
import java.util.*;

public class Trasmissione {

	int prossimoIdPiatto = 100;
	TreeMap<String, Concorrente> mappaConcorrenti = new TreeMap<String, Concorrente>();
	LinkedList<Concorrente> listaConcorrenti = new LinkedList<Concorrente>();
	TreeMap<Integer, Piatto> mappaPiatti = new TreeMap<Integer, Piatto>();
	TreeMap<Integer, Fase> mappaFasi = new TreeMap<Integer, Fase>();
	LinkedList<Sfida> listaSfide = new LinkedList<Sfida>();
	
	public Concorrente iscriviConcorrente(String nome, String cognome, String professione) {
		
		String iD = nome+" "+cognome.charAt(0)+".";
		
		if(mappaConcorrenti.containsKey(iD)) 
			return null;
	
		Concorrente cTemp = new Concorrente(iD, nome, cognome, professione );
		
		mappaConcorrenti.put(iD, cTemp);

		listaConcorrenti.add(cTemp);
		
		return cTemp;
	
	}
	

	public Concorrente cercaConcorrente(String idConcorrente) {
		return mappaConcorrenti.get(idConcorrente);
	}

	
	public Collection<Concorrente> elencoConcorrenti() {
		return mappaConcorrenti.values();
	}

	
	public Collection<Concorrente> elencoConcorrenti(String professione) {

		LinkedList<Concorrente> listaTemp = new LinkedList<Concorrente>();
		
		for(Concorrente c : listaConcorrenti)
			if(c.getProfessione().compareTo(professione)==0)
				listaTemp.add(c); 

		return listaTemp;
	}

	
	public int registraPiattoConcorrente(String nomePiatto, String idConcorrente) {

		int idPiatto = prossimoIdPiatto;
		
		prossimoIdPiatto++;
		
		Concorrente cTemp = this.cercaConcorrente(idConcorrente);
		
		if(cTemp==null)
			return -1;
		
		Piatto pTemp = new Piatto( idPiatto, nomePiatto, cTemp );
		mappaPiatti.put(idPiatto, pTemp);
	
		return idPiatto;
	}

	
	public Piatto cercaPiatto(int idPiatto) {
		return mappaPiatti.get(idPiatto);
	}
	

	public void aggiungiIngredientePiatto(int idPiatto, String ingrediente) throws EccezioneIngredienteDuplicato {
	
		Piatto pTemp = this.cercaPiatto(idPiatto);
		if(pTemp==null)
			return; 
		
		pTemp.aggiungiIngrediente(ingrediente);
	}
	

	public Collection<Piatto> elencoPiattiPerNome() {

		LinkedList<Piatto> listaTemp = new LinkedList<Piatto>( mappaPiatti.values()); 
		Collections.sort(listaTemp, new ComparatoreDiPiattiPerNome());
		return listaTemp;
	}
	
	
	public Collection<Piatto> elencoPiattiPerNumeroIngredienti() {
		LinkedList<Piatto> listaTemp = new LinkedList<Piatto>( mappaPiatti.values()); 
		Collections.sort(listaTemp, new ComparatoreDiPiattiPerNumeroIngredienti());
		return listaTemp;
	}
	
	
	public Fase definisciFase(int numero, String nome, int numeroMassimoConcorrenti) {

		Fase fTemp = new Fase(numero, nome, numeroMassimoConcorrenti);
		mappaFasi.put(numero, fTemp);
		return fTemp;
	}

	
	public void assegnaConcorrenteFase(int numeroFase, String idConcorrente) {
	
		Fase fTemp = mappaFasi.get(numeroFase);
		Concorrente cTemp = this.cercaConcorrente(idConcorrente);
		
		if(fTemp == null || cTemp==null)
			return; 
		
		fTemp.assegnaConcorrente(cTemp);
	}

	
	public void definisciSfidaFase(int numeroFase, String idConcorrente1, int idPiatto1, String idConcorrente2, int idPiatto2, String esito) {
		
		Fase fTemp = mappaFasi.get(numeroFase);
		Concorrente cTemp1 = this.cercaConcorrente(idConcorrente1);
		Concorrente cTemp2 = this.cercaConcorrente(idConcorrente2);
		if(fTemp == null || cTemp1 == null || cTemp2 == null)
			return;

		if(!fTemp.mappaConcorrenti.containsKey(idConcorrente1))
			return;
		if(!fTemp.mappaConcorrenti.containsKey(idConcorrente2))
			return;
		
		Piatto piatto1 = this.cercaPiatto(idPiatto1);
		Piatto piatto2 = this.cercaPiatto(idPiatto2);
		
		if(piatto1==null || piatto2==null)
			return;

		// Fatte le opportune verifiche richieste dal testo ...

		Sfida sfidaTemp = new Sfida(cTemp1, cTemp2, piatto1, piatto2, esito);
		fTemp.listaSfide.add(sfidaTemp);
		listaSfide.add(sfidaTemp);
	}

	
	public String descriviSfideFase(int numeroFase) {
		Fase fTemp = mappaFasi.get(numeroFase);
		if(fTemp==null)
			return "";
		if(fTemp.listaSfide==null)
			return "";
		
		String risultato = "";
		
		for(Sfida s : fTemp.listaSfide) {
			if(risultato!="")
				risultato+="\n";
			risultato+=""+s.c1.getId()+", "+s.p1.getIdPiatto()+", "+s.c2.getId()+", "+s.p2.getIdPiatto()+", "+s.esito;
		}
		
		return risultato;
	}
	

	public String descriviSfide() {

		String risultato = "";
		for(Fase f : mappaFasi.values())
			risultato+=this.descriviSfideFase(f.getNumeroFase())+"\n";
		
		return risultato;
	}

	
	public String determinaVincitoreSfida(String idConcorrente1, String idConcorrente2) {

		for(Sfida s : listaSfide)
			if(s.c1.getId().compareTo(idConcorrente1)==0 && s.c2.getId().compareTo(idConcorrente2)==0){
				int num1;
				int num2;
				String array[] = s.esito.split("-");
				num1 = Integer.parseInt(array[0]);
				num2 = Integer.parseInt(array[1]);
			
				if(num1>num2)
					return idConcorrente1;
				else
					return idConcorrente2;
			} 
			else if(s.c1.getId().compareTo(idConcorrente2)==0 && s.c2.getId().compareTo(idConcorrente1)==0){
				int num1;
				int num2;
				String array[] = s.esito.split("-");
				num1 = Integer.parseInt(array[0]);
				num2 = Integer.parseInt(array[1]);
			
				if(num1>num2)
					return idConcorrente2;
				else
					return idConcorrente1;
			} 
 
		return null;
	}
	
	
	public void leggiDaFile(String nomeFile) {
		
		FileReader fr;
		BufferedReader br;
		
		try {

			fr = new FileReader(nomeFile);
			br = new BufferedReader(fr);
			
			String riga;
			while( (riga=br.readLine())!=null) {
				
				String array[] = riga.split(";");
				
				String tipo = array[0];
				if(tipo.compareTo("C")==0) {
					// Riga concorrente
					
					if(array.length==4) {
						
						String nome = array[1];
						String cognome = array[2];
						String professione = array[3];
						
						this.iscriviConcorrente(nome, cognome, professione);
					}
					
				}
				else if(tipo.compareTo("P")==0) {
					// Riga piatto
					
					if(array.length==3) {
						String nomePiatto = array[1];
						String idConcorrente = array[2];
						this.registraPiattoConcorrente(nomePiatto, idConcorrente);

				}
			}
		}
			fr.close();
			br.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

