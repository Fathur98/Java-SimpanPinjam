package com.example.alami.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTransaction {

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
    public static class DataTransaction {

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("id")
        private String id;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("user_id")
        private String userId;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("user_name")
        private String userName;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("type")
        private String type;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("currency")
        private String currency;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("amount")
        private String amount;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("date")
        private String date;

    }

}
