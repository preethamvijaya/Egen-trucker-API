package IoT.org.controller;
import IoT.org.entity.VehicleInfo;
import IoT.org.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/vehicles")

public class VehiclesController {

    @Autowired
    VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<VehicleInfo> findAll()
    {
        return vehicleService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value="/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public VehicleInfo findOne(@PathVariable("id") String id)
    {
        return vehicleService.findOne(id);
    }


    @RequestMapping( value = "/{id}",method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<VehicleInfo> update(@RequestBody List<VehicleInfo> vehicle)
    {
        return  vehicleService.update(vehicle);
    }

//    @RequestMapping( value = "/{id}",method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public List<VehicleInfo> update(@RequestBody List<VehicleInfo> vehicle)
//    {
//        return  vehicleService.create(vehicle);
//    }





}
