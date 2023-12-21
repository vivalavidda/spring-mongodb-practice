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

#### Three-node replica set
docker-compose-three-node.file


## SPRING MONGODB
Spring Data의 MongoDB 지원
- Mongo 드라이버 인스턴스와 복제 세트에 대한 Java 기반의 @Configuration 클래스나 XML 네임스페이스를 사용한 Spring 구성 지원.
- 일반적인 Mongo 작업을 수행할 때 생산성을 높여주는 MongoTemplate 도우미 클래스. 문서와 POJO 사이의 객체 매핑이 통합되어 있음.
- Spring의 이식 가능한 데이터 액세스 예외 계층으로의 예외 변환.
- Spring의 변환 서비스와 통합된 기능이 풍부한 객체 매핑.
- 확장 가능하여 다른 메타데이터 형식을 지원하는 주석 기반 매핑 메타데이터.
- 지속성 및 매핑 수명주기 이벤트.
- Java 기반의 쿼리, 기준(Criteria), 업데이트 DSL.
- 사용자 지정 쿼리 메소드를 지원하는 저장소 인터페이스의 자동 구현.
- 타입-안전 쿼리를 지원하는 QueryDSL 통합.
- 멀티-문서 트랜잭션.
- GeoSpatial 통합.

#### Mongo Template
- MongoDB의 문서를 생성, 업데이트, 삭제 및 쿼리하는 편리한 방법을 제공
- MongoTemplate는 com.mongodb.client.MongoDatabase와 같은 저수준 MongoDB API와 직접 통신할 수 있는 콜백 메소드를 제공
#### Spring Data Repository
-  MongoDB에 저장된 데이터에 대한 접근과 조작을 쉽게 만드는 인터페이스 제공
- 타입-안전 쿼리를 지원하며, QueryDSL 통합 등으로 더욱 강력한 쿼리 기능을 제공
- 사용자 정의 쿼리 메소드를 포함하여, 데이터 액세스를 보다 편리하게 만드는 자동 구현 기능 포함
