package Escalera;

/**
 * Interface for simple loggin
 */
public interface Logger {
    static <T> void log(T data) {
        System.out.println(data);
 }
}