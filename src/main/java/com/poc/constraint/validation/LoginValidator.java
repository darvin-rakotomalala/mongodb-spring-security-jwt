package com.poc.constraint.validation;

import com.poc.exception.ErrorsEnum;
import com.poc.model.dto.LoginRequestDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class LoginValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return LoginRequestDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoginRequestDTO login = (LoginRequestDTO) target;

        if (StringUtils.isBlank(login.getUsername())) {
            errors.rejectValue("username", "login.username.empty", ErrorsEnum.ERR_MCS_USER_USERNAME_EMPTY.getErrorMessage());
        }
        if (StringUtils.isBlank(login.getPassword())) {
            errors.rejectValue("password", "login.password.empty", ErrorsEnum.ERR_MCS_USER_PASSWORD_EMPTY.getErrorMessage());
        }
    }
}
