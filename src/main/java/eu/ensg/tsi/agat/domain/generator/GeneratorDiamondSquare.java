package eu.ensg.tsi.agat.domain.generator;

import eu.ensg.tsi.agat.domain.utility.Round;

/**
 * Implémentation du pseudo code de wikipedia
 * https://fr.wikipedia.org/wiki/Algorithme_Diamant-Carr%C3%A9
 * @author Arnaud
 *
 */
public class GeneratorDiamondSquare implements IGeneratorStrategy {
	
	/**
	 * L'implémentation de la méthode hérité de l'interface IGeneratorStrategy
	 * C'est cette méthode qui est appelé par la classe Map au moment de la fonction generate()
	 * 
	 */
	@Override
	public void process(double[][] data) {
		diamondsquare(data);
		resample(data);
	}
	
	/**
	 * réétalone les valeurs de l'algorithme pour les recentrer entre 0 et 1
	 * @param data
	 */
	private void resample(double[][] data){
		double min = 0;
		double max = 0;

		
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length; j++) {
				min = Math.min(data[i][j], min);
				max = Math.max(data[i][j], max);
			}
		}
		
		double InvMaxMin = 1 / (max - min);
		
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length; j++) {
				data[i][j] = Round.round2Dec(InvMaxMin * (data[i][j] - max) +1);
			}
		}
		
	}
	
	/**
	 * L'algorithme de diamant - carré
	 * @param data
	 */
	private void diamondsquare(double[][] data){
		int h = data.length;
		
		data[0][0] 							 = Math.random() * h; 
		data[data.length -1][0] 			 = Math.random() * h;
		data[0][data.length -1] 			 = Math.random() * h;
		data[data.length -1][data.length -1] = Math.random() * h;
				
		int i = data.length -1;
		
		//phase diamant
		while(i>1){
			int id = i / 2;
			for (int x = id; x <= h - 1; x += i) {
				for (int y = id; y <= h - 1; y += i) {
					double moyenne = (data[(x - id)][(y - id)]
									+ data[(x - id)][(y + id)]
									+ data[(x + id)][(y + id)]
									+ data[(x + id)][(y - id)]) / 4;
					
					data[x][y] = moyenne + (Math.random()*2 -1) * id;
				}
			}
			//phase carré
			for (int x = 0; x <= h - 1; x += id) {
				int decalage;
				if (x % i == 0)
					decalage = id;
				else {
					decalage = 0;
				}
				for (int y = decalage; y <= h - 1; y += i) {
					int somme = 0;
					int n = 0;
					if (x >= id) {
						somme += data[(x - id)][y];
						n++;
					}
					if (x + id < h - 1) {
						somme += data[(x + id)][y];
						n++;
					}
					if (y >= id) {
						somme += data[x][(y - id)];
						n++;
					}
					if (y + id < h - 1) {
						somme += data[x][(y + id)];
						n++;
					}
					data[x][y] = somme/n + (Math.random()*2 -1) * id;
				}
			}
			i = id;
		}
	}		
}
