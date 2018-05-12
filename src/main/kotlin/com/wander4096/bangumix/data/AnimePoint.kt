package com.wander4096.bangumix.data

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

data class AnimePoint(val animeName: String = "",
                      val username: String = "",
                      val point: Int = 0): RowMapper<AnimePoint> {
    override fun mapRow(r: ResultSet, i: Int) = AnimePoint(
            r.getString("anime_name"),
            r.getString("username"),
            r.getInt("point")
    )
}
