package com.example.a33528.reslifetoolkit;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by s522706 on 2/23/2017.
 */

public class ProgrammingForm implements Serializable{

    private int id;
    private String raName = "";
    private String hallName = "";
    private String hallFloor = "";
    private String eventTitle = "No Title";
    private String eventReason = "";
    private String eventDescription = "";
    private String eventPublicity = "";
    private String eventDate = "";
    private String cost = "";
    private String attendees = "";
    private String goals = "";
    private boolean isEvent;
    private int positionForDelete;

    public ProgrammingForm()
    {

    }

    public ProgrammingForm(String raName, String hallName, String hallFloor, String eventTitle, String eventReason, String eventDescription, String eventPublicity, String cost, String attendees, String goals, boolean isEvent )
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
        this.isEvent = isEvent;
    }

    //Getters and setters for necessary items.


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getHallFloor() {
        return hallFloor;
    }

    public void setHallFloor(String hallFloor) {
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

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getAttendees() {
        return attendees;
    }

    public void setAttendees(String attendees) {
        this.attendees = attendees;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public boolean getIsEvent() {return isEvent;}

    public void setIsEvent(boolean event) {isEvent = event;}

    public void setPositionForDelete(int position){this.positionForDelete = position;}

    public int getPositionForDelete() {return positionForDelete;}

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String toString()
    {
        return raName + " " + eventTitle + " " + id;
    }

}
