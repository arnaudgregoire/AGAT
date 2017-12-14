package eu.ensg.tsi.agat.domain;

import eu.ensg.tsi.agat.domain.generator.IGeneratorStrategy;
import eu.ensg.tsi.agat.persistance.ASCWriter;
import eu.ensg.tsi.agat.persistance.GeotiffWriter;
import geotools.ShapefileReader;

public class Map {

	private IGeneratorStrategy generator;
	private double[][] data;
	private int sizeX;
	private int sizeY;
	public Bound bound; // Mon nom est Bound, James Bound
	public int resolution;
	public String crs; 
	
	/**
	 * Importe dans un objet de classe Bound les coordonnées
	 * du rectangle englobant un shapefile donné en entrée
	 * @param nomFichier le nom du shapefile (il y a un dossier shp dans agat, mais l'utilisateur 
	 * peut prendre son shp depuis n'importe quel endroit.)
	 * @return
	 */
	public Bound importShapefileBound(String nomFichier) {
		ShapefileReader shpReader = new ShapefileReader();
		Bound shpBound = shpReader.getBoundofShapefile(nomFichier);
		this.bound = shpBound;
		return shpBound;
	}
	
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
	public Map(IGeneratorStrategy generator, Bound bound, int resolution) {
		super();
		this.generator = generator;
		this.bound = bound;
		this.resolution = resolution;
	}

	
	public Map(IGeneratorStrategy generator, int resolution) {
		this.generator = generator;
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
		this.sizeX = (int) Math.floor(this.bound.getWidth()  / this.resolution);
		this.sizeY = (int) Math.floor(this.bound.getHeight() / this.resolution);
		this.setData(new double[sizeX][sizeY]);
	}
	
	
	/**
	 * Exporte les données de l'instance map en cours dans un fichier .asc
	 * Il sera ajouté dans le sous dossier data 
	 * @param nomFichier le nom du fichier, l'extension .asc sera rajouté
	 * TODO check si l'utilisateur a déja marqué l'extension .asc
	 */
	public void exportToASC(String nomFichier) {
		ASCWriter ascWriter = new ASCWriter();
		ascWriter.write(nomFichier, this);
	}
	
	/**
	 * Exporte les données de l'instance map en cours dans un fichier .tiff
	 * Il sera ajouté dans le sous dossier data 
	 * @param nomFichier le nom du fichier, l'extension .asc sera rajouté
	 * TODO check si l'utilisateur a déja marqué l'extension .geotiff
	 */
	public void exportToGeoTiff(String nomFichier) {
		GeotiffWriter geoWriter = new GeotiffWriter();
		geoWriter.write(nomFichier, this);
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
	
	public int getSizeX() {
		return sizeX;
	}
	
	public int getSizeY() {
		return sizeY;
	}
}
