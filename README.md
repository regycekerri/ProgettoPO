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

Nota: fare attenzione all'inserimento dei parametri, poichè l'applicazione è <i>case-sensitive</i>!

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
GET | localhost:8080/actualdata?country={country}&cnt={cnt} |

La seguente rotta richiede, come è possibile constatare, i seguenti parametri:
* <b>```country```</b> : nome dello stato
* <b>```cnt```</b> : numero di città circostanti la capitale (compresa)

con i seguenti vincoli:
* il nome dello stato deve appartenere alla lista degli stati supportati dall'applicazione
* il numero di città circostanti la capitale (compresa) deve appartenere al seguente range [1-50]

Nel caso in cui i vincoli non vengano rispettati verranno lanciate delle eccezioni e si avranno in risposta i seguenti messaggi:
* ```This country is not supported```
* ```Invalid number of cities (cnt must belong to this range: [1,50])```

Nel caso in cui i vincoli vengano invece rispettati la risposta ottenuta sarà una lista di città circostanti la capitale (compresa) con i corrispettivi valori di umidità e visibilità attuali.

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

![alt text](https://github.com/regycekerri/ProgettoPO/blob/master/UML/rotta2.png)

#### #3

Tipo | Rotta |
---- | ----- |
GET | localhost:8080/history |

La seguente rotta restituisce la lista degli stati che possiedono un archivio storico, tratta dal file <i>src/main/resources/history/countries-with-history.txt</i>.

Se ad esempio viene richiamata tale rotta nel caso in cui l'unico stato che possiede un archivio storico sia l'Italia, si avrà una risposta del seguente tipo:

```
[
    "Italy"
]
```
Di seguito è riportato, a scopo illustrativo, il diagramma di sequenza corrispondente a questa rotta:

![alt text](https://github.com/regycekerri/ProgettoPO/blob/master/UML/rotta3.png)

#### #4

Tipo | Rotta |
---- | ----- |
POST | localhost:8080/history/create |

La seguente rotta permette all'utente di creare l'archivio storico di uno stato, attraverso l'inserimento di un <b>```RequestBody```</b> di tipo stringa, contenente il nome dello stato. Anche in questo caso vanno rispettati dei vincoli:
* il nome dello stato deve appartenere alla lista degli stati supportati dall'applicazione
* lo stato non deve già possedere un archivio storico

Nel caso in cui i vincoli non vengano rispettati verranno lanciate delle eccezioni e si avranno in risposta i seguenti messaggi:
* ```This country is not supported```
* ```History for this country is already available```

Nel caso in cui invece i vincoli vengano rispettati verrà creato un nuovo file(<i>(nome dello stato).txt</i>) contenente l'archivio, all'interno della cartella <i>src/main/resources/history</i>. Inoltre verrà ovviamente aggiornata anche la lista degli stati che possiedono un archivio storico. L'utente infine riceverà il seguente messaggio di conferma:
* ```History for this country has been created!```

Di seguito è riportato, a scopo illustrativo, il diagramma di sequenza corrispondente a questa rotta:

![alt text](https://github.com/regycekerri/ProgettoPO/blob/master/UML/rotta4.png)

 #### #5

Tipo | Rotta |
---- | ----- |
POST | localhost:8080/history/remove |

La seguente rotta permette all'utente di cancellare l'archivio storico di uno stato, attraverso l'inserimento di un <b>```RequestBody```</b> di tipo stringa, contenente il nome
dello stato. In questo caso va rispettato il seguente vincolo:
* lo stato deve possedere l'archivio storico

Nel caso in cui il vincolo non venga rispettato verrà lanciata un'eccezione e si avrà in risposta il seguente messaggio:
* ```History for this country doesn't exist!```

Nel caso in cui invece il vincolo venga rispettato verrà cancellato il file corrispondente all'archivio storico. Inoltre verrà ovviamente aggiornata anche la lista degli stati che possiedono un archivio storico. L'utente infine riceverà il seguente messaggio di conferma:
* ```History for this country has been removed!```

Di seguito è riportato, a scopo illustrativo, il diagramma di sequenza corrispondente a questa rotta:

![alt text](https://github.com/regycekerri/ProgettoPO/blob/master/UML/rotta5.png)
 
 #### #6

Tipo | Rotta |
---- | ----- |
GET | localhost:8080/stats/humidity/{order}/{period}?country={country}&cnt={cnt} |

La seguente rotta richiede, come è possibile constatare, i seguenti parametri:
* <b>```order```</b> : modalità di ordinamento
* <b>```period```</b> : periodo in giorni sul quale effettuare le statistiche (a partire dal giorno corrente)
* <b>```country```</b> : nome dello stato
* <b>```cnt```</b> : numero di città circostanti la capitale (compresa)

con i seguenti vincoli:
* la modalità di ordinamento deve coincidere con una delle seguenti quattro stringhe:
  * ```min``` : ordinamento crescente basato sui valori di umidità minimi
  * ```max``` : ordinamento crescente basato sui valori di umidità massimi
  * ```avg``` : ordinamento crescente basato sui valori di umidità medi
  * ```var``` : ordinamento crescente basato sulle varianze dei valori di umidità
* il periodo in giorni sul quale effettuare le statistiche (a partire dal giorno corrente) deve appartenere al seguente range [1-30]
* il nome dello stato deve appartenere alla lista degli stati che possiedono un archivio storico
* il numero di città circostanti la capitale (compresa) deve appartenere al seguente range [1-50]

Nel caso in cui i vincoli non vengano rispettati verranno lanciate delle eccezioni e si avranno in risposta i seguenti messaggi:
* ```Invalid order (order must equal: [min, max, avg, var])```
* ```Invalid period (period must belong to this range: [1,30])```
* ```History for this country doesn't exist!```
* ```Invalid number of cities (cnt must belong to this range: [1,50])```

Nel caso in cui i vincoli vengano invece rispettati la risposta ottenuta sarà una lista di città circostanti la capitale (compresa), ordinata secondo la modalità specificata, con i corrispettivi valori minimi, massimi, medi e di varianza di umidità, calcolati sui dati filtrati in base al periodo specificato.

Di seguito è riportato un esempio di chiamata e relativa risposta:
* <i>localhost:8080/stats/humidity/min/5?country=Italy&cnt=5</i>

```
[
    {
        "name": "Rome",
        "lat": 41.8947,
        "lon": 12.4839,
        "city_id": 0,
        "par": "humidity",
        "min": 51,
        "max": 93,
        "avg": 76.85714285714286,
        "var": 233.8367346938776
    },
    {
        "name": "Vatican City",
        "lat": 41.9024,
        "lon": 12.4533,
        "city_id": 0,
        "par": "humidity",
        "min": 51,
        "max": 93,
        "avg": 76.28571428571429,
        "var": 248.20408163265304
    },
    {
        "name": "State of the Vatican City",
        "lat": 41.9022,
        "lon": 12.4533,
        "city_id": 0,
        "par": "humidity",
        "min": 51,
        "max": 93,
        "avg": 76.85714285714286,
        "var": 233.8367346938776
    },
    {
        "name": "Pigna",
        "lat": 41.8964,
        "lon": 12.4846,
        "city_id": 0,
        "par": "humidity",
        "min": 58,
        "max": 93,
        "avg": 77.28571428571429,
        "var": 203.63265306122452
    },
    {
        "name": "Trevi",
        "lat": 41.9046,
        "lon": 12.4918,
        "city_id": 0,
        "par": "humidity",
        "min": 58,
        "max": 93,
        "avg": 77.28571428571429,
        "var": 203.63265306122452
    }
]
```

Di seguito è riportato, a scopo illustrativo, il diagramma di sequenza corrispondente a questa rotta:

![alt text](https://github.com/regycekerri/ProgettoPO/blob/master/UML/rotta6.png)

#### #7

Tipo | Rotta |
---- | ----- |
GET | localhost:8080/stats/visibility/{order}/{period}?country={country}&cnt={cnt} |

La seguente rotta richiede, come è possibile constatare, i seguenti parametri:
* <b>```order```</b> : modalità di ordinamento
* <b>```period```</b> : periodo in giorni sul quale effettuare le statistiche (a partire dal giorno corrente)
* <b>```country```</b> : nome dello stato
* <b>```cnt```</b> : numero di città circostanti la capitale (compresa)

con i seguenti vincoli:
* la modalità di ordinamento deve coincidere con una delle seguenti quattro stringhe:
  * ```min``` : ordinamento crescente basato sui valori di visibilità minimi
  * ```max``` : ordinamento crescente basato sui valori di visibilità massimi
  * ```avg``` : ordinamento crescente basato sui valori di visibilità medi
  * ```var``` : ordinamento crescente basato sulle varianze dei valori di visibilità
* il periodo in giorni sul quale effettuare le statistiche (a partire dal giorno corrente) deve appartenere al seguente range [1-30]
* il nome dello stato deve appartenere alla lista degli stati che possiedono un archivio storico
* il numero di città circostanti la capitale (compresa) deve appartenere al seguente range [1-50]

Nel caso in cui i vincoli non vengano rispettati verranno lanciate delle eccezioni e si avranno in risposta i seguenti messaggi:
* ```Invalid order (order must equal: [min, max, avg, var])```
* ```Invalid period (period must belong to this range: [1,30])```
* ```History for this country doesn't exist!```
* ```Invalid number of cities (cnt must belong to this range: [1,50])```

Nel caso in cui i vincoli vengano invece rispettati la risposta ottenuta sarà una lista di città circostanti la capitale (compresa), ordinata secondo la modalità specificata, con i corrispettivi valori minimi, massimi, medi e di varianza di visibilità, calcolati sui dati filtrati in base al periodo specificato.

Di seguito è riportato un esempio di chiamata e relativa risposta:
* <i>localhost:8080/stats/visibility/min/5?country=Italy&cnt=5</i>

```
[
    {
        "name": "Rome",
        "lat": 41.8947,
        "lon": 12.4839,
        "city_id": 0,
        "par": "visibility",
        "min": 4000,
        "max": 10000,
        "avg": 7714.285714285715,
        "var": 7061224.48979592
    },
    {
        "name": "Pigna",
        "lat": 41.8964,
        "lon": 12.4846,
        "city_id": 0,
        "par": "visibility",
        "min": 5000,
        "max": 10000,
        "avg": 8571.42857142857,
        "var": 5102040.81632653
    },
    {
        "name": "Trevi",
        "lat": 41.9046,
        "lon": 12.4918,
        "city_id": 0,
        "par": "visibility",
        "min": 5000,
        "max": 10000,
        "avg": 8857.142857142857,
        "var": 3551020.408163266
    },
    {
        "name": "Vatican City",
        "lat": 41.9024,
        "lon": 12.4533,
        "city_id": 0,
        "par": "visibility",
        "min": 5000,
        "max": 10000,
        "avg": 8571.42857142857,
        "var": 5102040.81632653
    },
    {
        "name": "State of the Vatican City",
        "lat": 41.9022,
        "lon": 12.4533,
        "city_id": 0,
        "par": "visibility",
        "min": 5000,
        "max": 10000,
        "avg": 8571.42857142857,
        "var": 5102040.81632653
    }
]
```

Di seguito è riportato, a scopo illustrativo, il diagramma di sequenza corrispondente a questa rotta:

![alt text](https://github.com/regycekerri/ProgettoPO/blob/master/UML/rotta7.png)

## Documentazione

Di seguito è riportato il link alla documentazione dell'applicazione in Javadoc:
* https://github.com/regycekerri/ProgettoPO/blob/master/OPENWEATHERAPP/doc/index.html

## Crediti

Di seguito sono riportati gli autori del progetto:
* Cekerri Regy: 50%
* Vigliotta Michele: 50%

Ogni parte del progetto è stata svolta con la supervisione di entrambi.
