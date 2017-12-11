package eu.ensg.tsi.agat.domain;

public class GeneratorSimplexNoise implements IGeneratorStrategy {

	public void process(double[][] data) {
	    SimplexNoise simplexNoise=new SimplexNoise(data.length,0.1,5000);


	    for(int i=0;i<data.length;i++){
	        for(int j=0;j<data.length;j++){
	            data[i][j]=simplexNoise.getNoise(i,j);
	        }
	    }

	}

}
