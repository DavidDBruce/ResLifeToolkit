package com.example.a33528.reslifetoolkit;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by s522706 on 2/23/2017.
 */

public class ProgrammingForm {
    private String raName;
    private String hallName;
    private int hallFloor;
    private String eventTitle;
    private String eventReason;
    private String eventDescription;
    private String eventPublicity;
    private double cost;
    private int attendees;
    private String goals;
    private boolean isEvent;
    private String createDate = "";
    private String modDate = "";

    public ProgrammingForm()
    {

    }

    public ProgrammingForm(String raName, String hallName, int hallFloor, String eventTitle, String eventReason, String eventDescription, String eventPublicity, double cost, int attendees, String goals )
    {
        this.raName = raName;
        this.hallName = hallName;
        this.hallFloor = hallFloor;
        this.eventTitle = eventTitle;
        this.eventReason = eventReason;
        this.eventDescription = eventDescription;
        this.eventPublicity = eventPublicity;
        this.cost = cost;
        this.attendees = attendees;
        this.goals = goals;
        isEvent = true;

        //Initializing dates

        createDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date()).toString();
        modDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    //Getters and setters for necessary items.

    public String getRaName() {
        return raName;
    }

    public void setRaName(String raName) {
        this.raName = raName;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public int getHallFloor() {
        return hallFloor;
    }

    public void setHallFloor(int hallFloor) {
        this.hallFloor = hallFloor;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventReason() {
        return eventReason;
    }

    public void setEventReason(String eventReason) {
        this.eventReason = eventReason;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {this.eventDescription = eventDescription;}

    public String getEventPublicity() {
        return eventPublicity;
    }

    public void setEventPublicity(String eventPublicity) {
        this.eventPublicity = eventPublicity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getAttendees() {
        return attendees;
    }

    public void setAttendees(int attendees) {
        this.attendees = attendees;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public boolean getIsEvent() {return isEvent;}

    public void setEvent(boolean event) {isEvent = event;}

    public String getCreateDate() {return createDate;}

    public String getModDate() {return modDate;}

    public void setModDate(String modDate) {this.modDate = modDate;}
}
