package eu.ensg.tsi.agat.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class BoundTest {

	@Test
	public void testconstructors() {
		Bound test = new Bound(new Point(0,0), new Point(2,2));
		
		assertEquals(0,(int) test.getBottomLeft().getX());
		assertEquals(0,(int) test.getBottomLeft().getY());
		
		assertEquals(2,(int) test.getBottomRight().getX());
		assertEquals(0,(int) test.getBottomRight().getY());
		
		assertEquals(0,(int) test.getUpperleft().getX());
		assertEquals(2,(int) test.getUpperleft().getY());
		
		assertEquals(2,(int) test.getUpperRight().getX());
		assertEquals(2,(int) test.getUpperRight().getY());
		
		test = new Bound(new Point("MOMO LA ZARZOUILLE",2), new Point(0,0), new Point(2,0), new Point(2,2));
		
		assertEquals(0,(int) test.getBottomLeft().getX());
		assertEquals(0,(int) test.getBottomLeft().getY());
		
		assertEquals(2,(int) test.getBottomRight().getX());
		assertEquals(0,(int) test.getBottomRight().getY());
		
		assertEquals(0,(int) test.getUpperleft().getX());
		assertEquals(2,(int) test.getUpperleft().getY());
		
		assertEquals(2,(int) test.getUpperRight().getX());
		assertEquals(2,(int) test.getUpperRight().getY());
	}
	
	@Test
	public void testdistance() {
		Bound test = new Bound(new Point(0,0), new Point(3,4));
		assertEquals(4, (int) test.getHeight());
		assertEquals(3, (int) test.getWidth());
		assertEquals( 5, (int) test.distance(test.getBottomLeft(), test.getUpperRight()));
	}


}
