package eu.ensg.tsi.agat.geotools;

import java.io.File;

import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.grid.io.AbstractGridFormat;
import org.geotools.coverage.grid.io.GridCoverage2DReader;
import org.geotools.coverage.grid.io.GridFormatFinder;
import org.geotools.referencing.CRS;
import org.opengis.geometry.BoundingBox;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import eu.ensg.tsi.agat.domain.Bound;
import eu.ensg.tsi.agat.domain.Point;

public class RasterReader implements IReader {
	
	/**
	 * 	
	 * La méthode appelé par la classe Map dans la fonction d'import de raster
	 * Si il n'existe pas de fichier ou que le fichier n'a pas réussi à être lu,
	 * une emprise nulle sera renvoyé
	 * @param FilePath le nom du fichier
	 * @param epsg l'identifiant epsg de la map
	 * @return L'emprise associé au shapefile ouvert
	 */
	@Override
	public Bound getBoundofFile(String filePath, int epsg) {
		double left   = Double.NaN; 
		double right  = Double.NaN; 
		double top    = Double.NaN; 
		double bottom = Double.NaN; 
		File file = new File(filePath);

		try {
			AbstractGridFormat format     = GridFormatFinder.findFormat(file);
			GridCoverage2DReader reader   = format.getReader(file);	
			GridCoverage2D coverage       = (GridCoverage2D) reader.read(null);
			CoordinateReferenceSystem crs = coverage.getCoordinateReferenceSystem2D();
			BoundingBox envelope          = coverage.getEnvelope2D().toBounds(crs);
			
			left   = envelope.getMinX();
			right  = envelope.getMaxX();
			top    = envelope.getMaxY();
			bottom = envelope.getMinY();
			
			CoordinateReferenceSystem sourceCRS = CRS.decode("EPSG:" + epsg);
			int epsgFile = (int) CRS.lookupEpsgCode(crs, false);
			int epsgSource = (int) CRS.lookupEpsgCode(sourceCRS, false);
			
			if(epsgFile != epsgSource) {
				coverage.getEnvelope2D().toBounds(sourceCRS);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("WARNING : le fichier n'a pas pu être lu, une emprise nulle a été généré");
		}
	
		return new Bound( new Point(left,bottom), new Point(right,top));
	}

}
