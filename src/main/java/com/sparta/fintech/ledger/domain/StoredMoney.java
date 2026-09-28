package com.sparta.fintech.ledger.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class StoredMoney {
    private StoredMoney() {}

    public static boolean isRepresentable(BigDecimal amount) {
        // TODO [특강 1 / 4-2] 반올림 없이 DECIMAL(19,2)에 들어가는지 판정하세요. 0.005와 10의 17제곱은 허용하지 않습니다.
        throw new UnsupportedOperationException("TODO [특강 1 / 4-2] 반올림 없이 DECIMAL(19,2)에 들어가는지 판정하세요. 0.005와 10의 17제곱은 허용하지 않습니다.");
    }

    public static BigDecimal positive(BigDecimal amount, String name) {
        // TODO [특강 1 / 4-2] 거래 금액이 양수이고 저장 범위에 들어가는지 검사하세요.
        throw new UnsupportedOperationException("TODO [특강 1 / 4-2] 거래 금액이 양수이고 저장 범위에 들어가는지 검사하세요.");
    }

    public static BigDecimal nonNegative(BigDecimal amount, String name) {
        // TODO [특강 1 / 4-2] 잔액과 잔액 스냅샷이 0 이상이고 저장 범위에 들어가는지 검사하세요.
        throw new UnsupportedOperationException("TODO [특강 1 / 4-2] 잔액과 잔액 스냅샷이 0 이상이고 저장 범위에 들어가는지 검사하세요.");
    }

    private static BigDecimal exact(BigDecimal amount, String name) {
        // TODO [특강 1 / 4-2] 불필요한 끝자리 0은 허용하면서 값이 달라지는 반올림을 거절하세요.
        throw new UnsupportedOperationException("TODO [특강 1 / 4-2] 불필요한 끝자리 0은 허용하면서 값이 달라지는 반올림을 거절하세요.");
    }
}
