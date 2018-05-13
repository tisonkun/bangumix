package com.wander4096.bangumix.data

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

data class AnimeWithRank(val animeName: String = "",
                         val directorName: String = "",
                         val synopsis: String = "",
                         val rank: Double = 0.0): RowMapper<AnimeWithRank> {
    override fun mapRow(r: ResultSet, i: Int) = AnimeWithRank(
            r.getString("anime_name"),
            r.getString("director_name"),
            r.getString("synopsis"),
            r.getDouble("rank")
    )
}