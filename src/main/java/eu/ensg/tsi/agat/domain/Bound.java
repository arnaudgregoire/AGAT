package eu.ensg.tsi.agat.domain;


/**
 * 
 * @author Arnaud
 * Emprise de la map
 */
public class Bound {
	private Point upperleft;
	private Point bottomLeft;
	private Point bottomRight;
	private Point upperRight;
	private double width;
	private double height;
	
	/**
	 * Une fonction de calcul de distance cartésienne pour trouver la largeur et la hauteur
	 * de la map
	 * @param a le premier point
	 * @param b le deuxième point
	 * @return la distance séparant les points a et b
	 */
	public double distance(Point a, Point b) {
		double dx = a.getX() - b.getX();
		double dy = a.getY() - b.getY();
		return Math.sqrt(dx*dx + dy*dy);
	}
	
	
	/**
	 * Calcul la hauteur et la largeur de la map
	 */
	private void computeWidthHeight() {
		this.width  = distance(this.bottomLeft, this.bottomRight);
		this.height = distance(this.bottomLeft, this.upperleft);
	}
	
	
	/**
	 * Le constructeur de l'emprise avec 2 points : en bas à gauche, en haut à droite
	 * @param bottomLeft le coin inférieur gauche de la map
	 * @param upperRight le coin supérieur droit de la map
	 */
	public Bound(Point bottomLeft, Point upperRight) {
		super();
		this.bottomLeft  = bottomLeft;
		this.upperRight  = upperRight;
		this.upperleft   = new Point(this.bottomLeft.getX(), this.upperRight.getY());
		this.bottomRight = new Point(this.upperRight.getX(), this.bottomLeft.getY());
		this.computeWidthHeight();
	}
	
	/**
	 *  Le constructeur de l'emprise avec les 4 points limites
	 * @param upperleft
	 * @param bottomLeft
	 * @param bottomRight
	 * @param upperRight
	 */
	public Bound(Point upperleft, Point bottomLeft, Point bottomRight, Point upperRight) {
		super();
		this.upperleft = upperleft;
		this.bottomLeft = bottomLeft;
		this.bottomRight = bottomRight;
		this.upperRight = upperRight;
		this.computeWidthHeight();
	}


	public Point getUpperleft() {
		return upperleft;
	}
	public void setUpperleft(Point upperleft) {
		this.upperleft = upperleft;
	}
	public Point getBottomLeft() {
		return bottomLeft;
	}
	public void setBottomLeft(Point bottomLeft) {
		this.bottomLeft = bottomLeft;
	}
	public Point getBottomRight() {
		return bottomRight;
	}
	public void setBottomRight(Point bottomRight) {
		this.bottomRight = bottomRight;
	}
	public Point getUpperRight() {
		return upperRight;
	}
	public void setUpperRight(Point upperRight) {
		this.upperRight = upperRight;
	}
	public double getWidth() {
		return width;
	}
	public double getHeight() {
		return height;
	}

}
