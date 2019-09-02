package com.free.zdp.repository;

import com.free.zdp.domain.TLDUSER;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository extends PagingAndSortingRepository<TLDUSER,Long> {


    @Query("select v from TLDUSER v where v.id = ?1")
    TLDUSER findByIds(Long id);

    /**
     * 根据爱好找
     */
    @Query("select v from TLDUSER v where v.username = ?1")
    TLDUSER findUserByName(String name);
}
