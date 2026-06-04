# Mogilepj 프로젝트 정리

## 1. 프로젝트 개요

Mogilepj는 주류 정보를 조회하고, 사용자가 주류에 대한 리뷰와 테이스팅 노트를 남길 수 있도록 만든 주류 리뷰 플랫폼입니다. 백엔드는 Spring Boot 기반 REST API 서버이며, MariaDB에 주류, 회원, 리뷰, 맛/향 태그 데이터를 저장합니다.

서비스의 근본 가치는 사용자가 마트, 편의점, 술집에서 실제 술을 마주한 순간 바코드 스캔을 통해 술 정보와 커뮤니티 데이터로 연결되는 흐름입니다.

```text
마트 / 편의점 / 술집
↓
술 바코드 스캔
↓
술 정보 확인
↓
리뷰 작성
↓
맛/향 데이터 축적
↓
검색 및 추천
```

주요 사용 흐름은 다음과 같습니다.

1. 사용자가 회원 가입 또는 로그인을 한다.
2. 바코드나 주류 번호로 주류 정보를 조회한다.
3. 주류의 맛, 향, 도수, 가격, 평균 별점 등 상세 정보를 확인한다.
4. 해당 주류에 대한 리뷰를 조회하거나 작성한다.
5. 테이스팅 노트를 통해 더 세부적인 시음 기록을 남기는 구조를 목표로 한다.

## 2. 기술 스택

| 구분 | 내용 |
| --- | --- |
| Language | Java 17 |
| Framework | Spring Boot 2.5.7 |
| Build Tool | Gradle |
| ORM | Spring Data JPA, Hibernate |
| Database | MariaDB |
| JDBC Driver | MariaDB Java Client |
| 기타 | Lombok |

## 3. 실행 환경

`src/main/resources/application.properties` 기준 설정입니다.

| 항목 | 값 |
| --- | --- |
| Server Port | `9096` |
| Database URL | `jdbc:mariadb://localhost:3307/firstdrinkdb` |
| Database User | `mobile` |
| Database Password | `mobile!` |
| Hibernate DDL | `update` |

실행 명령:

```bash
./gradlew bootRun
```

Windows 환경에서는 다음 명령을 사용할 수 있습니다.

```bash
gradlew.bat bootRun
```

서버 실행 후 기본 접속 주소:

```text
http://localhost:9096
```

## 4. 프로젝트 구조

```text
src/main/java/com/example/mobilepj
├── FirstAlcoholApplication.java
├── controller
│   ├── AlcoholController.java
│   ├── MemberController.java
│   ├── ReviewController.java
│   ├── TastenoteController.java
│   └── UserController.java
├── dto
│   └── AlcoholRequestDto.java
├── entity
│   ├── Alcohol.java
│   ├── Member.java
│   ├── Review.java
│   ├── Scent.java
│   ├── Taste.java
│   ├── Tastenote.java
│   └── User.java
├── repository
│   ├── AlcoholRepository.java
│   ├── MemberRepository.java
│   ├── ReviewRepository.java
│   ├── ScentRepository.java
│   ├── TasteRepository.java
│   ├── TastenoteRepository.java
│   └── UserRepository.java
└── service
    ├── AlcoholService.java
    ├── MemberService.java
    ├── ReviewService.java
    ├── TastenoteService.java
    └── UserService.java
```

리소스 폴더:

```text
src/main/resources
├── application.properties
└── sql
    ├── firstdrinkdb_schema.sql
    ├── insert_alcohol.sql
    ├── insert_taste.sql
    ├── insert_scent.sql
    ├── insert_alcohol_taste.sql
    └── insert_alcohol_scent.sql
```

## 5. 계층별 역할

### Controller

클라이언트 요청을 받는 REST API 계층입니다.

| Controller | Base Path | 역할 |
| --- | --- | --- |
| `AlcoholController` | `/alcohols` | 주류 목록, 주류 단건 조회, 바코드 조회, 주류 등록 |
| `ReviewController` | `/review` | 주류별 리뷰 조회, 리뷰 등록 |
| `MemberController` | `/members` | 회원가입, 로그인, 아이디/닉네임 중복 확인, 회원 조회 |
| `UserController` | `/users` | 기본 User CRUD API |
| `TastenoteController` | `/tastenote` | 현재 활성화된 API 없음 |

### Service

비즈니스 로직 계층입니다. Controller에서 받은 요청을 처리하고 Repository를 호출합니다.

| Service | 역할 |
| --- | --- |
| `AlcoholService` | 주류 조회/저장, Taste/Scent 연결 |
| `ReviewService` | 리뷰 저장, 주류 번호 기준 리뷰 목록 조회 |
| `MemberService` | 회원 저장, 로그인 검증, 중복 확인 |
| `UserService` | User CRUD |
| `TastenoteService` | 테이스팅 노트 조회 로직 일부 보유 |

### Repository

Spring Data JPA를 이용한 데이터 접근 계층입니다.

| Repository | 주요 기능 |
| --- | --- |
| `AlcoholRepository` | `findByBarcode` 제공 |
| `ReviewRepository` | `findAllByAlcoholNumber` 제공 |
| `MemberRepository` | `findByNickname`, `findByIdAndPassword` 제공 |
| `TasteRepository` | 맛 태그 조회 |
| `ScentRepository` | 향 태그 조회 |
| `TastenoteRepository` | 테이스팅 노트 데이터 접근 |
| `UserRepository` | User 데이터 접근 |

## 6. 주요 도메인 모델

### Alcohol

주류 정보를 나타내는 핵심 엔티티입니다.

주요 필드:

| 필드 | 설명 |
| --- | --- |
| `alcoholNumber` | 주류 고유 번호 |
| `name` | 주류명 |
| `barcode` | 바코드 |
| `category` | 주류 카테고리 |
| `volume` | 용량 |
| `price` | 가격 |
| `content` | 도수 |
| `avgStar` | 평균 별점 |
| `ibu` | 쓴맛 지수 |
| `tasteDetail` | 맛 설명 |
| `detail` | 상세 설명 |
| `tastes` | 맛 태그 목록 |
| `scents` | 향 태그 목록 |

`Alcohol`은 `Taste`, `Scent`와 다대다 관계를 가집니다.

### Taste / Scent

주류의 맛과 향 태그를 표현합니다.

| Entity | 주요 필드 |
| --- | --- |
| `Taste` | `tasteNumber`, `tasteInfo` |
| `Scent` | `scentNumber`, `scentInfo` |

### Review

사용자가 주류에 대해 작성한 리뷰입니다.

주요 필드:

| 필드 | 설명 |
| --- | --- |
| `reviewNumber` | 리뷰 번호 |
| `nickname` | 작성자 닉네임 |
| `reviewStarpoint` | 리뷰 별점 |
| `creationDate` | 작성일 |
| `alcoholNumber` | 대상 주류 번호 |
| `reviewInfo` | 리뷰 내용 |
| `picture` | 리뷰 이미지 |

### Member

실제 회원 기능에 사용되는 엔티티입니다.

주요 필드:

| 필드 | 설명 |
| --- | --- |
| `id` | 회원 아이디 |
| `nickname` | 닉네임 |
| `password` | 비밀번호 |
| `phone` | 전화번호 |
| `grade` | 회원 등급 |
| `point` | 포인트 |
| `view_num` | 조회 수 |
| `tastenote_num` | 테이스팅 노트 수 |
| `starpoint` | 회원 별점 |

### Tastenote

상세 시음 기록을 저장하기 위한 엔티티입니다. 현재 엔티티와 서비스는 존재하지만, Controller에서 활성화된 API는 없습니다.

주요 필드:

| 필드 | 설명 |
| --- | --- |
| `tastenoteNumber` | 테이스팅 노트 번호 |
| `nickname` | 작성자 닉네임 |
| `alcoholNumber` | 대상 주류 번호 |
| `tastenoteStarpoint` | 테이스팅 노트 별점 |
| `tastenoteInfo` | 노트 내용 |
| `tastenoteFormat` | 노트 형식 |
| `tasteNumber` | 맛 번호 |
| `scentNumber` | 향 번호 |
| `tastingDay` | 시음일 |
| `memo` | 메모 |
| `firstScent` | 첫 향 |
| `middleScent` | 중간 향 |
| `finalScent` | 끝 향 |
| `glass` | 잔 종류 |
| `color` | 색상 |
| `viscosity` | 점도 |
| `sugar` | 당도 |

## 7. 주요 기능

### 주류 정보 조회

주류 전체 목록을 조회하거나, 주류 번호 또는 바코드로 단건 조회할 수 있습니다.

관련 API:

```http
GET /alcohols/
GET /alcohols/{alcoholNumber}
GET /alcohols/serch/{barcode}
```

### 주류 등록

주류 기본 정보와 맛/향 태그 ID 목록을 받아 새 주류를 저장합니다.

관련 API:

```http
POST /alcohols/
```

저장 과정:

1. `AlcoholRequestDto`로 요청을 받는다.
2. 주류 기본 정보를 먼저 저장한다.
3. `tasteIds`, `scentIds`로 Taste/Scent 목록을 조회한다.
4. 주류와 맛/향 태그를 연결한다.
5. 최종 Alcohol을 다시 저장한다.

### 리뷰 조회 및 등록

특정 주류 번호에 연결된 리뷰 목록을 조회하고, 새 리뷰를 등록할 수 있습니다.

관련 API:

```http
GET /review/{alcoholNumber}
POST /review/
```

### 회원 기능

회원 가입, 로그인, 아이디 중복 확인, 닉네임 중복 확인, 회원 단건 조회를 제공합니다.

관련 API:

```http
GET /members/checkId
GET /members/checkNickname
POST /members/signup
POST /members/login
GET /members/{id}
```

### User API

`/users` API는 `Member`와 별도로 존재하는 기본 CRUD API입니다. 현재 프로젝트의 실제 회원 기능은 `/members`가 더 중심에 가깝습니다.

## 8. API 문서

상세한 API 경로, 요청 파라미터, 요청/응답 예시는 `API.md`에 정리되어 있습니다.

```text
API.md
```

요구사항 정의서와 분석서는 `REQUIREMENTS.md`에 별도로 정리되어 있습니다.

```text
REQUIREMENTS.md
```

요구사항 대비 현재 구현 여부는 `IMPLEMENTATION_STATUS.md`에 체크리스트로 정리되어 있습니다.

```text
IMPLEMENTATION_STATUS.md
```

## 9. 데이터베이스 초기화 파일

`src/main/resources/sql` 폴더에는 스키마와 초기 데이터 SQL이 들어 있습니다.

| 파일 | 설명 |
| --- | --- |
| `firstdrinkdb_schema.sql` | DB 스키마 |
| `insert_alcohol.sql` | 주류 초기 데이터 |
| `insert_taste.sql` | 맛 태그 초기 데이터 |
| `insert_scent.sql` | 향 태그 초기 데이터 |
| `insert_alcohol_taste.sql` | 주류-맛 연결 데이터 |
| `insert_alcohol_scent.sql` | 주류-향 연결 데이터 |
| `create_alcohol_taste.sql` | 주류-맛 연결 테이블 생성 |
| `creaet_alcohol_scent.sql` | 주류-향 연결 테이블 생성 |

## 10. 현재 구현상 주의점

- `README.md`는 한글 인코딩이 깨져 있어 새 문서 기준으로 프로젝트를 보는 것이 좋습니다.
- 바코드 조회 API 경로가 `/alcohols/serch/{barcode}`로 되어 있습니다. 오타로 보이지만 현재 클라이언트는 이 경로를 사용해야 합니다.
- `TastenoteController`에는 활성화된 API가 없습니다.
- `Review.reviewNumber`는 자동 생성 설정이 없어 리뷰 등록 시 클라이언트가 직접 번호를 보내야 합니다.
- `/members/login`은 로그인 성공 여부를 boolean으로만 반환합니다.
- `Member.password`가 API 응답에 포함될 수 있으므로 응답 DTO 분리가 필요합니다.
- `/users/login`은 실제 로그인 검증이 아니라 User 저장 로직을 호출합니다.
- `User`와 `Member`가 모두 존재하므로 실제 회원 모델을 하나로 정리하는 것이 좋습니다.

## 11. 개선 방향

우선순위가 높은 개선 작업은 다음과 같습니다.

1. `README.md` 인코딩 복구 또는 `PROJECT.md` 기준으로 README 재작성
2. `/alcohols/serch/{barcode}`를 `/alcohols/search/{barcode}`로 변경
3. 로그인 성공 시 회원 정보 또는 인증 토큰 반환 구조 추가
4. 비밀번호 응답 노출 방지를 위한 DTO 분리
5. 리뷰 번호와 테이스팅 노트 번호에 `@GeneratedValue` 적용
6. `TastenoteController` API 구현
7. `User`와 `Member` 도메인 역할 정리
8. 예외 처리와 HTTP 상태 코드 정리
9. API 테스트 또는 Controller 테스트 추가

## 12. 한 줄 요약

mobilepj는 MariaDB에 저장된 주류 데이터를 바코드와 주류 번호로 조회하고, 회원이 주류 리뷰와 테이스팅 노트를 작성하는 것을 목표로 한 Spring Boot 기반 주류 리뷰 API 서버입니다.
