package com.wander4096.bangumix.data

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

data class AnimeTag(val animeName: String = "",
                    val username: String = "",
                    val tagContent: String = ""): RowMapper<AnimeTag> {
    override fun mapRow(r: ResultSet, i: Int) = AnimeTag(
            r.getString("anime_name"),
            r.getString("username"),
            r.getString("tag_content")
    )
}
