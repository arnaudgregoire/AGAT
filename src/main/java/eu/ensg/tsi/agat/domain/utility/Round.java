package eu.ensg.tsi.agat.domain.utility;

public class Round {
	public static double round2Dec(Double a){
		return Math.round(a * 100.0) / 100.0 ;
	}
}
