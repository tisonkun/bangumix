package com.wander4096.bangumix.service

import com.wander4096.bangumix.data.AnimeTag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class TagService @Autowired constructor(
        private val jdbcTemplate: JdbcTemplate
){
    fun findByAnimeAndUser(animeName: String, username: String): List<AnimeTag> {
        return jdbcTemplate.query("select * from bangumix_anime_tag where anime_name=? and username=?", arrayOf(animeName, username), AnimeTag())
    }
}