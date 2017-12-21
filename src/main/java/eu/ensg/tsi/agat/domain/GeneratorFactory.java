package eu.ensg.tsi.agat.domain;

import eu.ensg.tsi.agat.domain.generator.GeneratorDiamondSquare;
import eu.ensg.tsi.agat.domain.generator.GeneratorFlat;
import eu.ensg.tsi.agat.domain.generator.GeneratorPerlinNoise;
import eu.ensg.tsi.agat.domain.generator.GeneratorRandom;
import eu.ensg.tsi.agat.domain.generator.GeneratorSimplexNoise;
import eu.ensg.tsi.agat.domain.generator.GeneratorValueNoise;
import eu.ensg.tsi.agat.domain.generator.IGeneratorStrategy;
import exceptions.StrategyNotFoundException;

/**
 * Cette classe met en place le design pattern fabrique statique vu en cours avec Mickael Borne
 * http://mborne.github.io/cours-patron-conception/#1
 * Cette fabrique statique permet d'instancier indifférement toutes les classes 
 * implémentant l'interface IGeneratorStrategy
 * @author arnaudgregoire
 *
 */
public class GeneratorFactory {
	
	/**
	 * Méthode principale de la fabrique statique Fabrique statique 
	 * @param nom la chaine de caractère correspondant au nom du générateur
	 * @return l'instance de la classe correspondant  
	 * @throws StrategyNotFoundException Si la chaine de caractère n'est pas reconnu, cette exception est retourné
	 */
	public IGeneratorStrategy create(String nom) throws StrategyNotFoundException {
		IGeneratorStrategy strategy;
		switch (nom) {
		
		case "flat":
			strategy = new GeneratorFlat();
			break;
		
		case "value":
			strategy = new GeneratorValueNoise();
			break;
			
		case "simplex":
			strategy = new GeneratorSimplexNoise();
			break;
		
		case "random":
			strategy = new GeneratorRandom();
			break;
		
		case "perlin":
			strategy = new GeneratorPerlinNoise();
			break;
			
		case "diamond":
			strategy = new GeneratorDiamondSquare();
			break;
			
		default:
			throw new StrategyNotFoundException("Le générateur pseudo aléatoire " + nom + " n'a pas été trouvé.");
		}
		
		return strategy;
	}
}
