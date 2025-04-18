package com.SRK;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class VisitorScanRequest {
	 private String placeOfVisit;
	    private String purposeOfVisit;
	    private String studentRegNo;
	    private String studentRoomNo;
	    private String studentName;
	    private String hostelName;
	    private LocalDateTime timeOfOuting;
}
