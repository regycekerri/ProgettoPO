<h1 align="center"> OPENWEATHERAPP </h1>

## Installazione

L'applicazione è scaricabile in uno dei seguenti modi:
* dal Prompt dei Comandi digitando:  <i> git clone https://github.com/regycekerri/ProgettoPO.git </i>
* clonandola da Eclipse attraverso il link precedente
* sotto forma di cartella compressa cliccando su <i>code</i> (di colore verde in alto a destra sulla pagina   corrente)

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

* <i>com.progettoPO.OPENWEATHERAPP.controller</i>

![alt text](https://github.com/regycekerri/ProgettoPO/blob/master/UML/com.progettoPO.OPENWEATHERAPP.controller.png)

* <i>com.progettoPO.OPENWEATHERAPP.exception</i>

![alt text](https://github.com/regycekerri/ProgettoPO/blob/master/UML/com.progettoPO.OPENWEATHERAPP.exception.png)

* <i>com.progettoPO.OPENWEATHERAPP.model</i>

![alt text](https://github.com/regycekerri/ProgettoPO/blob/master/UML/com.progettoPO.OPENWEATHERAPP.model.png)

* <i>com.progettoPO.OPENWEATHERAPP.service</i>

![alt text](https://github.com/regycekerri/ProgettoPO/blob/master/UML/com.progettoPO.OPENWEATHERAPP.service.png)

* <i>com.progettoPO.OPENWEATHERAPP.utilities</i>

![alt text](https://github.com/regycekerri/ProgettoPO/blob/master/UML/com.progettoPO.OPENWEATHERAPP.utilities.png)



Per quanto riguarda invece i diagrammi delle classi dei test:

![alt text](https://github.com/regycekerri/ProgettoPO/blob/master/UML/test.png)

## Guida all'utilizzo

Una volta installata l'applicazione, per poterla utilizzare occorrerà semplicemente avviarla in uno dei seguenti modi:
* come SpringBoot Application 
* costruendo attraverso Maven un pacchetto eseguibile di tipo <i>.jar</i>

In entrambi i casi, ciò che verrà fatto dall'applicazione sarà avviare un server web di tipo Tomcat alla porta <b>```localhost:8080```</b>, pronto a rispondere alle richieste dell'utente. Per effettuare le richieste è necessario richiamare le corrispettive rotte attraverso l'utilizzo di un browser o di un api-tester come Postman (altamente raccomandato).

Di seguito saranno fornite le spiegazioni dettagliate sul funzionamento di ciascuna rotta.

#### #1

Tipo | Rotta | 
---- | ----- | 
GET | localhost:8080/countries |

La seguente rotta restituisce semplicemente la lista degli stati supportati dall'applicazione, sotto forma di tabella HTML. Proprio per tale motivo è altamente raccomandato l'utilizzo di un browser, poichè in grado di generare la tabella nel modo corretto, a partire dal linguaggio HTML.

Di seguito è riportato, a scopo illustrativo, il diagramma di sequenza corrispondente a questa rotta:

![alt text](https://github.com/regycekerri/ProgettoPO/blob/master/UML/rotta1.png)

#### #2

Tipo | Rotta |
---- | ----- |
GET | localhost:8080/actualdata?country={country}&cnt={cnt}

La seguente rotta richiede, come è possibile constatare, i seguenti parametri:
* <b>```country```</b> : nome dello stato
* <b>```cnt```</b> : numero di città circostanti la capitale (compresa)

con i seguenti vincoli:
* il nome dello stato deve appartenere alla lista degli stati supportati dall'applicazione
* il numero di città circostanti la capitale (compresa) deve appartenere al seguente range [1-50]

In caso in cui i vincoli non vengano rispettati verranno lanciate delle eccezioni e si avranno in risposta i seguenti messaggi:
* ```This country is not supported```
* ```Invalid number of cities (cnt must belong to this range: [1,50])```

Nel caso in cui i vincoli vengano invece rispettati la risposta ottenuta sarà una lista di città con i corrispettivi valori di umidità e visibilità attuali.

Di seguito è riportato un esempio di chiamata e relativa risposta:
* <i>localhost:8080/actualdata?country=Italy&cnt=2</i>

```
[
    {
        "name": "Pigna",
        "lat": 41.8964,
        "lon": 12.4846,
        "city_id": 6545151,
        "humidity": 58,
        "visibility": 10000
    },
    {
        "name": "Rome",
        "lat": 41.8947,
        "lon": 12.4839,
        "city_id": 3169070,
        "humidity": 58,
        "visibility": 10000
    }
]
```

Di seguito è riportato, a scopo illustrativo, il diagramma di sequenza corrispondente a questa rotta:

![alt text]

#### #3

Tipo | Rotta |
---- | ----- |
GET | localhost:8080/history |

La seguente rotta restituisce la lista degli stati che hanno un archivio storico

Di seguito è riportato, a scopo illustrativo, il diagramma di sequenza corrispondente a questa rotta:






#### #4

Tipo | Rotta |
---- | ----- |
 | localhost:8080 |
 
 
 
 
 
 
 
 
 
 
 #### #5

Tipo | Rotta |
---- | ----- |
 | localhost:8080 |
 
 
 
 
 
 
 
 
 
 
 #### #6

Tipo | Rotta |
---- | ----- |
 | localhost:8080 |







#### #7

Tipo | Rotta |
---- | ----- |
 | localhost:8080 |


