package IoT.org.controller;


import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import IoT.org.entity.Reading;
import IoT.org.repository.ReadingRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("integrationtest")
public class ReadingsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ReadingRepository readingsRepository;


    @Before
    public void setup() {
        Reading reading = new Reading();
        reading.setLatitude(41.42);
        reading.setLongitude(78.23);
        reading.setVin("reading-Vin");
        readingsRepository.save(reading);
    }

    @After
    public void cleanup() {
        readingsRepository.deleteAll();
    }

    @Test
    public void findAllReadings() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/readings"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vin", Matchers.is("reading-Vin")));
    }

    @Test
    public void findReadingByVin() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/readings/reading-Vin"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.latitude", Matchers.is(41.42)));
    }

    @Test
    public void findGeolocationByVin() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/readings/geolocation/reading-Vin"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.containsString("Geolocation Details")));
    }

    @Test
    public void create() throws Exception {
    }
}
