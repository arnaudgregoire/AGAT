package eu.ensg.tsi.agat.domain.generator;

import static org.junit.Assert.*;

import org.junit.Test;

import eu.ensg.tsi.agat.domain.Bound;
import eu.ensg.tsi.agat.domain.Map;
import eu.ensg.tsi.agat.domain.Point;

public class GeneratorFlatTest {

	@Test
	public void test() {
		Map testCarte = new Map("flat");
		testCarte.generate();	
		for (int i = 0; i < testCarte.getData().length; i++) {
			for (int j = 0; j < testCarte.getData()[0].length; j++) {
				assertEquals( 1, (int) testCarte.getData()[i][j] );
			}
		}

	}

}
