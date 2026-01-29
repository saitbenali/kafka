# kafka# kafka-spring-demo

Exemple simple Kafka + Spring Boot pour développement dans GitHub Codespaces.

Étapes rapides:
1. Ouvrir le repo dans un Codespace.
2. Dans le terminal du Codespace: `docker compose up -d` (démarre Zookeeper + Kafka).
3. Lancer l'application: `./mvnw spring-boot:run` ou `mvn spring-boot:run`.
4. Envoyer un message: `curl -X POST "http://localhost:8080/api/publish?message=hello"`.

Notes:
- Dans Codespaces, `localhost:9092` est accessible depuis l'application qui tourne dans le même Codespace.
- Pour voir les logs du consumer, regarde le terminal où tourne l'application Spring Boot.