package com.wander4096.bangumix.service

import com.wander4096.bangumix.data.AnimeRank
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class AnimeRankService @Autowired constructor(
        private val jdbcTemplate: JdbcTemplate
){
    fun findAll(): List<AnimeRank> = jdbcTemplate.query("select * from bangumix_anime_rank", AnimeRank())
}