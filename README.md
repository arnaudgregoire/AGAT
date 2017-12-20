# AGAT

[![License: CC BY 4.0](https://img.shields.io/badge/License-CC%20BY%204.0-lightgrey.svg)](https://creativecommons.org/licenses/by/4.0/)
[![Build Status](https://travis-ci.org/arnaudgregoire/AGAT.svg?branch=master)](https://travis-ci.org/arnaudgregoire/AGAT)

Algorithmes de Génération Aléatoires de Terrains

## Premiers pas

Les instructions suivantes vous donneront un ordre d'idée des capacités de la librairie AGAT et de comment les utiliser.

### Premier MNT

```java
Map map = new Map("simplex");
map.generate();
map.exportToGeoTiff("testSimplex");
```

Ce script créé un MNT puis le stocke dans le fichier ./data/testSimplex.tiff.  

## Personaliser sa map

### Changer l'emprise

L'emprise par défaut va du point (0,0) au point (100,100).
Il y a plusieurs façons de modifier l'emprise d'une map. On peut le faire à la main en définissant une nouvelle emprise à l'aide de 2 points :
 
 - le point en bas à gauche 
 - le point en haut à droite
 
 ce qui nous donne :

```java
Map map = new Map("simplex");
map.setBound(new Bound( new Point(0,0) , new Point(300,200) )); 
map.generate();
map.exportToGeoTiff("testChangeEmprise");
```

La librarie propose aussi d'importer l'emprise d'un shapefile, son utilisation est détaillé dans la partie Import/Export 

### Changer la résolution

AGAT propose à ses utilisateurs de changer la résolution d'une map. Par défaut, la résolution de toutes les maps est fixé à 1.

```java
Map map = new Map("value");
map.setResolution(2);
map.generate();
map.exportToGeoTiff("testChangeResolution");
```

Plus la résolution sera précise, plus la taille de la carte sera lourde. Pour éviter ce problème, la librairie propose à ces utilisateurs une résolution conseillé :

```java
Map map = new Map("perlin");
map.setResolution(map.getAdvisedResolution());
map.generate();
map.exportToGeoTiff("testAdvisedResolution");
```

### Changer l'amplitude

### Changer le système de projection

### Changer la méthode de générations

Différents mots-clés correspodant à différents types de générations peuvent être utilisés. 
 
  - "simplex" : bruit de simplex
  - "perlin" : bruit de Perlin
  - "random" : bruit blanc
  - "value" : bruit de valeurs
  - "flat" : valeurs uniformes
  - "diamond" : algorithme diamond square
  
Remarque :   

## Import / Export

### Exporter sa map

La librairie permet d'exporter des MNT au format 

```java
Map map = new Map("simplex");
map.generate();
map.exportToGeoTiff("testSimplex");
map.exportToASC("testSimplex");
```

Ce script créé un MNT et l'enregistre 2 fois dans le sous dossier data:
 
  - une premiére fois au format Geotiff
  - une seconde fois au format ASC
  
Remarque : l'export au format ASC est sensiblement plus long car non optimisable ( il est écrit avec des libraries bas niveaux I/O). Préférez donc l'export au format geotiff pour de meilleurs performances.   

### Importer une emprise depuis un fichier vecteur

On peut charger l'emprise d'un fichier shp pour générer un MNT de la zone. 
Si l'identifiant EPSG du shapefile ne correspond pas à celui de l'objet Map (l'identifiant par défaut est EPSG:2154, projection Lambert 93), la fonction essaiera de créer l'emprise dans la projection associé au shapefile puis de la reprojeter dans la projection associé à l'objet Map.

```java
Map map = new Map("simplex");
map.importShapefileBound("shp/buffer_dissolve_paris.shp");
testMap.setResolution(testMap.getAdvisedResolution());
map.generate();
map.exportToGeoTiff("testParis");
```

Ici, on notera l'utilisation de la méthode getAdvisedResolution() qui permet simplement de renvoyer la valeur conseillé par la librarie pour effectuer la génération. En effet, si la résolution est trop élevée, la taille du fichier et les temps de calculs vont trés vite augmenter ( 45 secondes pour 100 000 000 de points avec la méthode de bruit simplex ).

### Importer une emprise depuis un fichier raster

Même chose que précédemment mais avec un raster.

```java
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

