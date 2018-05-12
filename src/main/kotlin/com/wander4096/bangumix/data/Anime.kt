package com.wander4096.bangumix.data

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

data class Anime(val animeName: String = "",
                 val directorName: String = "",
                 val synopsis: String = ""): RowMapper<Anime> {
    override fun mapRow(r: ResultSet, i: Int) = Anime(
            r.getString("anime_name"),
            r.getString("director_name"),
            r.getString("synopsis")
    )
}