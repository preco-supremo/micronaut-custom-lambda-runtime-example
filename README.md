##

1. Build native image

```bash
./mvnw package -Dpackaging=native-image -Dmicronaut.runtime=lambda -Pgraalvm 
```

2. Build docker image

```bash
podman compose --file docker-compose.yml build
```
3. Run docker containers

```bash
podman compose --file docker-compose.yml up
```

4. Wait for MySQL be ready
```
db-1         | 2025-06-02T20:05:17.467609Z 0 [System] [MY-010931] [Server] /usr/sbin/mysqld: ready for connections. Version: '9.3.0'  socket: '/var/run/mysqld/mysqld.sock'  port: 3306  MySQL Community Server - GPL.
```

5. Invoke lambda runtime

```bash
curl -d {} -X POST http://localhost:9000/2015-03-31/functions/function/invocations
```

6. See docker logs

```
demo-java-1  | 02 Jun 2025 17:05:24,055 [INFO] (rapid) INVOKE START(requestId: b6037420-81ca-407c-bb80-df2bdbf5ea99)
demo-java-1  | Hibernate: insert into `product` (`created_at`,`price`,`title`,`updated_at`) values (?,?,?,?)
demo-java-1  | Hibernate: select p1_0.`id`,p1_0.`created_at`,p1_0.`price`,p1_0.`title`,p1_0.`updated_at` from `product` p1_0
demo-java-1  | Product{id=2, title='TV', price=999.90, createdAt=2025-06-02T17:05:24, updatedAt=2025-06-02T17:05:24}
demo-java-1  | 02 Jun 2025 17:05:24,154 [INFO] (rapid) INVOKE RTDONE(status: success, produced bytes: 0, duration: 99.711000ms)
```