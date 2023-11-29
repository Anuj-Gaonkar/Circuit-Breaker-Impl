package com.neosoft.resilience.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    private String activity;
    private String type;
    private String link;
    private String key;
    private Integer participants;
    private Double price;
    private Double accessibility;
}
