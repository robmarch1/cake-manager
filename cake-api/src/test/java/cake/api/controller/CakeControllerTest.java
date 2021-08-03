package cake.api.controller;

import cake.api.model.Cake;
import cake.api.model.CakeEntity;
import cake.api.model.CakeSummary;
import cake.api.repository.CakeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CakeControllerTest {

    private static final CakeSummary chocolateCakeSummary = new CakeSummary("chocolate", "Chocolate cake");
    private static final CakeSummary fruitCakeSummary = new CakeSummary("fruit", "Fruit cake");
    private static final Cake chocolateCake = new Cake("chocolate", "Chocolate cake", "Yum", "chocolate-cake.jpg");
    private static final Cake fruitCake = new Cake("fruit", "Fruit cake", "Yum", "fruit-cake.jpg");

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CakeRepository cakeRepository;

    @BeforeEach
    public void setup() {
        cakeRepository.deleteAll();
        cakeRepository.save(new CakeEntity("chocolate", "Chocolate cake", "Yum", "chocolate-cake.jpg"));
        cakeRepository.save(new CakeEntity("fruit", "Fruit cake", "Yum", "fruit-cake.jpg"));
        cakeRepository.findAll();
    }

    @Test
    public void rootShouldReturnSummaryList() {
        // When
        CakeSummary[] summaryList = restTemplate.getForObject(endpoint("/"), CakeSummary[].class);

        // Then
        assertEquals(2, summaryList.length);
        assertEquals(chocolateCakeSummary, summaryList[0]);
        assertEquals(fruitCakeSummary, summaryList[1]);
    }

    @Test
    public void cakesShouldListAllCakesInFull() {
        // When
        Cake[] fullList = restTemplate.getForObject(endpoint("/cakes"), Cake[].class);

        // Then
        assertEquals(2, fullList.length);
        assertEquals(chocolateCake, fullList[0]);
        assertEquals(fruitCake, fullList[1]);
    }

    @Test
    public void canGetCakeBySlug() {
        // When
        Cake actual = restTemplate.getForObject(endpoint("/cakes/chocolate"), Cake.class);

        // Then
        assertEquals(chocolateCake, actual);
    }

    @Test
    public void cantGetUnknownCakeBySlug() {
        // When
        ResponseEntity<Cake> response = restTemplate.getForEntity(endpoint("/cakes/something-else"), Cake.class);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void canStoreNewCake() {
        // Given
        Cake battenburg = new Cake("battenburg", "Battenburg", "Yum", "battenburg.jpg");

        // When
        restTemplate.put(endpoint("/cakes"), battenburg);
        Cake stored = restTemplate.getForObject("/cakes/battenburg", Cake.class);

        // Then
        assertEquals(battenburg, stored);
    }

    @Test
    public void canUpdateCake() {
        // Given
        Cake updatedChocolateCake = new Cake("chocolate", "Even Chocolate-y-er Cake", "Delicious!", "even-chocolateyer.jpg");

        // When
        restTemplate.put(endpoint("/cakes"), updatedChocolateCake);
        Cake stored = restTemplate.getForObject("/cakes/chocolate", Cake.class);

        // Then
        assertEquals(updatedChocolateCake, stored);
    }

    @Test
    public void cantStoreCakeWithoutSlug() {
        // Given
        Cake empty = new Cake();

        // When
        ResponseEntity<Void> response = restTemplate.exchange(endpoint("/cakes"), HttpMethod.PUT,
                new HttpEntity<>(empty), Void.class);

        // Then
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

    @Test
    public void cantStoreCakeWithoutTitle() {
        // Given
        Cake untitled = new Cake("new", null, "Something without a name", "blank.jpg");

        // When
        ResponseEntity<Void> response = restTemplate.exchange(endpoint("/cakes"), HttpMethod.PUT,
                new HttpEntity<>(untitled), Void.class);

        // Then
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

    @Test
    public void cantStoreCakeWithoutDescription() {
        // Given
        Cake indescribable = new Cake("new", "Indescribable", null, "indescribable.jpg");

        // When
        ResponseEntity<Void> response = restTemplate.exchange(endpoint("/cakes"), HttpMethod.PUT,
                new HttpEntity<>(indescribable), Void.class);

        // Then
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

    @Test
    public void cantStoreCakeWithoutImage() {
        // Given
        Cake unpresentable = new Cake("new", "Unpresentable", "Can't show this to anyone", null);

        // When
        ResponseEntity<Void> response = restTemplate.exchange(endpoint("/cakes"), HttpMethod.PUT,
                new HttpEntity<>(unpresentable), Void.class);

        // Then
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

    @Test
    public void cantStoreCakeIfNothingHasChanged() {
        // Given
        Cake chocolateCopy = new Cake("chocolate", "Chocolate cake", "Yum", "chocolate-cake.jpg");

        // When
        ResponseEntity<Void> response = restTemplate.exchange(endpoint("/cakes"), HttpMethod.PUT,
                new HttpEntity<>(chocolateCopy), Void.class);

        // Then
        assertEquals(HttpStatus.NOT_MODIFIED, response.getStatusCode());
    }

    @Test
    public void canDeleteCake() {
        // When
        restTemplate.delete(endpoint("/cakes/fruit"));
        ResponseEntity<Cake> getFruitCakeResponse = restTemplate.getForEntity(endpoint("/cakes/fruit"), Cake.class);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, getFruitCakeResponse.getStatusCode());
    }

    @Test
    public void cantDeleteCakeThatIsntThere() {
        // When
        ResponseEntity<Void> response = restTemplate.exchange(endpoint("/cakes/something-not-there"), HttpMethod.DELETE,
                new HttpEntity<>(null, null), Void.class);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    private String endpoint(String path) {
        return "http://localhost:" + port + path;
    }
}