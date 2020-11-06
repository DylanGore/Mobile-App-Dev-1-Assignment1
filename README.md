# Mobile App Development 1: Assignment 1

This is a Kotlin-based weather app.

[View Releases](https://github.com/DylanGore/Mobile-App-Dev-1-Assignment1/releases)

## Features

-   Display weather warnings from [Met Éireann](https://met.ie)
-   Display most recent conditions reported by [Met Éireann](https://met.ie)'s 25 weather observation stations
-   Display a weather forecast for a specified location using data from the [Meteorologisk institutt (met.no)](https://met.no)
-   Save and load a list of locations to/from a JSON file
-   Colour, formatting and Unicode emoji in supported terminals

## Libraries Used

-   [OKHttp](https://github.com/square/okhttp) - used for calling APIs
-   [Klaxon](https://github.com/cbeust/klaxon) - JSON parsing for API data
-   [gson](https://github.com/google/gson) - serialisation/deserialisation of location data
-   [JUnit](https://github.com/junit-team/junit5) - automated tests
-   [Mordant](https://github.com/ajalt/mordant) - used for text formatting
-   [Gradle Shadow](https://github.com/johnrengelman/shadow) - used to create a 'fat' jar including dependencies
-   [Kotlin Logging](https://github.com/MicroUtils/kotlin-logging) - logging

## Build

You can build a JAR file using the following command:

```bash
./gradlew shadowJar
```

## Running

To run the JAR file, execute the following command:

```bash
java -jar MobileApp-Assignment1-1.1-all.jar
```

## Branches

-   [main](https://github.com/DylanGore/Mobile-App-Dev-1-Assignment1/tree/main) - Main branch
-   [development](https://github.com/DylanGore/Mobile-App-Dev-1-Assignment1/tree/development) - Development branch
-   [tornadofx](https://github.com/DylanGore/Mobile-App-Dev-1-Assignment1/tree/tornadofx) - TornadoFX test branch
