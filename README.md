# AGAT

[![License: CC BY 4.0](https://img.shields.io/badge/License-CC%20BY%204.0-lightgrey.svg)](https://creativecommons.org/licenses/by/4.0/)
[![Build Status](https://travis-ci.org/arnaudgregoire/AGAT.svg?branch=master)](https://travis-ci.org/arnaudgregoire/AGAT)

Algorithmes de G�n�ration Al�atoires de Terrains

## Premiers pas

Les instructions suivantes vous donneront un ordre d'id�e des capacit�s de la librairie AGAT et de comment les utiliser.

### Premier MNT

```
Map map = new Map("simplex");
map.generate();
map.exportToGeoTiff("testSimplex");
```

Ce script cr�� un MNT puis le stocke dans le fichier ./data/testSimplex.tiff.  

### Diff�rents types de g�n�rations

Diff�rents mots-cl�s correspodant à diff�rents types de g�n�rations peuvent être utilis�s. 
 
  - "simplex" : bruit de simplex
  - "perlin" : bruit de Perlin
  - "random" : bruit blanc
  - "value" : bruit de valeurs
  - "flat" : valeurs uniformes

## Exporter sa map

La librairie permet d'exporter des MNT au format 

```
Map map = new Map("simplex");
map.generate();
map.exportToGeoTiff("testSimplex");
map.exportToASC("testSimplex");
```

Ce script cr�� un MNT et l'enregistre 2 fois dans le sous dossier data:
 
  - une premi�re fois au format Geotiff
  - une seconde fois au format ASC

### Importer une emprise depuis un fichier vecteur

On peut charger l'emprise d'un fichier shp pour g�n�rer un MNT de la zone. 
Si l'identifiant EPSG du shapefile ne correspond pas � celui de l'objet Map (l'identifiant par d�faut est EPSG:2154, projection Lambert 93), la fonction essaiera de cr�er l'emprise dans la projection associ� au shapefile puis de la reprojeter dans la projection associ� � l'objet Map.

```
Map map = new Map("simplex");
map.importShapefileBound("shp/buffer_dissolve_paris.shp");
map.resolution = map.getAdvisedResolution();
map.generate();
map.exportToGeoTiff("testParis");
```

Ici, on notera l'utilisation de la m�thode getAdvisedResolution() qui permet simplement de renvoyer la valeur conseill� par la librarie pour effectuer la g�n�ration. En effet, si la r�solution est trop �lev�e, la taille du fichier et les temps de calculs vont tr�s vite augmenter ( 45 secondes pour 100 000 000 de points avec la m�thode de bruit simplex ).

## Built With

* [Geotools](http://www.dropwizard.io/1.0.2/docs/) - Boite à outils SIG 
* [Maven](http://www.geotools.org/) - Gestion des d�pendances


## License

Ce projet est sous la license Creative Common BY - Voir le [LICENSE.md](LICENSE.md) pour plus de d�tails.

