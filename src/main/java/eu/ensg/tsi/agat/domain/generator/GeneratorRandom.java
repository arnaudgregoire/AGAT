package eu.ensg.tsi.agat.domain.generator;


/**
 * un générateur de terrains totalement aléatoire
 * sans aucune cohérence
 * Les valeurs générés sont choisies aléatoirement entre 0 et 1
 * @author arnaudgregoire
 *
 */
public class GeneratorRandom implements IGeneratorStrategy {

	public void process(double[][] data) {
		
	    for(int i=0; i<data.length; i++) {
	        for(int j=0; j<data[i].length; j++) {
	           data[i][j] = Math.floor(Math.random() * 10)/10;
	        }
	    }
	}

}
