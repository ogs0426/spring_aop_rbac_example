package com.oh.sec.eunms;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@RequiredArgsConstructor
public enum AccountProviderType {

    GOOGLE      (6,"go", "google");

    @Getter
    private final Integer ID;

    private final String CODE;

    @Getter
    private final String PROVIDER;

    @JsonCreator
    public static AccountProviderType from(String value) {

        for (AccountProviderType item : AccountProviderType.values()) {
            if(Objects.equals(item.getCODE(), value)) {
                return item;
            }
        }
        return null;
    }

    @JsonValue
    public String getCODE() {
        return CODE;
    }

    public static List<String> getProviderList() {
        List<String> providerList = new ArrayList<>();

        for (AccountProviderType item : AccountProviderType.values()) {
            if(item.getPROVIDER() != null)
                providerList.add(item.getPROVIDER());
        }

        return providerList;
    }

    public static List<String> getOauthCodeList() {
        List<String> codeList = new ArrayList<>();

        for (AccountProviderType item : AccountProviderType.values()) {
            if(item.getPROVIDER() != null)
                codeList.add(item.getCODE());
        }

        return codeList;
    }

    public static AccountProviderType getProviderByCode(String code) {
        for (AccountProviderType item : AccountProviderType.values()) {
            if(Objects.equals(item.getCODE(), code))
                return item;
        }

        return null;
    }

    public static AccountProviderType getProviderById(Integer id) {
        for (AccountProviderType item : AccountProviderType.values()) {
            if(Objects.equals(item.getID(), id))
                return item;
        }

        return null;
    }

    public static Boolean isOauthCode(String code) {
        for (AccountProviderType item : AccountProviderType.values()) {
            if(item.getPROVIDER() != null && item.getCODE().equals(code))
                return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}
