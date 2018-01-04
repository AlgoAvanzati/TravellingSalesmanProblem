package org.algoavanzati.tsp;

import java.util.HashSet;
import java.util.Set;

/**
 * Questa classe implementa la struttura dati basata del grafo pesato 
 * utilizzando una matrice di incidenza
 * 
 * @author sergio
 *
 */
public class GrafoPesato {
	
	private int[][] mat;
	private int size;
	private int numVertex;
	
	private static final int UNSETTED = -1;
	
	/**
	 * Crea un nuovo grafo pesato composta 
	 * da un numero di nodi pari a numVertex
	 * @param numVertex numero di nodi presenti nel grafo
	 */
	public GrafoPesato( int numVertex ){
		int[][] mat = new int[numVertex][numVertex];
		for( int i = 0; i < numVertex; i++ ){
			for(int j = 0; j < i; j++){
				mat[i][j] = UNSETTED;
				mat[j][i] = UNSETTED;
			}
		}
		init(mat, numVertex);
	}
	
	/**
	 * Crea un nuovo grafo pesato partendo da una matrice di incidenza mat.
	 *  - Se presente l'arco (i,j) il relativo peso deve essere nella posizione mat[i][j] e mat[j][i]
	 *  - Se non è presente l'arco, la matrice deve assumere nelle posizioni mat[i][j] e mat[j][i] valore UNSETTED
	 * @param mat matrice di partenza per costruire il grafo
	 * @param numVertex numero di vertici presente nel grafo
	 */
	public GrafoPesato( int[][] mat , int numVertex ){
		init(mat, numVertex);
	}
	
	/**
	 * Inizializza la matrice di incidenza del grafo 
	 * con una matrice di partenza
	 * @param mat matrice di partenza
	 * @param numVertex numero di nodi presenti nel grafo
	 */
	private void init(int[][] mat , int numVertex){
		this.mat = mat;
		this.size = numVertex;
		this.numVertex = numVertex;	
	}
	
	
	
	/**
	 * Imposta un arco e=(i,j) nel grafo pesato con relativo costo-c
	 * @param i nodo-i dell'arco e
	 * @param j nodo-j dell'arco e
	 * @param costo costo dell'arco
	 */
	public void setEdge(int i, int j, int costo){
		this.mat[i][j] = costo;
		this.mat[j][i] = costo;
	}
	
	/**
	 * Restituisci il costo associato all'arco e=(i,j)
	 * @param i nodo-i dell'arco e
	 * @param j nodo-j dell'arco e
	 * @return costo associato all'arco e
	 */
	public int getCosto(int i, int j){
		return this.mat[i][j];
	}
	
	/**
	 * Rimuovi un vertice dal grafo pesato
	 * @param i
	 */
	public void removeVertex( int i ){
		boolean found = false;
		for( int k = 0; k < this.size; k++ ){
			if( this.mat[i][k] != UNSETTED ) found = true;
			this.mat[k][i] = UNSETTED;
			this.mat[i][k] = UNSETTED;
		}
		if( found )this.numVertex--;	
	}

	/**
	 * Restituisci il numero di vertici presente nel grafo
	 * @return numero di vertici presente nel grafo
	 */
	public int numVertex(){
		return this.numVertex;
	}
	
	
	/**
	 * Restituisci i vertici presenti nel grafo
	 * @return Insieme di vertici presenti nel grafo
	 */
	public Set<Integer> getVertex(){
		Set<Integer> out = new HashSet<Integer>();
		for( int i = 0; i< this.size; i++){
			out.add(i);
		}
		return out;
	}
	
	
	/**
	 * Restituisci i figli di un nodo-v presente nel grafo
	 * @param v nodo-v di cui si vogliono i figli
	 * @return Insieme di vertici presenti nel grafo
	 */
	public Set<Integer> children( int v ){
		Set<Integer> currChildren = new HashSet<Integer>();
		for( int k = 0; k < this.size; k++ ){
			if( this.mat[v][k] != UNSETTED ) currChildren.add(k);
		}
		return currChildren;
	}
	


	@Override
	public String toString() {
		String out = "GrafoPesato: \n";
		out += "UNSETTED(value) : " + this.UNSETTED + "\n";
		out += "(mat) :\n";
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				out += this.mat[i][j] + "\t";
			}
			out += "\n";
		}
		return out;
	}
	
	
	
}
