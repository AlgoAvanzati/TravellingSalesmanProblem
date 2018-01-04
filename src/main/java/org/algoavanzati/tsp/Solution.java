package org.algoavanzati.tsp;

import java.util.Stack;

/**
 * Solution è l'oggetto soluzione usato dall'algoritmo TSP per
 * calcolare la soluzione al problema.
 * 
 * Al suo interno sono presenti, oltre al giro dei nodi della soluzione,
 * anche altre informazioni circa lo stato dell'esecuzione dell'algoritmo.
 * L'algoritmo ricorsivo che ne fa uso infatti utilizza la tecnica di 
 * BranchAndBound per riuscire a calcolare la soluzione ottima, come ad esempio 
 * il bound ed il valore della soluzione attuale.
 * 
 * @author sergio
 *
 */
public class Solution{

	// nodi presenti nella soluzione
	private int numNodi;
	
	// valore della soluzione attuale
	private int solutionValue;
	
	// giro(parziale) della soluzione
	private Stack<Integer> s;
	
	// singoli costi aggiunti durante le iterazioni
	private Stack<Integer> costi; //no
	
	// bound memorizzato lungo l'esecuzione
	private Integer bound;
	
	
	/**
	 * Crea una soluzione
	 * @param numNodi numero di nodi presenti nel grafo
	 */
	public Solution(int numNodi){
		this.numNodi = numNodi;
		this.s = new Stack<Integer>();
		this.solutionValue = 0;
		this.bound = null;
		this.costi = new Stack<Integer>();

	}
	
	/**
	 * Crea una soluzione in base ad una gia esistente
	 * @param solution soluzione già esistente
	 */
	public Solution(Solution solution){
		this.numNodi = solution.numNodi;
		this.s = (Stack<Integer>)solution.s.clone();
		this.solutionValue = solution.solutionValue;
		this.bound = solution.bound;
		this.costi = (Stack<Integer>)solution.costi.clone();
		
	}
	
	
	/**
	 * Inserisci un nodo-v alla soluzione con relativo costo di arrivo
	 * @param v nodo-v da aggiungere alla soluzione
	 * @param costoArcoAssociato costo dell'arco attraversato dal precedente nodo al nodo-v
	 */
	public void push(Integer v, Integer costoArcoAssociato){
		this.s.push(v);
		this.costi.add(costoArcoAssociato);
		this.solutionValue += costoArcoAssociato;		
	}
	

	/**
	 * Riumovi l'ultimo nodo inserito nella soluzione.
	 * Aggiusta quindi il costo della soluzione ed il giro percorso.
	 */
	public void pop(){
		this.s.pop();
		this.solutionValue -= this.costi.pop();
	}
	
	/**
	 * Controlla se il nodo-v è presente nel giro percorso.
	 * Restituisce true se presente, false altrimenti
	 * @param v nodo-v da verificare se presente nella soluzione.
	 * @return
	 */
	public boolean contains( int v ){
		return this.s.contains(v);
	}
	
	/**
	 * Restituisce il numero di nodi presenti nel giro parziale
	 * @return numero di nodi presenti nel giro
	 */
	public int count(){
		return this.s.size();
	}
	
	/**
	 * Verifica se il giro della soluzione è vuoto.
	 * Se è vuoto restituisce true, false altrimenti
	 * @return se è vuoto il giro della soluzione
	 */
	public boolean isEmpty(){
		return this.s.isEmpty();
	}
	
	/**
	 * Restituisce l'ultimo nodo visitato dal giro
	 * @return ultimo nodo visitato dal giro della soluzione
	 */
	public int lastNode(){
		return this.s.peek();
	}
	
	/**
	 * Restituisce il valore attuale della soluzione.
	 * @return il valore attuale della soluzione
	 */
	public int value(){
		return this.solutionValue;
	}

	/**
	 * Restituisce il primo nodo visitato dal giro, anche noto come il nodo partenza
	 * @return il primo nodo visitato dal giro (nodo partenza)
	 */
	public int firstNode(){
		return this.s.firstElement();
	}
	
	/**
	 * Verifica che se la soluzione è completa.
	 * Se completa restituisce true, false altrimenti.
	 * Il check avviene verificando che il giro contiene n+1 nodi 
	 * e che il primo nodo combacia con l'ultimo.
	 * 
	 * @return se la soluzione è completa
	 */
	public boolean isComplete(){
		return ( this.s.size() == (this.numNodi + 1)
				 && this.s.firstElement().equals( this.s.lastElement() )
				);
	}
	
	/**
	 * Restituisce un clone dell'insieme di veritici presenti nella soluzione
	 * @return clone dell'insieme di veritici presenti nella soluzione
	 */
	public Stack<Integer> getVertex(){
		Stack<Integer> copy = (Stack<Integer>)this.s.clone();
		return copy;
	}
	
	/**
	 * Verifica se è presente un bound nella soluzione corrente.
	 * Restituisce true se presente, false altrimenti
	 * @return presenza di un bound nella soluzione corrente
	 */
	public boolean hasBound(){
		return this.bound != null;
	}
	
	/**
	 * Restituisce il bound presente nella soluzione corrente.
	 * @return bound presente nella soluzione corrente
	 */
	public Integer getBound(){
		return this.bound;
	}
	
	
	/**
	 * Imposta un nuovo bound alla soluzione corrente
	 * @param bound nuovo bound della soluzione corrente
	 */
	public void setBound(Integer bound) {
		this.bound = bound;
	}

	
	@Override
	public String toString() {
		return "Solution [solutionValue=" + solutionValue + ", S=" + s + ", Bound=" + bound + ", isComplete="+ this.isComplete() +" ]";
	}
	
	

	
}
