package com.SRK;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class VisitorScan {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placeOfVisit;
    private String purposeOfVisit;
    private String studentRegNo;
    private String studentRoomNo;
    private String studentName;
    private String hostelName;
    private LocalDateTime timeOfOuting;
    private LocalDateTime timeOfReturn;
    
    
}
