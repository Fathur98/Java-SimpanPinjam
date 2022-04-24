package com.example.alami.service.impl;

import com.example.alami.model.entity.EntityTransaction;
import com.example.alami.model.entity.EntityUser;
import com.example.alami.model.request.RequestAddTransaction;
import com.example.alami.model.request.RequestAddUser;
import com.example.alami.model.response.ResponseTransaction;
import com.example.alami.model.response.ResponseUser;
import com.example.alami.repository.RepositoryTransaction;
import com.example.alami.repository.RepositoryUser;
import com.example.alami.service.ServiceApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ServiceApplicationImpl implements ServiceApplication {

    @Autowired
    RepositoryUser repositoryUser;

    @Autowired
    RepositoryTransaction repositoryTransaction;

    @Override
    public ResponseUser getUsers() {
        List<EntityUser> entityUserList = repositoryUser.findAll();

        List<ResponseUser.DataUser> dataUserList = new ArrayList<>();
        for (EntityUser entityUser : entityUserList) {
            String dateOfBirth = new SimpleDateFormat("dd MMM yyyy").format(entityUser.getDateOfBirth());

            ResponseUser.DataUser dataUser = new ResponseUser.DataUser();
            dataUser.setId(String.valueOf(entityUser.getId()));
            dataUser.setName(entityUser.getName());
            dataUser.setDateOfBirth(dateOfBirth.toUpperCase());
            dataUser.setAddress(entityUser.getAddress());

            dataUserList.add(dataUser);
        }

        ResponseUser responseListUser = new ResponseUser();
        responseListUser.setResponseCode("200");
        responseListUser.setResponseMessage("SUCCESS");
        responseListUser.setData(dataUserList);
        return responseListUser;
    }

    @Override
    public ResponseUser addUser(RequestAddUser requestAddUser) {
        String name = requestAddUser.getName();
        String dateOfBirth = requestAddUser.getDateOfBirth();
        String address = requestAddUser.getAddress();

        ResponseUser responseUser = new ResponseUser();
        String responseCode = "400", responseMessage;
        if (name.isEmpty()) {
            responseMessage = "NAME CAN'T BE EMPTY";
        } else if (name.length() > 35) {
            responseMessage = "MAX CHARACTER FOR NAME IS 35 CHARACTERS";
        } else if (!name.replace(" ", "").chars().allMatch(Character::isLetter)) {
            responseMessage = "NAME MUST BE ALPHABETICAL CHARACTERS";
        } else if (dateOfBirth.isEmpty()) {
            responseMessage = "DATE OF BIRTH CAN'T BE EMPTY";
        } else if (dateOfBirth.length() > 8 || !isFormatDate(dateOfBirth)) {
            responseMessage = "DATE OF BIRTH MUST IN DDMMYYYY FORMAT";
        } else if (Objects.requireNonNull(getDate(dateOfBirth)).after(new Date())) {
            responseMessage = "DATE OF BIRTH IS INVALID";
        } else if (address.isEmpty()) {
            responseMessage = "ADDRESS CAN'T BE EMPTY";
        } else if (address.length() > 35) {
            responseMessage = "MAX CHARACTER FOR ADDRESS IS 35 CHARACTERS";
        } else if (!address.replace(" ", "").chars().allMatch(Character::isLetterOrDigit)) {
            responseMessage = "ADDRESS MUST BE ALPHABETICAL OR NUMERICAL CHARACTERS";
        } else {
            String id = "11" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());

            EntityUser entityUser = new EntityUser();
            entityUser.setId(Long.valueOf(id));
            entityUser.setName(name);
            entityUser.setDateOfBirth(getDate(dateOfBirth));
            entityUser.setAddress(address);
            repositoryUser.save(entityUser);

            responseCode = "201";
            responseMessage = "SUCCESS";

            String dateOfBirthNew = new SimpleDateFormat("dd MMM yyyy").format(entityUser.getDateOfBirth());

            ResponseUser.DataUser dataUser = new ResponseUser.DataUser();
            dataUser.setId(String.valueOf(entityUser.getId()));
            dataUser.setName(entityUser.getName());
            dataUser.setDateOfBirth(dateOfBirthNew);
            dataUser.setAddress(entityUser.getAddress());

            responseUser.setData(dataUser);
        }

        responseUser.setResponseCode(responseCode);
        responseUser.setResponseMessage(responseMessage);
        return responseUser;
    }

    @Override
    public ResponseTransaction getTransactionsByDate(String from, String to) {
        ResponseTransaction responseListTransaction = new ResponseTransaction();

        String responseCode = "400", responseMessage;
        if (from.isEmpty() || to.isEmpty()) {
            responseMessage = "DATE CAN'T BE EMPTY";
        } else if (from.length() > 8 || !isFormatDate(from) || to.length() > 8 || !isFormatDate(to)) {
            responseMessage = "DATE MUST IN DDMMYYYY FORMAT";
        } else if (Objects.requireNonNull(getDate(from)).after(new Date())) {
            responseMessage = "DATE IS INVALID";
        } else if (Objects.requireNonNull(getDate(to)).after(new Date())) {
            responseMessage = "DATE IS INVALID";
        } else if (Objects.requireNonNull(getDate(from)).after(getDate(to))) {
            responseMessage = "DATE IS INVALID";
        } else {
            List<EntityTransaction> entityTransactionList = repositoryTransaction.findAllByDateBetween(
                    getDate(from),
                    new Date(Objects.requireNonNull(getDate(to)).getTime() + 1000 * 60 * 60 * 24)
            );

            List<ResponseTransaction.DataTransaction> dataTransactionList = new ArrayList<>();
            for (EntityTransaction entityTransaction : entityTransactionList) {
                String date = new SimpleDateFormat("dd MMM yyyy").format(entityTransaction.getDate());

                ResponseTransaction.DataTransaction dataTransaction = new ResponseTransaction.DataTransaction();
                dataTransaction.setId(String.valueOf(entityTransaction.getId()));
                dataTransaction.setUserId(String.valueOf(entityTransaction.getUserId()));
                dataTransaction.setUserName(entityTransaction.getUserName());
                dataTransaction.setType(entityTransaction.getType());
                dataTransaction.setCurrency(entityTransaction.getCurrency());
                dataTransaction.setAmount(String.valueOf(entityTransaction.getAmount()));
                dataTransaction.setDate(date.toUpperCase());

                dataTransactionList.add(dataTransaction);
            }

            responseCode = "200";
            responseMessage = "SUCCESS";
            responseListTransaction.setData(dataTransactionList);
        }

        responseListTransaction.setResponseCode(responseCode);
        responseListTransaction.setResponseMessage(responseMessage);
        return responseListTransaction;
    }

    @Override
    public ResponseTransaction getTransactionsByUserId(String id) {
        ResponseTransaction responseListTransaction = new ResponseTransaction();
        Optional<EntityUser> entityUser = repositoryUser.findById(Long.valueOf(id));

        String responseCode = "400", responseMessage;
        if (entityUser.isPresent()) {
            List<EntityTransaction> entityTransactionList = repositoryTransaction.findAllByUserId(Long.valueOf(id));

            List<ResponseTransaction.DataTransaction> dataTransactionList = new ArrayList<>();
            for (EntityTransaction entityTransaction : entityTransactionList) {
                String date = new SimpleDateFormat("dd MMM yyyy").format(entityTransaction.getDate());

                ResponseTransaction.DataTransaction dataTransaction = new ResponseTransaction.DataTransaction();
                dataTransaction.setId(String.valueOf(entityTransaction.getId()));
                dataTransaction.setUserId(String.valueOf(entityTransaction.getUserId()));
                dataTransaction.setUserName(entityTransaction.getUserName());
                dataTransaction.setType(entityTransaction.getType());
                dataTransaction.setCurrency(entityTransaction.getCurrency());
                dataTransaction.setAmount(String.valueOf(entityTransaction.getAmount()));
                dataTransaction.setDate(date.toUpperCase());

                dataTransactionList.add(dataTransaction);
            }

            responseCode = "200";
            responseMessage = "SUCCESS";
            responseListTransaction.setData(dataTransactionList);
        } else {
            responseMessage = "USER ID UNREGISTERED";
        }

        responseListTransaction.setResponseCode(responseCode);
        responseListTransaction.setResponseMessage(responseMessage);
        return responseListTransaction;
    }

    @Override
    public ResponseTransaction addTransaction(RequestAddTransaction requestAddTransaction) {
        String userId = requestAddTransaction.getUserId();
        String type = requestAddTransaction.getType();
        String amount = requestAddTransaction.getAmount();

        ResponseTransaction responseTransaction = new ResponseTransaction();
        String responseCode = "400", responseMessage;
        if (userId.isEmpty()) {
            responseMessage = "USER ID CAN'T BE EMPTY";
        } else if (!userId.replace(" ", "").chars().allMatch(Character::isDigit)) {
            responseMessage = "USER ID MUST BE NUMERICAL CHARACTERS";
        } else if (type.isEmpty()) {
            responseMessage = "TRANSACTION TYPE CAN'T BE EMPTY";
        } else if (!isTypeTransaction(type)) {
            responseMessage = "TRANSACTION TYPE UNREGISTERED";
        } else if (amount.isEmpty()) {
            responseMessage = "AMOUNT CAN'T BE EMPTY";
        } else if (!amount.replaceAll("[-+]", "").chars().allMatch(Character::isDigit)) {
            responseMessage = "AMOUNT MUST BE NUMERICAL CHARACTERS";
        } else if (Integer.parseInt(amount) < 0) {
            responseMessage = "AMOUNT CAN'T BE NEGATIVE";
        } else {
            Optional<EntityUser> entityUser = repositoryUser.findById(Long.valueOf(userId));
            if (entityUser.isPresent()) {
                String id = "12" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
                String name = entityUser.get().getName();

                EntityTransaction entityTransaction = new EntityTransaction();
                entityTransaction.setId(Long.valueOf(id));
                entityTransaction.setUserId(Long.valueOf(userId));
                entityTransaction.setUserName(name);
                entityTransaction.setType(type);
                entityTransaction.setCurrency("IDR");
                entityTransaction.setAmount(Long.valueOf(amount));
                entityTransaction.setDate(new Date());
                repositoryTransaction.save(entityTransaction);

                responseCode = "201";
                responseMessage = "SUCCESS";

                String dateNew = new SimpleDateFormat("dd MMM yyyy").format(entityTransaction.getDate());

                ResponseTransaction.DataTransaction dataTransaction = new ResponseTransaction.DataTransaction();
                dataTransaction.setId(String.valueOf(entityTransaction.getId()));
                dataTransaction.setUserId(String.valueOf(entityTransaction.getUserId()));
                dataTransaction.setUserName(entityTransaction.getUserName());
                dataTransaction.setType(entityTransaction.getType());
                dataTransaction.setCurrency(entityTransaction.getCurrency());
                dataTransaction.setAmount(String.valueOf(entityTransaction.getAmount()));
                dataTransaction.setDate(dateNew);

                responseTransaction.setData(dataTransaction);
            } else {
                responseMessage = "USER ID UNREGISTERED";
            }
        }

        responseTransaction.setResponseCode(responseCode);
        responseTransaction.setResponseMessage(responseMessage);
        return responseTransaction;
    }

    public Boolean isTypeTransaction(String type) {
        return type.matches("PENYERAHAN DANA|PENGAMBILAN DANA|PEMINJAMAN DANA|PENGEMBALIAN DANA");
    }

    public Date getDate(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyy");
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.getMessage();
            return null;
        }
    }

    public Boolean isFormatDate(String date) {
        return date.matches("^((0[1-9]|[12]\\d|3[01])(0[1-9]|1[012])(19|2[01])\\d\\d)$");
    }

}
