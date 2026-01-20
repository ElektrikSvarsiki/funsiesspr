package org.svarsik.funsiesspr.configuration;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class TestDev implements TestInterface{
    @Override
    public void test() {
        System.out.println("Test Dev");
    }
}
