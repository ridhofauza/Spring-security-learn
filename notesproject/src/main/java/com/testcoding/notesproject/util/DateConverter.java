/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testcoding.notesproject.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author user
 */
public class DateConverter {
    
    private String dateTime;

    public DateConverter(String dateTime) {
        this.dateTime = dateTime;
    }
    
    public LocalDateTime toDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(this.dateTime, formatter);
        return dateTime;
    }
    
}
