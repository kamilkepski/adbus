package pl.kepski.busdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kepski.busdemo.entity.Ad;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {

    @Query(
            value = "SELECT * FROM ads a WHERE a.user_id = ?1",
            nativeQuery = true)
    List<Ad> findByUserId(Long id);

    @Modifying
    @Query(
            value = "DELETE FROM ads a WHERE a.id = ?1",
            nativeQuery = true)
    void deleteById(Long id);

    @Query(
            value = "SELECT COUNT(*) FROM ads WHERE user_id = ?1",
            nativeQuery = true)
    Long countUserAds(@Param("user_id") Long id);
}
