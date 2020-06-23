package IoT.org.service;

import IoT.org.exception.ReadingNotFoundException;
import IoT.org.repository.AlertRepository;
import IoT.org.repository.ReadingRepository;
import IoT.org.repository.TireRepository;
import IoT.org.repository.VehicleRepository;
import IoT.org.entity.Alert;
import IoT.org.entity.Reading;
import IoT.org.entity.Vehicle;
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
    public Reading getReading(String vin)
    {
        Optional<Reading> r = readrepo.findById(vin);
        if(!r.isPresent())      {
            throw new ReadingNotFoundException("Reading for Vehicle with id= "+vin+" not found");
        }
        return r.get();
    }

    @Transactional
    public Alert createAlert(Reading reading)
    {
        Alert a= new Alert();

        Optional<Vehicle> v= vehicleRepo.findById(reading.getVin());

        if(reading.getEngineRpm()>v.get().getRedlineRpm())
        {
            //a.setAlertId("RPM");
            //a.setTimestamp(reading.getTimestamp());
            a.setPriority("High");
            a.setMessage("Engine Rpm is more than redLine Rpm");
            a.setVin(reading.getVin());
            alertrepo.save(a);

        }
        else if(reading.getFuelVolume()<(0.1*(v.get().getMaxFuelVolume())))
        {

            a.setPriority("Medium");
            a.setMessage("Fuel reaching low.");
            a.setVin(reading.getVin());
            alertrepo.save(a);

        }
        else if((reading.getTires().getFrontLeft()<32||reading.getTires().getFrontLeft()>36)
                || (reading.getTires().getFrontRight()<32||reading.getTires().getFrontRight()>36)
                ||(reading.getTires().getRearLeft()<32||reading.getTires().getRearLeft()>36)
                ||(reading.getTires().getRearRight()<32||reading.getTires().getRearRight()>36))
        {

            a.setPriority("Low");
            a.setMessage("Tire Pressure Low");
            a.setVin(reading.getVin());
            alertrepo.save(a);

        }
        else if(reading.isEngineCoolantLow() ||reading.isCheckEngineLightOn())
        {
            a.setPriority("Low");
            a.setMessage("Engine coolant low or check engine light is on");
            a.setVin(reading.getVin());
            alertrepo.save(a);

        }

        return  a;
    }



    @Transactional
    public Reading create(Reading reading) {
        tirerepo.save(reading.getTires());
        reading.setAlerts(createAlert(reading));
        return readrepo.save(reading);
    }

    @Transactional

    public List<Alert> getHighAlerts(){
        return alertrepo.getHighAlerts();
    }







}
