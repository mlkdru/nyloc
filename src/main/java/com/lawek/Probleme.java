package com.lawek;

public interface Probleme {
	
	/**
	 * Evalue un réseau de neurones quelconque et lui donne un score entre -1 le moins bon et 1.
	 * Cette methode correspond à la fonction de 'fitting' du problème et définit l'objectif
	 * des strategies dont le but est toujours d'obtenir le réseau avec le meilleurs score.	 * 
	 * @param reseau le réseau à évaluer
	 * @return Lez score du réseau entre -1 et 1
	 */
	double evalue( NeuralNetwork reseau );			
	
	/**
	 * Revois le nombre de cas connus pour ce problème. De manière à pouvoir parcourir les cas.
	 * @return int le nombre de cas connus
	 */
	long getCaseLenght();	
	
	/**
	 * Revois le cas connu n°i pour ce problème.  
	 * @param i index du cas
	 * @return
	 */
	Case getCase(long i);
}
