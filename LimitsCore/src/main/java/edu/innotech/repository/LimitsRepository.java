package edu.innotech.repository;

import edu.innotech.entity.UsersLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface LimitsRepository extends JpaRepository<UsersLimit, Long> {
    @Query(value = "select l from UsersLimit l where l.userId = :userId")
    Optional<UsersLimit> findLimitByUserId(@Param("userId") Long userId);

    @Modifying
    @Query(value = "insert into userslimits (user_id, limit_value) values (:userId, :limitValue)", nativeQuery = true)
    void createUserLimit(@Param("userId") Long userId, @Param("limitValue") Double limitValue);

    @Modifying
    @Transactional
    @Query(value = "update UsersLimit l set l.limitValue = :limit where l.userId = :userId")
    void  updateLimitValue(@Param("userId") Long userId, @Param("limit") Double limit);

    @Modifying
    @Transactional
    @Query(value = "update UsersLimit l set l.limitValue = l.limitValue - :dec where l.userId = :userId")
    void  decreaseLimitValue(@Param("userId") Long userId, @Param("dec") Double decrement);

    @Modifying
    @Transactional
    @Query(value = "update UsersLimit l set l.limitValue = l.limitValue + :rec where l.userId = :userId")
    void  recoveryLimitValue(@Param("userId") Long userId, @Param("rec") Double recovery);

    @Modifying
    @Transactional
    @Query(value = "update UsersLimit l set l.limitValue = 10000.00 ")
    void setLimitValue();
}

