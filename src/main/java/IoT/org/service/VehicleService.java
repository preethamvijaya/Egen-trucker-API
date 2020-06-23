package IoT.org.service;

import IoT.org.entity.Vehicle;
import java.util.List;

public interface VehicleService {


    List<Vehicle> findAll();
    Vehicle findOne(String vin);
    List<Vehicle> update(List<Vehicle> v);


}
