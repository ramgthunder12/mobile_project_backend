# Mogilepj API 정리

Mogilepj는 주류 바코드 조회, 주류 정보, 리뷰, 회원 가입/로그인을 제공하는 Spring Boot 기반 주류 리뷰 플랫폼입니다.

- Base URL: `http://localhost:9096`
- Content-Type: `application/json`
- 주요 코드 위치: `src/main/java/com/example/mobilepj/controller`

## 1. 주류 API

Base path: `/alcohols`

### 주류 전체 목록 조회

```http
GET /alcohols/
```

응답: `Alcohol[]`

```json
[
  {
    "alcoholNumber": 1,
    "name": "제품명",
    "barcode": "8800000000000",
    "category": "맥주",
    "volume": "500ml",
    "price": "3000",
    "content": 4.5,
    "avgStar": 4.2,
    "ibu": 20,
    "tasteDetail": "가벼운 단맛",
    "detail": "제품 설명",
    "tastes": [],
    "scents": []
  }
]
```

### 주류 번호로 조회

```http
GET /alcohols/{alcoholNumber}
```

Path parameter:

| 이름 | 타입 | 설명 |
| --- | --- | --- |
| `alcoholNumber` | number | 주류 고유 번호 |

응답: `Alcohol`

### 바코드로 주류 조회

```http
GET /alcohols/serch/{barcode}
```

주의: 현재 코드의 실제 경로가 `/serch`입니다. 일반적인 철자는 `/search`이지만, 클라이언트에서는 현재 코드 기준으로 `/serch`를 호출해야 합니다.

Path parameter:

| 이름 | 타입 | 설명 |
| --- | --- | --- |
| `barcode` | string | 주류 바코드 |

응답: `Alcohol`

### 주류 등록

```http
POST /alcohols/
```

요청 바디:

```json
{
  "name": "제품명",
  "barcode": "8800000000000",
  "category": "맥주",
  "volume": "500ml",
  "price": "3000",
  "content": 4.5,
  "avgStar": 0,
  "ibu": 20,
  "tasteDetail": "가벼운 단맛",
  "detail": "제품 설명",
  "tasteIds": [1, 2],
  "scentIds": [1, 3]
}
```

응답: 저장된 `Alcohol`

## 2. 리뷰 API

Base path: `/review`

### 특정 주류의 리뷰 목록 조회

```http
GET /review/{alcoholNumber}
```

Path parameter:

| 이름 | 타입 | 설명 |
| --- | --- | --- |
| `alcoholNumber` | number | 주류 고유 번호 |

응답: `Review[]`

```json
[
  {
    "reviewNumber": 1,
    "nickname": "tester",
    "common": "공통 코멘트",
    "reviewStarpoint": 4.5,
    "creationDate": "2026-06-04T12:00:00.000+00:00",
    "alcoholNumber": 1,
    "reviewInfo": "리뷰 내용",
    "picture": null
  }
]
```

### 리뷰 등록

```http
POST /review/
```

요청 바디:

```json
{
  "reviewNumber": 1,
  "nickname": "tester",
  "common": "공통 코멘트",
  "reviewStarpoint": 4.5,
  "creationDate": "2026-06-04T12:00:00.000+00:00",
  "alcoholNumber": 1,
  "reviewInfo": "리뷰 내용",
  "picture": null
}
```

응답:

```json
true
```

## 3. 회원 API

Base path: `/members`

### 회원 전체 목록 조회

```http
GET /members/
```

응답: `Member[]`

### 아이디 중복 확인

```http
GET /members/checkId?id={id}
```

Query parameter:

| 이름 | 타입 | 설명 |
| --- | --- | --- |
| `id` | string | 확인할 회원 아이디 |

응답:

```json
true
```

`true`이면 이미 존재하는 아이디입니다.

### 닉네임 중복 확인

```http
GET /members/checkNickname?nickname={nickname}
```

Query parameter:

| 이름 | 타입 | 설명 |
| --- | --- | --- |
| `nickname` | string | 확인할 닉네임 |

응답:

```json
true
```

`true`이면 이미 존재하는 닉네임입니다.

### 회원 가입

```http
POST /members/signup
```

요청 바디:

```json
{
  "id": "testId",
  "password": "testPassword",
  "nickname": "testNickname",
  "phone": "010-0000-0000"
}
```

응답:

```json
true
```

### 로그인

```http
POST /members/login
```

요청 바디:

```json
{
  "id": "testId",
  "password": "testPassword"
}
```

응답:

```json
true
```

`true`이면 아이디와 비밀번호가 일치합니다.

### 아이디로 회원 조회

```http
GET /members/{id}
```

Path parameter:

| 이름 | 타입 | 설명 |
| --- | --- | --- |
| `id` | string | 회원 아이디 |

응답: `Member`

```json
{
  "id": "testId",
  "nickname": "testNickname",
  "password": "testPassword",
  "phone": "010-0000-0000",
  "grade": 1,
  "point": 0,
  "view_num": 0,
  "tastenote_num": 0,
  "starpoint": 0.0
}
```

## 4. User API

Base path: `/users`

주의: `Member`와 별도로 존재하는 테스트/기본 사용자 API로 보입니다. 실제 회원 기능은 `/members` 쪽을 우선 사용하면 됩니다.

### User 전체 조회

```http
GET /users/
```

응답: `User[]`

### User 단건 조회

```http
GET /users/{id}
```

응답: `User`

### User 등록

```http
POST /users/
```

요청 바디:

```json
{
  "name": "홍길동",
  "email": "test@example.com"
}
```

응답: 저장된 `User`

### User 로그인

```http
POST /users/login
```

주의: 현재 구현은 로그인 검증이 아니라 `UserService.addUser()`를 호출해 User를 새로 저장합니다.

요청 바디:

```json
{
  "name": "홍길동",
  "email": "test@example.com"
}
```

응답: 저장된 `User`

### User 수정

```http
PUT /users/{id}
```

요청 바디:

```json
{
  "name": "수정된 이름",
  "email": "updated@example.com"
}
```

응답: 수정된 `User`

### User 삭제

```http
DELETE /users/{id}
```

응답 바디 없음

### User 검색

```http
GET /users/search?id={id}&name={name}
```

주의: 현재 구현은 `name` 값을 사용하지 않고, `id`가 `0`이 아니면 id로만 조회합니다.

## 5. Tastenote API

Base path: `/tastenote`

현재 `TastenoteController`에는 활성화된 API가 없습니다. 주석 처리된 전체 조회 API만 남아 있습니다.

## 6. 데이터 모델 요약

### Alcohol

| 필드 | 타입 | 설명 |
| --- | --- | --- |
| `alcoholNumber` | number | 주류 고유 번호 |
| `name` | string | 주류명 |
| `barcode` | string | 바코드 |
| `category` | string | 카테고리 |
| `volume` | string | 용량 |
| `price` | string | 가격 |
| `content` | number | 도수 |
| `avgStar` | number | 평균 별점 |
| `ibu` | number | 쓴맛 지수 |
| `tasteDetail` | string | 맛 상세 설명 |
| `detail` | string | 상세 설명 |
| `tastes` | array | 맛 태그 목록 |
| `scents` | array | 향 태그 목록 |

### Member

| 필드 | 타입 | 설명 |
| --- | --- | --- |
| `id` | string | 회원 아이디 |
| `nickname` | string | 닉네임 |
| `password` | string | 비밀번호 |
| `phone` | string | 전화번호 |
| `grade` | number | 등급, 기본값 1 |
| `point` | number | 포인트 |
| `view_num` | number | 조회 수 |
| `tastenote_num` | number | 테이스팅 노트 수 |
| `starpoint` | number | 별점 |

### Review

| 필드 | 타입 | 설명 |
| --- | --- | --- |
| `reviewNumber` | number | 리뷰 번호 |
| `nickname` | string | 작성자 닉네임 |
| `common` | string | 공통 코멘트 |
| `reviewStarpoint` | number | 리뷰 별점 |
| `creationDate` | string | 작성일 |
| `alcoholNumber` | number | 주류 번호 |
| `reviewInfo` | string | 리뷰 내용 |
| `picture` | string/null | 이미지 byte 배열의 JSON 표현 |

## 7. 개선 필요 사항

- `/alcohols/serch/{barcode}`는 `/alcohols/search/{barcode}`로 오타 수정 권장
- `/review/` 등록 시 `reviewNumber`가 자동 생성되지 않으므로 클라이언트가 직접 번호를 보내야 함
- `/members/login`은 boolean만 반환하므로 로그인 성공 시 회원 정보 또는 토큰 반환 구조 검토 필요
- `password`가 응답에 그대로 노출될 수 있어 DTO 분리 권장
- `/users/login`은 실제 로그인 로직이 아니라 User 등록 로직임
- `TastenoteController`에는 현재 활성 API가 없음
