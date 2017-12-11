package eu.ensg.tsi.agat.persistance;

import eu.ensg.tsi.agat.domain.Map;

public interface IWriter {
	public void write(String nomFichier, Map map);
}
