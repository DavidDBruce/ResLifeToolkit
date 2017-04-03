package com.example.a33528.reslifetoolkit;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by S522706 on 4/2/2017.
 */

public class DutyLog implements Serializable {

    private String round8 = "";
    private String round10 = "";
    private String round12 = "";
    private String round2 = "";
    private String roundDay = "";
    private String raOnDuty = "";
    private String createDate = "";
    private String modDate = "";
    private ArrayList<Documentation> documentations;
    private ArrayList<Documentation> workOrders;
    private int positionForDelete;

    public DutyLog()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        createDate = formatter.format(Calendar.getInstance().getTime());
        modDate = createDate;
    }

    public DutyLog(String round8, String round10, String round12, String round2, String roundDay, String raOnDuty, ArrayList<Documentation> documentations, ArrayList<Documentation> workOrders)
    {
        this.round8 = round8;
        this.round10 = round10;
        this.round12 = round12;
        this.round2 = round2;
        this.roundDay = roundDay;
        this.raOnDuty = raOnDuty;
        this.documentations = documentations;
        this.workOrders = workOrders;
    }

    public String getRound8() {
        return round8;
    }

    public void setRound8(String round8) {
        this.round8 = round8;
    }

    public String getRound10() {
        return round10;
    }

    public void setRound10(String round10) {
        this.round10 = round10;
    }

    public String getRound12() {
        return round12;
    }

    public void setRound12(String round12) {
        this.round12 = round12;
    }

    public String getRound2() {
        return round2;
    }

    public void setRound2(String round2) {
        this.round2 = round2;
    }

    public String getRoundDay() {
        return roundDay;
    }

    public void setRoundDay(String roundDay) {
        this.roundDay = roundDay;
    }

    public String getRaOnDuty() {
        return raOnDuty;
    }

    public void setRaOnDuty(String raOnDuty) {
        this.raOnDuty = raOnDuty;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getModDate() {
        return modDate;
    }

    public void setModDate(String modDate) {
        this.modDate = modDate;
    }

    public ArrayList<Documentation> getDocumentations() {
        return documentations;
    }

    public void setDocumentations(ArrayList<Documentation> documentations) {
        this.documentations = documentations;
    }

    public ArrayList<Documentation> getWorkOrders() {
        return workOrders;
    }

    public void setWorkOrders(ArrayList<Documentation> workOrders) {
        this.workOrders = workOrders;
    }

    public int getPositionForDelete() {
        return positionForDelete;
    }

    public void setPositionForDelete(int positionForDelete) {
        this.positionForDelete = positionForDelete;
    }
}

class Documentation
{

    private String docMessage = "";
    private String docCreateDate = "";
    private String docModDate = "";
    private String docCreateTime = "";
    private String docModTime = "";

    public Documentation(){
        SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm");
        docCreateTime = timeFormatter.format(Calendar.getInstance().getTime());
        docModTime = docCreateTime;

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        docCreateDate = dateFormatter.format(Calendar.getInstance().getTime());
        docModDate = docCreateDate;
    }

    public Documentation(String docMessage){
        this.docMessage = docMessage;
    }

    public String getDocMessage() {
        return docMessage;
    }

    public void setDocMessage(String docMessage) {
        this.docMessage = docMessage;
    }

    public String getDocCreateDate() {
        return docCreateDate;
    }

    public String getDocModDate() {
        return docModDate;
    }

    public void setDocModDate(String docModDate) {
        this.docModDate = docModDate;
    }

    public String getDocCreateTime() {
        return docCreateTime;
    }

    public String getDocModTime() {
        return docModTime;
    }

    public void setDocModTime(String docModTime) {
        this.docModTime = docModTime;
    }
}
