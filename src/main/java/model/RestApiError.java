package model;

public class RestApiError {
    public String httpStatus;
    public String errorMessage;
    public String ErrorDetails;
    public RestApiError(){
        super();
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public String getErrorDetails() {
        return ErrorDetails;
    }
    public void setErrorDetails(String errorDetails) {
        this.ErrorDetails = errorDetails;
    }
}
