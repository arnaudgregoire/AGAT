package eu.ensg.tsi.agat.domain;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import eu.ensg.tsi.agat.domain.generator.GeneratorValueNoise;
import junit.framework.TestCase;

public class MapTest extends TestCase {


	
	@Test
	public void testImport() {
		GeneratorValueNoise valueMaker = new GeneratorValueNoise();
		Map testMap = new Map(valueMaker, 1);
		testMap.importShapefileBound("shp/buffer_dissolve_paris.shp");
		System.out.println(testMap.bound.getBottomLeft().getX());
		assertNotEquals(Double.NaN, testMap.bound.getBottomLeft().getX());	
	}
}
	