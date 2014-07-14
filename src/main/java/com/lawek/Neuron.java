package com.lawek;

import java.util.ArrayList;
import java.util.List;

public class Neuron {
	List<Synapse> entrees = new ArrayList<>();
	Synapse sortie;
	
	/**
	 * Etat du neurone entre -1 et 1
	 */
	double etat;
	
	/**
	 * Prend la somme pondérée des valeurs en entrees et applique la fonction de seuil
	 * @return
	 */
	public void transfert()
	{
		double res = 0;
		for(Synapse entree : entrees)
			res += entree.getSortie();
		
		etat = fonctionTransfert(res);
	}

	public double getEtat() {
		return etat;
	}

	private double fonctionTransfert(double res)
	{
		return 0;
	}
}
