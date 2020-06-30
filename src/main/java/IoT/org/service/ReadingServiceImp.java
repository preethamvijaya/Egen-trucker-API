package IoT.org.service;

import IoT.org.exception.ReadingNotFoundException;
import IoT.org.repository.AlertRepository;
import IoT.org.repository.ReadingRepository;
import IoT.org.repository.TireRepository;
import IoT.org.repository.VehicleRepository;
import IoT.org.entity.Alert;
import IoT.org.entity.Reading;
import IoT.org.entity.VehicleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ReadingServiceImp implements ReadingService {

    @Autowired
    ReadingRepository readrepo;

    @Autowired
    private TireRepository tirerepo;

    @Autowired
    private AlertRepository alertrepo;

    @Autowired
    private VehicleRepository vehicleRepo;

    @Transactional
    public Reading fetchReading(String vin)
    {
        Optional<Reading> r = readrepo.findById(vin);
        if(!r.isPresent())      {
            throw new ReadingNotFoundException("Reading for VehicleInfo with id= "+vin+" not found");
        }
        return r.get();
    }

    @Transactional
    public List<Reading> fetchAllReadings(){return (List<Reading>) readrepo.findAll();}



    @Transactional
    public Alert createAlert1(Reading reading) {
        Alert alert1 = new Alert();

        Optional<VehicleInfo> v = vehicleRepo.findById(reading.getVin());

        if (reading.getEngineRpm() > v.get().getRedlineRpm()) {
            //a.setAlertId("RPM");
            //a.setTimestamp(reading.getTimestamp());
            alert1.setPriority("High");
            alert1.setMessage("Engine Rpm is more than redLine Rpm");
            alert1.setVin(reading.getVin());
            alertrepo.save(alert1);

        }
        return alert1;
    }
    @Transactional
    public Alert createAlert2(Reading reading) {
        Alert alert2 = new Alert();

        Optional<VehicleInfo> v = vehicleRepo.findById(reading.getVin());

        if(reading.getFuelVolume()<(0.1*(v.get().getMaxFuelVolume())))
        {

            alert2.setPriority("Medium");
            alert2.setMessage("Fuel reaching low.");
            alert2.setVin(reading.getVin());
            alertrepo.save(alert2);

        }
        return alert2;
    }

    @Transactional
    public Alert createAlert3(Reading reading) {
        Alert alert3 = new Alert();

        Optional<VehicleInfo> v = vehicleRepo.findById(reading.getVin());

        if((reading.getTires().getFrontLeft()<32||reading.getTires().getFrontLeft()>36)
                || (reading.getTires().getFrontRight()<32||reading.getTires().getFrontRight()>36)
                ||(reading.getTires().getRearLeft()<32||reading.getTires().getRearLeft()>36)
                ||(reading.getTires().getRearRight()<32||reading.getTires().getRearRight()>36))
        {

            alert3.setPriority("Low");
            alert3.setMessage("Tire Pressure Low");
            alert3.setVin(reading.getVin());
            alertrepo.save(alert3);

        }
        return alert3;
    }

    @Transactional
    public Alert createAlert4(Reading reading)
    {
        Alert alert4= new Alert();

        Optional<VehicleInfo> v= vehicleRepo.findById(reading.getVin());

        if(reading.isEngineCoolantLow() ||reading.isCheckEngineLightOn())
        {
            alert4.setPriority("Low");
            alert4.setMessage("Engine coolant low or check engine light is on");
            alert4.setVin(reading.getVin());
            alertrepo.save(alert4);

        }

        return  alert4;
    }


    @Transactional
    public Reading create(Reading reading) {
        tirerepo.save(reading.getTires());
        reading.setAlerts(createAlert1(reading));
        reading.setAlerts(createAlert2(reading));
        reading.setAlerts(createAlert3(reading));
        reading.setAlerts(createAlert4(reading));
        return readrepo.save(reading);
    }

    @Transactional
    public List<Alert> getHighAlerts(){
        return alertrepo.getHighAlerts();
    }


    @Transactional
    public List<Alert> getVehicleAlerts(String id){
        return alertrepo.getVehicleAlerts(id);
    }


    public List<Reading> findGeoLocation(String id) {
        //Optional<List<VehicleInfo>> existing = repo.findGeoLocation(id);
//        if (!existing.isPresent()){
//            throw new ResourceNotFoundException("Vehicle with vin " + vin + " doesn't exist.");
//        }
        return readrepo.findGeoLocation(id);
    }



}
