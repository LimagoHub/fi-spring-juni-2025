package de.fi.tag1_02simplespring;

import de.fi.tag1_02simplespring.demo.Demo;
import de.fi.tag1_02simplespring.math.Calculator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {


    private final Calculator calculator;

    public MyRunner(@Qualifier("secure") final Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void run(final String... args) throws Exception {
        System.out.println(calculator.add(3,4));
    }
}
