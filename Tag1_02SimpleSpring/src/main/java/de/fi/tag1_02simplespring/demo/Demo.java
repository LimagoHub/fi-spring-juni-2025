package de.fi.tag1_02simplespring.demo;

import de.fi.tag1_02simplespring.translator.Translator;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Lazy
//@Scope("prototype")
@Scope("prototype")
public class Demo {



    private final String message ;
    private final Translator translator;

    //@Autowired
    public Demo(final Translator translator,@Value("${Demo.gruss}") final String message ) {
        this.translator = translator;
        this.message = message;
        System.out.println(translator.translate("Constructor Demo"));
        System.out.println(translator.translate(message));
    }

    @PostConstruct
    public void init() {
        System.out.println(translator.translate(message));
    }

    @PreDestroy
    public void destructor(){
        System.out.println("...und Tschuess");
    }
}
