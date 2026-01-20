package org.svarsik.funsiesspr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.svarsik.funsiesspr.configuration.AppProperties;
import org.svarsik.funsiesspr.configuration.TestInterface;
import org.svarsik.funsiesspr.controller.TestPrototype;
import org.svarsik.funsiesspr.controller.TestRequest;
import org.svarsik.funsiesspr.controller.TestSingleton;

@SpringBootApplication
public class FunsiesSprApplication {
    static void main(String[] args) {

         ConfigurableApplicationContext ctx = SpringApplication.run(FunsiesSprApplication.class, args);

        System.out.println(ctx.getBean(AppProperties.class).getEnv());

        ctx.getBean(TestInterface.class).test();
    }
}
