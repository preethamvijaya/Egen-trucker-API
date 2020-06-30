package IoT.org.controller;

import IoT.org.entity.Alert;
import IoT.org.repository.AlertRepository;
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



@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("integrationtest")
public class AlertsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AlertRepository alertRepository;


    @Before
    public void setup() {
        Alert alert = new Alert();
        alert.setAlertId("alert-id");
        alert.setMessage("Engine Failure");
        alert.setPriority("HIGH");
        alert.setVin("Alert-Vin");
        alertRepository.save(alert);
    }

    @After
    public void cleanup() {
        alertRepository.deleteAll();
    }

    @Test
    public void getAllAlerts() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/alerts/high"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.containsString("All HIGH Alerts")));
    }



}
