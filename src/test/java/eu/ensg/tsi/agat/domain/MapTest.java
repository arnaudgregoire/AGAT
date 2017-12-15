package eu.ensg.tsi.agat.domain;

import static org.junit.Assert.assertNotEquals;

import java.io.File;

import org.junit.Test;

import eu.ensg.tsi.agat.domain.generator.GeneratorFlat;
import junit.framework.TestCase;

public class MapTest extends TestCase {


	
	@Test
	public void testimportShapefileBound() {
		GeneratorFlat flat = new GeneratorFlat();
		Map testMap = new Map(flat, 1);
		testMap.importShapefileBound("shp/buffer_dissolve_paris.shp");
		assertNotEquals(Double.NaN, testMap.bound.getBottomLeft().getX());	
		assertNotEquals(Double.NaN, testMap.bound.getBottomLeft().getY());	
		assertNotEquals(Double.NaN, testMap.bound.getBottomRight().getX());	
		assertNotEquals(Double.NaN, testMap.bound.getBottomRight().getY());	
		assertNotEquals(Double.NaN, testMap.bound.getUpperleft().getX());	
		assertNotEquals(Double.NaN, testMap.bound.getUpperleft().getY());	
		assertNotEquals(Double.NaN, testMap.bound.getUpperRight().getX());	
		assertNotEquals(Double.NaN, testMap.bound.getUpperRight().getY());	
	}
	
	@Test
	public void testtostring(){
		GeneratorFlat flat = new GeneratorFlat();
		Map testMap = new Map(flat, 1);
		double [][] datatest= { {0,12}, {4,154}};
		testMap.setData(datatest);
		assertEquals("[ 0.0  12.0 ][ 4.0  154.0 ]",testMap.toString());
	}
	
	@Test
	public void testpregenerate(){
		GeneratorFlat flat = new GeneratorFlat();
		Bound bound = new Bound(new Point(0,0),new Point(10,10));
		int resolution = 1;
		Map testMap = new Map(flat, bound, resolution);
		testMap.pregenerate();
		assertNotNull(testMap.getData());
		assertEquals(testMap.getSizeX(), 10);
		assertEquals(testMap.getSizeY(), 10);
	}
	
	@Test
	public void testgenerate(){
		GeneratorFlat flat = new GeneratorFlat();
		Bound bound = new Bound(new Point(0,0),new Point(10,10));
		int resolution = 1;
		Map testMap = new Map(flat, bound, resolution);
		assertNotNull(testMap);		
		testMap.pregenerate();
		testMap.generate();
		for (int i = 0; i < testMap.getSizeX(); i++) {
			for (int j = 0; j < testMap.getSizeY(); j++) {
				assertEquals(1, (int) testMap.getData()[i][j]); 
			}
		}
	}
	
	@Test
	public void testexportToASC(){
		GeneratorFlat flat = new GeneratorFlat();
		Bound bound = new Bound(new Point(0,0),new Point(10,10));
		int resolution = 1;
		Map testMap = new Map(flat, bound, resolution);
		testMap.pregenerate();
		testMap.generate();
		int nbExceptions = 0;
		try {
			testMap.exportToASC("testFlat");
		}
		catch(Exception e) {
			nbExceptions ++;
		}
		assertEquals(nbExceptions, 0);
		nbExceptions = 0;
		testMap.exportToASC("/////////////un mauvais nom testFlat2");
		File f = new File("data//////////////un mauvais nom testFlat2");	
		assertEquals(f.exists(), false);
	}
	
	@Test
	public void testexportToGeoTiff(){
		GeneratorFlat flat = new GeneratorFlat();
		Bound bound = new Bound(new Point(0,0),new Point(10,10));
		int resolution = 1;
		Map testMap = new Map(flat, bound, resolution);
		testMap.pregenerate();
		testMap.generate();
		int nbExceptions = 0;
		try {
			testMap.exportToGeoTiff("testFlat");
		}
		catch(Exception e) {
			nbExceptions ++;
		}
		assertEquals(nbExceptions, 0);
		nbExceptions = 0;
		testMap.exportToGeoTiff("/////////////un mauvais nom testFlat2");
		File f = new File("data//////////////un mauvais nom testFlat2");	
		assertEquals(f.exists(), false);
	}
	
}
	