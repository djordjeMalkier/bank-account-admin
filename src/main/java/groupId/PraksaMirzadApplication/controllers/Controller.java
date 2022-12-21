package groupId.PraksaMirzadApplication.controllers;

import groupId.PraksaMirzadApplication.model.AuthenticationRequest;
import groupId.PraksaMirzadApplication.dto.BankAccountDTO;
import groupId.PraksaMirzadApplication.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/account_admin", method = RequestMethod.GET)
public class Controller {

    private final Service service;


    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> auth(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.auth(request));
    }

    @PostMapping("/addBankAccount")
    public ResponseEntity<BankAccountDTO> addBankAccount(
            @RequestBody BankAccountDTO bankAccountDTO,
            @RequestHeader (HttpHeaders.AUTHORIZATION) String token
    ) {

        return ok(service.addBankAccount(bankAccountDTO, token));
    }

    @DeleteMapping("/deleteBankAccount")
    public ResponseEntity<BankAccountDTO> deleteBankAccount(
            @RequestParam String personalId,
            @RequestParam Integer bankAccountId,
            @RequestHeader (HttpHeaders.AUTHORIZATION) String token
    ) {
        return ok(service.deleteBankAccount(personalId,bankAccountId,token));
    }
}
