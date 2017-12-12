package eu.ensg.tsi.agat.domain.generator;

import org.junit.Test;

import eu.ensg.tsi.agat.domain.Bound;
import eu.ensg.tsi.agat.domain.Map;
import eu.ensg.tsi.agat.domain.Point;
import eu.ensg.tsi.agat.domain.generator.GeneratorPerlinNoise;

public class GeneratorPerlinNoiseTest {

	@Test
	public void testGenerator() {
		GeneratorPerlinNoise perlinMaker = new GeneratorPerlinNoise();
		Bound testBound = new Bound(new Point(0,0), new Point(100,100));
		Map testCarte = new Map(perlinMaker, testBound, 1);
		testCarte.generate();	
		testCarte.exportToASC("Perlintest");
	}

}
