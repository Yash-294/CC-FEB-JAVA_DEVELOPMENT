package com.codeclausetask1;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Task1Controller {

    @FXML
    private Label indiantime;
    @FXML
    private Label countrytime;
    @FXML
    private ComboBox<String> country;

    private String newCountry;

    private ZoneId zone;

    public void initialize(){
        ObservableList<String> countries=FXCollections.observableArrayList("Australia","Bangladesh","Brazil","Canada ","China","Denmark","Egypt","France","Germany","Hungary","Indonesia","Italy","Kenya","Malaysia","Mauritius","Mexico","Myanmar","Nepal","New Zealand","Peru","Saudi Arabia","Singapore","South Africa","Sri Lanka","Thailand","Turkey","United Kingdom","United States of America","Vietnam","Zimbabwe");
        country.setItems(countries);
        Timeline changingtime= new Timeline(new KeyFrame(Duration.ZERO,event->{
            DateTimeFormatter dateformat=DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");
            LocalDateTime localdate=LocalDateTime.now();
            String currdate=localdate.format(dateformat);
            indiantime.setText(currdate);
            if(newCountry!=null){
                switch(newCountry){
                    case "Australia":zone=ZoneId.of("Australia/Sydney");
                                    break;
                    case "Bangladesh":zone=ZoneId.of("Asia/Dhaka");
                    break;
                    case "Brazil":zone=ZoneId.of("Brazil/West");
                    break;
                    case "Canada":zone=ZoneId.of("Canada/Central");
                    break;
                    case "China":zone=ZoneId.of("Asia/Shanghai");
                    break;
                    case "Denmark":zone=ZoneId.of("CET");
                    break;
                    case "Egypt":zone=ZoneId.of("Egypt");
                    break;
                    case "France":zone=ZoneId.of("CET");
                    break;
                    case "Germany":zone=ZoneId.of("CET");
                    break;
                    case "Hungary":zone=ZoneId.of("CET");
                    break;
                    case "Indonesia":zone=ZoneId.of("Asia/Jakarta");
                    break;
                    case "Italy":zone=ZoneId.of("CET");
                    break;
                    case "Kenya":zone=ZoneId.of("Africa/Nairobi");
                    break;
                    case "Malaysia":zone=ZoneId.of("Asia/Jakarta");
                    break;
                    case "Mauritius":zone=ZoneId.of("Indian/Mauritius");
                    break;
                    case "Mexico":zone=ZoneId.of("Mexico/General");
                    break;
                    case "Myanmar":zone=ZoneId.of("Asia/Dhaka");
                    break;
                    case "Nepal":zone=ZoneId.of("Asia/Kathmandu");
                    break;
                    case "New Zealand":zone=ZoneId.of("Pacific/Chatham");
                    break;
                    case "Peru":zone=ZoneId.of("America/New_York");
                    break;
                    case "Saudi Arabia":zone=ZoneId.of("Asia/Riyadh");
                    break;
                    case "Singapore":zone=ZoneId.of("Singapore");
                    break;
                    case "South Africa":zone=ZoneId.of("Africa/Nairobi");
                    break;
                    case "Sri Lanka":zone=ZoneId.of("Asia/Colombo");
                    break;
                    case "Thailand":zone=ZoneId.of("Asia/Bangkok");
                    break;
                    case "Turkey":zone=ZoneId.of("Asia/Kuwait");
                    break;
                    case "United Kingdom":zone=ZoneId.of("Australia/Sydney");
                    break;
                    case "United States of America":zone=ZoneId.of("America/Sao_Paulo");
                    break;
                    case "Vietnam":zone=ZoneId.of("Australia/Sydney");
                    break;
                    case "Zimbabwe":zone=ZoneId.of("Australia/Sydney");
                    break;
                }
                LocalDateTime difflocaldate=LocalDateTime.now(zone);
                String diffcurrdate=" --> "+difflocaldate.format(dateformat);
                countrytime.setText(diffcurrdate);
            }
        }),new KeyFrame(Duration.seconds(1)));
        changingtime.setCycleCount(Timeline.INDEFINITE);
        changingtime.play();
        country.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> value,String oldvalue,String newvalue)->{
            newCountry=newvalue;
        });
    }
}
