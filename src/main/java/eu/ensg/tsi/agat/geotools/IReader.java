package eu.ensg.tsi.agat.geotools;

import eu.ensg.tsi.agat.domain.Bound;

public interface IReader {
	 public Bound getBoundofFile(String FilePath, int epsg);
}
