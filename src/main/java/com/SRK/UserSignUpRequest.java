package com.SRK;

import java.util.List;

import lombok.Data;

@Data
public class UserSignUpRequest {
	private String name;
    private String password;
	private HostelDetail hostelDetail;
    private List<Student> studentData;
    private List<Staff> staffDetail;
}
