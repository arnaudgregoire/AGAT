package eu.ensg.tsi.agat.geotools;

import static org.junit.Assert.*;

import org.junit.Test;

import eu.ensg.tsi.agat.domain.Map;

public class RasterReaderTest {

	@Test
	public void test() {
		Map map = new Map("simplex");
		map.generate();	
		map.importRasterBound("data/testValue.tiff");
		assertEquals(100, (int) map.bound.getUpperRight().getX());
		assertEquals(100, (int) map.bound.getUpperRight().getY());
	}
	
	@Test
	public void testnotexist() {
		Map map = new Map("simplex");
		map.generate();
		map.importRasterBound("sq gdfsf l la lùfdsdsm qs sdù lcw ");
		assertEquals(Double.NaN, map.bound.getBottomLeft().getX(),0.000000001);
	}

}
