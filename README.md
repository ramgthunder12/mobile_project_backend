# 🍷 Mobile Project - 주류 바코드 인식 및 테이스팅 노트 공유 앱

## 📌 프로젝트 소개
이 프로젝트는 주류 바코드를 카메라로 인식하여 해당 주류의 맛과 리뷰를 확인할 수 있는 모바일 애플리케이션입니다.  
사용자는 직접 **테이스팅 노트**를 작성하고, 다른 사람들이 남긴 리뷰도 확인할 수 있습니다.  

## 🎯 주요 기능
- 📸 **바코드 인식**: 카메라를 통해 주류의 바코드를 스캔하면 해당 제품 정보를 조회
- ⭐ **맛과 리뷰 제공**: 주류의 특징, 맛, 향에 대한 설명 제공
- 📱 **리뷰 작성**: 맛과 평점 리뷰 작성 가능
- ✍ **테이스팅 노트 작성**: 사용자들이 직접 시음 노트를 남기고 공유
- 🔍 **다른 사용자의 리뷰 확인**: 다른 사람들이 작성한 테이스팅 노트 확인 가능

## 🚀 실행 환경
- JDK 17
- Gradle 8.4 (Wrapper)
- Spring Boot 2.5.7
- Hibernate 5.4.32.Final
- MariaDB Driver 2.4.1
- IDE: Eclipse

## 🔗 API 엔드포인트
- /alcohol/* - 주류 정보 관련 API  
- /review/* - 리뷰 관련 API  
- /tastenote/* - 테이스팅 노트 관련 API  
- /member/* - 회원 관리 API

## 📌 앞으로 할 일 (To-do)

- [ ] API 목록 정리
- [ ] tastenote api 구현

---

## 📜 개발 히스토리 (커밋 기반 정리)

### 1️⃣ 프로젝트 초기 설정 (2025-02-24)

> 🎉 `e55ace7` — Init mobile project backend

Spring Boot 백엔드 프로젝트를 초기 생성하여 전체 구조를 구축했습니다.

- **빌드 설정**: `build.gradle` (Spring Boot 2.5.7, JDK 17, MariaDB, Lombok, JPA)
- **엔티티 (Entity)**: `Alcohol`, `Member`, `Review`, `Tastenote`, `User`
- **리포지토리 (Repository)**: `AlcoholRepository`, `MemberRepository`, `ReviewRepository`, `TastenoteRepository`, `UserRepository`
- **서비스 (Service)**: `AlcoholService`, `MemberService`, `ReviewService`, `TastenoteService`, `UserService`
- **컨트롤러 (Controller)**: `AlcoholController`, `MemberController`, `ReviewController`, `TastenoteController`, `UserController`
- **기타**: `application.properties`, 테스트 코드

### 2️⃣ 문서 작성 — README 생성 (2025-02-24)

> 📝 `bff0ad3` — Docs: Update README.md (프로젝트 소개 및 주요기능 작성)

- README.md를 추가하고 프로젝트 소개 및 주요 기능(바코드 인식, 리뷰, 테이스팅 노트 등)을 기술

### 3️⃣ 버그 수정 — ReviewController (2025-03-19)

> 🐛 `1476551` — fix: Change @RequestParam to @PathVariable in ReviewController reviewList endpoint

- `ReviewController`의 `reviewList` 엔드포인트에서 `@RequestParam` → `@PathVariable`로 변경하여 URL 경로 변수를 올바르게 매핑
- `application.properties` 설정 업데이트

### 4️⃣ 빌드 설정 수정 (2025-03-19)

> `a87a5e4` — build.gradle 수정

- `build.gradle`의 `sourceCompatibility` 설정 조정

### 5️⃣ 주류-향/맛 관계 데이터 추가 (2025-06-12)

> 🗃️ `11f01c7` — chore: add SQL insert scripts for alcohol-scent-taste relations

- 주류(Alcohol)와 향(Scent)/맛(Taste) 관계 데이터를 위한 SQL INSERT 스크립트 추가
- `build.gradle` 의존성 업데이트

### 6️⃣ 무한 직렬화 루프 버그 수정 및 엔티티 확장 (2025-06-13)

> 🐛 `9cdfde0` — fix: prevent infinite serialization loop in alcohol-scent/taste bidirectional mapping using @JsonIgnore

Alcohol ↔ Scent/Taste 양방향 `@ManyToMany` 매핑에서 JSON 직렬화 시 무한 루프가 발생하는 문제를 `@JsonIgnore`로 해결했습니다.

- **신규 엔티티**: `Scent`, `Taste` (향/맛 프로필)
- **신규 리포지토리**: `ScentRepository`, `TasteRepository`
- **신규 DTO**: `AlcoholRequestDto` (주류 생성/수정용 데이터 전송 객체)
- **서비스 확장**: `AlcoholService`에 향/맛 관련 비즈니스 로직 추가
- **컨트롤러 수정**: `AlcoholController` 엔드포인트 개선

### 7️⃣ PR 머지 — 향/맛 관계 리팩토링 (2025-06-14)

> `2377ad6` — Merge pull request #1 from ramgthunder12/refactor/alcohol-scent-taste-relations

- `refactor/alcohol-scent-taste-relations` 브랜치를 `main`에 머지

### 8️⃣ README 보강 — 실행 환경 (2025-08-25)

> 📝 `316614e` — Docs: add execution environment to Mobile Project README

- README에 **실행 환경** 섹션 추가 (JDK 17, Gradle 8.4, Spring Boot 2.5.7, Hibernate, MariaDB, Eclipse)

### 9️⃣ README 보강 — API 엔드포인트 (2025-08-26 ~ 08-29)

> 📝 `8bc3e89` — Docs: add API endpoints section in Mobile Project README  
> 📝 `2b271eb` — Docs: unify bullet markers in API endpoints section  
> 📝 `6791fb1` — docs(readme): retitle API section (🔗), add To-do, remove /user/*

- README에 **API 엔드포인트** 섹션 추가 (`/alcohol/*`, `/review/*`, `/tastenote/*`, `/member/*`)
- API 엔드포인트 마크다운 서식 통일
- API 섹션 제목을 🔗로 변경, **To-do** 섹션 추가, `/user/*` 엔드포인트 제거

---

### 📊 커밋 요약

| 분류 | 커밋 수 | 주요 내용 |
|------|---------|-----------|
| 🎉 초기 설정 | 1 | Spring Boot 프로젝트 생성, 전체 구조 구축 |
| 🐛 버그 수정 | 2 | ReviewController 경로 변수 수정, JSON 직렬화 루프 수정 |
| ✨ 기능 확장 | 2 | Scent/Taste 엔티티 추가, 주류-향/맛 관계 매핑 |
| 📝 문서화 | 5 | README 생성 및 지속적 보강 (소개, 실행환경, API, To-do) |
| 🔧 기타 | 1 | 빌드 설정 수정 |
