package com.oh.sec.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoleReq {

    @NotNull
    private String name;

}
