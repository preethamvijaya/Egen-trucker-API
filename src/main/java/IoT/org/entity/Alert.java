package IoT.org.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Alert {

    @Id
    private String alertId;

    private String priority;
    private String message;
    private String vin;

    public Alert() {
        this.alertId = UUID.randomUUID().toString();
    }

    public Alert(String alertId, String priority, String message, String vin) {
        this.alertId = alertId;
        this.priority = priority;
        this.message = message;
        this.vin = vin;
    }

    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "alertId='" + alertId + '\'' +
                ", priority='" + priority + '\'' +
                ", message='" + message + '\'' +
                ", vin='" + vin + '\'' +
                '}';
    }
}
