package eu.ensg.tsi.agat.domain;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import junit.framework.TestCase;

public class MapTest extends TestCase {

	@Test
	public void testMap() {
		//GeneratorFlat flatMaker = new GeneratorFlat();
		//GeneratorRandom randomMaker = new GeneratorRandom();
		GeneratorValueNoise valueMaker = new GeneratorValueNoise();
		Bound testBound = new Bound(new Point(-100,-100), new Point(0,0));
		Map testCarte = new Map(valueMaker, testBound, 1);
		testCarte.generate();	
		//testCarte.consoleDraw();
		testCarte.exportToASC("test");
		//double[][] testData = testCarte.getData();
		//assertEquals((int)testData[0][0],1);
		assertEquals(1, testCarte.resolution);
	}
	
	@Test
	public void testImport() {
		GeneratorValueNoise valueMaker = new GeneratorValueNoise();
		Map testMap = new Map(valueMaker, 1);
		testMap.importShapefileBound("shp/buffer_dissolve_paris.shp");
		System.out.println(testMap.bound.getBottomLeft().getX());
		assertNotEquals(Double.NaN, testMap.bound.getBottomLeft().getX());	
	}
}
	