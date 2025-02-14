package com.retaileroffers.rewards.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    int status;
    String message;

    public ErrorResponse(int status, String message) {
        this.message=message;
        this.status=status;
    }
}
