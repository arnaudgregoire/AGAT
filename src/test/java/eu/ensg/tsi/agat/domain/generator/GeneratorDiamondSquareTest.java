package eu.ensg.tsi.agat.domain.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import eu.ensg.tsi.agat.domain.Bound;
import eu.ensg.tsi.agat.domain.Map;
import eu.ensg.tsi.agat.domain.Point;
import exceptions.MapNotElligibleToDiamondSquareException;

public class GeneratorDiamondSquareTest {

	@Test
	public void test() {
		Map map = new Map("diamond");
		map.setBound(new Bound(new Point(0,0), new Point(141,129)));
		int nbExceptions = 0;
		try{
			map.generate();	
		}
		catch(MapNotElligibleToDiamondSquareException e){
			nbExceptions ++;
		}
		assertEquals(1, nbExceptions);
		
		map = new Map("diamond");
		//map.setBound(new Bound(new Point(0,0), new Point(2049,2049)));
		map.generate();
		double somme = 0;
		
		for (int i = 0; i < map.getData().length; i++) {
			for (int j = 0; j < map.getData()[0].length; j++) {
				assertEquals(true, map.getData()[i][j]>=0);
				assertEquals(true, map.getData()[i][j]<=1);
				somme += map.getData()[i][j];
			}
		}
		assertNotEquals(0, (int) somme);
		map.exportToGeoTiff("testdiamond");
	}
	
	
	@Test
	public void test2(){
		Map map = new Map("perlin");
		map.setResolution(map.getAdvisedResolution());
		map.generate();
		map.exportToGeoTiff("testAdvisedResolution");
	}

}
