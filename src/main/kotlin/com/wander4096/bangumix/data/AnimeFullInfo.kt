package com.wander4096.bangumix.data

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

data class AnimeFullInfo(val animeName: String = "",
                         val directorName: String = "",
                         val synopsis: String = "",
                         val rank: Double = 0.0,
                         val tags: List<String> = arrayListOf()): RowMapper<AnimeFullInfo> {
    override fun mapRow(r: ResultSet, i: Int) = AnimeFullInfo(
            r.getString("anime_name"),
            r.getString("director_name"),
            r.getString("synopsis"),
            r.getDouble("rank"),
            r.getString("tags").split("/").take(5)
    )
}