package exercise;

import exercise.annotation.Inspect;
import exercise.model.Address;

public class Application {
    public static void main(String[] args) {
        for (var method : Address.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Inspect.class)) {
                final var name = method.getName();
                final var type = method.getReturnType().getSimpleName();
                System.out.println("Method " + name + " returns a value of type " + type);
            }
        }
    }
}
