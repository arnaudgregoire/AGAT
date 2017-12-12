package eu.ensg.tsi.agat.domain.generator;

import org.junit.Test;

import eu.ensg.tsi.agat.domain.Bound;
import eu.ensg.tsi.agat.domain.Map;
import eu.ensg.tsi.agat.domain.Point;
import eu.ensg.tsi.agat.domain.generator.GeneratorSimplexNoise;

public class GeneratorSimplexNoiseTest {

	@Test
	public void test() {
		GeneratorSimplexNoise SimplexMaker = new GeneratorSimplexNoise();
		Bound testBound = new Bound(new Point(0,0), new Point(150,100));
		Map testCarte = new Map(SimplexMaker, testBound, 1);
		testCarte.generate();	
		testCarte.exportToASC("Simplextest");
		testCarte.exportToGeoTiff("Simplextest");
	}

}
