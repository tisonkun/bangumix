package com.wander4096.bangumix.data

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

data class AnimeComment(val commentId: Int = 0,
                        val animeName: String = "",
                        val username: String = "",
                        val commentContent: String = ""): RowMapper<AnimeComment> {
    override fun mapRow(r: ResultSet, i: Int) = AnimeComment(
            r.getInt("comment_id"),
            r.getString("anime_name"),
            r.getString("username"),
            r.getString("comment_content")
    )
}
