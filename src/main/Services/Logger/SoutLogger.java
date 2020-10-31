package main.Services.Logger;

public class SoutLogger implements LoggerInterface {
    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
