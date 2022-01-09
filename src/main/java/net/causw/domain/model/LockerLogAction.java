package net.causw.domain.model;

import net.causw.domain.exceptions.BadRequestException;
import net.causw.domain.exceptions.ErrorCode;

import java.util.Arrays;

public enum LockerLogAction {
    ENABLE("enable"),
    DISABLE("disable"),
    REGISTER("register"),
    RETURN("return");

    private String value;

    LockerLogAction(String value) {
        this.value = value;
    }

    public static LockerLogAction of(String value) {
        return Arrays.stream(values())
                .filter(v -> value.equalsIgnoreCase(v.value))
                .findFirst()
                .orElseThrow(
                        () -> new BadRequestException(
                                ErrorCode.INVALID_REQUEST_ROLE,
                                String.format("'%s' is invalid : not supported", value)
                        )
                );
    }
}
