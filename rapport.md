Grégoire Arnaud 

# Rapport Sujet n°3 : API de génération de terrain aléatoire

La génération de terrain aléatoire consiste à simuler un MNT, par exemple afin d’offrir aux joueurs
d’un jeu vidéo, une expérience différente à chaque partie.

## Contexte et Objectifs

L’objectif général du projet et le développement d’une API, ici une bibliothèque logicielle qui
expose des fonctionnalités consommées par autres logiciels. 

L’objectif du projet est d’implémenter au moins un algorithme de génération de terrain aléatoire.
Objectifs :

 - Génération d’un MNT aléatoire
 - Différents algorithmes : aléatoire pur, Diamond Square, etc.
 - Chargement d’une couche de données géographiques pour définir l’emprise du MNT
 - Export du MNT : ASC et géotiff.

## Choix techniques

### Choix des librairies/dépendances

Pour lire des shapefiles/raster et écrire des géotiff, le choix de geotools s'est très vite imposé (http://www.geotools.org/). Il présentait l'avantage par rapport à GDAL d'avoir une documentation beaucoup plus fournies avec beaucoup plus d'exemples.

De plus, pour compiler, le projet nécessite Maven, de par la nature même du projet.

### Conception & design pattern

 Durant la conception, il a été décidé d'utiliser les designs patterns stratégie et fabrique statique, détaillé dans le cours de Mickael Borne. (http://mborne.github.io/cours-patron-conception/#1).

 Le patron stratégie est utilisé pour regrouper les différents comportements de l'application ( génération diamond square, simplex, value, etc ...) dans une seule interface. Cela permet à la classe Map d'implémenter indifféremment n'importe lequel des algorithmes de générations.

 Le design pattern fabrique statique est utilisé pour générer facilement des instances de méthodes de générations. Il est ainsi plus facile pour l'utilisateur de manipuler les objets d'algorithmes de générations.

### Algorithmes de générations aléatoires implémentés

Après un bref état de l'art, il a été décidé d'implémenter les méthodes suivantes :

 - bruit de valeurs
 - bruit de Perlin
 - bruit de Simplex
 - Algorithme de Diamant-Carré

Leurs implémentations dans la librarie est très largement basé sur des implémentations existantes ou de la réécriture du pseudo code. Toutes les sources sont cités dans les différents fichiers.

## Conduite du projet

### Test Driven Development

 Le projet a été réalisé dans l'ensemble en Test Driven Development. 95.5% du code est couvert par des test unitaires. Ce nombre résulte du test de couverture de ECLemma.

### Intégration continue

De plus, le projet est en intégration continue avec Travis (https://travis-ci.org/arnaudgregoire/AGAT). Cela donne à l'utilisateur la garantie que tous les tests joués fonctionnent.


## Rendu 

 - l’API, packagée et prête à être utilisée comme bibliothèque : Comme illustré dans la documentation utilisateur, il suffit simplement d'installer une dépendance à la librarie AGAT dans le pom.xml pour pouvoir profiter de l'ensemble des fonctionalités.

 - Le code, sous forme d’un projet Maven : L'ensemble du code de la librarie forme un projet Maven. Il est divisé en 3 parties : domain, persistance, geotools.

 - Les tests unitaires : L'ensemble des tests unitaires est disponible dans le dossier src/test/java.

 - La javadoc générée : Toute la documentation est disponible dans le dossier doc.

 - Une petite doc utilisateur, directement intégrée dans le Readme du git.
 
 - Un mini-rapport explicitant la conduite du projet, et justifiant les choix techniques 

 - Un diagramme de classe illustrant l’API développée : Le diagramme de classe d'AGAT est disponible en tant que projet StarUML ainsi qu'en photo dans le dossier uml.

 - le dossier img contient une galerie d'image des MNT générés en 3D avec AGAT

 - le dossier colormap contient l'ensemble de règles utilisés pour colorier le MNT en fonction de l'altitude