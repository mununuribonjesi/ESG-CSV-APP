package org.esg.domain;

public record Customer(Long customerRef, String customerName, String addressLine1, String addressLine2, String town,
                       String county, String country, String postcode) {
}