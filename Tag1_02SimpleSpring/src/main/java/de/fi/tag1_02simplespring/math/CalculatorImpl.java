package de.fi.tag1_02simplespring.math;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Qualifier("impl")
public class CalculatorImpl implements Calculator {

    @Override
    public double add(double a, double b){
         return a+b;
    }

    @Override
    public double sub(double a, double b){
        return add(a , -b);
    }
}
