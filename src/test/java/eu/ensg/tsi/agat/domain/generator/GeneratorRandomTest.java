package eu.ensg.tsi.agat.domain.generator;


import org.junit.Test;

import eu.ensg.tsi.agat.domain.Bound;
import eu.ensg.tsi.agat.domain.Map;
import eu.ensg.tsi.agat.domain.Point;

public class GeneratorRandomTest {

	@Test
	public void test() {
		GeneratorRandom randomMaker = new GeneratorRandom();
		Bound testBound = new Bound(new Point(0,0), new Point(100,100));
		Map testCarte = new Map(randomMaker, testBound, 1);
		testCarte.generate();	
		testCarte.exportToASC("Randomtest");
		testCarte.exportToGeoTiff("Randomtest");
	}

}
