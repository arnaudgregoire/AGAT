package eu.ensg.tsi.agat.persistance;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import eu.ensg.tsi.agat.domain.Map;

public class ASCWriter implements IWriter {

	public String buffer = "";
	
	/**
	 * On marque notre String Buffer dans le fichier nomFichier.asc
	 */
	public void write(String nomFichier, Map map) {
		Writer writer = null;
		addMetadata(map);
		addData(map);
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream( nomFichier + ".asc"), "utf-8"));
		    writer.write(this.buffer);
		} catch (IOException ex) {
		  // report
		} finally {
		   try {writer.close();} catch (Exception ex) {/*ignore*/}
		}
	}
	
	/**
	 * On ajoute les métadatas des fichiers
	 * ncolls nombre de colonnes
	 * nrows nombre de lignes
	 * xllcorner la limite en x bas gauche du MNT
	 * yllcorner la limite en y bas gauche du MNT
	 * cellsize la résolution
	 * NODATA_value la valeur quand il manque des datas
	 */
	private void addMetadata(Map map) {
		this.buffer += "ncols "     + map.getSizeX() + "\n";
		this.buffer += "nrows "     + map.getSizeY() + "\n";
		this.buffer += "xllcorner " + map.getBound().getBottomLeft().getX() + "\n";
		this.buffer += "yllcorner " + map.getBound().getBottomLeft().getY() + "\n";
		this.buffer += "cellsize "  + map.getResolution() + "\n";
		this.buffer += "NODATA_value -9999 \n"; 
	}

	/**
	 * on ajoute les datas de la map dans notre buffer String
	 * @param map la map que l'on exporte
	 */
	private void addData(Map map) {
		
		for (int j = 0; j < map.getSizeY(); j++) {
			for (int i = 0; i < map.getSizeX(); i++) {
				this.buffer += map.getData()[i][j] + " ";
			}
			this.buffer += "\n";
		}
	}
}
