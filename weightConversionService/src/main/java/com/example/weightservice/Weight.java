package com.example.weightservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Weight {
    private String from;
    private String to;
    private double rate;
}
