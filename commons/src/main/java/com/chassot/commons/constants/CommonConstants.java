package com.chassot.commons.constants;

import com.chassot.commons.enumerations.ErrorMessageEnum;

public final class CommonConstants {

    public static final String TIME_ZONE_BRAZIL = "America/Sao_Paulo";

    private CommonConstants() {
        throw new IllegalStateException(ErrorMessageEnum.MISUSE_UTIL_CLASS.getMessage());
    }
}
