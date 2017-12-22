package eu.ensg.tsi.agat.persistance;

import java.awt.geom.Rectangle2D;
import java.awt.image.DataBuffer;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.media.jai.RasterFactory;

import org.geotools.coverage.CoverageFactoryFinder;
import org.geotools.coverage.grid.GridCoverageFactory;
import org.geotools.gce.geotiff.GeoTiffWriter;
import org.geotools.geometry.GeneralEnvelope;
import org.geotools.referencing.CRS;
import org.opengis.coverage.grid.GridCoverage;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import eu.ensg.tsi.agat.domain.Map;

public class GeotiffWriter implements IWriter {

	@Override
	public void write(String nomFichier, Map map)  {
		String cheminAcces =  nomFichier + ".tiff";
	    final File geotiff = new File(new StringBuilder().append(cheminAcces).toString());

        
        WritableRaster raster = RasterFactory.createBandedRaster(DataBuffer.TYPE_FLOAT,
                map.getSizeX(), map.getSizeY(), 1, null);
        
		for (int x=0; x<map.getSizeX(); x++) {
			for (int y=0; y<map.getSizeY(); y++) {
			raster.setSample(x, y, 0, map.getData()[x][y]);
			}
		}
		Rectangle2D bounds = new Rectangle2D.Double(map.getBound().getBottomLeft().getX(),
        								map.getBound().getBottomLeft().getY(),
        								map.getResolution() *map.getSizeX(),
                                        map.getResolution() * map.getSizeY());
        
        CoordinateReferenceSystem sourceCRS = null;
		try {
			sourceCRS = CRS.decode("EPSG:" + map.getCrs());
		} catch (NoSuchAuthorityCodeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FactoryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        	
        
        final GeneralEnvelope envelope = new GeneralEnvelope(sourceCRS);
        
        envelope.setRange(0, bounds.getMinX(), bounds.getMaxX());
        envelope.setRange(1, bounds.getMinY(), bounds.getMaxY());
        
        GridCoverageFactory factory = CoverageFactoryFinder.getGridCoverageFactory(null);
        
        GridCoverage coverage = factory.create("My grayscale coverage", raster, envelope);
        

		try {
			GeoTiffWriter writer = new GeoTiffWriter(geotiff);
            writer.write(coverage, null);
            writer.dispose();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
