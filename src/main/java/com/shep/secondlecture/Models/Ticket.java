package com.shep.secondlecture.Models;

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
    }

    public Ticket(String concertHall, String eventCode, Long time) {
        this(null, concertHall, eventCode, time, false, '\0', 0.0, 0.0);
    }

    public Ticket(){
        this(null, "Default", null, null, false, '\0', 0.0, 0.0);
    }

    public boolean shortVersionCheck(char stadiumSector){
        return stadiumSector != '\0' ? false : true;
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
