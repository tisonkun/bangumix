package com.wander4096.bangumix.data

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

data class User(val username: String = "",
                val password: String = "") : RowMapper<User> {
    override fun mapRow(r: ResultSet, i: Int) = User(
            r.getString("username"),
            r.getString("password"))
}