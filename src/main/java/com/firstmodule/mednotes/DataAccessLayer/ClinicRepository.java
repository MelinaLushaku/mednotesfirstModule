package com.firstmodule.mednotes.DataAccessLayer;

import com.firstmodule.mednotes.Model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<Clinic, Integer> {
}
