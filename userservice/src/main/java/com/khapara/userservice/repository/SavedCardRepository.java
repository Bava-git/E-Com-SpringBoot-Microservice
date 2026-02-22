package com.khapara.userservice.repository;

import com.khapara.userservice.entity.SavedCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SavedCardRepository extends JpaRepository<SavedCard, Long> {
    List<SavedCard> findByUserId(Long userId);

    Optional<SavedCard> findByIdAndUserId(Long id, Long userId);

    boolean existsByIdAndUserId(Long id, Long userId);

    @Modifying
    @Query("update CardDetails c set c.isDefault = false where c.userId = :userId")
    void resetDefaultForUser(@Param("userId") Long userId);


}
