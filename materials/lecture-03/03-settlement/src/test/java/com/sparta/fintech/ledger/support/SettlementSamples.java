package com.sparta.fintech.ledger.support;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.fintech.ledger.domain.SettlementDirection;
import com.sparta.fintech.ledger.reconciliation.service.ExternalReconciliationItem;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 실제 은행 통신이나 송금 API 실행 없이 준비한 기관 간 지급·수취 약정의 실습 표본입니다.
 * JSON의 입력과 expected는 분리됩니다. DTO 읽기는 엔티티·업무 TODO를 호출하지 않습니다.
 */
public final class SettlementSamples {
    private SettlementSamples() {}
    public static final LocalDate BASE_DATE = LocalDate.of(2026, 9, 15);
    public static final String INSTITUTION = "OTHER-BANK";
    public static final String CURRENCY = "KRW";

    public record Obligation(String tid, String gid, SettlementDirection direction, BigDecimal amount) {}
    public record Expected(BigDecimal payable, BigDecimal receivable, BigDecimal net, int mismatchCount) {}
    public record Sample(LocalDate baseDate, String externalInstitutionCode, String currencyCode,
                         List<Obligation> internalObligations, List<ExternalReconciliationItem> externalItems,
                         Expected expected) {}

    public static Sample caseA() { return read("case-a.json"); }
    public static Sample caseB() { return read("case-b.json"); }
    public static Sample caseC() { return read("case-c.json"); }
    public static Sample caseD() { return read("case-d.json"); }

    public static Sample read(String name) {
        try (var stream = SettlementSamples.class.getResourceAsStream("/settlement/" + name)) {
            if (stream == null) throw new IOException("정산 표본을 찾을 수 없습니다: " + name);
            JsonNode data = new ObjectMapper().readTree(stream);
            List<Obligation> internal = new ArrayList<>();
            for (JsonNode row : data.path("sampleObligations")) internal.add(new Obligation(
                row.path("targetTid").asText(), row.path("targetGid").asText(),
                SettlementDirection.valueOf(row.path("paymentDirection").asText()), amount(row, "amount")));
            List<ExternalReconciliationItem> external = new ArrayList<>();
            for (JsonNode row : data.path("externalItems")) external.add(new ExternalReconciliationItem(
                row.path("targetTid").asText(), row.path("targetGid").asText(), amount(row, "amount"),
                SettlementDirection.valueOf(row.path("paymentDirection").asText())));
            JsonNode expected = data.path("expected");
            return new Sample(LocalDate.parse(data.path("baseDate").asText()),
                data.path("externalInstitutionCode").asText(), data.path("currencyCode").asText(),
                List.copyOf(internal), List.copyOf(external), new Expected(amount(expected, "payableAmount"),
                    amount(expected, "receivableAmount"), amount(expected, "netSettlementAmount"),
                    expected.path("mismatchCount").asInt()));
        } catch (IOException exception) { throw new UncheckedIOException(exception); }
    }
    private static BigDecimal amount(JsonNode row, String field) { return new BigDecimal(row.path(field).asText()); }
}
