package com.SRK;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VisitorScanController {
           @Autowired
           VisitorScanRepository repo;
           
           @PostMapping
           public ResponseEntity<String> scanVisitor(@RequestBody VisitorScanRequest request){
        	   VisitorScan scan =new VisitorScan();
        	   scan.setPlaceOfVisit(request.getPlaceOfVisit());
        	   scan.setPurposeOfVisit(request.getPurposeOfVisit());
        	   scan.setStudentRegNo(request.getStudentRegNo());
        	   scan.setStudentRoomNo(request.getStudentRoomNo());
        	   scan.setStudentName(request.getStudentName());
        	   scan.setHostelName(request.getHostelName());
        	   scan.setTimeOfOuting(request.getTimeOfOuting()!= null ? request.getTimeOfOuting() : LocalDateTime.now());
        	   repo.save(scan);
        	   return  ResponseEntity.ok("Visitor scan recorded successfully.");
           }
           @PutMapping("/return/{studentRegNo}")
           public ResponseEntity<?> confirmReturn(@PathVariable String studentRegNo) {
               // Get latest outing without return time
               Optional<VisitorScan> optional = repo
                       .findTopByStudentRegNoAndTimeOfReturnIsNullOrderByTimeOfOutingDesc(studentRegNo);

               if (optional.isPresent()) {
                   VisitorScan scan = optional.get();
                   scan.setTimeOfReturn(LocalDateTime.now());
                   repo.save(scan);

                   // Return details to show to the security guard
                   Map<String, Object> response = new HashMap<>();
                   response.put("studentName", scan.getStudentName());
                   response.put("studentRegNo", scan.getStudentRegNo());
                   response.put("roomNumber", scan.getStudentRoomNo());
                   response.put("hostelName", scan.getHostelName());
                   response.put("placeOfVisit", scan.getPlaceOfVisit());
                   response.put("purposeOfVisit", scan.getPurposeOfVisit());
                   response.put("timeOfOuting", scan.getTimeOfOuting());
                   response.put("timeOfReturn", scan.getTimeOfReturn());

                   return ResponseEntity.ok(response);
               }

               return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No active outing found for student.");
           }
       }

