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

import IoT.org.entity.VehicleInfo;
import IoT.org.repository.VehicleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("integrationtest")
public class VehicleControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private VehicleRepository vehicleRepository;


    @Before
    public void setup() {
        VehicleInfo vehicle = new VehicleInfo();
        vehicle.setVin("Vehicle-Vin");
        vehicle.setMake("Honda");
        vehicle.setModel("Civic");
        vehicleRepository.save(vehicle);
    }

    @After
    public void cleanup() {
        vehicleRepository.deleteAll();
    }

    @Test
    public void getAllVehicle() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/vehicles"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vin", Matchers.is("Vehicle-Vin")));
    }


    @Test
    public void findOne() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/vehicles/Vehicle-Vin"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.make", Matchers.is("Honda")));
    }
    @Test
    public void update() throws Exception {
    }
}
