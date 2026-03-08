# 📌 JPA 기능 정리

이 문서는 프로젝트 전체에서 사용된 **Spring Data JPA / Hibernate** 기능들을 파일별, 기능별로 정리한 문서입니다.

---

## 📦 사용 기술 스택 (의존성)

`build.gradle` 에 선언된 JPA 관련 의존성:

```groovy
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
implementation 'org.hibernate:hibernate-core:5.4.32.Final'
implementation 'javax.persistence:javax.persistence-api:2.2'
implementation group: 'org.mariadb.jdbc', name: 'mariadb-java-client', version: '2.4.1'
```

---

## ⚙️ JPA 설정 (`application.properties`)

```properties
# 데이터소스 설정
spring.datasource.url=jdbc:mariadb://localhost:3307/firstdrinkdb
spring.datasource.username=mobile
spring.datasource.password=mobile!
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver

# Hibernate/JPA 설정
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect
```

| 설정 키 | 값 | 설명 |
|---|---|---|
| `ddl-auto` | `update` | 애플리케이션 시작 시 엔티티 정의를 기반으로 DB 스키마 자동 생성/수정 |
| `hibernate.dialect` | `MariaDBDialect` | Hibernate가 MariaDB에 맞는 SQL을 생성하도록 방언(Dialect) 지정 |

> ⚠️ **주의:** `ddl-auto=update`는 개발 편의를 위한 설정입니다.  
> 운영(Production) 환경에서는 의도치 않은 스키마 변경이나 데이터 손실이 발생할 수 있으므로,  
> **`validate`** (스키마 검증만 수행) 또는 **`none`** (자동 변경 비활성화)으로 변경하는 것을 권장합니다.

---

## 🗂️ 엔티티 클래스별 사용된 JPA 애노테이션

### `User.java`

| 애노테이션 | 사용 목적 |
|---|---|
| `@Entity` | 클래스를 JPA 엔티티로 선언 |
| `@Id` | 기본 키(PK) 필드 지정 |
| `@GeneratedValue(strategy = GenerationType.IDENTITY)` | DB의 AUTO_INCREMENT를 이용한 PK 자동 생성 |

---

### `Member.java`

| 애노테이션 | 사용 목적 |
|---|---|
| `@Entity` | 클래스를 JPA 엔티티로 선언 |
| `@Id` | 기본 키(PK) 필드 지정 (String 타입 - 로그인 ID) |
| `@Column(unique = true)` | `nickname`, `phone` 필드에 유니크 제약 조건 적용 |
| `@Column(columnDefinition = "int default 1")` | DB 컬럼의 기본값 직접 지정 (`grade` 필드) |

---

### `Alcohol.java`

| 애노테이션 | 사용 목적 |
|---|---|
| `@Entity` | 클래스를 JPA 엔티티로 선언 |
| `@Id` | 기본 키(PK) 필드 지정 |
| `@GeneratedValue(strategy = GenerationType.IDENTITY)` | DB AUTO_INCREMENT를 이용한 PK 자동 생성 |
| `@ManyToMany` | `Taste` 및 `Scent`와의 다대다(N:M) 관계 정의 (소유 측) |
| `@JoinTable(name = "alcohol_taste", ...)` | `alcohol_taste` 중간 조인 테이블 명시적 설정 |
| `@JoinTable(name = "alcohol_scent", ...)` | `alcohol_scent` 중간 조인 테이블 명시적 설정 |
| `@JoinColumn(name = "alcohol_number")` | 조인 테이블에서 Alcohol 측 외래 키 컬럼 이름 지정 |
| `@JoinColumn(name = "taste_number")` | 조인 테이블에서 Taste 측 외래 키 컬럼 이름 지정 |
| `@JoinColumn(name = "scent_number")` | 조인 테이블에서 Scent 측 외래 키 컬럼 이름 지정 |

> `addTaste()` / `addScent()` 헬퍼 메서드를 통해 양방향 관계의 양쪽을 동기화합니다.

---

### `Taste.java`

| 애노테이션 | 사용 목적 |
|---|---|
| `@Entity` | 클래스를 JPA 엔티티로 선언 |
| `@Id` | 기본 키(PK) 필드 지정 |
| `@GeneratedValue(strategy = GenerationType.IDENTITY)` | DB AUTO_INCREMENT를 이용한 PK 자동 생성 |
| `@ManyToMany(mappedBy = "tastes")` | `Alcohol`과의 다대다 관계에서 역방향(inverse) 측으로 선언 |
| `@JsonIgnore` | JSON 직렬화 시 순환 참조 방지를 위해 `alcohols` 컬렉션 무시 |

---

### `Scent.java`

| 애노테이션 | 사용 목적 |
|---|---|
| `@Entity` | 클래스를 JPA 엔티티로 선언 |
| `@Id` | 기본 키(PK) 필드 지정 |
| `@GeneratedValue(strategy = GenerationType.IDENTITY)` | DB AUTO_INCREMENT를 이용한 PK 자동 생성 |
| `@ManyToMany(mappedBy = "scents")` | `Alcohol`과의 다대다 관계에서 역방향(inverse) 측으로 선언 |
| `@JsonIgnore` | JSON 직렬화 시 순환 참조 방지를 위해 `alcohols` 컬렉션 무시 |

---

### `Review.java`

| 애노테이션 | 사용 목적 |
|---|---|
| `@Entity` | 클래스를 JPA 엔티티로 선언 |
| `@Id` | 기본 키(PK) 필드 지정 (수동 할당, `@GeneratedValue` 없음) |

---

### `Tastenote.java`

| 애노테이션 | 사용 목적 |
|---|---|
| `@Entity` | 클래스를 JPA 엔티티로 선언 |
| `@Id` | 기본 키(PK) 필드 지정 (수동 할당, `@GeneratedValue` 없음) |
| `@Column` | 컬럼 속성 정의 |

---

## 🗄️ 레포지토리 인터페이스별 사용된 JPA 기능

모든 레포지토리는 **`JpaRepository<엔티티, ID타입>`** 를 상속합니다.  
`JpaRepository`는 `save`, `findById`, `findAll`, `deleteById` 등의 기본 CRUD 메서드를 제공합니다.

| 레포지토리 | 제네릭 타입 | `@Repository` | 커스텀 쿼리 메서드 |
|---|---|---|---|
| `UserRepository` | `<User, Long>` | ❌ (암묵적 적용) | 없음 |
| `MemberRepository` | `<Member, String>` | ❌ (암묵적 적용) | `findByNickname`, `findByIdAndPassword` |
| `AlcoholRepository` | `<Alcohol, Integer>` | ❌ (암묵적 적용) | `findByBarcode` |
| `ReviewRepository` | `<Review, Integer>` | ❌ (암묵적 적용) | `findAllByAlcoholNumber` |
| `TastenoteRepository` | `<Tastenote, Integer>` | ❌ (암묵적 적용) | 없음 |
| `TasteRepository` | `<Taste, Integer>` | ✅ 명시적 선언 | 없음 |
| `ScentRepository` | `<Scent, Integer>` | ✅ 명시적 선언 | 없음 |

### Spring Data JPA 메서드 네이밍 규칙 (쿼리 자동 생성)

Spring Data JPA는 메서드 이름만으로 자동으로 SQL 쿼리를 생성합니다.

| 메서드 | 패턴 | 생성되는 쿼리 예시 |
|---|---|---|
| `findByNickname(String nickname)` | `findBy{필드명}` | `WHERE nickname = ?` |
| `findByIdAndPassword(String id, String password)` | `findBy{필드1}And{필드2}` | `WHERE id = ? AND password = ?` |
| `findByBarcode(String barcode)` | `findBy{필드명}` | `WHERE barcode = ?` |
| `findAllByAlcoholNumber(int alcoholNumber)` | `findAllBy{필드명}` | `WHERE alcohol_number = ?` (결과: List) |

---

## 🔗 엔티티 관계 (ERD 개요)

```
┌─────────────┐      ┌──────────────────┐      ┌─────────────┐
│   Alcohol   │      │  alcohol_taste   │      │   Taste     │
│  (소유 측)  │◄────►│  (조인 테이블)   │◄────►│  (역방향)   │
└─────────────┘      └──────────────────┘      └─────────────┘
       │
       │      ┌──────────────────┐      ┌─────────────┐
       └─────►│  alcohol_scent   │◄────►│   Scent     │
              │  (조인 테이블)   │      │  (역방향)   │
              └──────────────────┘      └─────────────┘

User ─── (관계 없음, 독립 엔티티)
Member ─── (관계 없음, 독립 엔티티)
Review ─── (alcoholNumber 필드로 수동 참조)
Tastenote ─── (alcoholNumber, tasteNumber, scentNumber 필드로 수동 참조)
```

| 관계 | 소유 엔티티 | 대상 엔티티 | 타입 | 중간 테이블 | Cascade | Fetch 타입 |
|---|---|---|---|---|---|---|
| Alcohol ↔ Taste | `Alcohol` | `Taste` | `@ManyToMany` | `alcohol_taste` | 미설정 (기본값) | 미설정 (기본: LAZY) |
| Alcohol ↔ Scent | `Alcohol` | `Scent` | `@ManyToMany` | `alcohol_scent` | 미설정 (기본값) | 미설정 (기본: LAZY) |

---

## 🔄 트랜잭션 (`@Transactional`)

| 파일 | 적용 위치 | import 패키지 |
|---|---|---|
| `AlcoholService.java` | `saveAlcohol()` 메서드 | `javax.transaction.Transactional` |
| `MemberService.java` | import만 존재, 실제 사용 없음 | `org.springframework.transaction.annotation.Transactional` |

`AlcoholService.saveAlcohol()` 은 주류 저장 + 맛/향 연관 관계 저장을 하나의 트랜잭션으로 묶어 원자성을 보장합니다.

---

## ✅ 사용된 JPA 기능 전체 요약

| 기능 | 사용 여부 | 사용 위치 |
|---|---|---|
| `@Entity` | ✅ 사용 | 7개 엔티티 클래스 전체 |
| `@Id` | ✅ 사용 | 7개 엔티티 클래스 전체 |
| `@GeneratedValue(IDENTITY)` | ✅ 사용 | User, Alcohol, Taste, Scent |
| `@Column(unique = true)` | ✅ 사용 | Member (nickname, phone) |
| `@Column(columnDefinition = ...)` | ✅ 사용 | Member (grade), Tastenote |
| `@ManyToMany` | ✅ 사용 | Alcohol (소유), Taste (역방향), Scent (역방향) |
| `@JoinTable` | ✅ 사용 | Alcohol (alcohol_taste, alcohol_scent) |
| `@JoinColumn` | ✅ 사용 | Alcohol (조인 테이블 FK 컬럼 명시) |
| `JpaRepository` 상속 | ✅ 사용 | 7개 레포지토리 전체 |
| 메서드 네이밍 쿼리 자동 생성 | ✅ 사용 | Member, Alcohol, Review 레포지토리 |
| `@Transactional` | ✅ 사용 | AlcoholService.saveAlcohol() |
| `@Repository` | ✅ 사용 (일부) | TasteRepository, ScentRepository |
| `ddl-auto=update` (스키마 자동 관리) | ✅ 사용 | application.properties |
| `@Query` (커스텀 JPQL) | ❌ 미사용 | - |
| `@OneToMany` / `@ManyToOne` / `@OneToOne` | ❌ 미사용 | - |
| Cascade 타입 설정 | ❌ 미사용 (기본값 적용) | - |
| Fetch 타입 설정 | ❌ 미사용 (기본값: LAZY) | - |
| `@NamedQuery` | ❌ 미사용 | - |
| `@Enumerated` | ❌ 미사용 | - |
| `@Embedded` / `@EmbeddedId` | ❌ 미사용 | - |
| `@Version` (낙관적 잠금) | ❌ 미사용 | - |
| `@Transient` | ❌ 미사용 | - |
