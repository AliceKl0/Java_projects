package ru.fa.me;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CalculationHistoryRepository extends JpaRepository<CalculationHistory, Long> {

    @Query("SELECT ch FROM CalculationHistory ch JOIN FETCH ch.user ORDER BY ch.timestamp DESC")
    List<CalculationHistory> findAllWithUser();

    @Query("SELECT ch FROM CalculationHistory ch JOIN FETCH ch.user WHERE ch.user = :user ORDER BY ch.timestamp DESC")
    List<CalculationHistory> findByUserOrderByTimestampDesc(User user);

    @Modifying
    @Query("DELETE FROM CalculationHistory h WHERE h.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}
