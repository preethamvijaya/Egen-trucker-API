package IoT.org.controller;


import io.swagger.annotations.Api;
import IoT.org.service.VehicleService;
import IoT.org.entity.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/vehicles")
@Api(description = "Employee related endpoints")

public class VehiclesController {

    @Autowired
    VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Vehicle> findAll()
    {
        return vehicleService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value="/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Vehicle findOne(@PathVariable("id") String id)
    {
        return vehicleService.findOne(id);
    }


    @RequestMapping( value = "/{id}",method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Vehicle> update(@RequestBody List<Vehicle> vehicle)
    {
        return  vehicleService.update(vehicle);
    }

//    @RequestMapping( value = "/{id}",method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public List<Vehicle> update(@RequestBody List<Vehicle> vehicle)
//    {
//        return  vehicleService.create(vehicle);
//    }


}
