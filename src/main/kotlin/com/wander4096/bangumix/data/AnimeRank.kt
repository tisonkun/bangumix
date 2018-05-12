package com.wander4096.bangumix.data

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

data class AnimeRank(val animeName: String = "",
                     val rank: Double = 0.0): RowMapper<AnimeRank> {
    override fun mapRow(r: ResultSet, i: Int) = AnimeRank(
            r.getString("anime_name"),
            r.getDouble("rank")
    )
}