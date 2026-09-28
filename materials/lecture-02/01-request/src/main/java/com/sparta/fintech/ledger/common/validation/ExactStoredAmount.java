package com.sparta.fintech.ledger.common.validation;

import com.sparta.fintech.ledger.domain.StoredMoney;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.*;
import java.math.BigDecimal;

@Documented
@Constraint(validatedBy = ExactStoredAmount.Validator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExactStoredAmount {
    String message() default "금액은 반올림 없이 DECIMAL(19,2) 범위로 표현할 수 있어야 합니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<ExactStoredAmount, BigDecimal> {
        @Override
        public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        // TODO [특강 2 / 1-3] 금액 표현 범위를 검사하고 null의 필수 검증은 별도 제약과 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 1-3] 금액 표현 범위를 검사하고 null의 필수 검증은 별도 제약과 연결하세요.");
    }
    }
}
