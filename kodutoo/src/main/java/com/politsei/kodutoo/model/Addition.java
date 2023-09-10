package com.politsei.kodutoo.model;

import lombok.Data;

@Data
public class Addition {
    private int addend1;
    private int addend2;
    private int sum;

    public Addition(int addend1, int addend2) {
        this.addend1 = addend1;
        this.addend2 = addend2;
        this.sum = addend1 + addend2;
    }
}