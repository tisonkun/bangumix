package com.wander4096.bangumix.service

import com.wander4096.bangumix.data.AnimeComment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class CommentService @Autowired constructor(
        private val jdbcTemplate: JdbcTemplate
){
    fun findAllByAnime(animeName: String): List<AnimeComment> {
        return jdbcTemplate.query("select * from bangumix_anime_comment where anime_name = ?", arrayOf(animeName), AnimeComment())
    }

    fun insertOne(animeComment: AnimeComment) {
        if (validateAnime(animeComment)) {
            jdbcTemplate.update("insert into bangumix_anime_comment (anime_name, username, comment_content) values (?,?,?)", animeComment.animeName, animeComment.username, animeComment.commentContent)
        } else {
            throw IllegalArgumentException("评论内容不可为空!")
        }
    }

    private fun validateAnime(animeComment: AnimeComment): Boolean {
        return animeComment.commentContent != ""
    }
}