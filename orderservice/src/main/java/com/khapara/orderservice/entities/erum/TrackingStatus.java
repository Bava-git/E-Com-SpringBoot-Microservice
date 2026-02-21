package com.khapara.orderservice.entities.erum;

public enum TrackingStatus {
    CONFIRMED("Confirmed"),
    SHIPPED("Shipped"),
    PENDING("Pending"),
    IN_TRANSIT("In Transit"),
    OUT_FOR_DELIVERY("Out for Delivery"),
    DELIVERED("Delivered");

    private final String displayName;

    TrackingStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

