package com.example.alami.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUser {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("response_code")
    private String responseCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("response_message")
    private String responseMessage;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("data")
    private Object data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataUser {

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("id")
        private String id;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("name")
        private String name;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("date_of_birth")
        private String dateOfBirth;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("address")
        private String address;

    }

}
