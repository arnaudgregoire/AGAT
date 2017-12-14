package eu.ensg.tsi.agat.persistence;



import eu.ensg.tsi.agat.domain.Bound;
import eu.ensg.tsi.agat.domain.Map;
import eu.ensg.tsi.agat.domain.Point;
import eu.ensg.tsi.agat.domain.generator.GeneratorSimplexNoise;

class GeotiffWriterTest {

	@org.junit.Test
	void test()  {
		GeneratorSimplexNoise SimplexMaker = new GeneratorSimplexNoise();
		Bound testBound = new Bound(new Point(0,0), new Point(100,100));
		Map testCarte = new Map(SimplexMaker, testBound, 1);
		testCarte.generate();	
		testCarte.exportToASC("testValue");
		testCarte.exportToGeoTiff("testValue.tiff");
	}

}
