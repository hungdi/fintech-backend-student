# 핀테크 백엔드 실습

1강에서 만든 고객과 계좌에 송금, 대사, 보안 기능을 차례로 추가하는 학생용 프로젝트입니다. 업무 메서드의 `TODO [특강 번호 / 절]`을 직접 구현하세요. 같은 프로젝트를 4강까지 이어 사용합니다.

## 시작하기

Java 17과 Gradle Wrapper 9.7.1, Spring Boot 3.5.16을 사용합니다. IDE에서 이 폴더의 `build.gradle`을 여세요. 패키지는 `com.sparta.fintech.ledger`입니다.

```sh
./gradlew compileJava compileTestJava
./gradlew smokeTest
./gradlew test
```

Windows에서는 `gradlew.bat`을 사용합니다. 최초 실행 시 Wrapper와 의존성을 받기 위한 네트워크가 필요합니다.

- `compileJava compileTestJava`: 현재 `src`의 소스와 테스트를 컴파일합니다. `materials`는 컴파일 대상에 포함하지 않습니다.
- `smokeTest`: DB 없이 DTO, UTC Clock 등 제공한 준비 코드를 확인합니다. 송금이나 대사 기능의 완성을 뜻하지 않습니다.
- `test`: 준비 테스트와 현재 추가한 과제 테스트를 모두 실행합니다. 처음에는 `DomainDesignExerciseTest`의 과제가 작성되지 않아 실패합니다. 과제 메서드의 `fail(...)`을 실제 검증으로 바꾸세요.

업무 메서드의 `UnsupportedOperationException`은 해당 구현 과제를 가리킵니다. 엔티티 매핑을 완성하기 전의 Spring 컨텍스트 오류는 DB를 사용하는 기능을 아직 준비하지 않았기 때문입니다. 순수 DTO 테스트와 앱 전체 기동은 구분해서 진행하세요.

## 강의별로 이어가기

| 강의 | 진행 방법 |
| --- | --- |
| 1. 도메인 설계 | 루트 `src`에서 엔티티 관계, DB 제약과 원장 저장을 작성합니다. |
| 2. 송금 | `materials/lecture-02`를 요청 DTO, 처리 Service, Controller 순으로 추가합니다. |
| 3. 대사 | `materials/lecture-03`의 내부 비교, 외부 자료 비교, 예약 실행을 차례로 추가합니다. |
| 4. 보안 | `materials/lecture-04`의 의존성과 보안 코드를 추가하고, 기존 송금 Controller와 Service를 연결합니다. |

파일을 추가하는 경로와 기존 메서드를 수정하는 위치는 [MATERIALS.md](MATERIALS.md)에 있습니다. 이미 작성한 파일은 그대로 두고 해당 절의 필드나 메서드만 추가합니다. IDE에서 `materials`를 별도의 source root로 등록하지 마세요.

## 로컬 DB 실습

DB 테스트는 H2 메모리 DB를 사용하는 `test` 프로필로 진행합니다. JPA 매핑, 상태 변경과 필요한 Service를 작성한 뒤 해당 통합 테스트를 추가하세요. 준비 상태 검사에는 DB가 필요하지 않습니다. H2 테스트와 실제 MySQL의 Lock 검증은 구분합니다.

MySQL을 사용할 때는 별도의 로컬 실습 DB `fintech_student`를 만들고, `.env.example`을 참고해 IDE의 환경변수에 접속값을 넣으세요. Spring Boot는 `.env` 파일을 자동으로 읽지 않습니다. 기본 설정은 `ddl-auto: validate`입니다. 직접 설계한 테이블과 매핑이 일치해야 앱을 시작할 수 있습니다. 네이밍 실험 후에는 기본 스네이크 케이스 설정으로 돌아옵니다.

서버와 테스트는 로컬 학습용입니다. 보안 의존성과 환경변수는 4강에서 추가합니다. 비밀값은 코드에 넣지 않고 환경변수로 전달하세요. 샘플의 이름과 연락처, 제공자 주소와 테스트 키는 실습용 값입니다.

시간은 UTC로 저장하고, 화면에 표시할 때 클라이언트 시간대로 바꿉니다. 금액은 `DECIMAL(19,2)`에 반올림 없이 표현할 수 있어야 합니다. 송금 실습은 KRW를 사용하며, 금액 검사에서 허용하는 소수 자릿수는 이 저장 정책을 따릅니다.
