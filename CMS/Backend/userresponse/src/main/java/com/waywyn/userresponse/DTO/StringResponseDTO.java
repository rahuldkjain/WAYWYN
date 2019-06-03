package com.waywyn.userresponse.DTO;

public class StringResponseDTO {
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "StringResponseDTO{" +
                "response='" + response + '\'' +
                '}';
    }
}
