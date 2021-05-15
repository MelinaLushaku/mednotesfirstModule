package com.firstmodule.mednotes.DataAccessLayer;

import com.firstmodule.mednotes.Model.Advertisement;
import com.firstmodule.mednotes.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer> {
    @Query("select p from Advertisement p where p.title=?1 ")
    List<Advertisement> findByTitle(String title);
}
