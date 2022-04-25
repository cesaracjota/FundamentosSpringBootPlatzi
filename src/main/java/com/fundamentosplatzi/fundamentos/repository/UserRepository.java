package com.fundamentosplatzi.fundamentos.repository;

import com.fundamentosplatzi.fundamentos.entity.User;
import com.fundamentosplatzi.fundamentos.dto.UserDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    @Query("Select u from User u WHERE u.email=?1")
    Optional<User> findByUserEmail(String email);

    @Query("Select u from User u WHERE u.name  like ?1%")
    List<User> findAndSort(String name, Sort sort);

    List<User> findByName(String name);

    Optional<User> findByEmailAndName(String email, String name);

    List<User> findByNameLike(String name);

    List<User> findByNameOrEmail(String name, String email);

    List<User> findByBirthDateBetween(LocalDate begin, LocalDate end);

    List<User> findByNameContainingOrderByIdDesc(String name);

    @Query("SELECT new com.fundamentosplatzi.fundamentos.dto.UserDto(u.id, u.name, u.birthDate)" +
    " FROM User u" +
            " where u.birthDate=:paramentroFecha " +
    "and u.email=:parametroEmail ")
    Optional<UserDto> getAllByBirthDateAndEmail(@Param("paramentroFecha") LocalDate date,
                                                @Param("parametroEmail") String email);

    List<User> findAll();
}
