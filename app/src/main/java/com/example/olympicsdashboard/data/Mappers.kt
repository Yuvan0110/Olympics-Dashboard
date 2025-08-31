package com.example.olympicsdashboard.data


import com.example.olympicsdashboard.data.dto.Competitors
import com.example.olympicsdashboard.data.dto.Country
import com.example.olympicsdashboard.data.dto.Event
import com.example.olympicsdashboard.data.dto.Sport
import com.example.olympicsdashboard.data.dto.Venue
import com.example.olympicsdashboard.domain.model.CompetitorModel
import com.example.olympicsdashboard.domain.model.CountryModel
import com.example.olympicsdashboard.domain.model.EventModel
import com.example.olympicsdashboard.domain.model.SportModel
import com.example.olympicsdashboard.domain.model.VenueModel

fun Country.toDomain(): CountryModel {
    return CountryModel(
        id = id.orEmpty(),
        name = name.orEmpty(),
        continent = continent.orEmpty(),
        flagUrl = flagUrl.orEmpty(),
        goldMedals = goldMedals ?: 0,
        silverMedals = silverMedals ?: 0,
        bronzeMedals = bronzeMedals ?: 0,
        totalMedals = totalMedals ?: 0,
        rank = rank ?: 0
    )
}


fun Sport.toDomain(): SportModel {
    return SportModel(
        id = id.orEmpty(),
        name = name.orEmpty()
    )
}

fun Venue.toDomain(): VenueModel {
    return VenueModel(
        id = id.orEmpty(),
        name = name.orEmpty()
    )
}


fun Event.toDomain(): EventModel {
    return EventModel(
        id = id ?: 0,
        sport = disciplineName.orEmpty(),
        venue = venueName.orEmpty(),
        gender = eventName.orEmpty(),
        startDate = startDate.orEmpty(),
        endDate = endDate.orEmpty(),
        status = status.orEmpty(),
        competitors = competitors.map { it.toDomain() }
    )
}


fun Competitors.toDomain(): CompetitorModel {
    return CompetitorModel(
        country = countryId.orEmpty(),
        flagUrl = countryFlagUrl.orEmpty(),
        name = competitorName.orEmpty(),
        score = resultMark.orEmpty(),
        result = resultWinnerLoserTie.orEmpty()
    )
}

