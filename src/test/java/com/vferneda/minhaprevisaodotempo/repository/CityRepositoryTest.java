package com.vferneda.minhaprevisaodotempo.repository;

import com.vferneda.minhaprevisaodotempo.model.entity.City;
import com.vferneda.minhaprevisaodotempo.repository.CityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CityRepositoryTest {

    @Autowired
    private CityRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void canSaveCity() {
        City city = createCity();
        city = repository.save(city);
        Assertions.assertNotNull(city.getId());
    }

    @Test
    public void canDeleteCity() {
        City city = createPersistCity();

        city = entityManager.find(City.class, city.getId());

        repository.delete(city);

        final City cityNoExists = entityManager.find(City.class, city.getId());
        Assertions.assertNull(cityNoExists);
    }

    @Test
    public void canSearchCityById() {
        City city = createPersistCity();
        final Optional<City> cityFind = repository.findById(city.getId());
        Assertions.assertTrue(cityFind.isPresent());
    }

    private City createCity() {
        return City.builder()
                .id(6323074L)//
                .name("Blumenau")//
                .country("BR")//
                .lon(-49.102268)//
                .lat(-26.87417).build();
    }

    private City createPersistCity() {
        City city = createCity();
        entityManager.persist(city);
        return city;
    }
}
