package com.example.alphasolutions.DTOs;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DeadlineEstimate {

        //Current date and time
        LocalDateTime now = LocalDateTime.now();

        //Specify the duration for the deadline estimate
        long durationInHours = 48;

        //Calculate the estimated deadline
        LocalDateTime deadline_time = now.plus(durationInHours, ChronoUnit.HOURS);
    }

