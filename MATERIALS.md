# 강의별 실습 자료

## 제공 범위

| 강의와 절 | 제공할 파일 | 제공하는 부분 | 학생이 구현할 부분 | 사용 전제 |
| --- | --- | --- | --- | --- |
| 1강 1-2. 고객 ID로 계좌 연결하기 | 루트 `domain`, `repository`, `materials/lecture-01/*.csv` | 필드 타입, 생성자와 getter, Repository 계약, 샘플 값 | 엔티티 관계, PK, FK, UNIQUE와 필수 제약 | Java 17 |
| 1강 4-2. 전표를 상세 내역과 연결하기 | `service/LedgerPostingCommand.java`, `domain/LedgerPostingRules.java`, `domain/StoredMoney.java` | DTO와 메서드 계약 | 금액 범위와 거래 유형별 분개 검증 | 앞 절의 관계 설계 |
| 1강 6-1. 원장 저장 순서와 트랜잭션 | `service/LedgerPostingService.java` | 주입과 반환 타입, 전표번호 생성 | 검증, 저장 순서, 거래 출처와 트랜잭션 | 원장 엔티티와 금액 검증 |
| 2강 1-3. Controller에서 Service로 전달할 DTO 구성하기 | `lecture-02/01-request` | 입력 필드와 검증기 연결 선언 | HTTP 금액 제약과 정확한 표현 범위 | 1강 `StoredMoney` |
| 2강 2-1~6-5 | `lecture-02/02-processing` | 추적 ID와 Clock 연결, Command와 Result, 실패 주입 계약, 샘플 데이터 생성 코드와 동시 요청 실행 코드 | 검증, 잔액, Lock, 요청 해시, 멱등성, 원장 연결 | 1강 원장 저장 |
| 2강 6-6. Controller에서 송금 Service 호출하기 | `lecture-02/03-http` | HTTP 경로와 응답 DTO | Service 연결과 오류 응답 | 처리 Service와 결과 타입 |
| 3강 2-4. 대사 결과를 엔티티에 담기 / 3-1. 샘플 데이터 저장 및 대사 실행 환경 준비하기 | `lecture-03/01-internal` | 엔티티와 결과 계약, 고정 샘플 | 차이 계산, 비교 규칙과 상태 판정 | 1~2강 엔티티와 완료 시각 |
| 3강 4-6. 제휴은행 거래자료의 금액 차이와 누락 테스트하기 | `lecture-03/02-external` | JSON 13개와 파일 읽기 코드, 비교용 오더 샘플 | 범위 조회, 누락과 금액 비교, 입력 오류 거절 | 기관 코드와 Repository 추가 |
| 3강 5-2. 실행 시각과 Service 호출 연결하기 | `lecture-03/03-scheduling` | 주입과 호출 테스트 예제, 비활성 설정 | cron과 UTC 날짜, Service 호출 | 내부 대사 Service |
| 4강 2-5. 로그인 입력과 JWT 인증 설정 연결하기 | `lecture-04/01-security` | 설정 바인딩, DTO, 주입 및 테스트 환경 | 로그인, JWT 검증, 역할과 소유권, 마스킹, 감사 기록 | 3강까지 구현, 보안 의존성 추가 |
| 4강 8-4. 초기 입금부터 송금과 대사까지 확인하기 | `lecture-04/02-integration` | 초기 입금 함수와 테스트 틀 | 입금, 로그인, 송금, 대사의 통합 검증 | 앞 강의의 잔액 변경과 원장 Service |

표의 `domain`과 `service`는 `src/main/java/com/sparta/fintech/ledger/` 아래 경로입니다. `lecture-02` 등의 경로는 `materials/` 아래를 뜻합니다. 각 새 파일의 전체 상대 경로는 문서 끝의 파일 목록에서 확인합니다.

생성자 주입, 단순 필드 보관과 DTO 변환은 제공합니다. `@Transactional`, 비관적 `@Lock`, 역할용 `@PreAuthorize`와 업무 검증은 해당 절에서 직접 적용합니다. `TransferPolicy.NONE`은 2강에서 추가 보안 정책을 아직 연결하지 않은 호출 계약입니다. 4강의 고객 요청에는 보안 정책을 연결해야 합니다.

## 자료 추가 방법과 현재 단계의 실행 범위

각 단계 폴더 안의 `src/main`과 `src/test` 파일을 루트의 **같은 상대 경로**에 추가하세요. 파일명 충돌이 나면 덮어쓰지 말고 아래의 기존 파일 수정 목록을 확인합니다. 이미 작업한 `src` 폴더 전체를 교체하지 않습니다. 이후 강의의 Java 파일도 필요한 자료 묶음을 추가한 다음 같은 프로젝트에서 사용합니다.

| 시점 | 새 자료 | DB 없이 확인할 범위 | 앱 기동 또는 통합 확인의 선행 구현 |
| --- | --- | --- | --- |
| 1강 시작 | 루트 `src` | 컴파일 대상은 루트만. `PreparationSmokeTest` | 엔티티 매핑과 실습 DB, 원장 Service와 상태 변경 |
| 2강 1-2~1-3 | `lecture-02/01-request/src`와 `dependencies.gradle`의 web 의존성 | DTO 생성 및 `TransferRequestExerciseTest`. Controller를 추가하지 않음 | 입력 제약 및 `StoredMoney` |
| 2강 2-1~6-5 | `lecture-02/02-processing/src` | `TransferPreparationSmokeTest`, 잔액 및 해시 단위테스트 | 아래 Repository 변경, 검증과 Lock, 원장 저장, 트랜잭션 |
| 2강 6-6 | `lecture-02/03-http/src` | DTO와 기존 단위테스트 | Controller 연결과 오류 응답 구현 |
| 3강 2-4~3-8 | `lecture-03/01-internal/src` | 결과 객체 계산 과제 | 아래 Repository 추가, 대사 엔티티 매핑과 비교 규칙 |
| 3강 4장 | 아래 오더 수정 후 `lecture-03/02-external/src` | `SettlementMaterialsSmokeTest`의 JSON 읽기 | 기관과 UTC 날짜 조회, 외부 대사와 정산 엔티티 |
| 3강 5장 | `lecture-03/03-scheduling/src` | `SchedulerExerciseTest`의 고정 Clock 호출 검사 | 예약 활성화와 cron 설정, 내부 대사 |
| 4강 0장 | 보안 의존성 추가 후 `lecture-04/01-security/src` | `SecurityPreparationSmokeTest`는 선언 연결만 확인, 마스킹 단위테스트 | 보안 Bean과 Properties 값 검증, 사용자 매핑과 아래 변경 |
| 4강 8-4 | `lecture-04/02-integration/src` | DTO 준비 검사는 유지 | 초기 입금과 로그인, 송금 및 대사 구현 |

컴파일은 문법과 타입의 연결을 확인하는 단계입니다. Spring 컨텍스트를 사용하지 않는 `smokeTest`는 미완성 JPA 매핑을 검사하지 않습니다. `test`는 과제도 모두 실행하므로 추가한 과제가 남아 있으면 실패합니다. 필요한 시점에 테스트 틀의 `fail(...)`을 실제 준비, 호출 및 assertion으로 교체하고 앞서 통과한 테스트를 함께 유지하세요.

## 1강: 루트에서 작성하기

### 1-2. 고객 ID로 계좌 연결하기 / 2-1. 지금 출금할 수 있는 금액은 얼마일까?

`domain`의 필드와 생성자는 뒤 강의에서도 사용하는 호출 계약입니다. 이 필드를 엔티티로 매핑하면서 고객, 계좌, 잔액과 한도의 관계를 직접 설계하세요. 계좌번호와 고객번호의 중복, 같은 계좌의 두 잔액 행, 없는 부모 참조를 DB 테스트로 확인합니다. `BaseTimeEntity`와 UTC 감사 시각 연결은 제공됩니다. `DmAccountBalance.version`의 동시 변경 처리는 5-3에서 적용합니다.

`materials/lecture-01/accounts.csv`는 첫 시나리오의 고객 역할과 시작 금액입니다. JPA가 생성한 ID는 `save()`의 반환 객체에서 읽습니다. 계정코드는 `ledger-codes.csv`를 사용합니다. CSV는 자동 적재되지 않습니다.

### 4-2. 전표를 상세 내역과 연결하기 / 6-1. 원장 저장 순서와 트랜잭션

`LedgerPostingCommand`는 전체 필드를 받는 11인자 생성자와 추적 ID 일부를 생략하는 9인자 생성자를 제공합니다. 9인자 형태에서 전표 TID는 `tid + "-JOURNAL"`, 회계원장 TID는 `tid + "-LEDGER"`입니다. 같은 TID를 재사용하면 같은 추적 ID가 만들어집니다. 초기 입금 함수 `FinancialTestData.openingDeposit()`은 고정 TID를 사용하므로 테스트 DB를 초기화한 뒤 한 번 호출합니다.

`AccountPosting`은 5인자이며 마지막 `balanceAfter`에는 거래 직후 **원장잔액**을 넣습니다. `JournalPosting`은 계정코드와 고객 계좌 ID를 별도 필드로 받습니다. 현금 계정 `100101`의 고객 계좌는 null이고 고객예수금은 `210101`입니다.

`LcLedgerAccount.normalBalanceType`은 계정과목의 잔액이 보통 남는 쪽인 차변 또는 대변을 나타냅니다.

`StoredMoney`에서 금액 범위 검사를 먼저 작성합니다. 거래 금액은 양수, 잔액은 0 이상이며 모두 `DECIMAL(19,2)`에 반올림 없이 표현 가능해야 합니다. `0.005`와 `100000000000000000`은 거절하고 `300.000`과 `0.0100`은 값이 달라지지 않으므로 허용합니다. 잔액 생성자도 이 검증을 사용합니다. 2강에서는 HTTP와 Service 입구에 같은 조건을 연결합니다.

`LedgerPostingRules`에는 수수료 없는 송금, 현금 입금, 현금 출금의 두 줄 분개 규칙을 작성하세요. 차대 합계 외에 각 분개와 계좌거래의 요청 금액 및 고객 계좌를 확인합니다. `LedgerPostingService.post()`의 저장 로직과 트랜잭션을 작성한 뒤, 2강 6-3에서 `postTransfer()`와 `DmTransaction.markTransferApi()`를 연결합니다. 원장을 직접 적재하는 실습과 API 송금의 출처는 각각 `LEDGER_EXERCISE`와 `TRANSFER_API`입니다.

## 2강: 기존 파일에 추가할 부분

### 2-2. 유효한 1회 송금 한도 검증 / 4-3. 두 계좌의 Lock 대상과 교착상태(Deadlock) 방지

다음 선언을 기존 Repository 안에 추가합니다. `java.util.List`, `java.util.Optional`과 해당 도메인 타입을 import하세요.

```java
// DmAccountLimitRepository에 추가
List<DmAccountLimit> findByAccountAccountIdAndLimitTypeAndActiveTrue(Long accountId, LimitType limitType);

// DmAccountBalanceRepository에 추가할 유효한 미완성 선언
// TODO [특강 2 / 4-3] 완료할 때 default 본문을 없애고 단건 JPQL과 비관적 Lock을 적용하세요.
default Optional<DmAccountBalance> findByAccountIdForUpdate(Long accountId) {
    throw new UnsupportedOperationException("TODO [특강 2 / 4-3] 계좌별 잔액 Lock 조회를 구현하세요.");
}
```

기존 `findByAccountAccountId()`는 유지합니다. `TransferProcessor.lockBalances()`에서 계좌 ID 순으로 단건 Lock을 얻도록 작성합니다. `process()`의 격리 수준과 트랜잭션, `TransferService`의 바깥 트랜잭션 제한은 학생 과제입니다. 요청 키의 Lock은 송금 DB 커밋 이후 해제되어야 합니다.

### 5-2-1. 송금 오더 필드 설계하기 / 6-3. TID, GID, OID를 원장 처리용 DTO에 전달하기

`DmTransferOrder`는 처음 추가할 때부터 `Instant completedAt`, getter, 3인자와 4인자 `complete()`를 제공합니다. 3인자 형태는 현재 시각을 넣어 4인자 형태에 위임합니다. 완료 정보와 상태를 기록하는 본문은 직접 작성하고, 실제 송금에서는 주입받은 `Clock.instant()`를 명시적으로 전달하세요.

`FinancialIdGenerator`, `GeneratedTransferTraceIds`와 UTC Clock은 준비 코드입니다. 반환 필드는 `tid`, `gid`, `journalTid`, `accountingLedgerTid`입니다. 학생은 이 값을 송금 오더와 원장 DTO에 연결합니다.

`TransferProcessor.toResult()`는 완료된 오더와 잔액을 내부 결과 DTO인 `TransferResult`로 변환합니다. `TransferHttpResponse`는 클라이언트에 공개할 `transferOrderId`, `tid`, `status`, `withdrawalAccountBalance` 네 필드만 담는 Response DTO입니다. Controller는 이 객체를 `ResponseEntity`의 본문에 넣어 반환합니다. 다른 고객의 입금 잔액은 고객 응답에 추가하지 않습니다. 대사 테스트에서는 Repository로 내부 추적 ID를 조회하세요.

### 7-1. 테스트 데이터와 실행 환경 준비하기

`TransferTestSupport.createTransferAccounts()`는 출금 잔액 1,000,000원, 입금 잔액 100,000원, 1회 한도 1,000,000원을 준비합니다. 300,000원 송금 후 두 잔액은 700,000원과 400,000원입니다. 이 준비 함수에는 초기 원장이 없으므로 3강의 정상 대사 데이터로 사용하지 않습니다.

`ConcurrentRequests.run()`은 전달받은 요청을 여러 스레드에서 동시에 실행합니다. 금액과 행 수의 검증은 직접 작성하세요. `TransferFailureHook`을 테스트 Bean이나 Spy로 교체해 네 실패 지점을 확인합니다. `AFTER_LEDGER_POSTING`도 포함합니다. 테스트 클래스 전체에 트랜잭션을 걸지 말고 Service 커밋이 끝난 뒤 다시 조회하세요.

`TestDatabaseReset`은 `jdbc:h2:mem:fintech_student`만 초기화합니다. 정리할 때만 FK 검사를 잠시 해제하고 finally에서 복구합니다. 제약 검증은 정상 FK 검사 상태에서 실제 저장을 호출해 확인합니다. DB를 초기화하지 않고 고정 식별값의 샘플을 다시 적재하면 중복 제약에 걸릴 수 있습니다.

## 3강: 앞 강의 파일을 유지하며 확장하기

### 3-1-1. 대사 저장용 Repository 및 결과 DTO 구현

기존 Repository에 아래 메서드를 추가하고, `java.util.List`, `java.util.Optional` 및 해당 엔티티를 import하세요. 단순 파생 조회 계약이며 이전 메서드는 유지합니다.

```java
// DiAccountTransactionRepository
List<DiAccountTransaction> findByAccountAccountId(Long accountId);
List<DiAccountTransaction> findByTransactionTransactionId(Long transactionId);
// LmJournalEntryRepository
Optional<LmJournalEntry> findByTransactionTransactionId(Long transactionId);
// LiJournalEntryLineRepository
List<LiJournalEntryLine> findByJournalEntryJournalEntryId(Long journalEntryId);
// LiAccountingLedgerRepository
boolean existsByTransactionTransactionId(Long transactionId);
List<LiAccountingLedger> findByJournalEntryJournalEntryId(Long journalEntryId);
// DmTransferOrderRepository
Optional<DmTransferOrder> findByTid(String tid);
List<DmTransferOrder> findByTransferOrderStatus(TransferOrderStatus transferOrderStatus);
```

3-2에서는 `run()`에 잔액 비교만 연결해 두 계좌의 결과를 검사합니다. 비교 규칙을 추가하면서 같은 `run()`을 확장하세요. 여섯 기본 규칙의 정상 샘플 결과는 12건입니다. 미완료 거래와 API 오더 규칙은 해당 위반 데이터를 추가한 별도 사례로 확인합니다. 초기 단계부터 모든 private TODO를 호출할 필요는 없습니다.

`InternalReconciliationTestSupport.createSampleData()`는 박개발 씨의 `330-001`, 강동원 씨의 `330-002`, 초기 입금 1,000원과 송금 300원, 현재 잔액 700원과 300원을 직접 적재합니다. 비교 알고리즘과 송금 Service는 호출하지 않습니다. 고객 및 원장 엔티티 매핑과 상태 변경 메서드는 앞 강의에서 완성되어 있어야 합니다.

`DmTransaction`의 시각 인자 생성자와 `complete(Instant)`, `LmJournalEntry.post(Instant)`는 1강의 틀에 이미 선언되어 있습니다. 중복 선언하지 않고 구현을 이어갑니다. 샘플 기준일은 `2026-09-15`이고 시각은 UTC입니다.

### 3-6. 회계원장의 차변과 대변 비교하기 / 6-3. 기본 규칙이 놓치는 오류도 테스트하기

`materials/lecture-03/01-internal/corruption-cases.json`은 DB를 변경할 대상과 기대 상태를 구분한 실험 자료입니다. 자동으로 DB를 수정하지 않습니다. 각 사례는 정상 샘플을 새로 적재한 상태에서 시작하고, `T-TRANSFER`의 행만 수정합니다.

| 변경 대상 | 거래와 전표 | 전표와 회계원장 | 회계원장 차대 합계 |
| --- | --- | --- | --- |
| 원장 양쪽 금액만 400원 | NORMAL | MISMATCH | NORMAL |
| 전표 상세와 원장 양쪽 금액을 모두 400원 | MISMATCH | MISMATCH | NORMAL |

고객예수금 원장의 고객 계좌만 다른 정상 계좌로 바꾼 사례도 추가하세요. 단순히 금액이 같다는 이유로 정상 처리해서는 안 됩니다. 비교 결과를 저장하면서 원본 금융 기록을 수정하지 않습니다.

### 4-3. 외부 대사 설계하기 / 4-5. Service에서 자료를 비교하고 결과 저장하기

외부 자료 묶음을 추가하기 전에 `DmTransferOrder`에 다음을 추가합니다.

- `String externalInstitutionCode` 필드와 `getExternalInstitutionCode()`를 추가합니다. DB 길이는 30, 생성 후 변경하지 않는 필드입니다.
- 기존 10인자 생성자를 유지하고 마지막에 `String externalInstitutionCode`를 받는 11인자 생성자를 추가합니다. 기존 생성자는 null 기관을 전달해 위임하도록 정리하고, 기관 값이 있으면 `[A-Z0-9_-]{1,30}` 형식인지 검사합니다. 기존 필드 초기화와 `PROCESSING` 상태를 보존합니다.
- `complete(..., Instant)`는 2강에서 작성한 것을 그대로 사용합니다. HTTP 송금 Command에는 기관 입력이 없습니다. 3강부터는 아래의 서버 설정값을 실제 API 경로의 새 오더에 연결합니다. 기존 10인자 형태는 기관을 지정하지 않는 직접 생성 사례에 사용합니다.

추가할 생성자의 계약은 다음과 같습니다. 본문은 위 조건에 맞춰 기존 초기화를 재사용하세요.

```java
public DmTransferOrder(String idempotencyKey, String requestHash, String tid, String gid,
    String journalTid, String accountingLedgerTid, DmAccount withdrawalAccount, DmAccount depositAccount,
    BigDecimal amount, String currencyCode, String externalInstitutionCode) {
    throw new UnsupportedOperationException("TODO [특강 3 / 3-1-3] 기존 초기화와 기관 코드 검증을 연결하세요.");
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

`ReconciliationTestSupport`는 같은 초기 입금과 송금에 외부 비교용 오더를 함께 준비합니다. 기관은 `OTHER-BANK`, 통화는 `KRW`, 완료 시각은 기준일 UTC 12:00입니다. 실제 은행 통신은 하지 않습니다. 내부 대사는 현재 전체 원장과 잔액을 비교하고, 외부 대사는 기관과 UTC 하루 범위를 한정합니다.

### 4-6-1. 제공된 JSON 파일의 입력과 기대 결과 구분하기

파일은 `materials/lecture-03/02-external/src/test/resources/settlement/`에 있습니다. `SettlementJson.read()`가 반환한 `input`의 세 값만 `SettlementService.calculate()`에 전달합니다. `expected`와 `expectedError`는 테스트에서만 사용하세요. `SettlementJson.read()`는 중복 TID나 잘못된 금액도 그대로 DTO로 변환하므로 Service의 입력 검증을 확인할 수 있습니다.

| 파일 | 입력의 의미 | 기대 결과 |
| --- | --- | --- |
| `normal.json` | 내부와 외부 300원 | 차이 없음 |
| `amount-mismatch.json` | 같은 TID의 금액 불일치 | 해당 상세 불일치 |
| `missing-bank.json` | 내부에만 송금 존재 | 외부 누락 |
| `external-only.json` | 외부에만 추가 거래 존재 | 내부 누락 |
| `offsetting-differences.json` | 두 거래의 차이가 서로 상쇄 | 합계 차이 0, 상세 불일치 2건 |
| `zero-external-only.json` | 외부에만 0원 기록 존재 | 없는 행과 0원을 구분한 불일치 |
| `invalid/*.json` 7개 | 중복 TID, 누락된 TID와 금액, 음수, 범위 및 자릿수 초과 | 예외, 결과 행 추가 없음 |

모든 자료는 기본 과제입니다. 운영 규모의 분할 조회와 장애 재시작은 추가 탐구 주제입니다. `ReconciliationService`의 합계 계산 메서드는 제공하지만 어떤 자료를 비교할지, 누락을 어떻게 판정할지는 직접 작성합니다.

### 5-1. 매일 UTC 02:00에 대사하도록 예약하기

`ReconciliationSchedulingConfig`의 예약 활성화와 `ReconciliationScheduler.runDaily()`의 `@Scheduled`를 작성합니다. 설정 키는 `reconciliation.schedule.cron`, 시간대는 UTC입니다. 기본 cron은 `-`로 비활성화하고 `application-batch.yml`의 기본 예약을 UTC 02:00으로 작성하세요. 활성화할 때 `batch` 프로필을 사용합니다.

고정 Clock 호출 테스트는 전달 날짜와 Service 호출을 검사합니다. 실제 예약이 발동하는지 확인하려면 구현 후 로컬 앱에서 별도로 관찰하세요. 실행일과 조회 대상 날짜는 같은 개념이 아니므로 현재 내부 대사의 범위를 유지합니다.

## 4강: 보안과 기존 기능 연결하기

### 0. 실습 준비: 3강 프로젝트에 보안 의존성 추가하기

`materials/lecture-04/dependencies.gradle`의 항목만 기존 `dependencies` 블록에 추가합니다. `01-security/src`는 새 파일입니다. `security` 프로필의 YAML은 기존 application.yml을 교체하지 않습니다. 테스트에는 `test`, `security-test` 프로필을 함께 사용합니다. 앞 강의의 Spring 통합 테스트도 보안 도입 후에는 이 프로필을 함께 쓰거나 테스트 설정에서 같은 속성을 제공하세요.

### 2-5-1. 현재 계정의 역할을 Spring Security 권한으로 연결하기

`security.config.SecurityProperties`의 타입과 `@ConfigurationProperties(prefix = "app.security")`, `SecurityConfig`의 `@EnableConfigurationProperties` 등록은 준비되어 있습니다. `issuer`, `long accessTokenTtlMinutes`, `jwtSecret`, `cryptoPassword`, `cryptoSalt`는 YAML의 kebab-case 이름과 연결됩니다.

Properties의 compact 생성자에서 기본 issuer `sparta-fintech`와 양수가 아닌 TTL의 기본값 30분을 적용하고, JWT 서명 키 `jwtSecret`이 32자 이상인지, 암호화 키 생성에 쓰는 `cryptoPassword`와 `cryptoSalt`가 비어 있지 않은지 검증하세요. 기본값은 매개변수에 먼저 적용한 뒤 검증합니다. 제공된 `validate()`의 본문만으로 생성자 매개변수를 바꿀 수는 없으므로 기본값 처리는 compact 생성자에 작성합니다. 초기에는 값 검증 TODO가 있어 Properties 생성과 Spring 기동이 실패합니다. smoke 검사는 선언의 연결만 확인합니다.

`AccountJwtAuthenticationConverter`는 JWT를 Spring Security 인증 객체로 변환하는 Converter 클래스입니다. 검증된 JWT의 계정 식별값으로 현재 DB의 계정 상태와 역할을 확인하고 인증 객체에 연결하세요.

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

`SecurityTestSupport.createSecurityFixture(ownerBalance, dailyLimit)`은 `owner`와 `other`, `410-001`과 `410-002`, 한도와 잔액만 준비합니다. 로그인 비밀번호는 `owner-pass`, `other-pass`이며 앞에서 구현한 PasswordEncoder와 암호화 Service를 사용합니다. 잔액만 준비한 샘플의 대사가 정상이라고 기대하지 않습니다.

샘플의 `owner-mfa-secret`과 `other-mfa-secret`은 암호화 실습용 시크릿입니다. 추가 인증 기능은 구현하지 않습니다. 로그인 비밀번호, JWT 서명 키, 암호화 대상 시크릿의 용도를 구분하세요.

초기 원장이 필요한 통합 실습은 잔액 0원으로 준비하고, `TransactionTemplate` 안에서 `FinancialTestData.openingDeposit()`을 한 번 호출합니다. 공개 메서드 `DmAccountBalance.increase()`와 `LedgerPostingService.post()`가 선행 구현입니다. 9인자 원장 DTO 생성자와 5인자 AccountPosting을 사용하며 `balanceAfter`는 `getLedgerBalance()`로 전달합니다.

고정 TID `T-OPENING`, 현금 코드 `100101`을 새로 저장하므로 반복 테스트 전에 H2 DB를 초기화하세요. 지급보류 사례는 별도 테스트에서 원장잔액 100원과 사용가능잔액 80원으로 시작해 100원을 입금합니다. 결과는 200원과 180원이고 저장할 `balanceAfter`는 200원입니다. 이 사례는 잔액 스냅샷 검증용이며 앞선 100원의 원장까지 준비한 정상 대사 사례와 구분합니다.

## 자료 링크를 연결할 경로

Git 주소가 정해지면 해당 강의의 자료 링크에 아래 경로를 연결합니다.

- 1강 1-2와 4-2: 루트 `src`, `materials/lecture-01`, 이 문서의 1강 안내
- 2강 1-3: `materials/lecture-02/01-request`
- 2강 6-3: `materials/lecture-02/02-processing/src/main/java/com/sparta/fintech/ledger/transfer/service/FinancialIdGenerator.java`
- 2강 6-6과 7-1: `materials/lecture-02/03-http`, `materials/lecture-02/02-processing/src/test`
- 3강 3-1-3: `materials/lecture-03/01-internal/src/test`
- 3강 4-6: `materials/lecture-03/02-external/src/test`
- 3강 5장: `materials/lecture-03/03-scheduling`
- 4강 2-5-1과 7-1-2: `materials/lecture-04/01-security`
- 4강 8-4: `materials/lecture-04/02-integration`

TODO: 공개할 학생용 Git 저장소 주소를 정한 뒤 위 상대 경로에 연결합니다.

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
- `materials/lecture-02/02-processing/src/test/java/com/sparta/fintech/ledger/preparation/TransferPreparationSmokeTest.java`
- `materials/lecture-02/02-processing/src/test/java/com/sparta/fintech/ledger/support/ConcurrentRequests.java`
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
- `materials/lecture-03/01-internal/src/main/java/com/sparta/fintech/ledger/domain/ReconciliationResultStatus.java`
- `materials/lecture-03/01-internal/src/main/java/com/sparta/fintech/ledger/domain/ReconciliationRunStatus.java`
- `materials/lecture-03/01-internal/src/main/java/com/sparta/fintech/ledger/domain/ReconciliationTargetType.java`
- `materials/lecture-03/01-internal/src/main/java/com/sparta/fintech/ledger/domain/SiReconciliationResult.java`
- `materials/lecture-03/01-internal/src/main/java/com/sparta/fintech/ledger/domain/SsReconciliationRun.java`
- `materials/lecture-03/01-internal/src/main/java/com/sparta/fintech/ledger/reconciliation/service/ReconciliationRunResult.java`
- `materials/lecture-03/01-internal/src/main/java/com/sparta/fintech/ledger/reconciliation/service/ReconciliationService.java`
- `materials/lecture-03/01-internal/src/main/java/com/sparta/fintech/ledger/repository/SiReconciliationResultRepository.java`
- `materials/lecture-03/01-internal/src/main/java/com/sparta/fintech/ledger/repository/SsReconciliationRunRepository.java`
- `materials/lecture-03/01-internal/src/test/java/com/sparta/fintech/ledger/exercise/ReconciliationExerciseTest.java`
- `materials/lecture-03/01-internal/src/test/java/com/sparta/fintech/ledger/support/InternalReconciliationTestSupport.java`
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
- `materials/lecture-04/01-security/src/test/java/com/sparta/fintech/ledger/preparation/SecurityPreparationSmokeTest.java`
- `materials/lecture-04/01-security/src/test/java/com/sparta/fintech/ledger/support/SecurityTestSupport.java`
- `materials/lecture-04/01-security/src/test/resources/application-security-test.yml`
- `materials/lecture-04/02-integration/src/test/java/com/sparta/fintech/ledger/exercise/OpeningDepositExerciseTest.java`
- `materials/lecture-04/02-integration/src/test/java/com/sparta/fintech/ledger/support/FinancialTestData.java`
- `materials/lecture-04/dependencies.gradle`
- `materials/lecture-04/security.env.example`

</details>
