
# 프로필 서비스

## 📖 프로젝트 소개

사용자의 프로필과 등급 정보를 관리하는 마이크로서비스입니다.

## ✨ 주요 기능

### 프로필 관리
- 닉네임 등록 및 중복 확인
- 닉네임 변경
- 프로필 생성, 수정 및 조회
- 프로필 이미지 변경

### 등급 관리
- 등급 생성, 수정 및 조회

## 🛠️ 기술 스택

- **언어:** Java 17
- **프레임워크:** Spring Boot 3.5.0
- **데이터베이스:** JPA (MySQL), MongoDB
- **메시징:** Kafka
- **기타:** Spring Cloud Eureka, Swagger (OpenAPI)

## 🚀 실행 방법

### Docker Compose 사용

1. 프로젝트 루트 디렉토리에서 아래 명령어를 실행합니다.
   ```bash
   docker-compose -f docker-compose-profile.yml up -d
   ```

2. 서비스가 시작되면 `http://localhost:8082` 에서 접근할 수 있습니다.

### 수동 실행

1. `application.yml` 파일에 데이터베이스 및 기타 설정을 구성합니다.
2. 프로젝트를 빌드합니다.
   ```bash
   ./gradlew build
   ```
3. 빌드된 jar 파일을 실행합니다.
   ```bash
   java -jar build/libs/profile-service-0.0.1-SNAPSHOT.jar
   ```

## 📝 API 문서

Swagger UI를 통해 API 문서를 확인할 수 있습니다.

- [http://localhost:8082/swagger-ui/index.html](http://localhost:8082/swagger-ui/index.html)

