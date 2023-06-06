package com.example.demo.account.controller.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountRoleCheckRequestForm {
    private String userToken;

    public AccountRoleCheckRequestForm(String userToken) {
        this.userToken = userToken;
    }
}
