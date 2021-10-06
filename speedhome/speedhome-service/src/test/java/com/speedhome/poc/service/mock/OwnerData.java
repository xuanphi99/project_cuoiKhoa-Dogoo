package com.speedhome.poc.service.mock;


import com.speedhome.poc.api.model.OwnerRequest;

public class OwnerData {
    public static final String idOwner = "C34543df-sa23-44f2-543d-6y454213d5f";
    private static final  String FULL_NAME = " phidx";
    private static final  String ADDRESS = "NAM ĐỊNH";
    private static final  Integer AGE = 20;

    public static OwnerRequest mockOwnerReq() {
        OwnerRequest ownerRequest = new OwnerRequest();

        ownerRequest.setFullName(FULL_NAME);
        ownerRequest.setAge(AGE);
        ownerRequest.setAddress(ADDRESS);


        return ownerRequest;
    }

}
