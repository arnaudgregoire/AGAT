# Compte rendu 


Mon pipeline est découpé en 3 étapes :
 
  - compilation
  - tests unitaires
  - déploiement


## Compilation

Pour l'étape compilation, on utilise maven pour essayer de compiler le projet 

```yaml
    - stage: compile
      script: mvn compile
```

## Tests unitaires

Pour l'étape tests unitaires, on utilise maven pour jouer les tests unitaires

```yml
    - stage: test
      script: mvn test
```

## Déploiement

Pour l'étape déploiement, on utilise maven pour essayer de déployer le projet, uniquement si le nom de la branche est master.

```yml
stages:
  - compile
  - test
  - name: deploy
    if: branch = master # déploiement conditionnel
```
