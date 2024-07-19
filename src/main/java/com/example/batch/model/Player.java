package com.example.batch.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Player implements Serializable {
    private String name;
    private String surname;
    private String height;
}
