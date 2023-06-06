package com.example.demo.account.controller;

import com.example.demo.account.controller.form.AccountLoginRequestForm;
import com.example.demo.account.controller.form.AccountLoginResponseForm;
import com.example.demo.account.controller.form.AccountRegisterRequestForm;
import com.example.demo.account.controller.form.AccountRoleCheckRequestForm;
import com.example.demo.account.entity.Role;
import com.example.demo.account.repository.AccountRepository;
import com.example.demo.account.service.AccountService;
import com.example.demo.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.example.demo.account.entity.RoleType.BUSINESS;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    final private AccountService accountService;
    final private RedisService redisService;

    @PostMapping("/create-account")
    public Boolean accountRegister (@RequestBody AccountRegisterRequestForm requestForm) {
        return accountService.register(requestForm.toAccountRegisterRequest());
    }

    @PostMapping("/login")
    public String accountLogin (@RequestBody AccountLoginRequestForm requestForm) {

        Long accountId = accountService.login(requestForm);
        if (accountId != null) {
            UUID userToken = UUID.randomUUID();

            redisService.setKeyAndValue(userToken.toString(), accountId);

            return userToken.toString();
        }
        return null;
    }

    @PostMapping("/check-role")
    public Boolean checkRole (@RequestBody AccountRoleCheckRequestForm requestForm) {

        Long accountId = redisService.getValueByKey(requestForm.getUserToken());

        if (accountId == null) {
            return false;
        }
        else {
           Role role = accountService.getRoleById(accountId);
           if (role.getRoleType().equals(BUSINESS)) {
               return true;
           }
        } return false;
    }
}
