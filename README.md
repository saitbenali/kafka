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

======================= plan de formation ========================
1- Sérialisation JSON + DTO (passer de String → objets JSON)
2- Partitions + concurrence du consumer
3- Gestion d’erreurs + DLQ (Dead Letter Queue) et retry/backoff
4- Tests d’intégration réalistes (Testcontainers ou EmbeddedKafka)
5- Transactions & idempotence du producer
6- Monitoring (Actuator + Micrometer + Prometheus)
7- Schema Registry / Avro (si tu veux schémas stricts)
8- Multibroker Docker Compose & réplication
9- CI (GitHub Actions) pour builder et tester avec Kafka
10- Sécurité (SSL/SASL) si environnement productif
=================================================================

<<<<<<<<<=============== Test des étapes ===========>>>>>>>>>  
-----1 envoi de string: 
curl -X POST "http://localhost:8080/api/publish?message=salut"


-----2 envoi d'objet
curl -X POST -H "Content-Type: application/json" -d '{"id":"1","payload":"salut"}' http://localhost:8080/api/publish


----3 concurrency
for i in $(seq 1 20); do
  curl -s -X POST -H "Content-Type: application/json" -d "{\"id\":\"$i\",\"payload\":\"msg-$i\"}" http://localhost:8080/api/publish
  sleep 0.05
done


----4- Tests d’intégration réalistes (Testcontainers ou EmbeddedKafka)
curl -v -X POST -H "Content-Type: application/json" -d '{"id":"1","payload":"ok"}' http://localhost:8080/api/publish
curl -v -X POST -H "Content-Type: application/json" -d '{"id":"bad1","payload":"fail"}' http://localhost:8080/api/publish