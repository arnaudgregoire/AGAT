package eu.ensg.tsi.agat.domain;

import eu.ensg.tsi.agat.domain.generator.GeneratorFlat;
import eu.ensg.tsi.agat.domain.generator.GeneratorPerlinNoise;
import eu.ensg.tsi.agat.domain.generator.GeneratorRandom;
import eu.ensg.tsi.agat.domain.generator.GeneratorSimplexNoise;
import eu.ensg.tsi.agat.domain.generator.GeneratorValueNoise;
import eu.ensg.tsi.agat.domain.generator.IGeneratorStrategy;
import exceptions.StrategyNotFoundException;

public class GeneratorFactory {
	
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
			
		default:
			throw new StrategyNotFoundException("Le générateur pseudo aléatoire " + nom + " n'a pas été trouvé.");
		}
		
		return strategy;
	}
}
