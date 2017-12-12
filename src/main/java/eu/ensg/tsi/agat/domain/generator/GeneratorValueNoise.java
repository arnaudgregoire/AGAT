package eu.ensg.tsi.agat.domain.generator;

/**
 * Un générateur de bruit par valeurs
 * inspiré du tutoriel "Bruits et nombres aléatoires cohérents"
 * par OpenClassrooms
 * @see https://openclassrooms.com/courses/bruits-et-nombres-aleatoires-coherents
 * @author Arnaud
 */
public class GeneratorValueNoise implements IGeneratorStrategy {

	private int pas;
	private int nbOctaves;
	private double persistance;
	private double [][] data;
	private double [][] noise;

	/**
	 * Un constructeur avec tous les paramètres
	 * @param pas Le pas est le facteur d'association des données, il doit être de taille comparable à celle de la map
	 * @param nbOctaves Le nombres d'octaves, plus il y a d'octaves, plus il y aura de détails
	 * @param persistance La persistance, comprise entre 0 et 1, plus la persitance est élevé, plus la granularité des données sera élevé
	 */
	public GeneratorValueNoise(int pas, int nbOctaves, double persistance) {
		super();
		this.pas = pas;
		this.nbOctaves = nbOctaves;
		this.persistance = persistance;
	}

	/**
	 * Le constructeur simplifié pour les utilisateurs débutants dans le bruit de valeurs
	 * Le pas est fixé plus tard en fonction de la taille du MNT
	 * Le nombre d'octaves et la persistence sont fixés à des seuils "moyens" 
	 */
	public GeneratorValueNoise() {
		super();
		this.pas = -1;
		this.nbOctaves = 5;
		this.persistance = 0.5;
	}

	/**
	 * L'implémentation de la méthode process hérité de la IGeneratorStrategy
	 * La méthode process est appelé par l'objet map
	 * C'est une utilisation du patron Strategy
	 */
	public void process(double[][] data) {
		this.data = data;
		if(this.pas == -1) {
			this.pas  = (int) min(data.length, data[0].length)/3;
		}
		createRandomNoise();
		
	    for(int i = 0; i < data.length ; i++)
	    {
	    	for (int j = 0; j < data[0].length; j++) {
	    		data[i][j] =Math.round(valueNoise(i, j) * 100.0) / 100.0 ;
			}
	    }
	}
	
	/**
	 * Une fonction retournant le minimum entre a et b
	 * @param a 
	 * @param b
	 * @return le minimum
	 */
	private int min(int a, int b) {
		int minimum = -1;
		if(a>b) {
			minimum = b;
		}
		else {
			minimum = a;
		}
		return minimum;
	}

	/**
	 * Créé une carte de valeurs aléatoires qui sera utilisé par le bruit de valeurs
	 * pour créer un bruit cohérent
	 */
	private void createRandomNoise() {
	    int longueur = data.length;
	    int hauteur = data[0].length;

	    int longueur_max = (int) Math.ceil(longueur * Math.pow(2, nbOctaves  - 1)  / pas) +1;
	    int hauteur_max  = (int) Math.ceil(hauteur  * Math.pow(2, nbOctaves  - 1)  / pas) +1;
	    
	    System.out.println(longueur_max);
	    System.out.println(hauteur_max);
	    
	    this.noise = new double[longueur_max][hauteur_max];
	    		
	    for(int i = 0; i < longueur_max ; i++)
	    {
	    	for (int j = 0; j < hauteur_max; j++) {
	    		noise[i][j] = Math.random();
			}
	    }
	        
	}
	
	/**
	 * La fonction implémentant le bruit de valeurs
	 * @param x l'abcisse du point où l'on veut déterminer une valeur de bruit 
	 * @param y l'ordonnée
	 * @return le bruit généré par la méthode du bruit de valeur
	 */
	private double valueNoise(double x, double y) {     
	        double somme = 0;
	        double p = 1;
	        int f = 1;
	        //System.out.println("new value noise");
	        for(int i = 0 ; i < nbOctaves ; i++) {
	                somme += p * smooth(x * f, y * f);
	                p *= persistance;
	                f *= 2;
	        }
	        return somme * (1 - persistance) / (1 - p);
	}

	/**
	 * Fonction intermédiaire appelant la fonction d'interpolation
	 * @param x
	 * @param y
	 * @return
	 */
	private double smooth(double x, double y) {
	   int i = (int) (x / pas);
	   int j = (int) (y / pas);
	   return interpolation_cos2D(noise[i][j], noise[i+1][j], noise[i][j+1], noise[i+1][j+1], (x / pas) % 1, (y / pas)% 1);
	}
	
	/**
	 * Fonctio, d'interpolation cosinuidale
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param x
	 * @param y
	 * @return
	 */
	private double interpolation_cos2D(double a, double b, double c, double d, double x, double y) {
		   double y1 = interpolation_cos1D(a, b, x);
		   double y2 = interpolation_cos1D(c, d, x);
		   return  interpolation_cos1D(y1, y2, y);
		}
	
	private double interpolation_cos1D(double a, double b, double x) {
		   double k = (1 - Math.cos(x * Math.PI)) / 2;
		    return a * (1 - k) + b * k;
		}

	public double [][] getData() {
		return data;
	}

	public void setData(double [][] data) {
		this.data = data;
	}

}
