package eu.ensg.tsi.agat.geotools;

import static org.junit.Assert.*;

import org.junit.Test;

import eu.ensg.tsi.agat.domain.Map;

public class RasterReaderTest {

	@Test
	public void test() {
		Map map = new Map("simplex");
		map.generate();	
		map.importRasterBound("shp/testValue.tiff");
		assertEquals(100, (int) map.getBound().getUpperRight().getX());
		assertEquals(100, (int) map.getBound().getUpperRight().getY());
	}
	
	@Test
	public void testnotexist() {
		Map map = new Map("simplex");
		map.generate();
		map.importRasterBound("sq gdfsf l la lsfdsdsm qs sds lcw ");
		assertEquals(Double.NaN, map.getBound().getBottomLeft().getX(),0.000000001);
	}

}
