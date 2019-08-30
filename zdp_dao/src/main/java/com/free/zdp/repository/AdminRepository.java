package com.free.zdp.repository;

import com.free.zdp.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository extends PagingAndSortingRepository<User,Long> {


    @Query("select v from User v where v.id = ?1")
    User findByIds(Long id);

    /**
     * 根据爱好找
     */
    @Query("select v from User v where v.username = ?1")
    User findUserByName(String name);
}
