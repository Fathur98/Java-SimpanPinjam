package com.example.alami.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAddTransaction {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("type")
    private String type;

    @JsonProperty("amount")
    private String amount;

}
