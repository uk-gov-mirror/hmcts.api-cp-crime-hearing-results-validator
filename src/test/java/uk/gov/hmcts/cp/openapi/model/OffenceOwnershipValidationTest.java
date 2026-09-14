package uk.gov.hmcts.cp.openapi.model;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.validation.Validation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class OffenceOwnershipValidationTest {

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "   "})
    void missing_or_blank_owner_should_fail_validation(String owner) {
        try (var factory = Validation.buildDefaultValidatorFactory()) {
            var offence = OffenceDto.builder().defendantId(owner).build();
            assertThat(factory.getValidator().validateProperty(offence, "defendantId")).isNotEmpty();
        }
    }

    @Test
    void supplied_owner_should_pass_validation() {
        try (var factory = Validation.buildDefaultValidatorFactory()) {
            var offence = OffenceDto.builder().defendantId("d1").build();
            assertThat(factory.getValidator().validateProperty(offence, "defendantId")).isEmpty();
        }
    }
}
