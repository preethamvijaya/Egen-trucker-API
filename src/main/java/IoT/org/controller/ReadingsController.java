package IoT.org.controller;




import IoT.org.entity.Vehicle;
import IoT.org.service.ReadingService;
import IoT.org.entity.Reading;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value ="/readings")
public class ReadingsController {

    @Autowired
    private ReadingService readingService;



    @RequestMapping(method=RequestMethod.GET, value="{vin}")
    public Reading getReading(@PathVariable("vin") String id)
    {return readingService.getReading(id);}


    @RequestMapping(method = RequestMethod.POST)
    public Reading create(@RequestBody Reading reading)
    {
        return  readingService.create(reading);
    }


}
