package com.politsei.kodutoo.service;

import com.politsei.kodutoo.model.Addition;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdditionService {

    private final List<Addition> additions = new ArrayList<>();

    public synchronized void addAddition(Addition addition) {
        additions.add(addition);
    }

    public synchronized List<Addition> searchAdditions(Integer value, boolean ascending) {
        return additions.stream()
                .filter(addition -> addition.getAddend1() == value ||
                        addition.getAddend2() == value ||
                        addition.getSum() == value)
                .sorted((a1, a2) -> ascending ? Integer.compare(a1.getSum(), a2.getSum()) :
                        Integer.compare(a2.getSum(), a1.getSum()))
                .collect(Collectors.toList());
    }
}

