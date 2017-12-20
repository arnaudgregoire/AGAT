# AGAT

[![License: CC BY 4.0](https://img.shields.io/badge/License-CC%20BY%204.0-lightgrey.svg)](https://creativecommons.org/licenses/by/4.0/)
[![Build Status](https://travis-ci.org/arnaudgregoire/AGAT.svg?branch=master)](https://travis-ci.org/arnaudgregoire/AGAT)

Algorithmes de Génération Aléatoires de Terrains

## Premiers pas

Les instructions suivantes vous donneront un ordre d'idée des capacités de la librairie AGAT et de comment les utiliser.

### Premier MNT

```
Map map = new Map("simplex");
map.generate();
map.exportToGeoTiff("testSimplex");
```

Ce script créé un MNT puis le stocke dans le fichier ./data/testSimplex.tiff.  

### Différents types de générations

Différents mots-clés correspodant à différents types de générations peuvent être utilisés. 
 
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

Ce script créé un MNT et l'enregistre 2 fois dans le sous dossier data:
 
  - une premiére fois au format Geotiff
  - une seconde fois au format ASC

### Importer une emprise depuis un fichier vecteur

On peut charger l'emprise d'un fichier shp pour générer un MNT de la zone. 
Si l'identifiant EPSG du shapefile ne correspond pas à celui de l'objet Map (l'identifiant par défaut est EPSG:2154, projection Lambert 93), la fonction essaiera de créer l'emprise dans la projection associé au shapefile puis de la reprojeter dans la projection associé à l'objet Map.

```
Map map = new Map("simplex");
map.importShapefileBound("shp/buffer_dissolve_paris.shp");
testMap.setResolution(testMap.getAdvisedResolution());
map.generate();
map.exportToGeoTiff("testParis");
```

Ici, on notera l'utilisation de la méthode getAdvisedResolution() qui permet simplement de renvoyer la valeur conseillé par la librarie pour effectuer la génération. En effet, si la résolution est trop élevée, la taille du fichier et les temps de calculs vont trés vite augmenter ( 45 secondes pour 100 000 000 de points avec la méthode de bruit simplex ).

### Importer une emprise depuis un fichier raster

Même chose que précédemment mais avec un raster.

```
Map map = new Map("simplex");
map.importRasterBound("data/testParis.tiff");
map.setResolution(testMap.getAdvisedResolution());
map.generate();
map.exportToGeoTiff("testParis2");
```

## Built With

* [Geotools](http://www.dropwizard.io/1.0.2/docs/) - Boite à outils SIG 
* [Maven](http://www.geotools.org/) - Gestion des dépendances


## License

Ce projet est sous la license Creative Common BY - Voir le [LICENSE.md](LICENSE.md) pour plus de détails.

