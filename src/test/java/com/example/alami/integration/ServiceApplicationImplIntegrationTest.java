package com.example.alami.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
public class ServiceApplicationImplIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testGetUsers() {
        try {
            MvcResult mvcResult = mockMvc
                    .perform(MockMvcRequestBuilders
                            .get("/api/alami/v1/users"))
                    .andExpect(MockMvcResultMatchers
                            .status()
                            .isOk())
                    .andReturn();
            String content = mvcResult.getResponse().getContentAsString();
            String response = "\"response_code\":\"200\",\"response_message\":\"Success\"";
            Assertions.assertTrue(content.contains(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetTransactionById1() {
        try {
            MvcResult mvcResult = mockMvc
                    .perform(MockMvcRequestBuilders
                            .get("/api/alami/v1/transactions/1120220101000000001"))
                    .andExpect(MockMvcResultMatchers
                            .status()
                            .isOk())
                    .andReturn();
            String content = mvcResult.getResponse().getContentAsString();
            String response = "\"response_code\":\"200\",\"response_message\":\"Success\"";
            Assertions.assertTrue(content.contains(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetTransactionById2() {
        try {
            MvcResult mvcResult = mockMvc
                    .perform(MockMvcRequestBuilders
                            .get("/api/alami/v1/transactions/1120220101000000009"))
                    .andExpect(MockMvcResultMatchers
                            .status()
                            .isOk())
                    .andReturn();
            String content = mvcResult.getResponse().getContentAsString();
            String response = "\"response_code\":\"400\",\"response_message\":\"User Id Unregistered\"";
            Assertions.assertTrue(content.contains(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetTransactionByDate1() {
        try {
            MvcResult mvcResult = mockMvc
                    .perform(MockMvcRequestBuilders
                            .get("/api/alami/v1/transactions/from=18082020/to=30092020"))
                    .andExpect(MockMvcResultMatchers
                            .status()
                            .isOk())
                    .andReturn();
            String content = mvcResult.getResponse().getContentAsString();
            String response = "\"response_code\":\"200\",\"response_message\":\"Success\"";
            Assertions.assertTrue(content.contains(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetTransactionByDate2() {
        try {
            MvcResult mvcResult = mockMvc
                    .perform(MockMvcRequestBuilders
                            .get("/api/alami/v1/transactions/from=32082020/to=30092020"))
                    .andExpect(MockMvcResultMatchers
                            .status()
                            .isOk())
                    .andReturn();
            String content = mvcResult.getResponse().getContentAsString();
            String response = "\"response_code\":\"400\",\"response_message\":\"Date Must in DDMMYYYY Format\"";
            Assertions.assertTrue(content.contains(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAddUser1() {
        try {
            String request = "" +
                    "{" +
                    "\"name\":\"Fathur Rahman Jamil\"," +
                    "\"date_of_birth\":\"27061997\"," +
                    "\"address\":\"Jalan Baladewa Utara No 8\"" +
                    "}";
            MvcResult mvcResult = mockMvc
                    .perform(MockMvcRequestBuilders
                            .post("/api/alami/v1/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(MockMvcResultMatchers
                            .status()
                            .isOk())
                    .andReturn();
            String content = mvcResult.getResponse().getContentAsString();
            String response = "\"response_code\":\"201\",\"response_message\":\"Success\"";
            Assertions.assertTrue(content.contains(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAddUser2() {
        try {
            String request = "" +
                    "{" +
                    "\"name\":\"Fathur Rahman Jamil\"," +
                    "\"date_of_birth\":\"27062099\"," +
                    "\"address\":\"Jalan Baladewa Utara No 8\"" +
                    "}";
            MvcResult mvcResult = mockMvc
                    .perform(MockMvcRequestBuilders
                            .post("/api/alami/v1/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(MockMvcResultMatchers
                            .status()
                            .isOk())
                    .andReturn();
            String content = mvcResult.getResponse().getContentAsString();
            String response = "\"response_code\":\"400\",\"response_message\":\"Date of Birth is Invalid\"";
            Assertions.assertTrue(content.contains(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAddTransaction1() {
        try {
            String request = "" +
                    "{" +
                    "\"user_id\": \"1120220101000000001\"," +
                    "\"type\": \"Penyerahan Dana\"," +
                    "\"amount\": \"2000000\"" +
                    "}";
            MvcResult mvcResult = mockMvc
                    .perform(MockMvcRequestBuilders
                            .post("/api/alami/v1/transactions")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(MockMvcResultMatchers
                            .status()
                            .isOk())
                    .andReturn();
            String content = mvcResult.getResponse().getContentAsString();
            String response = "\"response_code\":\"201\",\"response_message\":\"Success\"";
            Assertions.assertTrue(content.contains(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAddTransaction2() {
        try {
            String request = "" +
                    "{" +
                    "\"user_id\": \"1120220101000000009\"," +
                    "\"type\": \"Penyerahan Dana\"," +
                    "\"amount\": \"2000000\"" +
                    "}";
            MvcResult mvcResult = mockMvc
                    .perform(MockMvcRequestBuilders
                            .post("/api/alami/v1/transactions")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(MockMvcResultMatchers
                            .status()
                            .isOk())
                    .andReturn();
            String content = mvcResult.getResponse().getContentAsString();
            String response = "\"response_code\":\"400\",\"response_message\":\"User Id Unregistered\"";
            Assertions.assertTrue(content.contains(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
