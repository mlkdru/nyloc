package com.lawek;

/**
 * Exemple de d'implementation de problème.
 * Deviner la valeur future d'une courbe sinusoïdale à partir de 5 valeurs passées.
 * 
 * @author mlkdru
 *
 */
public class ProblemeSinus implements Probleme {
	
	double start = 0;
	double end = 10;
	double pas = 0.01;
	
	/**
	 * Réalise une moyenne de la différence absolue entre la sortie du réseau
	 * et une 20aine de cas au hasard.
	 */
	@Override
	public double evalue(NeuralNetwork reseau) {
		double score =0;
		for( int i=0; i<20; i++)
		{
			// Un cas au hasard
			Case cas = getCase( Math.round(Math.random()*getCaseLenght() ) );
			reseau.setCase(cas);
			reseau.transfert();
			// Comparer la sortie 
			double sortie =reseau.sorties.get(0).etat;
			score += Math.abs( cas.sorties.get(0)-sortie );
		}
		score = score / 20;
		return score;
	}

	@Override
	public long getCaseLenght() {		
		return Math.round( (end-start)/pas );
	}

	@Override
	public Case getCase(long i) {
		if( i<getCaseLenght() ){
			Case cas = new Case();
			for( long j=i; j<i+5; j++) 
			{
				// Valeure passée
				double valeure = Math.sin( start+j*pas );
				cas.entrees.add( valeure );
			}
			cas.sorties.add( Math.sin( start+(i+5)*pas ) );
			return cas;
		}
		return null;
	}

}
