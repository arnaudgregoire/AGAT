package eu.ensg.tsi.agat.domain;

public class Map {

	private IGeneratorStrategy generator;
	private double[][] data;
	public Bound bound; // Mon nom est Bound, James Bound
	public double resolution;
	
	/**
	 * Un affichage console des valeurs du MNT sous la forme
	 * [ 2.0  1.0 ]
	 * [ 1.0  3.0 ]
	 */
	public void consoleDraw() {
		for (int i = 0; i < this.data.length; i++) {
			System.out.print("[");
			for (int j = 0; j < this.data[i].length; j++) {
				System.out.print(" " + this.data[i][j] + " ");
			}
			System.out.println("]");
		}
	}
	
	
	/**
	 * Le constructeur bas niveau de la class Map
	 * @param generator Une instanciation d'une classe implémentant IGeneratorStrategy
	 * @param bound Les bords de la map
	 * @param resolution la résolution souhaité
	 */
	public Map(IGeneratorStrategy generator, Bound bound, double resolution) {
		super();
		this.generator = generator;
		this.bound = bound;
		this.resolution = resolution;
	}

	
	/**
	 * On génère un MNT aléatoire
	 * La méthode de génération dépend de la classe du generator instancié
	 * @return le MNT généré sous forme de matrice double[][]
	 */
	public double[][] generate(){
		this.pregenerate();
		this.generator.process(this.data);
		return this.data;
	};
	

	/**
	 * On calcule la taille de la matrice data qui contiendra les données du MNT
	 * Le nombre d'éléments de la matrice dépend de la résolution et de la largeur /hauteur
	 * de la map
	 */
	public void pregenerate() {
		int sizeX = (int) Math.floor(this.bound.getWidth()  / this.resolution);
		int sizeY = (int) Math.floor(this.bound.getHeight() / this.resolution);
		this.setData(new double[sizeX][sizeY]);
	}
	
	public IGeneratorStrategy getGenerator() {
		return generator;
	}

	public void setGenerator(IGeneratorStrategy generator) {
		this.generator = generator;
	}

	public double[][] getData() {
		return data;
	}

	public void setData(double[][] data) {
		this.data = data;
	}
}
