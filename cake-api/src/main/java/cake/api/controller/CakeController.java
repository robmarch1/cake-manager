package cake.api.controller;

import cake.api.exception.CakeNotChangedException;
import cake.api.exception.CakeNotFoundException;
import cake.api.exception.InvalidCakeException;
import cake.api.model.Cake;
import cake.api.model.CakeEntity;
import cake.api.model.CakeSummary;
import cake.api.repository.CakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class CakeController {

    private static final Logger log = Logger.getLogger(CakeController.class.getName());

    @Autowired
    private CakeRepository cakeRepository;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public CakeSummary[] getCakeSummaryList() {
        log.info("Retrieving cake summaries");
        return cakeRepository.findAll()
                .stream()
                .map(CakeSummary::new)
                .toArray(CakeSummary[]::new);
    }

    @GetMapping(path = "/cakes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cake[] getFullCakeList() {
        log.info("Retrieving full cake detail list");
        return cakeRepository.findAll()
                .stream()
                .map(Cake::new)
                .toArray(Cake[]::new);
    }

    @GetMapping(path = "/cakes/{cakeSlug}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CakeEntity getCake(@PathVariable String cakeSlug) throws CakeNotFoundException {
        log.info("Attempting to find cake by slug " + cakeSlug);
        CakeEntity cake = cakeRepository.getBySlug(cakeSlug);
        if (cake == null) {
            log.warning("Unable to find cake with slug " + cakeSlug);
            throw new CakeNotFoundException(cakeSlug);
        }
        return cake;
    }

    @PutMapping(path = "/cakes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void persistCake(@RequestBody Cake cake) throws InvalidCakeException, CakeNotChangedException {
        log.info("Attempting to store cake");
        if (cake.getSlug() == null || "".equals(cake.getSlug().trim())) {
            log.warning("Unable to store cake - missing slug");
            throw new InvalidCakeException(cake);
        }
        Optional<CakeEntity> stored = Optional.ofNullable(cakeRepository.getBySlug(cake.getSlug()));
        if (stored.isEmpty()) {
            log.info("Attempting to store new cake");
            storeNewCake(cake);
        } else {
            log.info("Attempting to update cake");
            updateCake(cake, stored.get());
        }
    }

    private void storeNewCake(Cake cake) throws InvalidCakeException {
        try {
            cakeRepository.save(
                    new CakeEntity(cake.getSlug(), cake.getTitle(), cake.getDescription(), cake.getImage()));
        } catch (DataIntegrityViolationException e) {
            log.log(Level.WARNING, "Unable to store invalid cake: " + cake.toString(), e);
            throw new InvalidCakeException(cake);
        }
    }

    private void updateCake(Cake toStore, CakeEntity original) throws CakeNotChangedException, InvalidCakeException {
        if (toStore.equals(new Cake(original))) {
            log.warning("Cake details unchanged");
            throw new CakeNotChangedException(toStore.getSlug());
        }
        original.setTitle(toStore.getTitle());
        original.setDescription(toStore.getDescription());
        original.setImage(toStore.getImage());
        try {
            cakeRepository.save(original);
        } catch (DataIntegrityViolationException e) {
            log.log(Level.WARNING, "Unable to store invalid cake: " + toStore.toString(), e);
            throw new InvalidCakeException(toStore);
        }
    }

    @DeleteMapping(path = "/cakes/{cakeSlug}")
    public void deleteCake(@PathVariable String cakeSlug) throws CakeNotFoundException {
        CakeEntity toDelete = cakeRepository.getBySlug(cakeSlug);
        if (toDelete == null) {
            throw new CakeNotFoundException(cakeSlug);
        }
        cakeRepository.delete(toDelete);
    }
}
