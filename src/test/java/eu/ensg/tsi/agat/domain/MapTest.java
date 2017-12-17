package eu.ensg.tsi.agat.domain;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;

import eu.ensg.tsi.agat.domain.generator.GeneratorFlat;


public class MapTest {

	@Test
	public void testconstructorTwoArg(){
		GeneratorFlat flat = new GeneratorFlat();
		Map testMap = new Map(flat, 1);
		assertEquals(0,(int) testMap.bound.getBottomLeft().getX());
		assertEquals(0,(int) testMap.bound.getBottomLeft().getY());		
		assertEquals(100,(int) testMap.bound.getBottomRight().getX());
		assertEquals(0,(int) testMap.bound.getBottomRight().getY());		
		assertEquals(0,(int) testMap.bound.getUpperleft().getX());
		assertEquals(100,(int) testMap.bound.getUpperleft().getY());	
		assertEquals(100,(int) testMap.bound.getUpperRight().getX());
		assertEquals(100,(int) testMap.bound.getUpperRight().getY());
	}
	
	@Test
	public void testconstructorOneArg(){
		GeneratorFlat flat = new GeneratorFlat();
		Map testMap = new Map(flat);
		assertEquals(0,(int) testMap.bound.getBottomLeft().getX());
		assertEquals(0,(int) testMap.bound.getBottomLeft().getY());		
		assertEquals(100,(int) testMap.bound.getBottomRight().getX());
		assertEquals(0,(int) testMap.bound.getBottomRight().getY());		
		assertEquals(0,(int) testMap.bound.getUpperleft().getX());
		assertEquals(100,(int) testMap.bound.getUpperleft().getY());	
		assertEquals(100,(int) testMap.bound.getUpperRight().getX());
		assertEquals(100,(int) testMap.bound.getUpperRight().getY());
	}
	
	@Test
	public void testConstructorUserFriendly(){
		Map testMap = new Map("flat");
		testMap = new Map("random");
		testMap = new Map("perlin");
		testMap = new Map("value");
		testMap = new Map("simplex");
		assertEquals(0,(int) testMap.bound.getBottomLeft().getX());
		assertEquals(0,(int) testMap.bound.getBottomLeft().getY());		
		assertEquals(100,(int) testMap.bound.getBottomRight().getX());
		assertEquals(0,(int) testMap.bound.getBottomRight().getY());		
		assertEquals(0,(int) testMap.bound.getUpperleft().getX());
		assertEquals(100,(int) testMap.bound.getUpperleft().getY());	
		assertEquals(100,(int) testMap.bound.getUpperRight().getX());
		assertEquals(100,(int) testMap.bound.getUpperRight().getY());
		int nbExceptions = 0;
		try {
			testMap = new Map("un mauvais nom de générateur !");
		}
		catch(Exception e) {
			nbExceptions ++;
		}
		assertEquals(1, nbExceptions);
	}
	

	@Test
	public void testimportShapefileBound() {
		GeneratorFlat flat = new GeneratorFlat();
		Map testMap = new Map(flat, 1);
		testMap.importShapefileBound("shp/buffer_dissolve_paris.shp");
		assertNotSame(Double.NaN, testMap.bound.getBottomLeft().getX());	
		assertNotSame(Double.NaN, testMap.bound.getBottomLeft().getY());	
		assertNotSame(Double.NaN, testMap.bound.getBottomRight().getX());	
		assertNotSame(Double.NaN, testMap.bound.getBottomRight().getY());	
		assertNotSame(Double.NaN, testMap.bound.getUpperleft().getX());	
		assertNotSame(Double.NaN, testMap.bound.getUpperleft().getY());	
		assertNotSame(Double.NaN, testMap.bound.getUpperRight().getX());	
		assertNotSame(Double.NaN, testMap.bound.getUpperRight().getY());	
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
	