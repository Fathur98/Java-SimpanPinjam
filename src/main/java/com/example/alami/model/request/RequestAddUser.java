package com.example.alami.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAddUser {

    @JsonProperty("name")
    private String name;

    @JsonProperty("date_of_birth")
    private String dateOfBirth;

    @JsonProperty("address")
    private String address;

}
