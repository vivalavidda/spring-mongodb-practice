# spring-mongodb-practice
### 도커 - MongoDB - 단일 인스턴스 복제세트X
docker run -d --name mongodb -v /data/docker/mongo:/data/db
-e MONGO_INITDB_ROOT_USERNAME=root
-e MONGO_INITDB_ROOT_PASSWORD=1234
-p 27017:27017 mongo


### 도커 - MongoDB cluster 
https://medium.com/workleap/the-only-local-mongodb-replica-set-with-docker-compose-guide-youll-ever-need-2f0b74dd8384
MongoDB 복제본 세트를 로컬에서 실행하기 위한 설정
복제본 설정 시 MongoDB의 강력한 기능 사용 가능
- 트랜잭션
- 변경 스트림
- opLog

#### Single-node replica set 
로컬 개발의 경우 단일 노드 복제본 세트만으로도 충분
트랜잭션 및 변경 스트림에 대한 액세스를 제공
docker-compose-single-node.file
