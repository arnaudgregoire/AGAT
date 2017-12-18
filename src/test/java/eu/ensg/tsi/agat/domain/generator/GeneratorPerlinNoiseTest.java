package eu.ensg.tsi.agat.domain.generator;

import static org.junit.Assert.*;

import org.junit.Test;

import eu.ensg.tsi.agat.domain.Map;

public class GeneratorPerlinNoiseTest {

	@Test
	public void testGenerator() {
		Map map = new Map("perlin");
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
	}
}
