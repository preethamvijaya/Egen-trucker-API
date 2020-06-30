package IoT.org.service;

import IoT.org.entity.VehicleInfo;

import java.util.List;

public interface VehicleService {


    List<VehicleInfo> findAll();
    VehicleInfo findOne(String vin);
    List<VehicleInfo> update(List<VehicleInfo> v);



}
