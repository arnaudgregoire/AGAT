package eu.ensg.tsi.agat.speed;

import org.junit.Test;

import eu.ensg.tsi.agat.domain.Bound;
import eu.ensg.tsi.agat.domain.Map;
import eu.ensg.tsi.agat.domain.Point;

public class SpeedTest {

	@Test
	public void testSpeedrandom() {
		Map map = new Map("random");
		map.setBound(new Bound(new Point(0,0), new Point(2049,2049)));
		map.generate();
		map.exportToGeoTiff("data/random2k");
	} 
	
	@Test
	public void testSpeedflat() {
		Map map = new Map("flat");
		map.setBound(new Bound(new Point(0,0), new Point(2049,2049)));
		map.generate();
		map.exportToGeoTiff("data/flat2k");
	} 
	@Test
	public void testSpeedvalue() {
		Map map = new Map("value");
		map.setBound(new Bound(new Point(0,0), new Point(2049,2049)));
		map.generate();
		map.exportToGeoTiff("data/value2k");
	}
	
	@Test
	public void testSpeedperlin() {
		Map map = new Map("perlin");
		map.setBound(new Bound(new Point(0,0), new Point(2049,2049)));
		map.generate();
		map.exportToGeoTiff("data/perlin2k");
	} 
	
	@Test
	public void testSpeedsimplex() {
		Map map = new Map("simplex");
		map.setBound(new Bound(new Point(0,0), new Point(2049,2049)));
		map.generate();
		map.exportToGeoTiff("data/simplex2k");
	} 
	
	@Test
	public void testSpeeddiamond() {
		Map map = new Map("diamond");
		map.setBound(new Bound(new Point(0,0), new Point(2049,2049)));
		map.generate();
		map.exportToGeoTiff("data/diamon2k");
	} 
}
