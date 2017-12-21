package eu.ensg.tsi.agat.domain.generator;
/**
 * L'interface de stratégie de génération
 * Avec l'ensemble des classes l'implémentant ainsi que la classe Map, 
 * elles forment un design pattern stratégie
 * @author arnaudgregoire
 *
 */
public interface IGeneratorStrategy {
	public void process( double[][] data);
}
