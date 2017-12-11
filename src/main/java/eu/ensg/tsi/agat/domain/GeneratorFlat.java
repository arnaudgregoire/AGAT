package eu.ensg.tsi.agat.domain;

public class GeneratorFlat implements IGeneratorStrategy {

	/**
	 * Remplit le tableau data de la map avec uniquement des 0 et des 1
	 */
	public void process( double [][] data) {

	    for(int i=0; i<data.length; i++) {
	        for(int j=0; j<data[i].length; j++) {
	           data[i][j] = 1;
	        }
	    }
	}

}
