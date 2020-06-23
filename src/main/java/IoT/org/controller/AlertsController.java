package IoT.org.controller;

import IoT.org.entity.Alert;
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
    public List<Alert> getHighAlerts()
    {
        return  readingService.getHighAlerts();
    }



}
