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
	private Bound bound; // Mon nom est Bound, James Bound
	private int resolution;
	private int crs; 

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
	 * @param generator Une instanciation d'une classe implémentant IGeneratorStrategy
	 * @param bound Les bords de la map
	 * @param resolution la résolution souhaité
	 * @param crs Le numéro EPSG de la projection cartographique choisi
	 */
	public Map(String nomStrategy, int resolution, Bound bound, int crs) throws StrategyNotFoundException {
		GeneratorFactory factory = new GeneratorFactory();
		this.generator = factory.create(nomStrategy);
		this.setResolution(resolution);
		this.setBound(bound);
		this.setCrs(crs);
	};

	/**
	 * Le constructeur bas niveau de la class Map
	 * @param generator Une instanciation d'une classe implémentant IGeneratorStrategy
	 * @param bound Les bords de la map
	 * @param resolution la résolution souhaité
	 */
	public Map(String nomStrategy, int resolution, Bound bound) throws StrategyNotFoundException {
		GeneratorFactory factory = new GeneratorFactory();
		this.generator = factory.create(nomStrategy);
		this.setResolution(resolution);
		this.setBound(bound);
		this.setCrs(2154);
	};
	
	/**
	 * Le constructeur avec une emprise par défaut 
	 * @param generator Une instanciation d'une classe implémentant IGeneratorStrategy
	 * @param resolution la résolution souhaité
	 * l'emprise par défaut est 0/0 -> 100/100
	 */
	public Map(String nomStrategy, int resolution) throws StrategyNotFoundException {
		GeneratorFactory factory = new GeneratorFactory();
		this.generator = factory.create(nomStrategy);
		this.setResolution(resolution);
		this.setBound(new Bound(new Point(0,0), new Point(100,100)));
		this.setCrs(2154);
	};

	
	/**
	 * Le constructeur le plus user-friendly possible avec juste le nom du générateur à rentrer
	 * la résolution et l'emprise sont générés par défaut
	 * résolution 1 et emprise (0,0) -> (100,100)
	 * Les noms de générateur pris en compte sont : 
	 * flat, perlin, random, simplex, value
	 * @param nomStrategy
	 * @throws StrategyNotFoundException
	 */
	public Map(String nomStrategy) throws StrategyNotFoundException {
		GeneratorFactory factory = new GeneratorFactory();
		this.generator = factory.create(nomStrategy);
		this.setResolution(1);
		this.setBound(new Bound( new Point(0,0), new Point(100,100)));
		this.setCrs(2154);
	};
	
	/**
	 * On calcule la taille de la matrice data qui contiendra les données du MNT
	 * Le nombre d'éléments de la matrice dépend de la résolution et de la largeur /hauteur
	 * de la map
	 */
	public void pregenerate() {
		this.sizeX = (int) Math.floor(this.getBound().getWidth()  / this.getResolution());
		this.sizeY = (int) Math.floor(this.getBound().getHeight() / this.getResolution());
		this.setData(new double[sizeX][sizeY]);
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
	 * Importe dans un objet de classe Bound les coordonnées
	 * du rectangle englobant un shapefile donné en entrée
	 * @param nomFichier le nom du shapefile (il y a un dossier shp dans agat, mais l'utilisateur 
	 * peut prendre son shp depuis n'importe quel endroit.)
	 * @return
	 */
	public Bound importShapefileBound(String nomFichier) {
		VectorReader shpReader = new VectorReader();
		Bound shpBound = shpReader.getBoundofFile(nomFichier, this.getCrs());
		this.setBound(shpBound);
		return shpBound;
	}
	
	/**
	 * Importe dans un objet de classe Bound les coordonnées
	 * du rectangle englobant un shapefile donné en entrée
	 * @param nomFichier le nom du raster 
	 * @return
	 */
	public Bound importRasterBound(String nomFichier) {
		RasterReader shpReader = new RasterReader();
		Bound shpBound = shpReader.getBoundofFile(nomFichier, this.getCrs());
		this.setBound(shpBound);
		return shpBound;
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
	
	/**
	 * Calcule pour l'utilisateur une résolution adapté é la carte é savoir la longueur 
	 * du plus petit coté /100
	 * @return la résolution conseillé
	 */
	public int getAdvisedResolution(){
		int min = Math.min(this.getSizeX(), this.getSizeY());
		return (int) min/100;
	}
	

	public double[][] getData() {
		return data;
	}

	public void setData(double[][] data) {
		this.data = data;
	}
	
	public int getSizeX() {
		this.sizeX = (int) Math.floor(this.getBound().getWidth()  / this.getResolution());
		return sizeX;
	}
	
	public int getSizeY() {
		this.sizeY = (int) Math.floor(this.getBound().getHeight() / this.getResolution());
		return sizeY;
	}
	public IGeneratorStrategy getGenerator() {
		return generator;
	}


	public void setGenerator(IGeneratorStrategy generator) {
		this.generator = generator;
	}


	public int getResolution() {
		return resolution;
	}


	public void setResolution(int resolution) {
		this.resolution = resolution;
	}


	public int getCrs() {
		return crs;
	}


	public void setCrs(int crs) {
		this.crs = crs;
	}


	public Bound getBound() {
		return bound;
	}


	public void setBound(Bound bound) {
		this.bound = bound;
	}
}
