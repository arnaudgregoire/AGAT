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

## Exporter sa map

La librairie permet d'exporter des MNT au format 

```
Map map = new Map("simplex");
map.generate();
map.exportToGeoTiff("testSimplex");
map.exportToASC("testSimplex");
```

Ce script créé un MNT et l'enregistre 2 fois dans le sous dossier data:
 
  - une première fois au format Geotiff
  - une seconde fois au format ASC

### Importer une emprise

Explain what these tests test and why

```
Give an example
```


## Built With

* [Geotools](http://www.dropwizard.io/1.0.2/docs/) - Boite à outils SIG 
* [Maven](http://www.geotools.org/) - Gestion des dépendances


## License

This project is licensed under the Creative Common BY - see the [LICENSE.md](LICENSE.md) file for details

