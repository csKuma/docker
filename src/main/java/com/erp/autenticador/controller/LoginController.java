package com.erp.autenticador.controller;

import com.erp.autenticador.model.request.CheckTokenRequest;
import com.erp.autenticador.model.request.LoginDTO;
import com.erp.autenticador.model.response.CheckTokenDTO;
import com.erp.autenticador.model.response.TokenResponse;
import com.erp.autenticador.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/login")
public class LoginController {

    private LoginService service;

    public LoginController(LoginService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TokenResponse> login(@RequestBody @Validated LoginDTO dto){
        return ResponseEntity.ok().body(service.login(dto));
    }
    @PostMapping("/checkToken")
    public ResponseEntity<CheckTokenDTO> checkToken(@RequestBody CheckTokenRequest token){
        return ResponseEntity.ok().body(service.checkToken(token));
    }
}
