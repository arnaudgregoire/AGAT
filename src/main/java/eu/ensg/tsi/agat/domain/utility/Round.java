package eu.ensg.tsi.agat.domain.utility;
/**
 * Une toute petite classe avec une seule fonction dont l'unique but est 
 * d'arrondir à 2 décimales toutes les valeurs des maps
 * Cela permet de restreindre considérablement la taille des fichiers générés.
 * @author arnaudgregoire
 *
 */
public class Round {
	public static double round2Dec(Double a){
		return Math.round(a * 100.0) / 100.0 ;
	}
}
