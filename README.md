<h1 align="center"> OPENWEATHERAPP </h1>

## Installazione

L'applicazione è scaricabile in uno dei seguenti modi:
* dal Prompt dei Comandi digitando:  <i> git clone https://github.com/regycekerri/ProgettoPO.git </i>
* clonandola da Eclipse attraverso il link precedente
* sottoforma di cartella compressa cliccando su <i>code</i> (di colore verde in alto a destra sulla pagina   corrente)

## Introduzione
 
La nostra applicazione consente di ottenere informazioni sull'umidità e sulla visibilità attuali delle capitali e delle corrispondenti città limitrofe. Inoltre sarà possibile salvare su richiesta dell'utente tali informazioni ogni 5 ore e generare da esse delle statistiche, che riguardano i valori minimi, massimi, medi e le varianze relative all'umidità e alla visibilità di ciascuna città in un particolare intervallo di tempo a scelta dell'utente.

Un modo efficace per introdurre il funzionamento dell'applicazione è ricorrere al diagramma dei casi d'uso:

![alt text](https://github.com/regycekerri/ProgettoPO/blob/master/UML/Diagramma%20dei%20casi%20d'uso.png)

## Avvertenze

Il corretto funzionamento dell'applicazione dipende dalla validità della chiave contenuta in <i> src/main/resources/APIkey.txt </i>, che consente di interagire con l'API [Current Weather Data](https://openweathermap.org/current#cycle).
Nel caso di non validità della chiave occorrerà sostituirla con una nuova, ottenibile gratuitamente accedendo alla pagina di [OpenWeather](https://openweathermap.org/).

## Struttura dell'applicazione

Un modo efficace per schematizzare la struttura dell'applicazione è ricorrere ai diagrammi delle classi, suddivisi nei vari packages e riportati di seguito:

* <i>com.progettoPO.OPENWEATHERAPP</i>

![alt text](https://github.com/regycekerri/ProgettoPO/blob/master/UML/com.progettoPO.OPENWEATHERAPP.png)
