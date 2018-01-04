package org.algoavanzati.testexec;

import org.algoavanzati.tsp.GrafoPesato;
import org.algoavanzati.tsp.Solution;
import org.algoavanzati.tsp.TSP;

public class TestRunningTSP {

	public static void main(String[] args) {
		
		System.out.println("Test Running TSP");
		System.out.println("In questo esempio si prende una istanza di GrafoPesato e si lancia l'algoritmo partendo da vari nodi\n\n");
		
		GrafoPesato G = new GrafoPesato(5);
		
		G.setEdge(0, 1, 1);
		G.setEdge(0, 2, 6);
		G.setEdge(0, 3, 5);
		G.setEdge(0, 4, 1);
		
		G.setEdge(1, 2, 1);
		G.setEdge(1, 3, 3);
		
		G.setEdge(2, 3, 1);
		G.setEdge(2, 4, 3);
		
		G.setEdge(3, 4, 1);
		
		
		
		GrafoPesato Gb = new GrafoPesato(10);
		Gb.setEdge(0, 1, 1);
		Gb.setEdge(0, 7, 1);
		Gb.setEdge(0, 3, 1);
		Gb.setEdge(1, 2, 1);		
		Gb.setEdge(1, 6, 1);
		Gb.setEdge(1, 5, 1);
		Gb.setEdge(2, 3, 1);
		Gb.setEdge(3, 4, 1);
		Gb.setEdge(3, 7, 1);
		Gb.setEdge(3, 8, 1);
		Gb.setEdge(4, 5, 1);
		Gb.setEdge(5, 8, 1);
		Gb.setEdge(6, 7, 1);
		Gb.setEdge(6, 8, 1);
		Gb.setEdge(7, 8, 1);
		Gb.setEdge(7, 9, 1);
		Gb.setEdge(8, 9, 1);
		///NEW!
		Gb.setEdge(0, 4, 1);

		
		
		System.out.println(Gb);
		System.out.println("numero vertici : " + Gb.numVertex());
		
		//System.out.println("figli di 1 : " + G.children(1) + "\n\n");
		
		for( Integer x : Gb.getVertex() ){
			Solution s = TSP.tsp(Gb, x);
			System.out.println("Sol in " + x + " :: " + s);

		}
		
		//System.out.println("solution : " + s);

	}
	

}
