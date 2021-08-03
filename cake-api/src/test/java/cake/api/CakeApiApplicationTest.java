package cake.api;

import cake.api.controller.CakeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CakeApiApplicationTest {

    @Autowired
    private CakeController cakeController;

    @Test
    public void contextLoads() {
        assertNotNull(cakeController);
    }
}
