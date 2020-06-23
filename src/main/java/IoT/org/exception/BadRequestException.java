package IoT.org.exception;

import org.hibernate.boot.jaxb.internal.stax.JpaOrmXmlEventReader;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code =HttpStatus.BAD_REQUEST)
public class BadRequestException extends JpaOrmXmlEventReader.BadVersionException {
    public BadRequestException(String message)
    {
        super(message);
    }
}
