package com.wander4096.bangumix.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.SqlParameter
import org.springframework.stereotype.Service
import org.springframework.util.LinkedCaseInsensitiveMap
import java.sql.Types

@Service
class RecommendService @Autowired constructor(
        private val jdbcTemplate: JdbcTemplate
){
    fun recommendForUser(username: String): List<String> {
        val result = jdbcTemplate.call({
            val callableStatement = it.prepareCall("{call bangumix_recommend(?)}")
            callableStatement.setString(1, username)
            callableStatement
        }, listOf(
                SqlParameter(Types.VARCHAR)
        ))["#result-set-1"] as ArrayList<LinkedCaseInsensitiveMap<String>>

        return result.flatMap { it.values }
    }
}