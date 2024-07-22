package com.shep.secondlecture.models;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class Ticket {
    private String id;
    private String concertHall;
    private String eventCode;
    private Long time;
    private boolean isPromo;
    private char stadiumSector;
    private double maxBackpackWeight;
    private double price;
    private LocalDateTime creationTime;



/*
Getter/Setter to show that I'm familiar with that but using lombok is way more readable from my point
    public void setConcertHall(String concertHall) {
        this.concertHall = concertHall;
    }
    public String getConcertHall() {
        return concertHall;
    }
*/

    public Ticket(String id, String concertHall, String eventCode, Long time, boolean isPromo, char stadiumSector, double maxBackpackWeight, double price) {
        this.id = id;
        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.time = time;
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.maxBackpackWeight = maxBackpackWeight;
        this.price = price;
        this.creationTime = LocalDateTime.now();
        validDataLongCheck(id, concertHall, eventCode, stadiumSector);
    }

    public Ticket(String concertHall, String eventCode, Long time) {
        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.time = time;
        validDataShortCheck(concertHall, eventCode);
    }

    public Ticket(){
        this.concertHall = "Default";
        this.creationTime = LocalDateTime.now();
    }

    public boolean shortVersionCheck(char stadiumSector){
        return stadiumSector != '\0' ? false : true;
    }

    public void validDataShortCheck(String concertHall, String eventCode){
        if(concertHall.length() > 10 || concertHall.length() < 1){
            throw new IllegalArgumentException("Concert hall length must be in 1-10 range!"
                    + " Your value: " + concertHall + " is " + concertHall.length());
        }

        if(eventCode.length() > 3 || eventCode.length() < 1){
            throw new IllegalArgumentException("Event code length must be in 1-3 range!"
                    + " Your value: " + eventCode + " is " + eventCode.length());
        }
    }

    public void validDataLongCheck(String id, String concertHall, String eventCode, char stadiumSector){
        validDataShortCheck(concertHall, eventCode);

        if(!(stadiumSector >= 'A' && stadiumSector <= 'C')){
            throw new IllegalArgumentException("Stadium sector must be in A-C range!"
                    + " Your value: " + stadiumSector);
        }

        if(id.length() > 4 || id.length() < 1){
            throw new IllegalArgumentException("Ticket id length must be in 1-4 range!"
                    + " Your value " + id + " is " + id.length());
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
            stringResult = "Concert hall = " + concertHall + ", Time = " + convertUnixTimestampToLocalDateTime(time).format(formatter);
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
}
