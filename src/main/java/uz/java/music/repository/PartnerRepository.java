package uz.java.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.java.music.entity.Partner;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
    @Modifying
    @Query("DELETE FROM Partner p WHERE p.id = :id")
    void deletePartner(@Param("id") Long id);
}
