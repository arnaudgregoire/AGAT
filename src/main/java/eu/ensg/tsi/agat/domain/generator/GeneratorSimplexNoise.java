package eu.ensg.tsi.agat.domain.generator;

import eu.ensg.tsi.agat.domain.generator.simplex.SimplexNoise;
import eu.ensg.tsi.agat.domain.utility.Round;

/**
 * Le générateur de map par bruit de simplex
 * Je me sers ici d'une implémentation existante et optimisé
 * Toutes les références des auteurs sont dans le package simplex
 * @author arnaudgregoire	
 *
 */
public class GeneratorSimplexNoise implements IGeneratorStrategy {

	private double persistence;
	private int numberOfOctaves;
	private int pas;
	
	/**
	 * Le constructeur du bruit de simplex avec tous les paramètres
	 * @param numberOfOctaves
	 * @param persistence
	 */
	public GeneratorSimplexNoise(int pas, int numberOfOctaves, double persistence) {
		super();
		this.persistence = persistence;
		this.numberOfOctaves = numberOfOctaves;
		this.setPas(pas);
	}
	
	/**
	 * Le constructeur simplifié du bruit de simplex,
	 * la persistence est réglé à .4
	 * le nombre d'octaves est réglé à 6
	 */
	public GeneratorSimplexNoise() {
		super();
		this.persistence = 0.4;
		this.numberOfOctaves = 6;
	}



	/**
	 * L'implémentation de la méthode process issu de l'interface IGeneratorStrategy
	 */
	public void process(double[][] data) {
	    SimplexNoise simplexNoise=new SimplexNoise(this.numberOfOctaves, data.length,this.persistence,1);

	    double moyenne = 0;
	    
	    for(int i=0;i<data.length;i++){
	        for(int j=0;j<data[0].length;j++){
	            data[i][j]=simplexNoise.getNoise(i,j);
	            moyenne += data[i][j];
	        }
	    }
	    
	    moyenne /= data.length * data[0].length;
	    
	    for(int i=0;i<data.length;i++){
	        for(int j=0;j<data[0].length;j++){
	            data[i][j] += moyenne +0.5;
	            
	            if (data[i][j]>=1) {
	            	data[i][j] = 1.0;
	            }
	            if(data[i][j]<=0) {
	            	data[i][j] = 0.0;
	            }
	            data[i][j] = Round.round2Dec(data[i][j]);
	        }
	    }
	    
	}

	public int getPas() {
		return pas;
	}

	public void setPas(int pas) {
		this.pas = pas;
	}


}
