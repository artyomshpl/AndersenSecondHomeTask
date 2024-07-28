package com.shep.fourthlecture.models.base;

import com.shep.fourthlecture.annotations.NotNull;
import com.shep.fourthlecture.models.impl.*;
import com.shep.fourthlecture.models.Ticket;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class User implements Identifiable, Printable {
    @NotNull
    private int id;
    private String role;
    private String name;

    public User(int id, String role, String name) {
        this.id = id;
        this.role = role;
        this.name = name;
    }

    public User(int id, String role) {
        this.id = id;
        this.role = role;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public void printRole() {
        System.out.println("Role: " + role);
    }

    public abstract void uniqueFunction(Ticket ticket);
}



