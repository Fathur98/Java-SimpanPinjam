package com.example.alami.unit;

import com.example.alami.service.impl.ServiceApplicationImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiceApplicationImplUnitTest {

    ServiceApplicationImpl application = new ServiceApplicationImpl();

    @Test
    void testIsTypeTransaction1() {
        Assertions.assertTrue(application.isTypeTransaction("PENYERAHAN DANA"));
    }

    @Test
    void testIsTypeTransaction2() {
        Assertions.assertFalse(application.isTypeTransaction("PENYEMANGAT DANA"));
    }

    @Test
    void testGetDate1() {
        try {
            Date date = new SimpleDateFormat("yyyyMMdd").parse("20220424");
            Assertions.assertEquals(date, application.getDate("24042022"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetDate2() {
        try {
            Date date = new SimpleDateFormat("yyyyMMdd").parse("20220424");
            Assertions.assertNotEquals(date, application.getDate("24042021"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void isFormatDate1() {
        Assertions.assertTrue(application.isFormatDate("24042022"));
    }

    @Test
    void isFormatDate2() {
        Assertions.assertFalse(application.isFormatDate("32042022"));
    }

}
