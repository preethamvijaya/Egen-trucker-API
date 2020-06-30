package IoT.org.service;

import IoT.org.entity.Alert;
import IoT.org.entity.Reading;
import IoT.org.entity.VehicleInfo;

import java.util.List;

public interface ReadingService {


    Reading create(Reading reading);
    Reading fetchReading(String vin);
    List<Reading> fetchAllReadings();
    Alert createAlert1(Reading reading);
    Alert createAlert2(Reading reading);
    Alert createAlert3(Reading reading);
    Alert createAlert4(Reading reading);
    List<Alert> getHighAlerts();
    List<Alert> getVehicleAlerts(String vin);
    List<Reading> findGeoLocation(String vin);






}
