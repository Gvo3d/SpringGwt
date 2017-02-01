package com.hellogwt.server;

import com.hellogwt.client.GreetingService;
import org.springframework.stereotype.Service;

@Service("greetingService")
public class GreetingServiceImpl implements GreetingService {

    public String greet(String name) {
        return "Hello, " + name + "!";
    }
}