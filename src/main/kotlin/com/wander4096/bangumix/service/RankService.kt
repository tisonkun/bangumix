package com.wander4096.bangumix.service

import com.wander4096.bangumix.data.AnimePoint
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class RankService @Autowired constructor(
        private val jdbcTemplate: JdbcTemplate
){
    fun findByAnimeAndUser(animeName: String, username: String): AnimePoint? {
        val maybeAnimePoint = jdbcTemplate.query("select * from bangumix_anime_point where anime_name = ? and username = ?", arrayOf(animeName, username), AnimePoint())
        return maybeAnimePoint.firstOrNull()
    }

    fun insertOne(animeName: String, username: String, pointAsString: String) {
        val point = validatePoint(pointAsString)
        if (alreadyPoint(animeName, username)) { // Update
            jdbcTemplate.update("update bangumix_anime_point set point = ? where anime_name = ? and username = ?", point, animeName, username)
        } else { // Insert
            jdbcTemplate.update("insert into bangumix_anime_point (anime_name, username, point) values (?,?,?)", animeName, username, point)
        }
    }

    fun validatePoint(pointAsString: String): Int {
        try {
            val point = pointAsString.toInt()
            if (point !in 1..5) {
                throw IllegalArgumentException("评分分数必须是 1 到 5 的整数")
            }
            return point
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("评分分数必须是 1 到 5 的整数")
        }
    }

    fun alreadyPoint(animeName: String, username: String): Boolean {
        return null != findByAnimeAndUser(animeName, username)
    }
}