package team.challenge.MobileStore.exception;

import lombok.NonNull;

public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException(String message) {
        super(message);
    }
}
