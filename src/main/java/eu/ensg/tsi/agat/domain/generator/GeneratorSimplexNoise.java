package eu.ensg.tsi.agat.domain.generator;

import eu.ensg.tsi.agat.domain.generator.simplex.SimplexNoise;
import eu.ensg.tsi.agat.domain.utility.Round;

/**
 * Le g�n�rateur de map par bruit de simplex
 * Je me sers ici d'une impl�mentation existante et optimis�
 * Toutes les r�f�rences des auteurs sont dans le package simplex
 * @author arnaudgregoire	
 *
 */
public class GeneratorSimplexNoise implements IGeneratorStrategy {

	private double persistence;
	private int numberOfOctaves;
	
	/**
	 * Le constructeur du bruit de simplex avec tous les paramètres
	 * @param numberOfOctaves
	 * @param persistence
	 */
	public GeneratorSimplexNoise(int numberOfOctaves, double persistence) {
		super();
		this.persistence = persistence;
		this.numberOfOctaves = numberOfOctaves;
	}
	
	/**
	 * Le constructeur simplifi� du bruit de simplex,
	 * la persistence est r�gl� à .4
	 * le nombre d'octaves est r�gl� à 6
	 */
	public GeneratorSimplexNoise() {
		super();
		this.persistence = 0.4;
		this.numberOfOctaves = 6;
	}



	/**
	 * L'impl�mentation de la m�thode process issu de l'interface IGeneratorStrategy
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


}
