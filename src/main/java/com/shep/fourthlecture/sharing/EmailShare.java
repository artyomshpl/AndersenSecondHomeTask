package com.shep.fourthlecture.sharing;

import com.shep.fourthlecture.sharing.impl.Shareable;

public class EmailShare implements Shareable {
    private String email;

    public EmailShare(String email) {
        this.email = email;
    }

    @Override
    public void share(String message) {
        System.out.println("Sharing ticket by email: " + email + " Message: " + message);
    }
}