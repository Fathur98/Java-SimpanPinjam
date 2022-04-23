package com.example.alami.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TRANSACTIONS")
public class EntityTransaction {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "AMOUNT")
    private String amount;

    @Column(name = "DATE")
    private Date date;

}
