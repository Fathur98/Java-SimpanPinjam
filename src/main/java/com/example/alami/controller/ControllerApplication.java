package com.example.alami.controller;

import com.example.alami.model.request.RequestAddTransaction;
import com.example.alami.model.request.RequestAddUser;
import com.example.alami.model.response.ResponseTransaction;
import com.example.alami.model.response.ResponseUser;
import com.example.alami.service.ServiceApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/alami/v1")
public class ControllerApplication {

    @Autowired
    ServiceApplication serviceApplication;

    @GetMapping(value = "/users")
    public ResponseUser getUsers() {
        return serviceApplication.getUsers();
    }

    @PostMapping(value = "/users")
    public ResponseUser addUser(@RequestBody RequestAddUser requestAddUser) {
        return serviceApplication.addUser(requestAddUser);
    }

    @GetMapping(value = "/transactions/from={from}/to={to}")
    public ResponseTransaction getTransactionByDate(@PathVariable String from, @PathVariable String to) {
        return serviceApplication.getTransactionsByDate(from, to);
    }

    @GetMapping(value = "/transactions/{id}")
    public ResponseTransaction getTransactionByUserId(@PathVariable String id) {
        return serviceApplication.getTransactionsByUserId(id);
    }

    @PostMapping(value = "/transactions")
    public ResponseTransaction addTransaction(@RequestBody RequestAddTransaction requestAddTransaction) {
        return serviceApplication.addTransaction(requestAddTransaction);
    }

}
