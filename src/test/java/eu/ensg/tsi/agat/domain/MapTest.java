package eu.ensg.tsi.agat.domain;

import org.junit.Test;

import junit.framework.TestCase;

public class MapTest extends TestCase {

	@Test
	public void testMap() {
		GeneratorFlat flatMaker = new GeneratorFlat();
		Bound testBound = new Bound(new Point(0,0), new Point(10,10));
		Map testCarte = new Map(flatMaker, testBound, 1);
		testCarte.generate();
		testCarte.consoleDraw();
		double[][] testData = testCarte.getData();
		assertEquals((int)testData[0][0],1);
	}
}
