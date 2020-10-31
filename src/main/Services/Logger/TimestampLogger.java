package main.Services.Logger;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

@Decorator
public class TimestampLogger implements LoggerInterface {
    @Inject
    @Delegate
    LoggerInterface logger;

    @Override
    public void log(String message) {
        logger.log(java.time.LocalDate.now() + " " + message);
    }
}
