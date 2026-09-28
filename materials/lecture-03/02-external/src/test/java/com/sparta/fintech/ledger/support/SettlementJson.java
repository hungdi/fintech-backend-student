package com.sparta.fintech.ledger.support;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.fintech.ledger.reconciliation.service.ExternalSettlementItem;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** JSON의 입력과 테스트 기대값을 나누어 읽습니다. 입력의 업무 검증은 SettlementService에서 작성하세요. */
public final class SettlementJson {
    private SettlementJson() {}
    public record Input(LocalDate baseDate, String externalInstitutionCode, List<ExternalSettlementItem> externalItems) {}
    public record Sample(Input input, JsonNode expected, String expectedError) {}

    public static Sample read(String resource) throws IOException {
        try (var stream = SettlementJson.class.getResourceAsStream("/settlement/" + resource)) {
            if (stream == null) throw new IOException("샘플 파일을 찾을 수 없습니다: " + resource);
            JsonNode root = new ObjectMapper().readTree(stream);
            List<ExternalSettlementItem> items = new ArrayList<>();
            for (JsonNode item : root.path("externalItems")) {
                String amount = text(item, "amount");
                items.add(new ExternalSettlementItem(text(item, "targetTid"), text(item, "targetGid"),
                    amount == null ? null : new BigDecimal(amount)));
            }
            return new Sample(new Input(LocalDate.parse(root.path("baseDate").asText()),
                root.path("externalInstitutionCode").asText(), List.copyOf(items)),
                root.get("expected"), text(root, "expectedError"));
        }
    }
    private static String text(JsonNode node, String key) {
        JsonNode value = node.get(key);
        return value == null || value.isNull() ? null : value.asText();
    }
}
