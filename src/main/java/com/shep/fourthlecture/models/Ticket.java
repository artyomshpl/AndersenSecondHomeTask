package com.shep.fourthlecture.models;

import com.shep.fourthlecture.annotations.NotNull;
import com.shep.fourthlecture.annotations.NotNullWarningProcessor;
import com.shep.fourthlecture.models.impl.Identifiable;
import com.shep.fourthlecture.models.impl.Printable;
import com.shep.fourthlecture.sharing.impl.Shareable;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class Ticket implements Identifiable, Printable {
    @NotNull
    private int id;
    private String concertHall;
    private String eventCode;
    private Long time;
    private boolean isPromo;
    private char stadiumSector;
    private double maxBackpackWeight;
    private double price;
    private LocalDateTime creationTime;

    private static int nextId = 0;

    private static int getNextId() {
        return nextId++;
    }

    public Ticket(String concertHall, String eventCode, Long time, boolean isPromo, char stadiumSector, double maxBackpackWeight, double price) {
        this.id = getNextId();
        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.time = time;
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.maxBackpackWeight = maxBackpackWeight;
        this.price = price;
        this.creationTime = LocalDateTime.now();
        validDataLongCheck(id, concertHall, eventCode, stadiumSector);
        NotNullWarningProcessor.processAnnotations(this);
    }

    public Ticket(String concertHall, String eventCode, Long time) {
        this.id = getNextId();
        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.time = time;
        validDataShortCheck(concertHall, eventCode);
    }

    public Ticket(){
        this.id = getNextId();
        this.concertHall = "Default";
        this.creationTime = LocalDateTime.now();
    }

    public boolean shortVersionCheck(char stadiumSector){
        return stadiumSector != '\0' ? false : true;
    }

    public void validDataShortCheck(String concertHall, String eventCode) {
        try {
            if (concertHall == null || concertHall.length() > 10 || concertHall.isEmpty()) {
                throw new IllegalArgumentException("Concert hall length must be in 1-10 range!"
                        + " Your value: " + concertHall + " is " + (concertHall != null ? concertHall.length() : 0));
            }

            if (eventCode == null || eventCode.length() > 3 || eventCode.isEmpty()) {
                throw new IllegalArgumentException("Event code length must be in 1-3 range!"
                        + " Your value: " + eventCode + " is " + (eventCode != null ? eventCode.length() : 0));
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + " in Ticket: " + this);
        }
    }


    public void validDataLongCheck(int id, String concertHall, String eventCode, char stadiumSector) {
        try {
            validDataShortCheck(concertHall, eventCode);

            if (!(stadiumSector >= 'A' && stadiumSector <= 'C')) {
                throw new IllegalArgumentException("Stadium sector must be in A-C range!"
                        + " Your value: " + stadiumSector);
            }
            String idForCheck = String.valueOf(id);
            if (idForCheck != null) {
                if (idForCheck.length() > 4 || idForCheck.isEmpty()) {
                    throw new IllegalArgumentException("Ticket id length must be in 1-4 range!"
                            + " Your value: " + id + " is " + idForCheck.length());
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + " in Ticket: " + this);
        }
    }


    public static LocalDateTime convertUnixTimestampToLocalDateTime(long unixTimestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(unixTimestamp), ZoneId.systemDefault());
    }

    @Override
    public String toString() {
        String stringResult;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy");
        if(this.shortVersionCheck(this.stadiumSector)){
            time = time == null ? Instant.now().getEpochSecond() : time;
            stringResult ="ID: = " + id + " Concert hall = " + concertHall + ", Time = " + convertUnixTimestampToLocalDateTime(time).format(formatter);
        } else {
            time = time == null ? Instant.now().getEpochSecond() : time;
            stringResult = "Ticket number (id) = " + id + ", Concert hall = " + concertHall
                    + ", Event code = " + eventCode
                    + ", Time = " + convertUnixTimestampToLocalDateTime(time).format(formatter) + ", Promo = " + isPromo
                    + ", StadiumSector = " + stadiumSector
                    + ", Max Backpack weight = " + maxBackpackWeight
                    + ", Price = " + price
                    + ", creationTime = " + creationTime.format(formatter);
        }

        return stringResult;
    }

    @Override
    public int getId(){
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void print() {
        Printable.super.print();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (isPromo != ticket.isPromo) return false;
        if (stadiumSector != ticket.stadiumSector) return false;
        if (Double.compare(ticket.maxBackpackWeight, maxBackpackWeight) != 0) return false;
        if (Double.compare(ticket.price, price) != 0) return false;
        if (concertHall != null ? !concertHall.equals(ticket.concertHall) : ticket.concertHall != null) return false;
        if (eventCode != null ? !eventCode.equals(ticket.eventCode) : ticket.eventCode != null) return false;
        if (time != null ? !time.equals(ticket.time) : ticket.time != null) return false;
        return creationTime != null ? creationTime.equals(ticket.creationTime) : ticket.creationTime == null;
    }


    @Override
    public int hashCode() {
        int result;
        result = (concertHall != null ? concertHall.hashCode() : 0);
        result = 31 * result + (eventCode != null ? eventCode.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (isPromo ? 1 : 0);
        result = 31 * result + (int) stadiumSector;
        result = 31 * result + Double.hashCode(maxBackpackWeight);
        result = 31 * result + Double.hashCode(price);
        result = 31 * result + (creationTime != null ? creationTime.hashCode() : 0);
        return result;
    }




    public void share(Shareable shareable) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy");
        String message = "Ticket number (id) = " + id + ", Concert hall = " + concertHall
                + ", Event code = " + eventCode
                + ", Time = " + convertUnixTimestampToLocalDateTime(time).format(formatter) + ", Promo = " + isPromo
                + ", StadiumSector = " + stadiumSector
                + ", Max Backpack weight = " + maxBackpackWeight
                + ", Price = " + price
                + ", creationTime = " + creationTime.format(formatter);
        shareable.share(message);
    }
}

