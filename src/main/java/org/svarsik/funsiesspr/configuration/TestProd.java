package org.svarsik.funsiesspr.configuration;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("prod")
@Component
public class TestProd implements TestInterface{
    @Override
    public void test() {
        System.out.println("Test Prod");
    }
}
