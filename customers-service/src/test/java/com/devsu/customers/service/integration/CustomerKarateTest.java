package com.devsu.customers.service.integration;

import com.intuit.karate.junit5.Karate;

public class CustomerKarateTest {

    @Karate.Test
    Karate testCustomers() {
        return Karate.run("karate/customer").relativeTo(getClass());
    }
}
