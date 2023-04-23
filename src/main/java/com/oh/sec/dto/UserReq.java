package com.oh.sec.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserReq {

    @NotNull
    private String name;

    @NotNull
    private String password;
}
