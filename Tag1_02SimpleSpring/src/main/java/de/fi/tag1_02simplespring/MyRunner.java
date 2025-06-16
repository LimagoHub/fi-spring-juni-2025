package de.fi.tag1_02simplespring;

import de.fi.tag1_02simplespring.demo.Demo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

   //private final Demo demo;
/*
    public MyRunner(final Demo demo) {
        this.demo = demo;
    }
*/

    @Override
    public void run(final String... args) throws Exception {
        System.out.println("Hallo Spring");
    }
}
