# spring-mongodb-practice
### 도커 - MongoDB - 단일 인스턴스 복제세트X
docker run -d --name mongodb -v /data/docker/mongo:/data/db
-e MONGO_INITDB_ROOT_USERNAME=root
-e MONGO_INITDB_ROOT_PASSWORD=1234
-p 27017:27017 mongo
