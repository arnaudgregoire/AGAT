package eu.ensg.tsi.agat.domain.generator;

public class GeneratorSimplexNoise implements IGeneratorStrategy {

	public void process(double[][] data) {
	    SimplexNoise simplexNoise=new SimplexNoise(data.length,0.1,100);


	    for(int i=0;i<data.length;i++){
	        for(int j=0;j<data[0].length;j++){
	            data[i][j]=(simplexNoise.getNoise(i,j) +0.1) * 5 ;
	        }
	    }

	}

}
