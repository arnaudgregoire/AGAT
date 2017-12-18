package eu.ensg.tsi.agat.domain;

import eu.ensg.tsi.agat.domain.generator.IGeneratorStrategy;
import eu.ensg.tsi.agat.geotools.RasterReader;
import eu.ensg.tsi.agat.geotools.VectorReader;
import eu.ensg.tsi.agat.persistance.ASCWriter;
import eu.ensg.tsi.agat.persistance.GeotiffWriter;
import exceptions.StrategyNotFoundException;

public class Map {

	private IGeneratorStrategy generator;
	private double[][] data;
	private int sizeX;
	private int sizeY;
	public Bound bound; // Mon nom est Bound, James Bound
	public int resolution;
	public int crs; 

	/**
	 * Un affichage console des valeurs du MNT sous la forme
	 * [ 2.0  1.0 ]
	 * [ 1.0  3.0 ]
	 */
	@Override
	public String toString() {
		String string = "";
		for (int i = 0; i < this.data.length; i++) {
			string+="[";
			for (int j = 0; j < this.data[i].length; j++) {
				string+=" " + this.data[i][j] + " ";
			}
			string+="]";
		}
		return string;
	}
	
	
	/**
	 * Le constructeur bas niveau de la class Map
	 * @param generator Une instanciation d'une classe impl�mentant IGeneratorStrategy
	 * @param bound Les bords de la map
	 * @param resolution la r�solution souhait�
	 * @param crs Le num�ro EPSG de la projection cartographique choisi
	 */
	public Map(String nomStrategy, int resolution, Bound bound, int crs) throws StrategyNotFoundException {
		GeneratorFactory factory = new GeneratorFactory();
		this.generator = factory.create(nomStrategy);
		this.resolution = resolution;
		this.bound = bound;
		this.crs = crs;
	};

	/**
	 * Le constructeur bas niveau de la class Map
	 * @param generator Une instanciation d'une classe impl�mentant IGeneratorStrategy
	 * @param bound Les bords de la map
	 * @param resolution la r�solution souhait�
	 */
	public Map(String nomStrategy, int resolution, Bound bound) throws StrategyNotFoundException {
		GeneratorFactory factory = new GeneratorFactory();
		this.generator = factory.create(nomStrategy);
		this.resolution = resolution;
		this.bound = bound;
		this.crs = 2154;
	};
	
	/**
	 * Le constructeur avec une emprise par d�faut 
	 * @param generator Une instanciation d'une classe impl�mentant IGeneratorStrategy
	 * @param resolution la r�solution souhait�
	 * l'emprise par d�faut est 0/0 -> 100/100
	 */
	public Map(String nomStrategy, int resolution) throws StrategyNotFoundException {
		GeneratorFactory factory = new GeneratorFactory();
		this.generator = factory.create(nomStrategy);
		this.resolution = resolution;
		this.bound = new Bound(new Point(0,0), new Point(100,100));
		this.crs = 2154;
	};

	
	/**
	 * Le constructeur le plus user-friendly possible avec juste le nom du g�n�rateur à rentrer
	 * la r�solution et l'emprise sont g�n�r�s par d�faut
	 * r�solution 1 et emprise (0,0) -> (100,100)
	 * Les noms de g�n�rateur pris en compte sont : 
	 * flat, perlin, random, simplex, value
	 * @param nomStrategy
	 * @throws StrategyNotFoundException
	 */
	public Map(String nomStrategy) throws StrategyNotFoundException {
		GeneratorFactory factory = new GeneratorFactory();
		this.generator = factory.create(nomStrategy);
		this.resolution = 1;
		this.bound = new Bound( new Point(0,0), new Point(100,100));
		this.crs = 2154;
	};
	
	/**
	 * On calcule la taille de la matrice data qui contiendra les donn�es du MNT
	 * Le nombre d'�l�ments de la matrice d�pend de la r�solution et de la largeur /hauteur
	 * de la map
	 */
	public void pregenerate() {
		this.sizeX = (int) Math.floor(this.bound.getWidth()  / this.resolution);
		this.sizeY = (int) Math.floor(this.bound.getHeight() / this.resolution);
		this.setData(new double[sizeX][sizeY]);
	}
	
	/**
	 * On g�nère un MNT al�atoire
	 * La m�thode de g�n�ration d�pend de la classe du generator instanci�
	 * @return le MNT g�n�r� sous forme de matrice double[][]
	 */
	public double[][] generate(){
		this.pregenerate();
		this.generator.process(this.data);
		return this.data;
	};
	
	
	/**
	 * Importe dans un objet de classe Bound les coordonn�es
	 * du rectangle englobant un shapefile donn� en entr�e
	 * @param nomFichier le nom du shapefile (il y a un dossier shp dans agat, mais l'utilisateur 
	 * peut prendre son shp depuis n'importe quel endroit.)
	 * @return
	 */
	public Bound importShapefileBound(String nomFichier) {
		VectorReader shpReader = new VectorReader();
		Bound shpBound = shpReader.getBoundofShapefile(nomFichier);
		this.bound = shpBound;
		return shpBound;
	}
	
	/**
	 * Importe dans un objet de classe Bound les coordonn�es
	 * du rectangle englobant un shapefile donn� en entr�e
	 * @param nomFichier le nom du raster 
	 * @return
	 */
	public Bound importRasterBound(String nomFichier) {
		RasterReader shpReader = new RasterReader();
		Bound shpBound = shpReader.getBoundofShapefile(nomFichier);
		this.bound = shpBound;
		return shpBound;
	}
	
	/**
	 * Exporte les donn�es de l'instance map en cours dans un fichier .asc
	 * Il sera ajout� dans le sous dossier data 
	 * @param nomFichier le nom du fichier, l'extension .asc sera rajout�
	 * TODO check si l'utilisateur a d�ja marqu� l'extension .asc
	 */
	public void exportToASC(String nomFichier) {
		ASCWriter ascWriter = new ASCWriter();
		ascWriter.write(nomFichier, this);
	}
	
	/**
	 * Exporte les donn�es de l'instance map en cours dans un fichier .tiff
	 * Il sera ajout� dans le sous dossier data 
	 * @param nomFichier le nom du fichier, l'extension .asc sera rajout�
	 * TODO check si l'utilisateur a d�ja marqu� l'extension .geotiff
	 */
	public void exportToGeoTiff(String nomFichier) {
		GeotiffWriter geoWriter = new GeotiffWriter();
		geoWriter.write(nomFichier, this);
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
