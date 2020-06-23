package IoT.org.exception;

import IoT.org.entity.Reading;

public class ReadingNotFoundException extends RuntimeException{

    public ReadingNotFoundException(String message)
    {
        super(message);
    }


}
