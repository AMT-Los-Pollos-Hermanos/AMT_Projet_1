<h1 align="center">AMT : Project 1 - Overflow</h1>
<h3 align="center">Gil Balsiger, Chris Barros, Julien Béguin & Gaëtan Daubresse</h3>
<p align="center">
  <img src="https://github.com/AMT-Los-Pollos-Hermanos/AMT_Projet_1/workflows/Tests/badge.svg?branch=master">
  <img src="https://github.com/AMT-Los-Pollos-Hermanos/AMT_Projet_1/workflows/Build%20and%20deploy/badge.svg?branch=master">
  <img src="https://img.shields.io/badge/Platform-Jakarta_EE_8-orange?logo=java">
  <img src="https://img.shields.io/badge/Version-1.0--SNAPSHOT-blue">
</p>

## Specifications

### Pages

| Name                             | Path                      | Access                |
| ---------------------------------| ------------------------- | ----------------------|
| Home page                        | `/`                       | Tout le monde         |
| Inscription / Connexion          | `/login`                  | Tout le monde         |
| Liste des questions              | `/questions`              | Tout le monde         |
| Page d'une question              | `/questions/<question id>`| Utilisateurs connectés|
| Nouveau commentaire              | `/comment/<content_id>`   | Utilisateurs connectés|
| Profil                           | `/profile/<user id>`      | Utilisateurs connectés|
| Changement du mot de passe       | `/changePassword`         | Utilisateurs connectés|

#### Page d'accueil
Contient un bouton qui donne accès à la liste des questions postées.

#### Page de connexion/inscription
Contient deux formulaires : un permettant la création d'un compte utilisateur, et un autre pour se connecter.

Pour le développement, il est possible de se connecter les utilisateurs suivant : 
gil, chris, gaetan et julien. Leur mot de passe est identique à leur nom d'utilisateur.

#### Page de la liste des questions
Contient la liste des questions qui ont été postées. Il est possible de chercher une question à l'aide de la barre de 
recherche selon son contenu ou son auteur. Il est possible de cliquer sur le titre d'une question pour accéder à son
contenu et lire les commentaires et les réponses associés. 
À gauche de la page, il y a un champ de texte qui permet de poster une question.

#### Page d'une question
On peut lire la question posée ainsi que tous les commentaires qui lui sont associés. Il suit ensuite la liste des réponses liées
à cette question ainsi que leurs commentaires respectifs.
Il est possible de voter pour tout contenu en cliquant sur les boutons + ou - pour respectivement upvote ou downvote.
Tout en bas de la page, il y a un champ de texte qui permet d'écrire une réponse à la question.

#### Nouveau commentaire
Contient un champ de texte qui permet l'ajout d'un commentaire à un contenu. Lorsque le commentaire est soumis, on est 
redirigé ensuite sur la page de la question.

#### Profil
Page qui contient des informations de l'utilisateur et un bouton permettant le changement du mot de passe.

#### Page du changement de mot de passe
Un formulaire qui demande l'ancien mot de passe utilisateur, le nouveau qui sera utilisé. 



### Politique de vote

- Un utilisateur peut voter pour :
    - Une question
    - Une réponse
    - Un commentaire
- Ces votes sont soit des upvotes, soit des downvotes
- Un contenu a un score de points et change selon si l'utilisateurs upvote ou downvote
- Un contenu ne peut pas être à la fois upvote ou downvote
- Rappuyer sur le bouton d'upvote alors que l'on avait déjà upvote annule ce upvote. Pareil pour le downvote 

### Database

![](db.png)

## Installation

```shell script
git clone --recurse-submodules https://github.com/AMT-Los-Pollos-Hermanos/AMT_Projet_1.git
cd AMT_Projet_1
docker pull ghcr.io/amt-los-pollos-hermanos/overflow:latest
docker-compose up
```

Aller à l'adresse http://localhost:9080/overflow

> À noter: Il est aussi possible d'obtenir une image Docker de l'application via le Github Repository en utilisant la
> commande suivante: 
>
> `docker pull ghcr.io/amt-los-pollos-hermanos/overflow:latest`
>
> À ce moment, il n'est pas nécessaire de lancer l'application via docker-compose, mais seulement la base de données avec la 
>commande : 
>
>`docker-compose up -d db`

## Infrastructure cloud avec Nomad et Consul

### Lancement de l'infra
```shell script
cd deployment/lab
vagrant up
```

Plus d'information sur [balsigergil/hashicorp-vagrant-lab](https://github.com/balsigergil/hashicorp-vagrant-lab).

| Service | URL                          |
|---------|------------------------------|
| Nomad   | http://192.168.33.10:4646/ui |
| Consul  | http://192.168.33.10:8500/ui |

### Jobs Nomad

Les jobspec Nomad sont dans le dossier [deployment](https://github.com/AMT-Los-Pollos-Hermanos/AMT_Projet_1/tree/master/deployment) et ont l'extension `.nomad`.

Il suffit de les lancer via [l'interface utilisateur de Nomad](http://192.168.33.10:4646/ui/jobs/run). En premier, le jobspec `traefik.nomad` et ensuite `overflow.nomad`.

L'interface de Traefik est disponible sur [http://192.168.33.10:8080](http://192.168.33.10:8080) 

L'application déployée est ensuite disponible via le load balancer à l'adresse : [http://192.168.33.10/overflow](http://192.168.33.10/overflow)

### JMeter

- Ouvrir JMeter 
- Aller dans File > Open et choisir le fichier overflow_test2.jmx situé dans le répertoire jmeter de notre projet 

#### Lancer les tests de charge

```shell script
./scripts/run-load-tests.sh
```

