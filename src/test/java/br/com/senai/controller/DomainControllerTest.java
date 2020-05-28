package br.com.senai.controller;

import br.com.senai.model.entity.Domain;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class DomainControllerTest {

    @Test
    public void domainEquals() {
        Domain domain = new Domain();
        assertThat(domain, equalTo(domain));
    }

}