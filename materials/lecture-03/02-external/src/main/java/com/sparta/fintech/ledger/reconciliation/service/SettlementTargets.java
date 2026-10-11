package com.sparta.fintech.ledger.reconciliation.service;

import com.sparta.fintech.ledger.domain.SiSettlementSampleObligation;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.*;

/** 금액뿐 아니라 TID/GID·방향·자료 ID까지 결합해 서로 다른 정산 자료를 구분한다. */
final class SettlementTargets {
    private SettlementTargets() {}
    static String fingerprint(List<SiSettlementSampleObligation> targets) {
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            try (DataOutputStream out = new DataOutputStream(bytes)) {
                for (var row : targets.stream().sorted(Comparator.comparing(SiSettlementSampleObligation::getTargetTid)).toList()) {
                    out.writeLong(row.getSettlementSampleObligationId());
                    for (String value : List.of(row.getBaseDate().toString(), row.getExternalInstitutionCode(), row.getCurrencyCode(),
                        row.getTargetTid(), row.getTargetGid(), row.getPaymentDirection().name(), row.getAmount().toPlainString())) {
                        byte[] data = value.getBytes(StandardCharsets.UTF_8); out.writeInt(data.length); out.write(data);
                    }
                }
            }
            return HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(bytes.toByteArray()));
        } catch (IOException | NoSuchAlgorithmException exception) { throw new IllegalStateException(exception); }
    }
}
