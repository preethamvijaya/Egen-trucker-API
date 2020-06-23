package IoT.org.service;

import IoT.org.entity.Alert;
import IoT.org.entity.Reading;

import java.util.List;

public interface ReadingService {


    Reading create(Reading reading);
    Reading getReading(String vin);
    Alert createAlert(Reading reading);
    List<Alert> getHighAlerts();





}
