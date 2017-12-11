package eu.ensg.tsi.agat.domain;

import org.junit.Test;

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
