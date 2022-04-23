package com.example.alami.service;

import com.example.alami.model.request.RequestAddTransaction;
import com.example.alami.model.request.RequestAddUser;
import com.example.alami.model.response.ResponseListTransaction;
import com.example.alami.model.response.ResponseListUser;
import com.example.alami.model.response.ResponseTransaction;
import com.example.alami.model.response.ResponseUser;
import org.springframework.stereotype.Service;

@Service
public interface ServiceApplication {

    ResponseListUser getUsers();

    ResponseUser addUser(RequestAddUser requestAddUser);

    ResponseListTransaction getTransactionsByDate(String from, String to);

    ResponseListTransaction getTransactionsByUserId(String id);

    ResponseTransaction addTransaction(RequestAddTransaction requestAddTransaction);

}
