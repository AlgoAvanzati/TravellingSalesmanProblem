package org.algoavanzati.tsp;

import java.util.Set;
import java.util.logging.Logger;

/**
 * Implementazione di TSP
 * Questa classe implementa TSP ricorsivo con tecnica BranchAndBound.
 * Il codice fa uso della classe Solution per passare, oltre alla soluzione,
 * anche le informazioni di stato (quali ad esempio il bound)
 * (I commenti sono presenti nel codice)
 * 
 * @author sergio
 *
 */
public class TSP {
	
	public static Solution tsp(GrafoPesato G, int nodo){
		
		Solution initSolution = new Solution(G.numVertex());
		initSolution.push(nodo, 0);
		
		return tsp(G, initSolution);
	}
	
	
	
	
	private static Solution tsp(GrafoPesato G, Solution S){

		////////////////////// casi base //////////////////////
		// (CASO-1) soluzione completa
		if( S.isComplete() ){
			
			// se la soluzione non possiede un bound, 
			// oppure il valore della soluzione è maggiore del bound, 
			//     reimpostalo (per restituire il nuovo bound alle altre chiamate)
			if( !S.hasBound() || S.getBound() > S.value()) S.setBound(S.value());
			return S;
		}
		
		
		// (CASO-2) il percorso non giunge ad una soluzione
		
		//prendi la lista dei prossimi nodi del giro
		Integer curr = S.lastNode();
		Set<Integer> nextChildren = G.children(curr);
		
		// se la lista contiene come prossimo nodo anche quello di partenza && ne manca effettivamente uno solo per completare il giro
		//    allora la lista dei prossimi nodi è proprio pari a tutti i nodi, meno quelli già visitati, più il nodo di partenza
		if( nextChildren.contains( S.firstNode() ) && S.count() == G.numVertex() ){
			nextChildren.removeAll( S.getVertex() );
			nextChildren.add(S.firstNode());
		}
		else{
		//   altrimenti sono quelli di prima (meno che quello di partenza) [NOTA: artifizio per le ADT di Java]
			nextChildren.removeAll( S.getVertex() );
		}

		
		// (quindi il caso base...) Se il percorso non giunge ad una soluzione, restituisci null
		if( nextChildren.isEmpty() ) return null;
		
		//////////////////////////////////////////////////////////////////
		
		
		
		// definiamo una soluzione migliore con best, inizialmente ignota (null)
		Solution best = null;
		
		if( S.hasBound() ){ // se la soluzione corrente presenta una bound, allora essa è completa : è la nuova best
			best = S;
		}
		for( Integer x : nextChildren ){ // per ogni prossimo nodo-x del giro parziale in corso...
			
			int costo = G.getCosto(curr, x); // calcola il costo per andare dal nodo corrente al nodo-x
			
			////System.out.println("----------->>> BOUND in FOR : " + S.getBound());
			////System.out.println("curr:" + curr + " & x:" + x + " -> costo=" + costo);
			
			
			// Se la soluzione non possiede un bound...
			if( !S.hasBound() ){
				
				
				// Seleziona il nodo-x e quindi 
				//   - crea una nuova soluzione s2
				//   - aggiungici il nuovo nodo-x con il relativo costo di (curr, x)
				//   - invoca ricorsivamente tsp sul nuovo oggetto soluzione
				Solution s2 = new Solution(S); 
				s2.push(x, costo );
				s2 = tsp(G, s2);
				
				// Se la chiamata restituisce una soluzione...
				if( s2 != null ){
				
					// se tale soluzione risulta essere completa, imposti il bound
					if( s2.isComplete() ) 
						S.setBound( s2.getBound() );
					
					// in ogni caso imposta questa nuova soluzione come best 
					// (ricordiamo che la soluzione corrente non possedeva un bound)
					best = s2;
				}
				
			}
			
			
			// Altrimenti se possiede un bound..
			// && se il valore della soluzione corrente + il costo dell'arco (curr, x) è minore del bound corrente..
			else if( S.value() + costo < S.getBound()  ){
				
				// Seleziona il nodo-x e quindi 
				//   - crea una nuova soluzione s1
				//   - aggiungici il nuovo nodo-x con il relativo costo di (curr, x)
				//   - invoca ricorsivamente tsp sul nuovo oggetto soluzione
				
				Solution s1 = new Solution(S);
				s1.push(x, costo);
				s1 = tsp(G, s1);
				
				// Se la chiamata restituisce una soluzione (in questo caso abbiamo un bound!)...
				if( s1 != null ) {
					
					// ... se tale soluzione risulta essere completa & ha un bound minore della soluzione corrente
					if( s1.isComplete() && s1.getBound() < S.getBound() ){
						
						// imposta il bound della soluzione corrente con quella ritornata (per le altre chiamate)
						S.setBound( s1.getBound() );
						
						// imposta la soluzione best con questa nuova ritornata 
						best = s1;
					}
					
				}
			}
			
			
			
		}
		
		// restituisci la soluzione best
		return best;
		
		
		
	}

}
