package com.wander4096.bangumix.service

import com.wander4096.bangumix.data.AnimeComment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class commentService @Autowired constructor(
        private val jdbcTemplate: JdbcTemplate
){
    fun findAllByAnime(animeName: String): List<AnimeComment> {
        return jdbcTemplate.query("select * from bangumix_anime_comment where anime_name = ?", arrayOf(animeName), AnimeComment())
    }
}