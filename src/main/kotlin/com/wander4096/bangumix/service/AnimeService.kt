package com.wander4096.bangumix.service

import com.wander4096.bangumix.data.Anime
import com.wander4096.bangumix.data.AnimeWithRank
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

    fun findAllWithRank(): List<AnimeWithRank> {
        return jdbcTemplate.query("select a.anime_name, director_name, synopsis, rank from bangumix_anime as a left join bangumix_anime_rank as b on a.anime_name = b.anime_name;", AnimeWithRank())
    }

    private fun validateAnime(anime: Anime): Boolean {
        return (anime.animeName.trim() != "")
                && (anime.directorName.trim() != "")
                && (anime.synopsis.trim() != "")
    }
}