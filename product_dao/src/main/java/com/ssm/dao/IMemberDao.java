package com.ssm.dao;

import com.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author: TJC
 * @Date: 2020/6/7 10:58
 * @description: TODO
 */
@Repository("memberDao")
public interface IMemberDao {

    @Select("select * from member where id=#{id}")
    Member findById(int id);
}
