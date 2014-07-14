package com.lawek;

import java.util.ArrayList;
import java.util.List;

/**
 * Modifie les réseaux de neurones d'un tank
 * 
 * Elle a une fonction d'évaluation, qui est le problème auquel doit répondre la stratégie
 * 
 * @author mlkdru
 *
 */
public class Strategy {
	/**
	 * Changer les poids des synapses d'un ou des ? réseaux de neurones
	 */
	public void run( Tank tank, Probleme probleme )
	{
		List<NeuralNetwork> reseaux = tank.reseaux;
		
		// pour tous les réseau, evaluation du réseau en réponse au problème
		List<Double> scores = new ArrayList<>( reseaux.size() );
		for( int i = 0; i <reseaux.size(); i++ )
			scores.set( i, probleme.evalue( reseaux.get(i) ) );
		
		// classement des réseaux,
		// et mesures adéquates
	}
}
