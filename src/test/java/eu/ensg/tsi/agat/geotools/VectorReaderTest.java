package eu.ensg.tsi.agat.geotools;

import static org.junit.Assert.*;

import org.junit.Test;

import eu.ensg.tsi.agat.domain.Map;

public class VectorReaderTest {

	@Test
	public void test() {
		Map map = new Map("simplex");
		map.generate();	
		map.importShapefileBound("shp/buffer_dissolve_paris.shp");
		assertEquals(643155, (int) Math.floor(map.bound.getBottomLeft().getX()));
	}
	
	@Test
	public void testnotexist() {
		Map map = new Map("simplex");
		map.generate();
		map.importShapefileBound("sq gdfsf l la lùfdsdsm qs sdù lcw ");
		assertEquals(Double.NaN, map.bound.getBottomLeft().getX(),0.000000001);
	}

}
