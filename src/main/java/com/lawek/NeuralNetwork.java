package com.lawek;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NeuralNetwork {
	List<Synapse> synapses;
	List<Neuron> neurones;
	
	List<Entree> entreesReseau;
	List<Neuron> sortiesReseau;
	
	double seuilMoyenne = 0.2;
	
	/**
	 * Parcours le réseau pour calculer tous les nouveaux états de neurones jusqu'à un état stable dans lequel l'état des neurones de sortie 
	 */
	public void transfert()
	{
		List<Neuron> liste = new ArrayList<>( neurones );
		Collections.shuffle(liste);
		
		double moyChang = seuilMoyenne + 1;
		
		while( moyChang > seuilMoyenne )
		{
			moyChang = 0;
			
			while( ! liste.isEmpty() )
			{
				Neuron neurone = liste.remove(0);
				
				double etat = neurone.getEtat();
				
				neurone.transfert();
				
				moyChang += Math.abs( neurone.getEtat() - etat );
			}
			
			moyChang /= neurones.size();
		}
	}
}
