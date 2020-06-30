package IoT.org.controller;

import IoT.org.entity.Alert;
import IoT.org.entity.Reading;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


import IoT.org.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value ="/alerts")
public class AlertsController {

    @Autowired
    private ReadingService readingService;



    //Listing alerts which are HIGH
    @RequestMapping(method= RequestMethod.GET, value="/high")
    public String getHighAlerts()
    {
        List<Alert> queryHigh = readingService.getHighAlerts();
        String alertResult = null;
        alertResult = "*****All HIGH Alerts*****<br>";

        for (Alert alt : queryHigh) {
            alertResult = alertResult+"<br>Alert  id: " + alt.getAlertId() + ", Message: "
                    + alt.getMessage()+" and Vehicle id " + alt.getVin();
        }


        return alertResult;


        //return  readingService.getHighAlerts();
    }


    //Listing specific vehicle all alerts
    @RequestMapping(method= RequestMethod.GET, value="/{vin}")
    public List<Alert> getVehicleAlerts(@PathVariable("vin") String id)
    {
        return  readingService.getVehicleAlerts(id);
    }


}
