package com.wander4096.bangumix.service

import com.wander4096.bangumix.data.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(
        private val jdbcTemplate: JdbcTemplate
){
    fun checkUsername(username: String): User? {
        val maybeUser = jdbcTemplate.query("select * from bangumix_user where username = ?", arrayOf(username), User())
        return maybeUser.firstOrNull()
    }

    fun checkUsernameAndPassword(username: String, password: String): User? {
        val maybeUser = jdbcTemplate.query("select * from bangumix_user where username = ? and password = ?", arrayOf(username, password), User())
        return maybeUser.firstOrNull()
    }

    fun registerUser(user: User) {
        if (null != checkUsername(user.username)) {
            throw IllegalArgumentException("用户已存在")
        } else {
            jdbcTemplate.update("insert bangumix_user (username, password) values (?, ?)", user.username, user.password)
        }
    }
}