package ms.mmontilla.registry.user.repository.datasource.jpa.repository;

import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class BootstrapJpaSessionTest {
    @Autowired
    TestEntityManager em;
    private Session session;
    @BeforeEach
    public void setup(){
        session = em.getEntityManager().unwrap(Session.class);
    }
    @Test
    void contextLoads() {
        Assertions.assertNotNull(session);
    }
}