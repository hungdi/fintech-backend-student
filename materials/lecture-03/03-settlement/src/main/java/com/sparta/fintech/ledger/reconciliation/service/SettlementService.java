package com.sparta.fintech.ledger.reconciliation.service;

import com.sparta.fintech.ledger.domain.*;
import com.sparta.fintech.ledger.repository.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

/** 정산 금액을 계산하고 담당자가 확인한다. 외부 자금 이체는 실행하지 않는다. */
@Service
/** TODO [특강 3 / 정산-5~정산-8] 범위별 지급·수취를 집계하고 최신 표본 대사와 연결하세요. MISMATCH도 CALCULATED를 저장하되 현재 원본이 같고 최신 대사가 NORMAL일 때만 확인합니다. 미확정 재계산은 같은 ID, 확정 금액 덮어쓰기 금지입니다. 필드·단순 getter·Service 주입은 제공합니다. */
public class SettlementService {
    private final SiSettlementSampleObligationRepository obligationRepository;
    private final SmExternalReconciliationRepository reconciliationRepository;
    private final SmSettlementRepository settlementRepository;
    public SettlementService(SiSettlementSampleObligationRepository obligations,
                             SmExternalReconciliationRepository reconciliations, SmSettlementRepository settlements) {
        obligationRepository = obligations; reconciliationRepository = reconciliations; settlementRepository = settlements;
    }
    /** MISMATCH도 계산 자료는 남기되, confirm에서 확정을 제한한다. */

    public SettlementCalculationResult calculate(LocalDate date, String institution, String currency, Long reconciliationId) {
        // TODO [특강 3 / 정산-5~정산-8] 범위·출처·최신성·원본 검증과 집계·재계산·확정 트랜잭션을 구현하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 정산-5~정산-8] 범위·출처·최신성·원본 검증과 집계·재계산·확정 트랜잭션을 구현하세요.");
    }

    public SettlementCalculationResult confirm(Long settlementId) {
        // TODO [특강 3 / 정산-5~정산-8] 범위·출처·최신성·원본 검증과 집계·재계산·확정 트랜잭션을 구현하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 정산-5~정산-8] 범위·출처·최신성·원본 검증과 집계·재계산·확정 트랜잭션을 구현하세요.");
    }
    private SmExternalReconciliation requireLinkedRun(LocalDate date, String institution, String currency, Long id) {
        // TODO [특강 3 / 정산-5~정산-8] 범위·출처·최신성·원본 검증과 집계·재계산·확정 트랜잭션을 구현하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 정산-5~정산-8] 범위·출처·최신성·원본 검증과 집계·재계산·확정 트랜잭션을 구현하세요.");
    }
    private List<SiSettlementSampleObligation> targets(LocalDate date, String institution, String currency) {
        // TODO [특강 3 / 정산-5~정산-8] 범위·출처·최신성·원본 검증과 집계·재계산·확정 트랜잭션을 구현하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 정산-5~정산-8] 범위·출처·최신성·원본 검증과 집계·재계산·확정 트랜잭션을 구현하세요.");
    }
    private BigDecimal sum(List<SiSettlementSampleObligation> targets, SettlementDirection direction) {
        // TODO [특강 3 / 정산-5~정산-8] 범위·출처·최신성·원본 검증과 집계·재계산·확정 트랜잭션을 구현하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 정산-5~정산-8] 범위·출처·최신성·원본 검증과 집계·재계산·확정 트랜잭션을 구현하세요.");
    }
    // 제공 코드: 검증·집계는 위 업무 메서드에서 작성하고, 여기서는 getter를 DTO로 옮깁니다.
    private SettlementCalculationResult result(SmSettlement settlement) {
        return new SettlementCalculationResult(settlement.getSettlementId(), settlement.getBaseDate(), settlement.getExternalInstitutionCode(),
            settlement.getCurrencyCode(), settlement.getPayableAmount(), settlement.getReceivableAmount(), settlement.getNetSettlementAmount(),
            settlement.getSettlementStatus(), settlement.getExternalReconciliation().getExternalReconciliationId());
    }
}
