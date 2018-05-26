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

    fun insertOne(animeName: String, username: String, tagContent: String) {
        validate(animeName, username, tagContent)
        jdbcTemplate.update("insert into bangumix_anime_tag (anime_name, username, tag_content) values (?,?,?)", animeName, username, tagContent)
    }

    fun removeOne(animeName: String, username: String, tagContent: String) {
        jdbcTemplate.update("delete from bangumix_anime_tag where anime_name=? and username=? and tag_content=?", animeName, username, tagContent)
    }

    fun validate(animeName: String, username: String, tagContent: String) {
        if ("" == (tagContent))
            throw IllegalArgumentException("标签不可为空！")
        if (tagContent in findByAnimeAndUser(animeName, username).map { it.tagContent })
            throw IllegalArgumentException("你已经添加过这个标签啦！")
    }
}