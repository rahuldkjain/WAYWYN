package com.waywyn.userresponse.DTO;

public class JsonResponseDTO {
    private Object data;
    private String error;
    private String message;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "JsonResponseDTO{" +
                ", data=" + data +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
