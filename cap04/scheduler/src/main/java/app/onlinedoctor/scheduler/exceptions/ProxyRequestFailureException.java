package app.onlinedoctor.scheduler.exceptions;

import lombok.Getter;

@Getter
public class ProxyRequestFailureException extends RuntimeException {

    private String externalServiceName;

    public ProxyRequestFailureException(Throwable cause, String externalServiceName) {
        super(cause);
        this.externalServiceName = externalServiceName;
    }
}
