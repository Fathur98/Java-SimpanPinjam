package com.example.alami.service;

import com.example.alami.model.request.RequestAddTransaction;
import com.example.alami.model.request.RequestAddUser;
import com.example.alami.model.response.ResponseTransaction;
import com.example.alami.model.response.ResponseUser;
import org.springframework.stereotype.Service;

@Service
public interface ServiceApplication {

    ResponseUser getUsers();

    ResponseUser addUser(RequestAddUser requestAddUser);

    ResponseTransaction getTransactionsByDate(String from, String to);

    ResponseTransaction getTransactionsByUserId(String id);

    ResponseTransaction addTransaction(RequestAddTransaction requestAddTransaction);

}
