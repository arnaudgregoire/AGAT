package eu.ensg.tsi.agat.geotools;

import eu.ensg.tsi.agat.domain.Bound;

/**
 * L'interface de lecture de fichier qui peuvent être aussi bien au format vecteur qu'au format raster
 * @author formation
 *
 */
public interface IReader {
	/**
	 * La méthode appelé par la classe Map dans la fonction d'import de shapefile/raster
	 * @param FilePath le nom du fichier
	 * @param epsg l'identifiant epsg de la map
	 * @return L'emprise associé au shapefile ouvert
	 */
	 public Bound getBoundofFile(String FilePath, int epsg);
}
