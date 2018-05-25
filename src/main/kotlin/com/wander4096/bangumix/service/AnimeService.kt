package com.wander4096.bangumix.service

import com.wander4096.bangumix.data.Anime
import com.wander4096.bangumix.data.AnimeFullInfo
import com.wander4096.bangumix.data.AnimeTag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class AnimeService @Autowired constructor(
        private val jdbcTemplate: JdbcTemplate
){
    fun findAll(): List<Anime> {
        return jdbcTemplate.query("select * from bangumix_anime", Anime())
    }

    fun findByName(name: String): Anime? {
        val maybeAnimes = jdbcTemplate.query("select * from bangumix_anime where anime_name = ?", arrayOf(name), Anime())
        return maybeAnimes.firstOrNull()
    }

    fun insertOne(anime: Anime) {
        when {
            !validateAnime(anime) -> throw IllegalArgumentException("动画信息格式有误，各项不可为空！")
            null != findByName(anime.animeName) -> throw IllegalArgumentException("同名动画已存在，新增失败！")
            else -> jdbcTemplate.update("insert into bangumix_anime (anime_name, director_name, synopsis) values (?, ?, ?)", anime.animeName, anime.directorName, anime.synopsis)
        }
    }

    fun findAllFullInformation(): List<AnimeFullInfo> {
        return jdbcTemplate.query("select * from bangumix_anime_full_info", AnimeFullInfo())
    }

    fun findOneFullInformation(animeName: String): AnimeFullInfo? {
        val result = jdbcTemplate.query("select * from bangumix_anime_full_info where anime_name=?", arrayOf(animeName), AnimeFullInfo())
        return result.firstOrNull()
    }

    private fun validateAnime(anime: Anime): Boolean {
        return (anime.animeName.trim() != "")
                && (anime.directorName.trim() != "")
                && (anime.synopsis.trim() != "")
    }
}