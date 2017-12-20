package eu.ensg.tsi.agat.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.io.File;

import org.junit.Test;


public class MapTest {

	@Test
	public void testconstructorTwoArg(){
		Map testMap = new Map("flat");
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
		Map testMap = new Map("flat");
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
		Map testMap = new Map("random");
		testMap.importShapefileBound("shp/buffer_dissolve_paris.shp");
		testMap.generate();
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
		Map testMap = new Map("flat");
		double [][] datatest= { {0,12}, {4,154}};
		testMap.setData(datatest);
		assertEquals("[ 0.0  12.0 ][ 4.0  154.0 ]",testMap.toString());
	}
	
	@Test
	public void testpregenerate(){
		Map testMap = new Map("flat");
		testMap.pregenerate();
		assertNotNull(testMap.getData());
		assertEquals(testMap.getSizeX(), 100);
		assertEquals(testMap.getSizeY(), 100);
	}
	
	@Test
	public void testgenerate(){
		Map testMap = new Map("flat");
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
		Map testMap = new Map("flat");
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
		Map testMap = new Map("flat");
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
	
	@Test
	public void testresolutionadpate() {
		Map map = new Map("simplex");
		map.importShapefileBound("shp/buffer_dissolve_paris.shp");
		map.resolution = map.getAdvisedResolution();
		System.out.println(map.resolution);
		map.generate();
		map.exportToGeoTiff("testPariiiiiiiis");
	}
	
}
	