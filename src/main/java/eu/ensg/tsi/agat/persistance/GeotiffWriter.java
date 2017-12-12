package eu.ensg.tsi.agat.persistance;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.grid.GridCoverageFactory;
import org.geotools.gce.geotiff.GeoTiffWriter;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.referencing.crs.DefaultGeographicCRS;

import eu.ensg.tsi.agat.domain.Map;

public class GeotiffWriter implements IWriter {

	@Override
	public void write(String nomFichier, Map map) {
		String cheminAcces = "data/" + nomFichier;
	    final File geotiff = new File(new StringBuilder().append(cheminAcces).toString());
	    // write down a fake geotiff with non-standard CRS
	    GridCoverageFactory factory = new GridCoverageFactory();
	    	SampleModel sampleModel = new SampleModel(int dataType, int w, int h, int numBands);
	    	DataBuffer(int dataType, int size);
	    	 Point origin
	    	Raster raster = new Raster(sampleModel, null, null);
            BufferedImage bi = new BufferedImage(map.getSizeX(), map.getSizeY(), 0);
            ReferencedEnvelope envelope = new ReferencedEnvelope(map.bound.getBottomLeft().getX(),
            													 map.bound.getBottomRight().getX(),
            													 map.bound.getBottomLeft().getY(),
            													 map.bound.getUpperleft().getY(),
            													 DefaultGeographicCRS.WGS84);
            
            GridCoverage2D test = factory.create("test", bi, envelope);
            GeoTiffWriter writer;
			try {
				writer = new GeoTiffWriter(geotiff);
	            writer.write(test, null);
	            writer.dispose();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
