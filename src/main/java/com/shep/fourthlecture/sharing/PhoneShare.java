package com.shep.fourthlecture.sharing;

import com.shep.fourthlecture.sharing.impl.Shareable;

public class PhoneShare implements Shareable {
    private String phoneNumber;

    public PhoneShare(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void share(String message) {
        System.out.println("Sharing ticket by phone: " + phoneNumber + " Message: " + message);
    }
}
