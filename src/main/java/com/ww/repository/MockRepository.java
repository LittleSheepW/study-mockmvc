package com.ww.repository;

import com.ww.pojo.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Program: study-mockmvc
 * @Description: MockRepository继承JpaRepository
 * @Author: Sun
 * @Create: 2019-04-22 11:19
 * @Version: 1.0
 **/
@Repository
public interface MockRepository extends JpaRepository<AuthUser, Long> {
}
