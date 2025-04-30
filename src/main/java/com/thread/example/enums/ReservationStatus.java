package com.thread.example.enums;

public enum ReservationStatus {
    RESERVED(1),
    TABLE_OCCUPIED_TIMEOUT(2),
    FAILED_TO_LOCK(3);

    private final int code;

    ReservationStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return getCode();
    }
}
