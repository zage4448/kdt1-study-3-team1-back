package com.example.demo.account.service;

import com.example.demo.account.controller.form.AccountLoginRequestForm;
import com.example.demo.account.controller.form.AccountLoginResponseForm;
import com.example.demo.account.controller.form.AccountRegisterRequest;
import com.example.demo.account.entity.Role;

public interface AccountService {
    Boolean register(AccountRegisterRequest registerRequest);

    Long login(AccountLoginRequestForm requestForm);

    Role getRoleById(Long accountId);

    Boolean checkEmailDuplication(String email);
}