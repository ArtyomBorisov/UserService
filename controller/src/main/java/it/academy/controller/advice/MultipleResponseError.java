package it.academy.controller.advice;

import java.util.List;

public class MultipleResponseError {
    private final String logref;
    private final List<Error> errors;

    public MultipleResponseError(String logref, List<Error> errors) {
        this.logref = logref;
        this.errors = errors;
    }

    public MultipleResponseError(List<Error> errors) {
        this.logref = "structured_error";
        this.errors = errors;
    }

    public String getLogref() {
        return logref;
    }

    public List<Error> getErrors() {
        return errors;
    }
}
