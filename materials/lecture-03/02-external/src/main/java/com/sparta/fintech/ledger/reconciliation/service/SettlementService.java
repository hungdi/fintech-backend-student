package com.sparta.fintech.ledger.reconciliation.service;

import com.sparta.fintech.ledger.domain.DmTransferOrder;
import com.sparta.fintech.ledger.domain.SiSettlementDetail;
import com.sparta.fintech.ledger.domain.SmSettlement;
import com.sparta.fintech.ledger.domain.TransferOrderStatus;
import com.sparta.fintech.ledger.repository.DmTransferOrderRepository;
import com.sparta.fintech.ledger.repository.SiSettlementDetailRepository;
import com.sparta.fintech.ledger.repository.SmSettlementRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
/** TODO [특강 3 / 4-5] 날짜, 기관 및 통화로 자료를 한정하고 TID별 누락과 차이를 저장하세요. */
public class SettlementService {

    private final DmTransferOrderRepository transferOrderRepository;
    private final SmSettlementRepository settlementRepository;
    private final SiSettlementDetailRepository settlementDetailRepository;

    public SettlementService(
        DmTransferOrderRepository transferOrderRepository,
        SmSettlementRepository settlementRepository,
        SiSettlementDetailRepository settlementDetailRepository
    ) {
        this.transferOrderRepository = transferOrderRepository;
        this.settlementRepository = settlementRepository;
        this.settlementDetailRepository = settlementDetailRepository;
    }

    public SettlementCalculationResult calculate(
        LocalDate baseDate,
        String externalInstitutionCode,
        List<ExternalSettlementItem> externalItems
    ) {
        // TODO [특강 3 / 4-5] 날짜, 기관 및 통화로 자료를 한정하고 TID별 누락과 차이를 저장하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 4-5] 날짜, 기관 및 통화로 자료를 한정하고 TID별 누락과 차이를 저장하세요.");
    }

    public SettlementCalculationResult calculate(LocalDate baseDate, String externalInstitutionCode,
                                                  String currencyCode, List<ExternalSettlementItem> externalItems) {
        // TODO [특강 3 / 4-5] 날짜, 기관 및 통화로 자료를 한정하고 TID별 누락과 차이를 저장하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 4-5] 날짜, 기관 및 통화로 자료를 한정하고 TID별 누락과 차이를 저장하세요.");
    }

    private Map<String, ExternalSettlementItem> indexExternalItems(List<ExternalSettlementItem> externalItems) {
        // TODO [특강 3 / 4-6-3] 누락된 TID와 금액, 중복 TID, 음수와 저장 범위 초과를 결과 저장 전에 거절하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 4-6-3] 누락된 TID와 금액, 중복 TID, 음수와 저장 범위 초과를 결과 저장 전에 거절하세요.");
    }

    private void requireText(String value, int maxLength, String name) {
        // TODO [특강 3 / 4-5] 날짜, 기관 및 통화로 자료를 한정하고 TID별 누락과 차이를 저장하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 4-5] 날짜, 기관 및 통화로 자료를 한정하고 TID별 누락과 차이를 저장하세요.");
    }

    private String settlementReason(
        InternalSettlementItem internal,
        ExternalSettlementItem external,
        BigDecimal internalAmount,
        BigDecimal externalAmount
    ) {
        // TODO [특강 3 / 4-4-2] 내부 누락, 외부 누락과 금액 차이를 구분한 사유를 작성하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 4-4-2] 내부 누락, 외부 누락과 금액 차이를 구분한 사유를 작성하세요.");
    }

    private record InternalSettlementItem(
        String tid,
        String gid,
        BigDecimal amount
    ) {
    }
}
