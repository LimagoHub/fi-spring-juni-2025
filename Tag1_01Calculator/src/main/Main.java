package main;

import client.CalcClient;
import math.Calculator;
import math.CalculatorImpl;
import math.CalculatorLogger;
import math.CalculatorSecure;

public class Main {
    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println();
            }
        });


        Calculator calculator = new CalculatorImpl();
        calculator = new CalculatorLogger(calculator);
        calculator = new CalculatorSecure(calculator);
        CalcClient calcClient = new CalcClient(calculator);
        calcClient.go();
    }
}