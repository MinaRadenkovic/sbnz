# SBnZ Class Diagram and Console Usage

This folder contains the class diagram for the backend solution and instructions for running the console application.

## Kako pokrenuti

1. Iz korenskog direktorijuma projekta pokrenite celu aplikaciju i zavisne module:
   ```bash
   mvn clean package
   ```
2. Pokrenite samo `service` modul zajedno sa potrebnim zavisnostima:
   ```bash
   mvn -pl service -am spring-boot:run
   ```
3. Aplikacija će pokrenuti konzolni interfejs i tražiti unos transakcija.

## Diagram

Pregledajte `class_diagram.puml` u PlantUML podržanom editoru ili koristite PlantUML da generišete sliku.
