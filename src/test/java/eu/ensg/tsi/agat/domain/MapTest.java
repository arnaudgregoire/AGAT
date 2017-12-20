package eu.ensg.tsi.agat.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.io.File;

import org.junit.Test;

import exceptions.StrategyNotFoundException;


public class MapTest {

	@Test
	public void testconstructorTwoArg(){
		Map testMap = new Map("flat");
		assertEquals(0,(int) testMap.getBound().getBottomLeft().getX());
		assertEquals(0,(int) testMap.getBound().getBottomLeft().getY());		
		assertEquals(100,(int) testMap.getBound().getBottomRight().getX());
		assertEquals(0,(int) testMap.getBound().getBottomRight().getY());		
		assertEquals(0,(int) testMap.getBound().getUpperleft().getX());
		assertEquals(100,(int) testMap.getBound().getUpperleft().getY());	
		assertEquals(100,(int) testMap.getBound().getUpperRight().getX());
		assertEquals(100,(int) testMap.getBound().getUpperRight().getY());
	}
	
	@Test
	public void testconstructorOneArg(){
		Map testMap = new Map("flat");
		assertEquals(0,(int) testMap.getBound().getBottomLeft().getX());
		assertEquals(0,(int) testMap.getBound().getBottomLeft().getY());		
		assertEquals(100,(int) testMap.getBound().getBottomRight().getX());
		assertEquals(0,(int) testMap.getBound().getBottomRight().getY());		
		assertEquals(0,(int) testMap.getBound().getUpperleft().getX());
		assertEquals(100,(int) testMap.getBound().getUpperleft().getY());	
		assertEquals(100,(int) testMap.getBound().getUpperRight().getX());
		assertEquals(100,(int) testMap.getBound().getUpperRight().getY());
	}
	
	@Test
	public void testConstructorUserFriendly(){
		Map testMap = new Map("flat");
		testMap = new Map("random");
		testMap = new Map("perlin");
		testMap = new Map("value");
		testMap = new Map("simplex");
		assertEquals(0,(int) testMap.getBound().getBottomLeft().getX());
		assertEquals(0,(int) testMap.getBound().getBottomLeft().getY());		
		assertEquals(100,(int) testMap.getBound().getBottomRight().getX());
		assertEquals(0,(int) testMap.getBound().getBottomRight().getY());		
		assertEquals(0,(int) testMap.getBound().getUpperleft().getX());
		assertEquals(100,(int) testMap.getBound().getUpperleft().getY());	
		assertEquals(100,(int) testMap.getBound().getUpperRight().getX());
		assertEquals(100,(int) testMap.getBound().getUpperRight().getY());
		int nbExceptions = 0;
		int nbAutreExceptions = 0;
		try {
			testMap = new Map("un mauvais nom de générateur !");
		}
		catch(Exception e) {
			if (e.getClass().equals(StrategyNotFoundException.class)){
				nbExceptions ++;
			}
			else{
				nbAutreExceptions ++;
			}
		}
		assertEquals(1, nbExceptions);
		assertEquals(0, nbAutreExceptions);
	}
	

	@Test
	public void testimportShapefileBound() {
		Map testMap = new Map("simplex");
		testMap.importShapefileBound("shp/buffer_dissolve_paris.shp");
		//testMap.generate();
		assertNotSame(Double.NaN, testMap.getBound().getBottomLeft().getX());	
		assertNotSame(Double.NaN, testMap.getBound().getBottomLeft().getY());	
		assertNotSame(Double.NaN, testMap.getBound().getBottomRight().getX());	
		assertNotSame(Double.NaN, testMap.getBound().getBottomRight().getY());	
		assertNotSame(Double.NaN, testMap.getBound().getUpperleft().getX());	
		assertNotSame(Double.NaN, testMap.getBound().getUpperleft().getY());	
		assertNotSame(Double.NaN, testMap.getBound().getUpperRight().getX());	
		assertNotSame(Double.NaN, testMap.getBound().getUpperRight().getY());
		testMap.setResolution(testMap.getAdvisedResolution());
		testMap.generate();
		testMap.exportToASC("testParis3");
	}
	
	@Test
	public void testimportrasterBound() {
		Map testMap = new Map("simplex");
		testMap.importRasterBound("data/testParis.tiff");
		//testMap.generate();
		assertNotSame(Double.NaN, testMap.getBound().getBottomLeft().getX());	
		assertNotSame(Double.NaN, testMap.getBound().getBottomLeft().getY());	
		assertNotSame(Double.NaN, testMap.getBound().getBottomRight().getX());	
		assertNotSame(Double.NaN, testMap.getBound().getBottomRight().getY());	
		assertNotSame(Double.NaN, testMap.getBound().getUpperleft().getX());	
		assertNotSame(Double.NaN, testMap.getBound().getUpperleft().getY());	
		assertNotSame(Double.NaN, testMap.getBound().getUpperRight().getX());	
		assertNotSame(Double.NaN, testMap.getBound().getUpperRight().getY());
		//testMap.setResolution(testMap.getAdvisedResolution());
		//testMap.generate();
		//testMap.exportToGeoTiff("testParis2");
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
		map.setResolution(map.getAdvisedResolution());
		System.out.println(map.getResolution());
		map.generate();
		map.exportToGeoTiff("testPariiiiiiiis");
	}
	
}
	