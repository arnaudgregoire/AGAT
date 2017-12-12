package eu.ensg.tsi.agat.domain;

import org.junit.Test;

import eu.ensg.tsi.agat.domain.generator.GeneratorSimplexNoise;

public class GeneratorSimplexNoiseTest {

	@Test
	public void test() {
		GeneratorSimplexNoise SimplexMaker = new GeneratorSimplexNoise();
		Bound testBound = new Bound(new Point(0,0), new Point(100,100));
		Map testCarte = new Map(SimplexMaker, testBound, 1);
		testCarte.generate();	
		testCarte.exportToASC("Simplextest");
	}

}
