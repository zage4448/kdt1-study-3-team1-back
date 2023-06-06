package com.example.demo.account.controller.form;

import com.example.demo.account.entity.RoleType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class AccountLoginResponseForm {

    private UUID userToken;

    public AccountLoginResponseForm(UUID userToken) {
        this.userToken = userToken;
    }
}