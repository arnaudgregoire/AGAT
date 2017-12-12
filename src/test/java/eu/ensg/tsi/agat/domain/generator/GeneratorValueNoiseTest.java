package eu.ensg.tsi.agat.domain.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import eu.ensg.tsi.agat.domain.Bound;
import eu.ensg.tsi.agat.domain.Map;
import eu.ensg.tsi.agat.domain.Point;

class GeneratorValueNoiseTest {

	@Test
	void test() {
		GeneratorValueNoise valueMaker = new GeneratorValueNoise();
		Bound testBound = new Bound(new Point(-100,-100), new Point(400,100));
		Map testCarte = new Map(valueMaker, testBound, 1);
		testCarte.generate();	
		testCarte.exportToASC("testValue");
		testCarte.exportToGeoTiff("testValue");
		assertEquals(1, testCarte.resolution);
	}

}
