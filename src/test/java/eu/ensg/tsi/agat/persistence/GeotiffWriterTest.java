package eu.ensg.tsi.agat.persistence;
import static org.junit.Assert.*;

import org.junit.Test;

import eu.ensg.tsi.agat.domain.Map;

public class GeotiffWriterTest {

	@Test
	public void testexportASC()  {
		Map testCarte = new Map("value");
		testCarte.generate();	
		int nbExceptions = 0;
		try {
			testCarte.exportToASC("testValue");
		}
		catch(Exception e) {
			nbExceptions ++;
		}
		assertEquals(0, nbExceptions);
	}
	
	@Test
	public void testexportgeotiff()  {
		Map testCarte = new Map("value");
		testCarte.generate();	
		int nbExceptions = 0;
		try {
			testCarte.exportToGeoTiff("testValue");
		}
		catch(Exception e) {
			nbExceptions ++;
		}
		assertEquals(0, nbExceptions);
	}

}
