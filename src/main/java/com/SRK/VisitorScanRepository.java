package com.SRK;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorScanRepository extends JpaRepository<VisitorScan,Long>{

	
	Optional<VisitorScan> findTopByStudentRegNoAndTimeOfReturnIsNullOrderByTimeOfOutingDesc(String string);

}
