# 강의별 실습 자료

## 제공 범위와 최초 적용 시점

| 처음 추가하거나 사용할 절 | 제공할 파일 | 제공하는 부분 | 학생이 구현할 부분 | 사용 전제 |
| --- | --- | --- | --- | --- |
| 1강 1-2. 고객 ID로 계좌 연결하기 | 루트 `domain`, `repository`, `materials/lecture-01/*.csv` | 필드 타입, 생성자와 getter, Repository 계약, 샘플 값 | 엔티티 관계, PK, FK, UNIQUE와 필수 제약 | Java 17 |
| 1강 2-1. 잔액 생성 과제 전 | `domain/StoredMoney.java` | 금액 검사 메서드 계약 | 표현 범위와 0 이상 검증. 양수 검증은 4-2에서 추가 | Java 17 |
| 1강 4-2. 전표를 상세 내역과 연결하기 | `service/LedgerPostingCommand.java`, `domain/LedgerPostingRules.java` | DTO와 메서드 계약 | 거래 금액의 양수 조건과 거래 유형별 분개 검증 | 앞 절의 관계 설계와 `StoredMoney` |
| 1강 6-1. 원장 저장 순서와 트랜잭션 | `service/LedgerPostingService.java` | 주입과 반환 타입, 전표번호 생성 | 검증, 저장 순서, 거래 출처와 트랜잭션 | 원장 엔티티와 금액 검증 |
| 2강 1-2~1-3. HTTP 입력과 Service 요청 DTO | `lecture-02/dependencies.gradle`, `lecture-02/01-request` | 입력 필드와 검증기 연결 선언 | HTTP 금액 제약과 정확한 표현 범위 | 1강 `StoredMoney` |
| 2강 2-1. 계좌를 조회하고 상태와 통화 검증하기 | `lecture-02/02-processing` | 추적 ID와 Clock 연결, Command와 Result, 실패 주입 계약, 샘플 데이터 생성 코드와 동시 요청 실행 코드 | 검증, 잔액, Lock, 요청 해시, 멱등성, 원장 연결 | 1강 원장 저장 |
| 2강 6-6. Controller에서 송금 Service 호출하기 | `lecture-02/03-http` | HTTP 경로와 응답 DTO | Service 연결과 오류 응답 | 처리 Service와 결과 타입 |
| 3강 2-2. 대사 실행과 상세 결과 선언 준비 | `lecture-03/01-internal` | 대사 결과와 일별 마감 필드 및 호출 계약, 고정 샘플 | 이 단계에서는 대사 엔티티와 결과 계산, 마감 처리는 2-5에 구현 | 아래 기존 파일 선언 추가와 1~2강 원장 구현 |
| 3강 2-5-1~2-5-3. 일별 마감 구현 | 2-2에서 추가한 마감 엔티티·Repository·Service와 기존 송금 파일 | 필드, 생성자, getter 및 메서드 계약 | 마감 매핑·검증 상태, 기초 등록과 날짜 전환, 송금 전 마감 준비 | 금액 및 잔액 검증과 계좌 Lock |
| 3강 3-1~3-2. 샘플 준비와 일별 잔액 비교 | 기존 `01-internal`의 테스트 지원 및 비교 Service | 고정 시각과 금융·마감 자료 | 날짜 범위 조회, 일별 잔액 비교와 실행 저장 | 2-5 마감 구현 완료 |
| 3강 4장 시작. 외부 대사 자료 준비하기 | `lecture-03/02-external` | JSON 13개와 파일 읽기 코드, 비교용 오더 샘플 | 범위 조회, 누락과 금액 비교, 입력 오류 처리 | 기관 코드와 Repository 추가 |
| 3강 5장. 대사 예약 실행 | `lecture-03/03-scheduling` | 두 스케줄러의 주입과 호출 테스트 예제, 비활성 설정 | 마감과 대사 cron, UTC 전날 계산, Service 호출 | 일별 마감과 내부 대사 Service |
| 4강 0장. 실습 준비 | `lecture-04/dependencies.gradle`, `lecture-04/01-security` | 설정 바인딩, DTO, 주입 및 테스트 환경 | 로그인, JWT 검증, 역할과 소유권, 마스킹, 감사 기록 | 3강까지 구현, 보안 의존성 추가 |
| 4강 8-4. 초기 입금부터 송금과 대사까지 확인하기 | `lecture-04/02-integration` | 초기 입금 함수와 테스트 틀 | 기초 잔액 등록, 입금, 로그인, 송금, 마감과 대사의 통합 검증 | 앞 강의의 잔액 변경과 시각 인자 원장 Service와 일별 마감 |

표의 `domain`과 `service`는 `src/main/java/com/sparta/fintech/ledger/` 아래 경로입니다. `lecture-02` 등의 경로는 `materials/` 아래를 뜻합니다. 각 새 파일의 전체 상대 경로는 문서 끝의 파일 목록에서 확인합니다.

표의 시점에 자료를 추가한 뒤 각 절에서 필요한 메서드를 구현합니다. 내부 대사 샘플은 2-5의 선행 구현을 마친 뒤 3-1-3에서 준비하고 3-2부터 실제 비교합니다. 외부 대사 샘플과 JSON은 4-6, 보안 샘플은 4강 7-1에서 사용합니다.

생성자 주입, 단순 필드 보관과 DTO 변환은 제공합니다. `@Transactional`, 비관적 `@Lock`, 역할용 `@PreAuthorize`와 업무 검증은 해당 절에서 직접 적용합니다. `TransferPolicy.NONE`은 2강에서 추가 보안 정책을 아직 연결하지 않은 호출 계약입니다. 4강의 고객 요청에는 보안 정책을 연결해야 합니다.

## 자료 추가 방법과 현재 단계의 실행 범위

각 단계 폴더 안의 `src/main`과 `src/test` 파일을 루트의 **같은 상대 경로**에 추가하세요. 파일명 충돌이 나면 덮어쓰지 말고 아래의 기존 파일 수정 목록을 확인합니다. 이미 작업한 `src` 폴더 전체를 교체하지 않습니다. 이후 강의의 Java 파일도 필요한 자료 묶음을 추가한 다음 같은 프로젝트에서 사용합니다.

| 시점 | 새 자료 | DB 없이 확인할 범위 | 앱 기동 또는 통합 확인의 선행 구현 |
| --- | --- | --- | --- |
| 1강 시작 | 루트 `src` | 컴파일 대상은 루트만. `PreparationSmokeTest` | 엔티티 매핑과 실습 DB, 원장 Service와 상태 변경 |
| 2강 1-2~1-3 | `lecture-02/01-request/src`와 `dependencies.gradle`의 web 의존성 | DTO 생성 및 `TransferRequestExerciseTest`. Controller를 추가하지 않음 | 입력 제약 및 `StoredMoney` |
| 2강 2-1 | `lecture-02/02-processing/src` | `TransferPreparationSmokeTest`, `ConcurrentRequestsTest`, 잔액 및 해시 단위테스트 | 아래 Repository 변경, 검증과 Lock, 원장 저장, 트랜잭션 |
| 2강 6-6 | `lecture-02/03-http/src` | DTO와 기존 단위테스트 | Controller 연결과 오류 응답 구현 |
| 3강 2-2 | `lecture-03/01-internal/src`와 아래 기존 파일 선언 확장 | 결과 객체 계산 과제 | 지금은 선언을 유지하고 2-5에서 마감 매핑과 Repository를 완성 |
| 3강 2-5-1~2-5-3 | 추가한 자료와 기존 잔액·원장·송금 파일 | 메서드 계약과 상태 단위 과제 | 마감 매핑과 상태 변경, 기준 등록, 날짜 전환과 트랜잭션 |
| 3강 3-1~3-2 | 기존 자료를 계속 사용 | 고정 시각과 샘플 입력 | 2-5 완료 후 샘플 적재, 기간 조회와 잔액 비교 |
| 3강 4장 시작 | 아래 오더 수정 후 `lecture-03/02-external/src` | `SettlementMaterialsSmokeTest`의 JSON 읽기 | 기관과 UTC 날짜 조회, 외부 대사와 정산 엔티티 |
| 3강 5장 | `lecture-03/03-scheduling/src` | `SchedulerExerciseTest`의 고정 Clock 호출 검사 | 두 예약의 활성화와 cron 설정, 마감 저장과 일별 내부 대사 |
| 4강 0장 | 보안 의존성 추가 후 `lecture-04/01-security/src` | `SecurityPreparationSmokeTest`는 선언 연결만 확인, 마스킹 단위테스트 | 보안 Bean과 Properties 값 검증, 사용자 매핑과 아래 변경 |
| 4강 8-4 | `lecture-04/02-integration/src` | DTO 준비 검사는 유지 | 초기 입금과 로그인, 송금 및 대사 구현 |

컴파일은 문법과 타입의 연결을 확인하는 단계입니다. Spring 컨텍스트를 사용하지 않는 `smokeTest`는 미완성 JPA 매핑을 검사하지 않습니다. `test`는 과제도 모두 실행하므로 추가한 과제가 남아 있으면 실패합니다. 필요한 시점에 테스트 틀의 `fail(...)`을 실제 준비, 호출 및 assertion으로 교체하고 앞서 통과한 테스트를 함께 유지하세요.

## 1강: 루트에서 작성하기

### 1-2. 고객 ID로 계좌 연결하기 / 2-1. 지금 출금할 수 있는 금액은 얼마일까?

`domain`의 필드와 생성자는 뒤 강의에서도 사용하는 호출 계약입니다. 이 필드를 엔티티로 매핑하면서 고객, 계좌, 잔액과 한도의 관계를 직접 설계하세요. 계좌번호와 고객번호의 중복, 같은 계좌의 두 잔액 행, 없는 부모 참조를 DB 테스트로 확인합니다. `BaseTimeEntity`와 UTC 감사 시각 연결은 제공됩니다. `DmAccountBalance.version`의 동시 변경 처리는 5-3에서 적용합니다.

`materials/lecture-01/accounts.csv`는 첫 시나리오의 고객 역할과 시작 금액입니다. JPA가 생성한 ID는 `save()`의 반환 객체에서 읽습니다. 계정코드는 `ledger-codes.csv`를 사용합니다. CSV는 자동 적재되지 않습니다.

### 2-1의 잔액 생성 전에: StoredMoney 검증 작성하기

`DmAccountBalance` 생성자는 `StoredMoney.nonNegative()`를 호출합니다. 잔액 객체 생성과 저장 과제보다 먼저 `isRepresentable()`, `exact()`, `nonNegative()`를 구현하세요. 금액은 `DECIMAL(19,2)`에 반올림 없이 저장할 수 있어야 합니다. `nonNegative()`에 null, 음수, `0.005` 또는 `100000000000000000`을 넣으면 `IllegalArgumentException`이 발생해야 합니다. `300.000`과 `0.0100`은 각각 300.00과 0.01로, 0은 0.00으로 반환합니다.

잔액의 금액 검증은 특강 #1 2-1에서 위 세 메서드로 작성합니다. 양수 거래 금액 검사인 `positive()`는 1강 4-2에서 추가합니다. 검증을 생략하거나 입력값을 그대로 반환해서 잔액 생성 과제를 통과시키지 않습니다.

### 4-2. 전표를 상세 내역과 연결하기 / 6-1. 원장 저장 순서와 트랜잭션

`LedgerPostingCommand`는 전체 필드를 받는 11인자 생성자와 추적 ID 일부를 생략하는 9인자 생성자를 제공합니다. 9인자 형태에서 전표 TID는 `tid + "-JOURNAL"`, 회계원장 TID는 `tid + "-LEDGER"`입니다. 같은 TID를 재사용하면 같은 추적 ID가 만들어집니다. 초기 입금 함수 `FinancialTestData.openingDeposit()`은 고정 TID를 사용하므로 테스트 DB를 초기화한 뒤 한 번 호출합니다.

`AccountPosting`은 5인자이며 마지막 `balanceAfter`에는 거래 직후 **원장 금액**을 넣습니다. `JournalPosting`은 계정코드와 고객 계좌 ID를 별도 필드로 받습니다. 현금 계정 `100101`의 고객 계좌는 null이고 고객예수금은 `210101`입니다.

`LcLedgerAccount.normalBalanceType`은 계정과목의 잔액이 보통 남는 쪽인 차변 또는 대변을 나타냅니다.

2-1에서 작성한 `StoredMoney`의 범위 검사를 유지하고, 4-2에서는 거래 금액이 양수인지 확인하는 `positive()`를 추가합니다. 2강에서는 HTTP와 Service 입구에서 같은 검사 메서드를 재사용합니다.

`LedgerPostingRules`에는 수수료 없는 송금, 현금 입금, 현금 출금의 두 줄 분개 규칙을 작성하세요. 차대 합계 외에 각 분개와 계좌거래의 요청 금액 및 고객 계좌를 확인합니다. `LedgerPostingService.post()`의 저장 로직과 트랜잭션을 작성한 뒤, 2강 6-3에서 `postTransfer()`와 `DmTransaction.markTransferApi()`를 연결합니다. 원장을 직접 적재하는 실습과 API 송금의 출처는 각각 `LEDGER_EXERCISE`와 `TRANSFER_API`입니다.

## 2강: 기존 파일에 추가할 부분

### 1-2~1-3. 의존성과 요청 DTO 추가하기

`materials/lecture-02/dependencies.gradle`의 Web 의존성 한 줄을 루트 `build.gradle`의 기존 `dependencies` 안에 추가합니다. 기존 JPA와 Validation 의존성은 유지하고, `dependencies` 블록을 중첩하지 않습니다. 이어서 `01-request/src`를 추가합니다. Controller는 `03-http`를 가져오는 6-6에서 추가합니다.

### 2-2. 1회 송금 한도 검증 / 4-2. 잔액 Lock 조회 / 4-3. 두 계좌의 Lock 획득 순서

다음 선언을 기존 Repository 안에 추가합니다. `java.util.List`, `java.util.Optional`과 해당 도메인 타입을 import하세요.

```java
// DmAccountLimitRepository에 추가
List<DmAccountLimit> findByAccountAccountIdAndLimitTypeAndActiveTrue(Long accountId, LimitType limitType);

// DmAccountBalanceRepository에 추가할 유효한 미완성 선언
// TODO [특강 2 / 4-2] default 본문을 없애고 단건 JPQL과 비관적 Lock을 적용하세요.
default Optional<DmAccountBalance> findByAccountIdForUpdate(Long accountId) {
    throw new UnsupportedOperationException("TODO [특강 2 / 4-2] 계좌별 잔액 Lock 조회를 구현하세요.");
}
```

기존 `findByAccountAccountId()`는 유지합니다. `TransferProcessor.lockBalances()`에서 계좌 ID 순으로 단건 Lock을 얻도록 작성합니다. `process()`의 격리 수준과 트랜잭션, `TransferService`의 바깥 트랜잭션 제한은 학생 과제입니다. 요청 키의 Lock은 송금 DB 커밋 이후 해제되어야 합니다.

### 5-2-1. 송금 오더 필드 설계하기 / 6-3. TID, GID, OID를 원장 처리용 DTO에 전달하기

`DmTransferOrder`는 처음 추가할 때부터 `Instant completedAt`, getter, 3인자와 4인자 `complete()`를 제공합니다. 3인자 형태는 현재 시각을 넣어 4인자 형태에 위임합니다. 완료 정보와 상태를 기록하는 본문은 직접 작성하고 null 완료 시각은 상태·PK 링크·전표번호 변경 전에 거절합니다. 실제 송금에서는 주입받은 `Clock.instant()`를 명시적으로 전달하세요.

`FinancialIdGenerator`, `GeneratedTransferTraceIds`와 UTC Clock은 준비 코드입니다. 반환 필드는 `tid`, `gid`, `journalTid`, `accountingLedgerTid`입니다. 학생은 이 값을 송금 오더와 원장 DTO에 연결합니다.

`TransferProcessor.toResult()`는 완료된 오더와 잔액을 내부 결과 DTO인 `TransferResult`로 변환합니다. `TransferHttpResponse`는 클라이언트에 공개할 `transferOrderId`, `tid`, `status`, `withdrawalAccountBalance` 네 필드만 담는 Response DTO입니다. Controller는 이 객체를 `ResponseEntity`의 본문에 넣어 반환합니다. 다른 고객의 입금 잔액은 고객 응답에 추가하지 않습니다. 대사 테스트에서는 Repository로 내부 추적 ID를 조회하세요.

### 5-4와 6-5. 기존 오더 처리의 메서드 인자 연결하기

`handleExistingOrder(TransferCommand command, String requestHash, DmTransferOrder order)`의 세 인자 선언을 유지합니다. 6-5의 기존 오더 처리 경로에서도 `handleExistingOrder(command, requestHash, existing)`으로 호출하세요. 5-4의 메서드 선언과 6-5의 호출에서 세 인자의 순서와 타입을 동일하게 맞춥니다.

6-3의 DTO 생성은 `TransferProcessor.toLedgerPostingCommand()`, 거래 출처 구분은 `LedgerPostingService.postTransfer()`와 `DmTransaction.markTransferApi()`를 수정합니다. 6-3-1에서는 원장 DTO를 구성하고, 6-3-2에서는 원장 실습과 API 송금의 출처를 구분합니다.

6-4-1의 오류 응답은 독립 파일 `transfer.web.ErrorResponse`를 사용합니다. `TransferExceptionHandler` 안에 같은 record를 중복 선언하지 않습니다. HTTP 파일은 6-6에 추가하고, 4강 6-1에서 공통 패키지로 옮깁니다.

### 7-1. 테스트 데이터와 실행 환경 준비하기

`TransferIntegrationExerciseTest`는 `TransferTestSupport`를 상속합니다. `test` 프로필, 공통 Repository 주입과 매 테스트 전 H2 초기화가 적용됩니다. 필요한 `TransferService`, 원장 조회 Repository와 실패 주입 설정은 테스트에 추가합니다. 테스트 클래스 전체에 트랜잭션을 걸지 말고 Service 커밋 이후 다시 조회하세요.

강의 7장의 금액은 다음 호출로 준비합니다. 문자열 인자는 출금 잔액, 입금 잔액, 1회 송금 한도 순서입니다.

```java
var sample = createTransferAccounts("1000.00", "100.00", "1000.00");
```

300원 송금 후 출금 잔액은 700원, 입금 잔액은 400원입니다. `sample.fromId()`와 `toId()`는 DB 계좌 ID, `fromNo()`와 `toNo()`는 계좌번호입니다. `TransferTestSupport.Accounts`의 반환 필드를 사용해 송금 요청을 구성하세요. 테스트마다 `createTransferAccounts()`를 한 번 호출합니다.

인자 없는 `createTransferAccounts()`도 유지합니다. 기본값은 출금 1,000,000원, 입금 100,000원, 1회 한도 1,000,000원입니다. 이 경우 300,000원 송금 후 700,000원과 400,000원입니다. 두 형태 모두 초기 원장을 만들지 않으므로 3강 정상 대사 샘플로 쓰지 않습니다.

### 7-1-3과 7-5. 요청별 결과와 예외를 모아 동시성 확인하기

7-1-3과 7-5에서는 제공된 `ConcurrentRequests.run(int, IntFunction)`으로 요청을 동시에 실행합니다. 요청 인덱스는 0부터 시작하며 `Attempt`의 `index()`, `result()`, `error()`, `succeeded()`로 각 시도의 결과를 읽습니다. 반환 순서는 인덱스 순서입니다. `ConcurrentRequests`, `TransferCommand`와 `BigDecimal`을 import하고, 준비한 `sample`과 주입한 `transferService`로 다음 호출을 구성합니다.

```java
var attempts = ConcurrentRequests.run(5, index -> transferService.transfer(
    new TransferCommand("different-" + index, sample.fromNo(), sample.toNo(),
        new BigDecimal("300"), "KRW", "동시 송금")));
```

서로 다른 키는 위처럼 인덱스를 붙입니다. 같은 키 재시도는 별도 테스트에서 새 샘플을 만들고 모든 요청에 `"same-key"`와 같은 금액 및 설명을 전달합니다. 성공 건수, 예외 종류, 총 잔액과 저장 행 수는 7-5의 과제로 직접 검증하세요.

업무 예외는 `Attempt.error()`에 모으므로 한 요청의 거절로 나머지 결과 수집을 중단하지 않습니다. 기존 `run(int, Callable)`은 성공값 목록을 반환하며, 모든 시도가 끝난 뒤 요청 예외가 있으면 첫 예외를 던지고 나머지는 suppressed 예외로 보관합니다. 성공과 실패를 함께 비교하는 과제에서는 인덱스가 있는 형태를 사용합니다.

준비와 결과 수집의 제한 시간은 각각 20초입니다. 시간 초과나 실행 오류가 발생하면 남은 작업을 취소하고 종료를 최대 20초 기다립니다. 호출 스레드의 인터럽트는 복구해 전달합니다. 종료 실패도 예외로 알리며, 기존 오류가 있으면 suppressed 예외에 추가합니다. 미종료 작업이 있으면 `TestDatabaseReset`이 이후 DB 초기화를 차단합니다. 원인을 해결하고 남은 작업을 종료한 뒤 다음 DB 테스트를 진행하세요.

`TransferFailureHook`을 테스트 Bean이나 Spy로 교체해 네 실패 지점을 확인합니다. `AFTER_LEDGER_POSTING`도 포함합니다. `TestDatabaseReset`은 H2 실습 DB만 초기화하며 FK 검사는 삭제 중에만 해제하고 finally에서 복구합니다. MySQL 검증은 [README의 로컬 DB 실습](README.md)의 별도 준비를 따릅니다.

## 3강: 앞 강의 파일을 유지하며 확장하기

### 2-2. 내부 대사 자료와 기존 파일의 선언 준비하기

2-2에서 `01-internal/src`를 추가하고 실행 엔티티와 결과 엔티티를 차례로 구현합니다. 마감 엔티티와 Repository 및 Service는 아래 2-5에서 완성하고 실제 내부 샘플은 3-1-3에서 호출합니다. `DailyClosingStatus`, `SmAccountDailyClosing`, `SmAccountDailyClosingRepository`, `DailyClosingService`도 이 자료에 포함합니다. 일별 마감의 필드, 생성자와 getter 및 호출 계약은 제공하며, JPA 매핑과 계좌·날짜 유일 제약, 금액 불변 제약, 실제 마감 보관과 검증 상태 처리 및 서비스 본문은 학생이 작성합니다.

자료를 추가하는 시점과 각 메서드를 구현하는 시점을 구분하세요. 2-2에서는 뒤 절에서 사용할 선언을 준비하고, 2-5에서 마감 기능을 완성한 뒤 3-1의 샘플 준비와 3-2의 일별 비교로 이어갑니다. `baseDate`는 결과 라벨과 실제 날짜 조회 조건에 함께 사용합니다.

새 자료를 추가한 뒤 아래 기존 파일을 순서대로 확장하세요. 표의 경로는 루트 `src/main/java/com/sparta/fintech/ledger/` 아래입니다. 1강 루트와 2강 `materials` 원본은 미리 덮어쓰지 않고, 3강까지 진행한 루트의 해당 파일에만 필요한 계약을 추가합니다.

| 기존 파일 | 추가할 내용과 계약 |
| --- | --- |
| `domain/DmAccountBalance.java` | `LocalDate activeBusinessDate`, nullable DB 컬럼 `active_business_date`, getter, `initializeBusinessDate(LocalDate)`, `advanceBusinessDate(LocalDate)` |
| `domain/DiAccountTransaction.java`, `domain/DmTransaction.java`, `domain/LmJournalEntry.java` | 아래 기간 조회 인덱스를 기존 `@Table`에 추가 |
| `domain/DmTransferOrder.java` | 완료 상태와 완료 시각 인덱스를 기존 매핑에 추가 |
| `repository/DmAccountBalanceRepository.java` | 개설일 경계로 대상 계좌 ID를 정렬 조회하는 `findAccountIdsOpenedBefore(Instant endAt)` |
| `repository/DiAccountTransactionRepository.java` | 기초 잔액 등록 전 거래 존재 검사, 계좌·방향별 완료 거래 기간 합계, 기간 내 미완료 원장 거래 조회 |
| `repository/DmTransactionRepository.java` | 완료 시각 범위의 거래 조회, 요청 시각 범위의 미완료 거래 조회 |
| `repository/LmJournalEntryRepository.java` | 완료 거래에 연결된 대상일 전표 조회, 전표 게시 시각 범위의 미완료 거래 조회 |
| `repository/DmTransferOrderRepository.java` | 완료 시각 범위의 송금 오더 조회. 외부 기관 조건은 4장 시작에 추가 |
| `service/LedgerPostingService.java` | 잔액 Repository 주입, 명시 시각을 받는 `post()`와 `postTransfer()` 확장, 현재 업무 날짜와 원장 처리일 검증 |
| `transfer/service/TransferProcessor.java` | `DailyClosingService` 주입과 잠금 후 시각 확정, 잔액 변경 전 마감 준비, 원장 및 오더에 같은 시각 전달 |

`activeBusinessDate`는 해당 계좌 잔액에 반영 중인 UTC 날짜입니다. 처음에는 null일 수 있습니다. `initializeBusinessDate()`는 null이 아닌 날짜만 받아 최초 값 또는 같은 날짜를 허용하고 다른 날짜로 초기화하려 하면 거절하세요. `advanceBusinessDate()`는 이미 초기화된 계좌에서 날짜를 뒤로 돌리지 못하게 작성합니다. 기존 `ledgerBalance`, `availableBalance`의 저장 범위 검사와 지급보류액 보존을 유지합니다. MySQL의 `validate` 설정은 스키마를 수정하지 않으므로 `active_business_date` 컬럼과 일별 마감 테이블의 DDL도 직접 적용합니다. 기존 거래가 있는 실습 DB에는 확인한 이전 마감 기준이 필요합니다. 현재 잔액을 과거 기초로 자동 등록해 오류를 숨기지 않습니다.

### 2-5. 하루의 마감 원장 금액을 따로 보관하기

마감은 2-5에서 구현하고 3-2에서 비교합니다. 두 기능을 한 절의 과제로 묶거나, 선언을 추가한 즉시 DB 샘플을 실행하지 않습니다.

### 2-5-1. 마감 금액과 검증 상태를 저장할 엔티티 만들기

`DailyClosingStatus`와 `SmAccountDailyClosing`을 사용해 필수값과 JPA 관계, 계좌·날짜 UNIQUE 및 조회 인덱스를 작성합니다. 마감 금액과 보관 시각 및 계좌·기준일·기초 표시는 생성 후 변경하지 않도록 매핑하세요. `openingBaseline()`, `markVerified()`, `markUnverified()`의 본문을 작성하고 정상 마감의 금액을 변경하는 setter는 추가하지 않습니다. 기초 등록과 대사 완료의 VERIFIED 출처를 구분합니다.

### 2-5-2. 최초 기초 금액과 날짜가 바뀌기 전의 마감 처리

2-2에서 준비한 기존 Repository 계약 중 다음 두 메서드는 마감 Service보다 먼저 완성합니다. `findAccountIdsOpenedBefore()`는 `openedAt < endAt`의 계좌 ID 오름차순 JPQL로 작성하고, `existsByAccountAccountId()`는 파생 조회입니다. 3-1의 기간 조회 목록에 같은 선언을 다시 추가하지 않습니다.

```java
// DmAccountBalanceRepository
List<Long> findAccountIdsOpenedBefore(Instant endAt);
// DiAccountTransactionRepository
boolean existsByAccountAccountId(Long accountId);
```

`DailyClosingService`의 공개 계약은 다음과 같습니다. 본문은 직접 작성합니다.

```java
SmAccountDailyClosing initializeOpeningBalance(Long accountId, LocalDate openingDate);
void prepareForPosting(DmAccountBalance balance, Instant postedAt);
int capture(LocalDate closedDate);
```

`initializeOpeningBalance()`는 거래와 기존 마감 기록이 없는 신규 계좌를 잠근 뒤 실제 초기 원장 금액을 최초 거래일 전날의 기준으로 등록합니다. `openingDate`는 계좌 개설일보다 빠르거나 현재 UTC 날짜보다 미래일 수 없습니다. 이 명시적 기초 잔액 등록은 첫 대사의 출발점입니다. 거래 이력이 있는 계좌를 0원으로 자동 초기화하거나 현재 잔액을 임의 과거일의 검증 완료 마감으로 등록하지 않습니다.

`prepareForPosting()`은 송금 트랜잭션이 계좌 잔액 행을 잠근 상태에서 잔액 변경 전에 호출합니다. 새 날짜 첫 거래라면 이전 날짜의 실제 원장 잔액을 저장하고 업무 날짜를 진행합니다. `capture()`는 별도 마감 배치에서 종료된 UTC 날짜까지 거래가 없었던 계좌도 처리합니다. 이미 저장한 마감 금액은 덮어쓰지 않습니다. 업무 날짜가 이미 지나갔는데 해당 날짜의 마감이 없으면 현재 잔액으로 복원하지 않고 거절하세요.

`SmAccountDailyClosing.openingBaseline()`과 상태 변경 메서드 및 매핑을 먼저 작성합니다. `SmAccountDailyClosingRepository.findByBaseDateForUpdate()`는 `ForUpdate`라는 이름만으로 JPQL이나 잠금을 적용하지 않습니다. 제공한 `@Query`, `@Lock` TODO가 남아 있으면 Spring Repository 생성 시 오류가 발생할 수 있습니다. 계좌 ID 순서의 조회·쓰기 잠금을 구현한 다음 DB 샘플과 통합 테스트를 실행하세요.

마감 서비스의 초기 기준 등록과 `capture()`는 `READ_COMMITTED` 트랜잭션과 계좌 ID 순서의 잔액 잠금을 사용하고, `prepareForPosting()`은 기존 송금 트랜잭션에 참여하는 `MANDATORY`로 작성합니다. 대사도 `READ_COMMITTED`에서 전날 마감 행을 먼저, 대상일 마감 행을 다음에 계좌 ID 순서로 잠급니다. 이후 날짜의 검증 상태 무효화에도 제공한 Lock 조회 계약을 사용하세요.

### 2-5-3. 기존 송금 흐름에서 잔액을 바꾸기 전에 호출하기

기존 `LedgerPostingService`에는 `DmAccountBalanceRepository balanceRepository` 필드를 추가하고 생성자의 `DmAccountRepository accountRepository` 다음에 같은 타입의 인자를 넣어 보관합니다. 기존 단일 Command 메서드를 유지하면서 다음 시각 인자 계약을 추가합니다.

```java
public LedgerPostingResult post(LedgerPostingCommand command, Instant postedAt);
public LedgerPostingResult postTransfer(LedgerPostingCommand command, Instant postedAt);
private LedgerPostingResult post(LedgerPostingCommand command, boolean apiTransfer, Instant postedAt);
```

기존 private 두 인자 구현은 세 인자 구현으로 확장하고, 단일 Command 공개 메서드는 `clock.instant()`를 전달해 같은 검증과 저장 순서를 재사용합니다. 새 시각 인자 공개 메서드에도 기존 원장 저장의 `@Transactional` 경계를 유지하여 잔액 잠금과 원장 저장이 하나의 호출 트랜잭션에서 처리되도록 합니다. `postedAt`이 null 또는 현재 시각보다 미래이면 거절합니다. 계좌거래의 계좌 ID를 중복 없이 정렬해 기존 잔액 Lock을 얻고, 이미 업무 날짜가 설정된 계좌는 그 날짜와 원장 처리일이 같아야 합니다. 거래 생성·계좌거래·전표 게시·거래 완료에 같은 시각을 전달하세요. `DmTransaction`의 시각 인자 생성자와 `complete(Instant)`, `DiAccountTransaction`의 시각을 받는 10인자 생성자, `LmJournalEntry.post(Instant)`는 이미 루트에 있으므로 중복 선언하지 않습니다. 요청 금액의 표현 범위와 유형별 분개 금액 및 고객 계좌 검증을 유지합니다.

기존 `TransferProcessor`에는 `DailyClosingService` import와 `private final DailyClosingService dailyClosingService`를 추가합니다. 생성자에서는 `LedgerPostingService ledgerPostingService` 다음, `FinancialIdGenerator idGenerator` 앞에 새 인자를 넣고 필드에 할당합니다. 생성자를 직접 호출하는 테스트도 같은 순서로 수정하세요.

`process(command, policy)`의 새 송금 경로는 다음 순서를 연결합니다. 이미 완료된 같은 요청의 재시도는 기존 결과만 반환하며 새 날짜 마감 준비나 원장 쓰기를 반복하지 않습니다.

1. 기존 검증 뒤 계좌 ID 순서로 두 잔액 Lock을 얻습니다.
2. Lock을 모두 얻은 뒤 `Instant postedAt = clock.instant()`를 한 번만 확보하고 UTC 업무 날짜를 계산합니다.
3. 두 잔액에 `dailyClosingService.prepareForPosting(balance, postedAt)`를 호출합니다.
4. 같은 업무 날짜로 한도와 정책을 검증하고 기존 오더 저장·출금·입금·실패 Hook을 수행합니다.
5. `ledgerPostingService.postTransfer(commandDto, postedAt)`를 호출하고 같은 `postedAt`으로 오더를 완료합니다.

마감 보관과 잔액 변경 및 원장 저장은 같은 송금 트랜잭션에서 롤백되어야 합니다. `DailyClosingService`를 주입하지 않은 2강 초기 자료를 미리 수정하지 말고, 3강 자료를 추가한 시점에 이 연결을 적용합니다.

### 3-1-1. Repository 및 결과 DTO 계약 추가하기

기존 Repository에 다음 계약을 추가합니다. 2-5-2에서 작성한 마감용 두 조회는 그대로 유지합니다. `List`, `Optional`, `Instant`, `BigDecimal`, 해당 도메인 타입을 import하세요. 단순 파생 조회는 선언으로 추가하고, 기간 조건이 있는 메서드는 미완성 `default` 본문에서 `UnsupportedOperationException`을 발생시키다가 구현 시 본문을 없애고 JPQL과 `@Param`을 작성합니다. `findByAccountIdForUpdate()`의 기존 단건 비관적 Lock은 유지합니다.

```java
// DiAccountTransactionRepository
List<DiAccountTransaction> findByAccountAccountId(Long accountId);
List<DiAccountTransaction> findByTransactionTransactionId(Long transactionId);
BigDecimal sumCompletedAmountInPeriod(Long accountId, DebitCreditType direction,
    Instant startAt, Instant endAt);
List<DmTransaction> findIncompleteTransactionsInPeriod(Instant startAt, Instant endAt);
List<DmTransaction> findCompletedTransactionsWithoutCompletionTimeInPeriod(
    Instant startAt, Instant endAt);

// DmTransactionRepository
List<DmTransaction> findCompletedInPeriod(Instant startAt, Instant endAt);
List<DmTransaction> findIncompleteRequestedInPeriod(Instant startAt, Instant endAt);
List<DmTransaction> findCompletedWithoutCompletionTimeRequestedInPeriod(
    Instant startAt, Instant endAt);

// LmJournalEntryRepository
Optional<LmJournalEntry> findByTransactionTransactionId(Long transactionId);
List<LmJournalEntry> findCompletedInPeriod(Instant startAt, Instant endAt);
List<DmTransaction> findIncompleteTransactionsInPeriod(Instant startAt, Instant endAt);
List<DmTransaction> findCompletedTransactionsWithoutCompletionTimeInPeriod(
    Instant startAt, Instant endAt);

// LiJournalEntryLineRepository
List<LiJournalEntryLine> findByJournalEntryJournalEntryId(Long journalEntryId);

// LiAccountingLedgerRepository
boolean existsByTransactionTransactionId(Long transactionId);
List<LiAccountingLedger> findByJournalEntryJournalEntryId(Long journalEntryId);

// DmTransferOrderRepository
Optional<DmTransferOrder> findByTid(String tid);
List<DmTransferOrder> findByTransferOrderStatus(TransferOrderStatus transferOrderStatus);
List<DmTransferOrder> findCompletedInPeriod(Instant startAt, Instant endAt);
```

기간 경계는 `startAt` 이상, `endAt` 미만입니다. 계좌거래 합계는 거래 상태 `COMPLETED`와 `occurredAt`을 기준으로 계좌 및 차대 방향을 제한합니다. 미완료 계좌거래 조회는 같은 `occurredAt` 범위의 미완료 거래를 중복 없이 반환합니다. 거래와 송금 오더의 완료 조회는 `completedAt`, 미완료 요청 조회는 `requestedAt`을 사용합니다. 완료 전표 조회는 연결 거래의 완료 상태와 `completedAt`, 미완료 전표 조회는 전표의 `postedAt`을 사용합니다. 범위와 상태의 조건을 생략해 `findAll()`로 대체하지 않습니다.

기간 조회를 지원하는 인덱스도 기존 엔티티의 `@Table(indexes = ...)`과 MySQL DDL에 추가하세요. 인덱스 이름은 직접 정하되 아래 컬럼 순서를 유지합니다. 표는 기본 스네이크 케이스 컬럼 기준이므로 학생이 명시한 `@Column`과 `@JoinColumn` 이름이 다르면 실제 DB 이름에 맞춥니다. 기존 인덱스와 관계 제약을 삭제하지 않습니다.

| 대상 엔티티 | 추가할 컬럼 조합 |
| --- | --- |
| `DiAccountTransaction` | `(account_id, occurred_at)`, `(occurred_at, transaction_id)` |
| `DmTransaction` | `(transaction_status, completed_at)`, `(requested_at, transaction_status)` |
| `LmJournalEntry` | `(posted_at)` |
| `DmTransferOrder` | `(transfer_order_status, completed_at)` |
| `SmAccountDailyClosing` | UNIQUE `(account_id, base_date)`, 조회 인덱스 `(base_date)` |

H2의 `create-drop`은 완성한 엔티티 선언을 적용하지만, MySQL의 `validate`는 인덱스나 유일 제약을 새로 만들지 않습니다. 로컬 실습 DDL과 JPA 선언을 함께 맞추세요.

예를 들어 미완성 기간 조회를 처음 선언할 때는 다음 형태를 사용합니다. 다른 기간 조회도 위 반환 타입과 인자를 그대로 유지하고 같은 방식으로 추가하세요.

```java
// DmTransactionRepository
// TODO [특강 3 / 3-1-1] completedAt의 반열린 기간과 완료 상태를 조회하세요.
default List<DmTransaction> findCompletedInPeriod(Instant startAt, Instant endAt) {
    throw new UnsupportedOperationException("TODO [특강 3 / 3-1-1] 기간 조회를 구현하세요.");
}
```

### 3-1-3. 날짜와 마감 기록을 포함한 테스트 샘플 준비하기

3-1의 `ReconciliationIntegrationExerciseTest`는 `InternalReconciliationTestSupport`가 제공하는 주입 필드, 초기화와 `createSampleData()`를 사용합니다. 샘플은 기준일 `2026-09-15`, 원장 처리 시각 UTC 12:00, 실행 Clock `2026-09-16 02:00 UTC`를 사용합니다. 초기 입금 1,000원과 송금 300원, 계좌 `330-001`과 `330-002`, 대상일 마감 700원과 300원을 준비합니다. 최초 기준 잔액과 대상일 실제 마감도 고정 자료로 준비하고 비교 알고리즘이나 송금 Service는 호출하지 않습니다. `SmAccountDailyClosing.openingBaseline()`과 `DmAccountBalance`의 업무 날짜 메서드, JPA 매핑과 Repository를 먼저 작성해야 샘플을 적재할 수 있습니다. 송금 오더와 외부 기관은 만들지 않는 내부 샘플이며 2강의 잔액만 있는 샘플과 구분하세요.

### 3-2. 일별 마감 원장 금액과 당일 입출금 내역 대조하기

일별 잔액 대사에서는 **전날 VERIFIED 마감 잔액 + 대상일 완료 입금 − 대상일 완료 출금**을 대상일에 따로 저장한 실제 원장 마감 금액과 비교합니다. 입출금만 재합산해 계산한 기대값을 실제 마감 금액으로 저장하지 않습니다. `baseDate`는 UTC 대상 거래일입니다.

`CAPTURED`는 실제 잔액을 저장했지만 아직 대사하지 않은 상태이며, `VERIFIED`는 대상일 전체 내부 대사의 불일치가 0건인 상태입니다. 대상일과 전날의 마감 자료가 없거나 전날 상태가 검증 완료가 아니면 대사 실행을 거절합니다. 불일치가 발견되면 해당 날짜와 이후 날짜의 검증 완료 상태를 무효화합니다. 마감 금액과 기존 대사 결과는 보존하고 재실행마다 새 실행 ID를 남깁니다.

3-2에서는 잔액 비교만 연결해 두 계좌 결과를 검사할 수 있습니다. 거래·전표·원장 규칙을 추가하면서 같은 `run()`을 확장하되, 일부 규칙만 작성한 상태를 전체 검증 완료로 표시하지 않습니다. 완성된 `run()`은 모든 내부 검사 후 불일치 0건일 때만 해당일 마감을 `VERIFIED`로 표시하세요. 테스트 개수나 고정 결과 건수 대신 날짜 범위, 계좌별 기대·실제 금액, 상태와 오류 사유를 확인합니다.

고정 마감 샘플의 현재 잔액을 나중에 바꾸어도 이미 저장한 과거 마감 금액은 바뀌지 않습니다. 잔액 불일치 사례는 마감 저장 전에 실제 잔액을 훼손한 별도 자료로 만들고, 거래·전표·회계원장 훼손 사례는 저장된 마감 금액을 보존한 상태에서 비교합니다. 이미 저장한 마감 금액을 999원으로 훼손하는 사례는 테스트 DB에만 `JdbcTemplate` 직접 SQL을 적용하는 오류 주입입니다. 이를 위해 엔티티 setter나 정상 Service의 마감 UPDATE 기능을 추가하거나 불변 매핑을 약화하지 않습니다. `ReconciliationIntegrationExerciseTest`의 일별 마감 과제에서는 날짜 전환, 거래 없는 날, 기초 잔액 누락과 재실행을 직접 구현해 확인하세요. 실패 송금 롤백도 앞 강의 실패 Hook 테스트에 마감 기록과 업무 날짜 검증을 추가합니다. `InternalReconciliationTestSupport.practiceClock`의 타입인 `MutableBusinessClock`은 고정 시각을 바꾸는 테스트 준비 코드입니다. `practiceClock.set(instant)`로 시각을 변경하고 각 테스트 전에는 기본 실행 시각으로 복구합니다. 서비스의 실제 날짜 계산은 공통 UTC Clock을 사용하고 테스트 코드의 시각 전환으로 다음 날 마감과 대사를 확인합니다.

### 3-5. 전표 상세와 회계원장 연결을 비교하기

`ReconciliationService.hasMatchingJournalAmount()`의 3-4 검증에는 전표 `POSTED` 상태를 포함합니다. 금액이나 날짜가 맞아도 `DRAFT` 또는 `CANCELLED`이면 정상으로 판정하지 않습니다.

`ReconciliationService.hasMatchingLedgerLines()`는 3-5에서 처음 구현합니다. 요청 금액, 전표 상세와 원장 행의 연결, 실제 고객 계좌를 확인해야 이 절의 정상 비교를 실행할 수 있습니다. 6-3은 이 메서드를 처음 작성하는 시점이 아니라 저장 후 금액이나 고객 계좌가 바뀐 추가 오류 사례를 검사하는 절입니다.

### 3-6. 회계원장의 차변과 대변 비교하기 / 6-3. 기본 규칙이 놓치는 오류도 테스트하기

`materials/lecture-03/01-internal/corruption-cases.json`은 DB를 변경할 대상과 기대 상태를 구분한 테스트 자료입니다. 자동으로 DB를 수정하지 않습니다. 각 사례는 정상 샘플을 새로 적재한 상태에서 시작하고, `T-TRANSFER`의 행만 수정합니다.

| 변경 대상 | 거래와 전표 | 전표와 회계원장 | 회계원장 차대 합계 |
| --- | --- | --- | --- |
| 원장 양쪽 금액만 400원 | NORMAL | MISMATCH | NORMAL |
| 전표 상세와 원장 양쪽 금액을 모두 400원 | MISMATCH | MISMATCH | NORMAL |

고객예수금 원장의 고객 계좌만 다른 정상 계좌로 바꾼 사례도 추가하세요. 단순히 금액이 같다는 이유로 정상 처리해서는 안 됩니다. 비교 결과를 저장하면서 원본 금융 기록을 수정하지 않습니다.

### 3-7-1. 완료 시각과 API 송금 오더의 상태·참조 검사

정상 완료 거래는 기존 `completedAt`의 UTC 기간 조회를 유지합니다. 그런데 상태가 `COMPLETED`인데 완료 시각이 null인 잘못된 기록은 그 조회에서 빠집니다. 3-7-1에서는 위 세 개의 완료 시각 누락 조회에 상태 `COMPLETED`와 `completedAt IS NULL`을 함께 적용하고, 요청 `requestedAt`, 연결 계좌거래 `occurredAt`, 연결 전표 `postedAt`이 대상일에 속한 후보를 합칩니다. 같은 실행에서 거래 ID로 중복을 제거하고 `COMPLETED_TRANSACTION_MISSING_COMPLETION_TIME`의 불일치를 기록하세요. 다른 시각으로 완료 시각을 채우거나 거래를 자동 정상 처리하지 않습니다. 근거가 여러 날짜에 걸치면 각 해당 날짜의 대사에서 오류 후보가 됩니다. 테스트는 날짜별로 새로운 DB 상태와 해당 전일·당일 마감 자료를 준비하여 앞 날짜의 불일치가 다음 날짜 선행 조건을 바꾸지 않도록 나눕니다.

`hasMatchingCompletedTransferOrder(DmTransferOrder order, DmTransaction transaction)`는 기존 식별값·금액·통화·전표 연결 검증에 두 상태의 완료 여부와 두 완료 시각의 null 및 정확한 `Instant.equals()` 비교를 추가하는 과제입니다. 거래→오더와 완료 오더→거래 양방향 검사에서 같은 계약을 사용합니다. 순방향에서 검사한 오더 ID는 역방향 결과를 중복 추가하지 않도록 관리합니다. 같은 UTC 날짜에 있다는 조건만으로 두 완료 시각이 같다고 판단하지 않습니다.

정상 저장 경로의 `DmTransaction.complete(Instant)`와 `DmTransferOrder.complete(..., Instant)`도 null 시각을 상태·완료 시각·거래 및 전표 링크·전표번호 변경 전에 거절해야 합니다. 이 입력 검증과 직접 SQL로 훼손한 과거 데이터를 발견하는 대사는 별도 과제로 확인합니다. 금액·원장 관계가 맞더라도 전표 상태가 `POSTED`가 아니면 3-4의 전표 비교는 불일치여야 합니다.

### 4장 시작. 외부 대사 자료 준비하기 / 4-5. Service에서 자료를 비교하고 결과 저장하기

4장을 시작할 때 외부 대사 자료를 준비합니다. `02-external/src`를 추가하기 전에 기존 `DmTransferOrder`에 다음을 추가합니다.

- `String externalInstitutionCode` 필드와 `getExternalInstitutionCode()`를 추가합니다. DB 길이는 30, 생성 후 변경하지 않는 필드입니다.
- 기존 10인자 생성자를 유지하고 마지막에 `String externalInstitutionCode`를 받는 11인자 생성자를 추가합니다. 기존 생성자는 null 기관을 전달해 위임하도록 정리하고, 기관 값이 있으면 `[A-Z0-9_-]{1,30}` 형식인지 검사합니다. 기존 필드 초기화와 `PROCESSING` 상태를 보존합니다.
- `complete(..., Instant)`는 2강에서 작성한 것을 그대로 사용합니다. HTTP 송금 Command에는 기관 입력이 없습니다. 3강부터는 아래의 서버 설정값을 실제 API 경로의 새 오더에 연결합니다. 기존 10인자 형태는 기관을 지정하지 않는 직접 생성 사례에 사용합니다.

추가할 생성자의 계약은 다음과 같습니다. 본문은 위 조건에 맞춰 기존 초기화를 재사용하세요.

```java
public DmTransferOrder(String idempotencyKey, String requestHash, String tid, String gid,
    String journalTid, String accountingLedgerTid, DmAccount withdrawalAccount, DmAccount depositAccount,
    BigDecimal amount, String currencyCode, String externalInstitutionCode) {
    throw new UnsupportedOperationException("TODO [특강 3 / 4장 시작] 기존 초기화 코드를 유지하고 기관 코드 검증을 구현하세요.");
}
```

기존 `TransferProcessor`에 `private final String settlementInstitution` 필드를 추가합니다. 생성자의 `TransferFailureHook failureHook` 다음, `Clock clock` 앞에 아래 인자를 추가하고 `this.settlementInstitution = settlementInstitution;`으로 주입값을 보관합니다.

```java
@org.springframework.beans.factory.annotation.Value("${transfer.settlement.institution:OTHER-BANK}") String settlementInstitution
```

`process(command, policy)`에서 새 `DmTransferOrder`를 생성할 때 기존 마지막 `command.currencyCode()` 뒤에 `settlementInstitution`을 전달하세요. 기존 검증과 잔액 변경 코드는 유지합니다. 생성자를 직접 사용하는 테스트도 이 위치에 기관 값을 추가하세요. 기존 `application.yml`에 아래 설정만 병합합니다.

```yaml
transfer:
  settlement:
    institution: ${TRANSFER_SETTLEMENT_INSTITUTION:OTHER-BANK}
```

`DmTransferOrderRepository`에 아래 조회 계약을 추가합니다. 구현 시 default 본문을 없애고 JPQL과 파라미터 선언을 작성하세요. 조건은 완료 상태, UTC 날짜의 `[startAt, endAt)` 범위, 기관과 통화입니다.

```java
default List<DmTransferOrder> findSettlementTargets(java.time.Instant startAt, java.time.Instant endAt,
    String institution, String currency) {
    throw new UnsupportedOperationException("TODO [특강 3 / 4-5] 외부 대사 대상 조회를 작성하세요.");
}
```

`ReconciliationTestSupport`는 같은 초기 입금과 송금에 외부 비교용 오더를 함께 준비합니다. 기관은 `OTHER-BANK`, 통화는 `KRW`, 완료 시각은 기준일 UTC 12:00입니다. 실제 은행 통신은 하지 않습니다. 내부 대사는 대상일의 거래·원장과 저장한 마감 잔액을 비교하고, 외부 대사는 기관과 통화 및 UTC 하루 범위를 한정합니다.

`SettlementService.indexExternalItems()`는 4-5의 `calculate()` 연결 전에 구현합니다. TID나 금액이 없거나 TID가 중복되면 결과를 저장하기 전에 `IllegalArgumentException`을 발생시키세요. 금액이 음수이거나 저장 범위를 넘는 경우도 같은 예외로 처리합니다. 4-6-3은 이 검증의 오류 사례를 검사하는 절입니다. JSON 읽기 코드에 이 업무 검증을 옮기지 않습니다.

4-6의 `SettlementExerciseTest`는 외부 대사용 `ReconciliationTestSupport`를 상속합니다. 이 클래스의 `createSampleData()`는 `OTHER-BANK` 기관의 완료된 `T-TRANSFER` 오더까지 준비합니다. 앞의 내부 대사 테스트는 기존 `InternalReconciliationTestSupport`를 계속 사용합니다. 한 테스트에서 두 샘플을 함께 적재하지 않습니다.

### 4-6-1. 제공된 JSON 파일의 입력과 기대 결과 구분하기

파일은 `materials/lecture-03/02-external/src/test/resources/settlement/`에 있습니다. `SettlementJson.read()`가 반환한 `input`의 세 값만 `SettlementService.calculate()`에 전달합니다. `expected`와 `expectedError`는 테스트에서만 사용하세요. `SettlementJson.read()`는 중복 TID나 잘못된 금액도 그대로 DTO로 변환하므로 Service의 입력 검증을 확인할 수 있습니다.

`SettlementJson.read("normal.json")`으로 파일을 읽고, 반환된 `Sample.input()`에서 날짜, 기관 코드와 거래 목록을 읽어 `calculate()`의 세 인자로 전달합니다. 파일 확장자 `.json`까지 전달하세요. `settlementService`는 `SettlementExerciseTest`에 주입해서 사용합니다.

```java
createSampleData();
var sample = SettlementJson.read("normal.json");
var input = sample.input();
var result = settlementService.calculate(
    input.baseDate(), input.externalInstitutionCode(), input.externalItems());
// sample.expected()와 result 및 저장된 상세 결과를 직접 비교하세요.
```

오류 자료는 `SettlementJson.read("invalid/negative-amount.json")`처럼 읽습니다. `sample.expectedError()`는 예상 오류 문구이며 Service에 전달하지 않습니다. `externalSourceSnapshot()`은 `SettlementExerciseTest`에 선언한 별도 과제 메서드이며 JSON 파서가 제공하지 않습니다. 4-6-2의 금융 원본 9개 테이블에 `sm_account_daily_closing`을 더해 모든 행과 모든 컬럼을 안정적인 순서로 조회하세요. 외부 정산 전후 반환값을 비교하며 정산 결과 테이블은 원본 비교에서 제외하고 따로 검사합니다.

| 파일 | 입력의 의미 | 기대 결과 |
| --- | --- | --- |
| `normal.json` | 내부와 외부 300원 | 차이 없음 |
| `amount-mismatch.json` | 같은 TID의 금액 불일치 | 해당 상세 불일치 |
| `missing-bank.json` | 내부에만 송금 존재 | 외부 누락 |
| `external-only.json` | 외부에만 추가 거래 존재 | 내부 누락 |
| `offsetting-differences.json` | 두 거래의 차이가 서로 상쇄 | 합계 차이 0, 상세 불일치 2건 |
| `zero-external-only.json` | 외부에만 0원 기록 존재 | 없는 행과 0원을 구분한 불일치 |
| `invalid/*.json` 7개 | 중복 TID, 누락된 TID와 금액, 음수, 범위 및 자릿수 초과 | 예외, 결과 행 추가 없음 |

외부 정산의 원본 보존에는 마감 금액·기준일·계좌·상태·보관 시각·기초 표시·검증 시각과 `verified_run_id` 및 감사 시각도 포함합니다. 정상 외부 정산, 잘못된 입력 거절, 같은 자료 재실행에서 모두 동일해야 합니다. 내부 대사 `run()`은 마감 검증 상태를 바꾸므로 이 외부 정산 검사와 같은 snapshot 정책을 공유하지 않습니다. 테스트 준비 중 검증 완료 상태가 필요하다면 외부 정산 전 스냅샷을 찍기 전에 준비합니다.

모든 자료는 기본 과제입니다. 운영 규모의 분할 조회와 장애 재시작은 추가 탐구 주제입니다. `ReconciliationService`의 합계 계산 메서드는 제공하지만 어떤 자료를 비교할지, 누락을 어떻게 판정할지는 직접 작성합니다.

### 5-1. 마감 저장과 대사를 각각 예약하기

`ReconciliationSchedulingConfig`의 예약 활성화와 두 스케줄러의 `runDaily()`에 `@Scheduled`를 작성합니다. `DailyClosingScheduler`는 `DailyClosingService.capture()`를, `ReconciliationScheduler`는 `ReconciliationService.run()`을 호출합니다. 둘 다 주입된 `Clock`으로 **UTC 실행일의 전날**을 계산합니다.

| 작업 | 설정 키 | 환경 변수 | `batch`에서 구현할 예약식 |
| --- | --- | --- | --- |
| 실제 마감 잔액 보관 | `reconciliation.closing.schedule.cron` | `DAILY_CLOSING_CRON` | `0 10 0 * * *` / UTC 00:10, 한국 09:10 |
| 전날 내부 대사 | `reconciliation.schedule.cron` | `RECONCILIATION_CRON` | `0 0 2 * * *` / UTC 02:00, 한국 11:00 |

기본 프로필과 제공 `application-batch.yml`은 두 cron을 `-`로 비활성화합니다. 위 예약식을 학생이 작성한 뒤 `batch` 프로필에서 활성화합니다. 두 작업은 분리되어 있으므로 수동 대사도 먼저 대상일 마감 자료를 확보해야 합니다. 새벽 현재 잔액을 전날 값으로 바로 저장하지 않고 2-5-2에서 구현한 날짜 전환 보관 규칙을 사용하세요.

고정 Clock 호출 테스트는 UTC 전날, 마감 서비스 호출과 대사 서비스 호출을 각각 검사합니다. 세 호출 검사는 실제 assertion을 제공하며 `runDaily()` TODO가 남아 있으면 실패합니다. 예약 설정 및 독립 비활성화의 두 테스트는 `fail(...)`을 실제 Spring 컨텍스트 검증으로 교체합니다. 실제 예약 발동은 구현 후 로컬 앱에서 별도로 관찰합니다. Spring Batch 청크 처리, 여러 서버의 실행 소유권, 실패 후 자동 재시작은 별도 확장 과제입니다.

## 4강: 보안과 기존 기능 연결하기

### 0. 실습 준비: 3강 프로젝트에 보안 의존성 추가하기

`materials/lecture-04/dependencies.gradle`의 항목만 기존 `dependencies` 블록에 추가합니다. `01-security/src`는 새 파일입니다. `security` 프로필의 YAML은 기존 application.yml을 교체하지 않습니다. 테스트에는 `test`, `security-test` 프로필을 함께 사용합니다. 앞 강의의 Spring 통합 테스트도 보안 도입 후에는 이 프로필을 함께 쓰거나 테스트 설정에서 같은 속성을 제공하세요.

보안 자료 추가 후 설정은 다음처럼 선택합니다.

| 실행 범위 | 사용할 설정과 프로필 |
| --- | --- |
| 보안 DB 통합 테스트와 8-4 | `SecurityTestSupport`를 상속하면 `test`, `security-test`, MockMvc와 DB 초기화가 적용됩니다. |
| 기존 2강과 3강의 DB 통합 테스트 | `TransferIntegrationExerciseTest`, `ReconciliationIntegrationExerciseTest`, `SettlementExerciseTest`에 `@ActiveProfiles("security-test")`를 추가합니다. 상위 클래스의 `test`가 함께 상속됩니다. |
| 별도로 작성한 Spring DB 테스트 | `@ActiveProfiles({"test", "security-test"})`를 적용합니다. |
| 일반 로컬 앱 실행 | DB 환경변수와 `security.env.example`의 환경변수를 준비한 뒤 `security` 프로필을 활성화합니다. |
| DTO, 마스킹, 스케줄러 직접 호출 등 DB 없는 단위테스트 | Spring 프로필을 추가하거나 DB 준비 클래스를 상속하지 않습니다. |

`ActiveProfiles`는 `org.springframework.test.context`에서 import합니다. 기존 JPA 매핑 과제를 DB 통합 테스트로 바꾼 경우에도 두 테스트 프로필을 적용합니다. `application-test.yml`은 H2 설정, `application-security-test.yml`은 테스트 보안 설정입니다. 두 파일을 합치거나 기본 DB 설정을 보안 YAML로 덮어쓰지 않습니다. 일반 실행 명령은 다음과 같으며, DB 테이블과 보안 Bean 구현을 먼저 준비해야 합니다.

```sh
./gradlew bootRun --args='--spring.profiles.active=security'
```

### 2-5-1. 현재 계정의 역할을 Spring Security 권한으로 연결하기

`security.config.SecurityProperties`의 타입과 `@ConfigurationProperties(prefix = "app.security")`, `SecurityConfig`의 `@EnableConfigurationProperties` 등록은 준비되어 있습니다. `issuer`, `long accessTokenTtlMinutes`, `jwtSecret`, `cryptoPassword`, `cryptoSalt`는 YAML의 kebab-case 이름과 연결됩니다.

Properties의 compact 생성자에서 기본 issuer `sparta-fintech`와 양수가 아닌 TTL의 기본값 30분을 적용하고, JWT 서명 키 `jwtSecret`이 32자 이상인지, 암호화 키 생성에 쓰는 `cryptoPassword`와 `cryptoSalt`가 비어 있지 않은지 검증하세요. 기본값은 매개변수에 먼저 적용한 뒤 검증합니다. 제공된 `validate()`의 본문만으로 생성자 매개변수를 바꿀 수는 없으므로 기본값 처리는 compact 생성자에 작성합니다. 초기에는 값 검증 TODO가 있어 Properties 생성과 Spring 기동이 실패합니다. smoke 검사는 선언의 연결만 확인합니다.

`AccountJwtAuthenticationConverter`는 JWT를 Spring Security 인증 객체로 변환하는 Converter 클래스입니다. 검증된 JWT의 계정 식별값으로 현재 DB의 계정 상태와 역할을 확인하고 인증 객체에 연결하세요. JWT의 `roles` claim은 발급 시점의 역할 정보이며 API 인가는 Converter가 연결한 현재 DB 역할을 사용합니다.

기본 실습은 LOCAL 사용자 로그인입니다. 제공자 URL은 예시이므로 실제 외부 로그인은 유효한 제공자 등록과 연결된 계정을 준비한 뒤 2-6에서 진행합니다. 테스트의 `training-client` 등은 공개된 가짜 설정입니다.

### 3-4. 보안 서비스에서 한도 검증 및 Controller 연결

기존 `TransferController`에서 `TransferService` 필드, import 및 생성자 인자를 소유권과 한도를 검사하는 `SecureTransferService`로 연결합니다. `transfer()`의 기존 Request 변환은 유지하고 호출 대상과 고객 역할 검사를 추가합니다. `SecurityConfig`의 메서드 보안 활성화와 인증이 필요한 Controller의 역할 제약도 작성하세요. 고객 API는 `ROLE_CUSTOMER`, 운영 대사 조회는 `ROLE_OPERATIONS`를 사용합니다.

`TransferService`와 `TransferProcessor`의 `TransferPolicy` 인자 메서드는 2강에 이미 있습니다. `SecureTransferService`에서 소유권과 일일 한도 정책을 연결하세요. 일일 한도 검사는 새 송금의 잔액 Lock을 얻은 트랜잭션 안에서 수행합니다. UTC 기준 해당 날짜의 완료 송금액과 이번 요청 금액을 합산해 한도와 비교하세요. 완료된 요청의 재시도에서는 이미 완료된 송금액을 신규 사용액으로 다시 더하지 않습니다. 바깥 트랜잭션을 추가해 커밋 전에 요청 키 Lock이 풀리게 만들지 않습니다.

기존 Repository에 다음 계약을 추가합니다. 첫 메서드는 default 본문을 없애고 완료 상태와 출금 계좌 및 UTC 시간 범위의 합계 JPQL로 구현합니다. 나머지는 파생 조회입니다.

```java
// DmTransferOrderRepository
// TODO [특강 4 / 3-4] 완료 송금 금액의 합계를 조회하세요.
default java.math.BigDecimal sumCompletedWithdrawalAmount(Long accountId,
    java.time.Instant startAt, java.time.Instant endAt) {
    throw new UnsupportedOperationException("TODO [특강 4 / 3-4] 일일 사용액 조회를 작성하세요.");
}
// LiAccountingLedgerRepository
List<LiAccountingLedger> findByCustomerAccountAccountId(Long accountId);
// SiReconciliationResultRepository
org.springframework.data.domain.Page<SiReconciliationResult> findByReconciliationRunReconciliationRunId(
    Long reconciliationRunId, org.springframework.data.domain.Pageable pageable);
```

`AmAuthUserRepository` 등의 미완성 default 조회도 같은 방식으로 본문을 없애고 조회와 필요한 참조 로딩을 작성합니다. 엔티티의 관계가 지연 로딩일 때 서비스 경계 밖에서 접근 가능한지도 확인하세요.

### 4-1. 암호문 형식 확인과 변조 검증 구분하기

`SensitiveDataCryptoService.encrypt()`는 암호화 실습용 시크릿을 암호화하고 `gcm:v1:` 접두어를 붙입니다. `decrypt()`에서는 지원하는 접두어인지 확인한 뒤 복호화 과정에서 변조 여부를 검증하세요. 접두어 확인 실패는 “지원하지 않는 암호문 형식입니다.”, 복호화 중 검증 실패는 “암호문 검증에 실패했습니다.”로 구분합니다.

### 5-3-1. 감사 기록에 저장할 문자열 처리하기

`AuditLogService.safeText()`는 줄바꿈과 탭을 공백으로 바꾸고, 이메일과 숫자 패턴을 마스킹한 뒤 길이를 제한하는 메서드로 구현합니다. `save()`에서는 해당 문자열 필드에 `safeText()`로 처리한 값을 사용하세요. 모든 개인정보를 자동으로 찾아내는 기능으로 가정하지 않습니다.

### 5-3-2. 송금 완료 기록을 같은 트랜잭션에서 저장하기 / 6-1. HTTP 상태 코드와 보안 실패 구분

송금 완료 기록 엔티티 `AhTransferCompletion`에는 오더 ID, 금액과 완료 시각 등을 저장합니다. 이 기록은 잔액 변경과 같은 DB 트랜잭션에서 저장하세요.

기존 `TransferProcessor`에 `private final TransferCompletionRecorder completionRecorder` 필드를 추가하고, 기존 생성자의 `Clock clock` 다음에 같은 타입의 인자를 추가해 필드에 할당합니다. `process(command, policy)`에서 오더를 완료한 뒤, 반환하기 전에 `completionRecorder.record(order)`를 호출하세요. 재시도 경로에서 중복 기록하지 않습니다. `TransferCompletionRecorder`의 필수 트랜잭션 조건을 직접 적용하고 저장 실패가 잔액과 원장까지 롤백하는지 확인합니다.

`TransferExceptionHandler`의 기존 입력 오류 및 409 규칙을 새 `common.web.ApiExceptionHandler`에 옮겨 보안과 감사 처리를 확장합니다. 같은 예외를 두 전역 Advice가 처리하지 않도록 기존 `TransferExceptionHandler`의 `@RestControllerAdvice`를 제거한 뒤 필요 없는 옛 클래스를 정리하세요. `transfer.web.ErrorResponse`를 사용하던 곳은 `common.web.ErrorResponse`로 변경하고 기존 HTTP 계약은 유지합니다. 일반 `IllegalStateException`은 500이며 요청 키 충돌만 409로 처리합니다.

### 7-1-2. 테스트 클래스와 초기화 준비 / 8-4. 초기 입금부터 송금과 대사까지 확인하기

`SecurityIntegrationExerciseTest`와 `OpeningDepositExerciseTest`는 `SecurityTestSupport`를 상속합니다. 공통 주입, 초기화와 `createSecurityFixture()`는 이미 제공하므로 같은 이름의 메서드를 다시 작성하지 않습니다. `SecurityExerciseTest.masksAccountNumber()`는 DB 없이 실행하는 단위테스트로 유지합니다.

HTTP 요청을 위한 `login()`, `bearer()`, `transferBody()`, 응답 JSON 읽기와 감사 조회 메서드는 7-1-2를 참고해 통합 테스트에 직접 작성합니다. `LedgerPostingService`, `DailyClosingService`, `ReconciliationService`, 마감 Repository와 `practiceClock`은 `SecurityTestSupport`의 제공된 주입 필드를 그대로 사용합니다. 중복 필드를 선언하지 말고 `PlatformTransactionManager`와 제공되지 않은 추가 Repository만 테스트에 직접 주입하세요. 제공한 `MutableBusinessClock`으로 업무 날짜를 진행합니다. `SecurityFixture`는 `ownerCustomerId()`, `ownerAccountNo()`, `otherAccountNo()`를 제공하며 계좌 ID는 Repository로 조회합니다.

`SecurityTestSupport.createSecurityFixture(ownerBalance, dailyLimit)`은 `owner`와 `other`, `410-001`과 `410-002`, 한도와 잔액만 준비합니다. 로그인 비밀번호는 `owner-pass`, `other-pass`이며 앞에서 구현한 PasswordEncoder와 암호화 Service를 사용합니다. 잔액만 준비한 샘플의 대사가 정상이라고 기대하지 않습니다.

샘플의 `owner-mfa-secret`과 `other-mfa-secret`은 암호화 실습용 시크릿입니다. 추가 인증 기능은 구현하지 않습니다. 로그인 비밀번호, JWT 서명 키, 암호화 대상 시크릿의 용도를 구분하세요.

초기 원장이 필요한 통합 실습은 두 계좌를 잔액 0원으로 준비하고, 거래 전에 `initializeOpeningBalance(accountId, openingDate)`로 각각 실제 기초 잔액을 등록합니다. 이어서 `TransactionTemplate` 안에서 `FinancialTestData.openingDeposit()`을 한 번 호출합니다. 공개 메서드 `DmAccountBalance.increase()`, 시각 인자 `LedgerPostingService.post(command, postedAt)`와 일별 마감 준비가 선행 구현입니다. 초기 입금 함수의 계약은 다음과 같습니다.

```java
FinancialTestData.openingDeposit(accountId, amount, accountBalanceRepository,
    ledgerAccountRepository, ledgerPostingService, dailyClosingService, clock);
```

기존 다섯 인자 뒤에 `DailyClosingService`와 `Clock`을 전달합니다. 이 함수는 잔액 행을 잠근 뒤 하나의 처리 시각으로 마감 준비, 잔액 증가와 원장 저장을 호출합니다. 9인자 원장 DTO 생성자와 5인자 AccountPosting을 사용하며 `balanceAfter`는 `getLedgerBalance()`로 전달합니다. 로그인, 송금과 같은 키 재시도를 확인한 뒤 Clock을 다음 UTC 날짜로 진행해 `capture(대상일)`과 `run(대상일)`을 차례로 호출합니다. 대상일 마감과 대사 결과를 조회해 700원·300원 및 `VERIFIED` 상태를 확인하세요. 당시 클라이언트 응답은 네 필드 계약을 계속 사용합니다.

고정 TID `T-OPENING`, 현금 코드 `100101`을 새로 저장하므로 반복 테스트 전에 H2 DB를 초기화하세요. 지급보류 사례는 별도 테스트에서 원장 금액 100원과 사용가능잔액 80원으로 시작해 100원을 입금합니다. 결과는 200원과 180원이고 저장할 `balanceAfter`는 200원입니다. 이 사례는 잔액 스냅샷 검증용이며 앞선 100원의 원장까지 준비한 정상 대사 사례와 구분합니다.

## 강의와 과제 테스트 대응

업무 과제 메서드의 `fail(...)`을 준비, 호출과 실제 assertion으로 교체합니다. 스케줄러의 일부 메서드는 호출 계약을 확인하는 assertion이 이미 있으므로 해당 서비스 호출을 구현해 통과시킵니다. 한 메서드에 여러 사례가 있으면 같은 클래스 안에서 테스트 메서드를 나눌 수 있습니다. 강의의 별도 테스트 클래스를 새로 만들고 같은 과제의 `fail(...)`을 남겨 두지 마세요. 미완성 표시를 삭제하거나 테스트를 비활성화하는 것으로 완료 처리하지 않습니다.

`exercise`는 `src/test/java/com/sparta/fintech/ledger/exercise/`입니다. 요청 DTO 테스트만 `transfer/web/`에 있습니다. 통합 테스트 클래스는 제공한 준비 클래스를 이미 상속하고, 단위테스트 클래스는 DB 없이 유지합니다.

| 강의 절 | 제공 테스트와 채울 메서드 | 실행 범위와 준비 |
| --- | --- | --- |
| 1강 1-2, 2-1 | `DomainDesignExerciseTest.customerAccountAndBalanceConstraints()` | 매핑과 `StoredMoney` 구현 후 test 프로필의 DB 테스트로 작성 |
| 1강 4-2 | `DomainDesignExerciseTest.requestAndJournalMustDescribeTheSameAmountAndAccounts()` | 원장 저장의 금액 및 고객 계좌 검증 |
| 2강 1-2~1-3 | `TransferRequestExerciseTest.amountMustFitStorageWithoutRounding()` | DB 없는 입력 검증 |
| 2강 2-3 | `TransferExerciseTest.withdrawalPreservesHeldAmount()` | DB 없는 잔액 변경 검증 |
| 2강 7-2~7-3 | `TransferIntegrationExerciseTest.rollbackRestoresBalancesAndAllLedgerRows()` | `TransferTestSupport`로 정상 송금의 잔액과 기록을 먼저 확인하고 실패 지점별 롤백 검증으로 확장 |
| 2강 7-4~7-5 | `TransferIntegrationExerciseTest.retriesAndConcurrentRequestsKeepMoneyAndRequestIdentity()` | 같은 키, 다른 내용, 서로 다른 키의 동시 요청을 각각 검증 |
| 3강 2-4 | `ReconciliationExerciseTest.equalTotalsDoNotHideMissingStructure()` | DB 없는 결과 객체 검증 |
| 3강 3-2-2 | `ReconciliationIntegrationExerciseTest.usesVerifiedOpeningAndCapturedClosingForDailyAmounts()` | 고정 전일 기준과 당일 마감 비교, 전체 대사 후 VERIFIED 확인 |
| 3강 3-2-2 | `ReconciliationIntegrationExerciseTest.includesANonZeroVerifiedOpeningBalance()` | 기초 500원과 당일 입출금을 포함한 기대·실제 마감 1,200원 |
| 3강 2-5-2, 5-3 | `ReconciliationIntegrationExerciseTest.closingBatchPreservesThePriorDayBeforeNextDayPosting()` | 다음 날 입금 전 마감 보관, 거래 없는 날짜와 재저장 불변 |
| 3강 2-5-3, 3-8-1 | `ReconciliationIntegrationExerciseTest.failedNextDayTransferRollsBackCapturedClosingAndBusinessDate()` | 다음 날 실패 송금의 마감 기록·업무 날짜·잔액과 원장 전체 롤백 |
| 3강 3-3~3-8, 6-3 | `ReconciliationIntegrationExerciseTest.limitsAllComparisonRulesToAHalfOpenUtcDay()` | 당일 00:00 포함, 다음 날 00:00과 전날 제외, 각 비교 규칙 범위 |
| 3강 3-2-1 | `ReconciliationIntegrationExerciseTest.rejectsMissingOrUnverifiedClosingBeforeSavingResults()` | 전일 자료 없음·미검증 또는 당일 자료 없음 시 예외와 결과 미저장 |
| 3강 3-3~3-8, 6-3 | `ReconciliationIntegrationExerciseTest.mismatchedClosingCannotServeAsTheNextDayOpening()` | 불일치 마감의 검증 거절, 이후 날짜 기준 무효화 |
| 3강 3-6, 6-3 | `ReconciliationIntegrationExerciseTest.ledgerOnlyAndJournalAndLedgerCorruptionHaveDifferentResults()` | 정상 샘플에서 금액 및 고객 계좌를 변경한 원장 오류 구분 |
| 1강 3-1 복습, 3강 3-7-1 | `ReconciliationExerciseTest.nullCompletionTimeIsRejectedBeforeTransactionStateChanges()` | 거래와 오더의 null 완료 시각 입력을 거절하고 상태·시각·링크 보존 |
| 3강 3-7-1, 6-3 | `ReconciliationIntegrationExerciseTest.completedTransactionsWithoutCompletionTimeRemainLifecycleCandidates()` | 완료 시각 null 후보의 요청·상세·전표 기간 조회와 거래 ID 중복 제거 |
| 3강 3-7-1, 6-3 | `ReconciliationIntegrationExerciseTest.apiOrderCompletionTimesMustMatchInBothDirections()` | 정확한 완료 시각 및 null을 거래·오더 양방향으로 검사 |
| 3강 3-4, 6-3 | `ReconciliationIntegrationExerciseTest.matchingAmountsDoNotMakeAnUnpostedJournalNormal()` | 금액이 맞는 DRAFT·CANCELLED 전표의 구조 불일치 |
| 3강 4-6-2 | `SettlementExerciseTest.suppliedFilesDetectDifferencesMissingRowsAndOffsettingTotals()` | `ReconciliationTestSupport`의 기관 오더와 정상 형식 JSON 6개, 재실행 검증 |
| 3강 4-6-3 | `SettlementExerciseTest.invalidInputIsRejectedWithoutSavingResults()` | 입력 오류 JSON 7개와 결과 미저장 검증 |
| 3강 4-6-2~4-6-3 | `SettlementExerciseTest.externalSettlementPreservesAllDailyClosingColumns()` | 외부 정산의 정상·거절·재실행에서 금융·마감 전체 원본 보존 |
| 3강 5-3 | `SchedulerExerciseTest.passesPreviousUtcDateToReconciliation()` | 고정 Clock과 Mock으로 대사에 UTC 전날 전달 검증 |
| 3강 5-3 | `SchedulerExerciseTest.passesPreviousUtcDateToClosing()` | Clock의 표시 시간대와 무관하게 마감에 UTC 전날 전달 검증 |
| 3강 5-3 | `SchedulerExerciseTest.propagatesFailuresFromBothBatchServices()` | 두 서비스의 실패가 스케줄러 호출자에게 전달되는지 검증 |
| 3강 5-3 | `SchedulerExerciseTest.schedulingIsDisabledByDefaultAndUsesSeparateUtcCrons()` | 기본 비활성, 별도 UTC 예약 등록과 Runnable 호출 과제 |
| 3강 5-3 | `SchedulerExerciseTest.eitherBatchCanBeDisabledWithoutDisablingTheOther()` | 한 예약을 꺼도 다른 예약이 유지되는지 검증하는 과제 |
| 4강 4-2부터 4-4 | `SecurityExerciseTest.masksAccountNumber()` | DB 없는 마스킹 검증 |
| 4강 7-1~7-3 | `SecurityIntegrationExerciseTest.loginOwnershipRoleAndTokenFailures()` | `SecurityTestSupport`로 로그인, 소유권, 역할, 만료 및 변조 토큰 검증 |
| 4강 8-2 | `SecurityIntegrationExerciseTest.dailyLimitReplayAndDurableCompletion()` | 누적 한도, 재시도, 동시 요청과 송금 완료 기록 검증 |
| 4강 8-4 | `OpeningDepositExerciseTest.openingDepositWithHeldFundsRecordsLedgerBalance()` | 원장 금액 100원, 사용가능잔액 80원에서 입금 후 balanceAfter 검증 |
| 4강 8-4 | `OpeningDepositExerciseTest.loginTransferReplayAndReconciliationUseCompleteOpeningLedger()` | 실제 기초 등록, 초기 원장과 로그인, 송금·재시도, 다음 UTC 날짜 마감과 VERIFIED 확인 |

2강의 송금 DB 과제는 `TransferIntegrationExerciseTest`, 4강의 보안 API 과제는 `SecurityIntegrationExerciseTest`에서 작성합니다. 추가한 강의의 과제만 먼저 선택하려면 루트에서 다음 명령을 사용합니다.

```sh
./gradlew test --tests '*TransferRequestExerciseTest' --tests '*TransferExerciseTest' --tests '*TransferIntegrationExerciseTest'
./gradlew test --tests '*ReconciliationExerciseTest' --tests '*ReconciliationIntegrationExerciseTest' --tests '*SettlementExerciseTest' --tests '*SchedulerExerciseTest'
./gradlew test --tests '*SecurityExerciseTest' --tests '*SecurityIntegrationExerciseTest' --tests '*OpeningDepositExerciseTest'
```

아직 해당 자료를 추가하지 않았다면 그 클래스의 선택 옵션도 제외합니다. 3강 과제는 `exercise` 패키지에 있으므로 `reconciliation.*` 필터로 선택하지 않습니다. 마지막에는 `./gradlew smokeTest`로 준비 코드를, `./gradlew test`로 지금까지 추가한 전체 과제를 확인합니다. `ConcurrentRequestsTest`는 DB 없이 동시 실행 코드 자체를 확인하는 준비 테스트이며 송금 동시성의 업무 검증을 대신하지 않습니다.

## 자료 링크를 연결할 경로

[학생용 저장소](https://github.com/hungdi/fintech-backend-student)의 [README.md](README.md)에서 시작합니다. 자료 링크는 위 최초 적용 시점 표의 경로를 사용합니다. 강의 자료가 제공하는 클래스명과 다른 경우에는 이 문서의 테스트 대응표와 메서드 계약을 함께 확인하세요.

## 새 파일 목록

각 `src` 앞의 단계 폴더를 제거한 경로가 루트에 추가할 위치입니다. 루트 파일은 이미 포함되어 있습니다. Java 파일의 TODO에 정확한 구현 절이 표시되어 있습니다. 아래 목록은 동일 파일을 다시 덮어쓰는 배포 목록이 아닙니다.

<details>
<summary>1강 파일 경로</summary>

- `materials/lecture-01/accounts.csv`
- `materials/lecture-01/ledger-codes.csv`
- `src/main/java/com/sparta/fintech/ledger/LedgerDomainApplication.java`
- `src/main/java/com/sparta/fintech/ledger/common/time/BusinessTimeConfiguration.java`
- `src/main/java/com/sparta/fintech/ledger/domain/AccountChangeType.java`
- `src/main/java/com/sparta/fintech/ledger/domain/AccountStatus.java`
- `src/main/java/com/sparta/fintech/ledger/domain/BaseTimeEntity.java`
- `src/main/java/com/sparta/fintech/ledger/domain/CmCustomer.java`
- `src/main/java/com/sparta/fintech/ledger/domain/CustomerStatus.java`
- `src/main/java/com/sparta/fintech/ledger/domain/DebitCreditType.java`
- `src/main/java/com/sparta/fintech/ledger/domain/DhAccountChange.java`
- `src/main/java/com/sparta/fintech/ledger/domain/DiAccountTransaction.java`
- `src/main/java/com/sparta/fintech/ledger/domain/DmAccount.java`
- `src/main/java/com/sparta/fintech/ledger/domain/DmAccountBalance.java`
- `src/main/java/com/sparta/fintech/ledger/domain/DmAccountLimit.java`
- `src/main/java/com/sparta/fintech/ledger/domain/DmTransaction.java`
- `src/main/java/com/sparta/fintech/ledger/domain/InsufficientBalanceException.java`
- `src/main/java/com/sparta/fintech/ledger/domain/JournalStatus.java`
- `src/main/java/com/sparta/fintech/ledger/domain/LcLedgerAccount.java`
- `src/main/java/com/sparta/fintech/ledger/domain/LedgerPostingRules.java`
- `src/main/java/com/sparta/fintech/ledger/domain/LiAccountingLedger.java`
- `src/main/java/com/sparta/fintech/ledger/domain/LiJournalEntryLine.java`
- `src/main/java/com/sparta/fintech/ledger/domain/LimitPeriod.java`
- `src/main/java/com/sparta/fintech/ledger/domain/LimitType.java`
- `src/main/java/com/sparta/fintech/ledger/domain/LmJournalEntry.java`
- `src/main/java/com/sparta/fintech/ledger/domain/StoredMoney.java`
- `src/main/java/com/sparta/fintech/ledger/domain/TransactionOrigin.java`
- `src/main/java/com/sparta/fintech/ledger/domain/TransactionStatus.java`
- `src/main/java/com/sparta/fintech/ledger/domain/TransactionType.java`
- `src/main/java/com/sparta/fintech/ledger/repository/CmCustomerRepository.java`
- `src/main/java/com/sparta/fintech/ledger/repository/DhAccountChangeRepository.java`
- `src/main/java/com/sparta/fintech/ledger/repository/DiAccountTransactionRepository.java`
- `src/main/java/com/sparta/fintech/ledger/repository/DmAccountBalanceRepository.java`
- `src/main/java/com/sparta/fintech/ledger/repository/DmAccountLimitRepository.java`
- `src/main/java/com/sparta/fintech/ledger/repository/DmAccountRepository.java`
- `src/main/java/com/sparta/fintech/ledger/repository/DmTransactionRepository.java`
- `src/main/java/com/sparta/fintech/ledger/repository/LcLedgerAccountRepository.java`
- `src/main/java/com/sparta/fintech/ledger/repository/LiAccountingLedgerRepository.java`
- `src/main/java/com/sparta/fintech/ledger/repository/LiJournalEntryLineRepository.java`
- `src/main/java/com/sparta/fintech/ledger/repository/LmJournalEntryRepository.java`
- `src/main/java/com/sparta/fintech/ledger/service/LedgerPostingCommand.java`
- `src/main/java/com/sparta/fintech/ledger/service/LedgerPostingResult.java`
- `src/main/java/com/sparta/fintech/ledger/service/LedgerPostingService.java`
- `src/main/resources/application.yml`
- `src/test/java/com/sparta/fintech/ledger/exercise/DomainDesignExerciseTest.java`
- `src/test/java/com/sparta/fintech/ledger/preparation/PreparationSmokeTest.java`
- `src/test/java/com/sparta/fintech/ledger/support/TestDatabaseReset.java`
- `src/test/resources/application-test.yml`

</details>

<details>
<summary>2강 파일 경로</summary>

- `materials/lecture-02/01-request/src/main/java/com/sparta/fintech/ledger/common/validation/ExactStoredAmount.java`
- `materials/lecture-02/01-request/src/main/java/com/sparta/fintech/ledger/transfer/service/TransferCommand.java`
- `materials/lecture-02/01-request/src/main/java/com/sparta/fintech/ledger/transfer/web/TransferHttpRequest.java`
- `materials/lecture-02/01-request/src/test/java/com/sparta/fintech/ledger/transfer/web/TransferRequestExerciseTest.java`
- `materials/lecture-02/02-processing/src/main/java/com/sparta/fintech/ledger/domain/DmTransferOrder.java`
- `materials/lecture-02/02-processing/src/main/java/com/sparta/fintech/ledger/domain/TransferOrderStatus.java`
- `materials/lecture-02/02-processing/src/main/java/com/sparta/fintech/ledger/repository/DmTransferOrderRepository.java`
- `materials/lecture-02/02-processing/src/main/java/com/sparta/fintech/ledger/transfer/service/FinancialIdGenerator.java`
- `materials/lecture-02/02-processing/src/main/java/com/sparta/fintech/ledger/transfer/service/GeneratedTransferTraceIds.java`
- `materials/lecture-02/02-processing/src/main/java/com/sparta/fintech/ledger/transfer/service/IdempotencyKeyConflictException.java`
- `materials/lecture-02/02-processing/src/main/java/com/sparta/fintech/ledger/transfer/service/IdempotencyKeyLockManager.java`
- `materials/lecture-02/02-processing/src/main/java/com/sparta/fintech/ledger/transfer/service/NoOpTransferFailureHook.java`
- `materials/lecture-02/02-processing/src/main/java/com/sparta/fintech/ledger/transfer/service/TransferFailureHook.java`
- `materials/lecture-02/02-processing/src/main/java/com/sparta/fintech/ledger/transfer/service/TransferFailurePoint.java`
- `materials/lecture-02/02-processing/src/main/java/com/sparta/fintech/ledger/transfer/service/TransferInProgressException.java`
- `materials/lecture-02/02-processing/src/main/java/com/sparta/fintech/ledger/transfer/service/TransferPolicy.java`
- `materials/lecture-02/02-processing/src/main/java/com/sparta/fintech/ledger/transfer/service/TransferProcessor.java`
- `materials/lecture-02/02-processing/src/main/java/com/sparta/fintech/ledger/transfer/service/TransferRequestHasher.java`
- `materials/lecture-02/02-processing/src/main/java/com/sparta/fintech/ledger/transfer/service/TransferResult.java`
- `materials/lecture-02/02-processing/src/main/java/com/sparta/fintech/ledger/transfer/service/TransferService.java`
- `materials/lecture-02/02-processing/src/test/java/com/sparta/fintech/ledger/exercise/TransferExerciseTest.java`
- `materials/lecture-02/02-processing/src/test/java/com/sparta/fintech/ledger/exercise/TransferIntegrationExerciseTest.java`
- `materials/lecture-02/02-processing/src/test/java/com/sparta/fintech/ledger/preparation/TransferPreparationSmokeTest.java`
- `materials/lecture-02/02-processing/src/test/java/com/sparta/fintech/ledger/support/ConcurrentRequests.java`
- `materials/lecture-02/02-processing/src/test/java/com/sparta/fintech/ledger/support/ConcurrentRequestsTest.java`
- `materials/lecture-02/02-processing/src/test/java/com/sparta/fintech/ledger/support/TransferTestSupport.java`
- `materials/lecture-02/03-http/src/main/java/com/sparta/fintech/ledger/transfer/web/ErrorResponse.java`
- `materials/lecture-02/03-http/src/main/java/com/sparta/fintech/ledger/transfer/web/TransferController.java`
- `materials/lecture-02/03-http/src/main/java/com/sparta/fintech/ledger/transfer/web/TransferExceptionHandler.java`
- `materials/lecture-02/03-http/src/main/java/com/sparta/fintech/ledger/transfer/web/TransferHttpResponse.java`
- `materials/lecture-02/dependencies.gradle`

</details>

<details>
<summary>3강 파일 경로</summary>

- `materials/lecture-03/01-internal/corruption-cases.json`
- `materials/lecture-03/01-internal/src/main/java/com/sparta/fintech/ledger/domain/DailyClosingStatus.java`
- `materials/lecture-03/01-internal/src/main/java/com/sparta/fintech/ledger/domain/ReconciliationResultStatus.java`
- `materials/lecture-03/01-internal/src/main/java/com/sparta/fintech/ledger/domain/ReconciliationRunStatus.java`
- `materials/lecture-03/01-internal/src/main/java/com/sparta/fintech/ledger/domain/ReconciliationTargetType.java`
- `materials/lecture-03/01-internal/src/main/java/com/sparta/fintech/ledger/domain/SiReconciliationResult.java`
- `materials/lecture-03/01-internal/src/main/java/com/sparta/fintech/ledger/domain/SmAccountDailyClosing.java`
- `materials/lecture-03/01-internal/src/main/java/com/sparta/fintech/ledger/domain/SsReconciliationRun.java`
- `materials/lecture-03/01-internal/src/main/java/com/sparta/fintech/ledger/reconciliation/service/DailyClosingService.java`
- `materials/lecture-03/01-internal/src/main/java/com/sparta/fintech/ledger/reconciliation/service/ReconciliationRunResult.java`
- `materials/lecture-03/01-internal/src/main/java/com/sparta/fintech/ledger/reconciliation/service/ReconciliationService.java`
- `materials/lecture-03/01-internal/src/main/java/com/sparta/fintech/ledger/repository/SiReconciliationResultRepository.java`
- `materials/lecture-03/01-internal/src/main/java/com/sparta/fintech/ledger/repository/SmAccountDailyClosingRepository.java`
- `materials/lecture-03/01-internal/src/main/java/com/sparta/fintech/ledger/repository/SsReconciliationRunRepository.java`
- `materials/lecture-03/01-internal/src/test/java/com/sparta/fintech/ledger/exercise/ReconciliationExerciseTest.java`
- `materials/lecture-03/01-internal/src/test/java/com/sparta/fintech/ledger/exercise/ReconciliationIntegrationExerciseTest.java`
- `materials/lecture-03/01-internal/src/test/java/com/sparta/fintech/ledger/support/InternalReconciliationTestSupport.java`
- `materials/lecture-03/01-internal/src/test/java/com/sparta/fintech/ledger/support/MutableBusinessClock.java`
- `materials/lecture-03/02-external/src/main/java/com/sparta/fintech/ledger/domain/SettlementStatus.java`
- `materials/lecture-03/02-external/src/main/java/com/sparta/fintech/ledger/domain/SiSettlementDetail.java`
- `materials/lecture-03/02-external/src/main/java/com/sparta/fintech/ledger/domain/SmSettlement.java`
- `materials/lecture-03/02-external/src/main/java/com/sparta/fintech/ledger/reconciliation/service/ExternalSettlementItem.java`
- `materials/lecture-03/02-external/src/main/java/com/sparta/fintech/ledger/reconciliation/service/SettlementCalculationResult.java`
- `materials/lecture-03/02-external/src/main/java/com/sparta/fintech/ledger/reconciliation/service/SettlementService.java`
- `materials/lecture-03/02-external/src/main/java/com/sparta/fintech/ledger/repository/SiSettlementDetailRepository.java`
- `materials/lecture-03/02-external/src/main/java/com/sparta/fintech/ledger/repository/SmSettlementRepository.java`
- `materials/lecture-03/02-external/src/test/java/com/sparta/fintech/ledger/exercise/SettlementExerciseTest.java`
- `materials/lecture-03/02-external/src/test/java/com/sparta/fintech/ledger/preparation/SettlementMaterialsSmokeTest.java`
- `materials/lecture-03/02-external/src/test/java/com/sparta/fintech/ledger/support/ReconciliationTestSupport.java`
- `materials/lecture-03/02-external/src/test/java/com/sparta/fintech/ledger/support/SettlementJson.java`
- `materials/lecture-03/02-external/src/test/resources/settlement/amount-mismatch.json`
- `materials/lecture-03/02-external/src/test/resources/settlement/external-only.json`
- `materials/lecture-03/02-external/src/test/resources/settlement/invalid/amount-overflow.json`
- `materials/lecture-03/02-external/src/test/resources/settlement/invalid/duplicate-tid-different-amount.json`
- `materials/lecture-03/02-external/src/test/resources/settlement/invalid/duplicate-tid.json`
- `materials/lecture-03/02-external/src/test/resources/settlement/invalid/missing-amount.json`
- `materials/lecture-03/02-external/src/test/resources/settlement/invalid/missing-tid.json`
- `materials/lecture-03/02-external/src/test/resources/settlement/invalid/negative-amount.json`
- `materials/lecture-03/02-external/src/test/resources/settlement/invalid/too-many-decimal-places.json`
- `materials/lecture-03/02-external/src/test/resources/settlement/missing-bank.json`
- `materials/lecture-03/02-external/src/test/resources/settlement/normal.json`
- `materials/lecture-03/02-external/src/test/resources/settlement/offsetting-differences.json`
- `materials/lecture-03/02-external/src/test/resources/settlement/zero-external-only.json`
- `materials/lecture-03/03-scheduling/src/main/java/com/sparta/fintech/ledger/reconciliation/scheduler/DailyClosingScheduler.java`
- `materials/lecture-03/03-scheduling/src/main/java/com/sparta/fintech/ledger/reconciliation/scheduler/ReconciliationScheduler.java`
- `materials/lecture-03/03-scheduling/src/main/java/com/sparta/fintech/ledger/reconciliation/scheduler/ReconciliationSchedulingConfig.java`
- `materials/lecture-03/03-scheduling/src/main/resources/application-batch.yml`
- `materials/lecture-03/03-scheduling/src/test/java/com/sparta/fintech/ledger/exercise/SchedulerExerciseTest.java`

</details>

<details>
<summary>4강 파일 경로</summary>

- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/account/service/AccountSummary.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/account/service/SecureAccountService.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/account/web/AccountController.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/account/web/AccountHttpResponse.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/common/web/ApiExceptionHandler.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/common/web/ErrorResponse.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/domain/AcAuthRole.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/domain/AhAuditLog.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/domain/AhTransferCompletion.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/domain/AmAuthUser.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/domain/AmAuthUserRole.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/domain/AuditActionType.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/domain/AuditResultType.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/domain/AuthProviderType.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/ledger/service/MaskedLedgerItem.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/ledger/service/SecureLedgerQueryService.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/ledger/web/LedgerQueryController.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/ledger/web/MaskedLedgerItemResponse.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/reconciliation/service/OperationsReconciliationQueryService.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/reconciliation/web/OperationsReconciliationController.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/repository/AcAuthRoleRepository.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/repository/AhAuditLogRepository.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/repository/AhTransferCompletionRepository.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/repository/AmAuthUserRepository.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/repository/AmAuthUserRoleRepository.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/security/config/SecurityConfig.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/security/config/SecurityProperties.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/security/service/AccountIdentityService.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/security/service/AccountJwtAuthenticationConverter.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/security/service/AuditAuthenticationEntryPoint.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/security/service/AuditLogService.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/security/service/AuditLogWriter.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/security/service/AuthSecretMigrationService.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/security/service/AuthService.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/security/service/CurrentUser.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/security/service/CurrentUserService.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/security/service/JwtTokenService.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/security/service/LoginCommand.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/security/service/LoginResult.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/security/service/MaskingService.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/security/service/OAuth2JwtSuccessHandler.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/security/service/RequestTraceContext.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/security/service/RequestTraceIdFilter.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/security/service/SensitiveDataCryptoService.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/security/web/AuthController.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/security/web/LoginHttpRequest.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/security/web/LoginHttpResponse.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/transfer/service/SecureTransferService.java`
- `materials/lecture-04/01-security/src/main/java/com/sparta/fintech/ledger/transfer/service/TransferCompletionRecorder.java`
- `materials/lecture-04/01-security/src/main/resources/application-security.yml`
- `materials/lecture-04/01-security/src/test/java/com/sparta/fintech/ledger/exercise/SecurityExerciseTest.java`
- `materials/lecture-04/01-security/src/test/java/com/sparta/fintech/ledger/exercise/SecurityIntegrationExerciseTest.java`
- `materials/lecture-04/01-security/src/test/java/com/sparta/fintech/ledger/preparation/SecurityPreparationSmokeTest.java`
- `materials/lecture-04/01-security/src/test/java/com/sparta/fintech/ledger/support/SecurityTestSupport.java`
- `materials/lecture-04/01-security/src/test/resources/application-security-test.yml`
- `materials/lecture-04/02-integration/src/test/java/com/sparta/fintech/ledger/exercise/OpeningDepositExerciseTest.java`
- `materials/lecture-04/02-integration/src/test/java/com/sparta/fintech/ledger/support/FinancialTestData.java`
- `materials/lecture-04/dependencies.gradle`
- `materials/lecture-04/security.env.example`

</details>
