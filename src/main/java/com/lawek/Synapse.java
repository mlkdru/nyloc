package com.lawek;

public class Synapse {
	Neuron entree;
	Neuron sortie;
	
	/**
	 * Poids
	 */
	double poids;

	public double getSortie() {
		return entree.getEtat() * poids;
	}
}
