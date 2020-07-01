package IoT.org.controller;
import IoT.org.entity.VehicleInfo;
import IoT.org.service.ReadingService;
import IoT.org.entity.Reading;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value ="/readings")
public class ReadingsController {

    @Autowired
    private ReadingService readingService;

    @RequestMapping(method=RequestMethod.GET, value="{vin}")
    public Reading fetchReading(@PathVariable("vin") String id)
    {
        return readingService.fetchReading(id);
    }

    @RequestMapping(method=RequestMethod.GET)
    public List<Reading> fetchAllReadings()
    {
        return readingService.fetchAllReadings();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Reading create(@RequestBody Reading reading)
    {
        return  readingService.create(reading);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/geolocation/{vin}")
    //@GetMapping("/geolocation/{vin}")
    public String findGeoLocation(@PathVariable("vin") String id){
        //System.out.println(readingService.findGeoLocation(id).get(2));
        List<Reading> check = readingService.findGeoLocation(id);
        String geoResult = null;
        for (Reading red : check) {
            geoResult = "Geolocation Details <br> vehicle id:" + red.getVin() + ", Latitude is "
                    + red.getLatitude() + ", and Longitude is " + red.getLongitude();
        }

        
        return geoResult;
    }

}
