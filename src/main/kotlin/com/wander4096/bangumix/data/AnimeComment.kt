package com.wander4096.bangumix.data

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.sql.Timestamp

data class AnimeComment(val commentId: Int = 0,
                        val animeName: String = "",
                        val username: String = "",
                        val commentContent: String = "",
                        val commentTimestamp: Timestamp = Timestamp(0)): RowMapper<AnimeComment> {
    override fun mapRow(r: ResultSet, i: Int) = AnimeComment(
            r.getInt("comment_id"),
            r.getString("anime_name"),
            r.getString("username"),
            r.getString("comment_content"),
            r.getTimestamp("comment_timestamp")
    )
}
