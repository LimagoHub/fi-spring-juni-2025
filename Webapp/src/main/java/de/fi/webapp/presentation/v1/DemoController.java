package de.fi.webapp.presentation.v1;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping(value = "/hallo", produces = MediaType.TEXT_PLAIN_VALUE)
    public String gruss() {
        return "Hallo Rest";
    }
}
