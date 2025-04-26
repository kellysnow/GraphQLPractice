package com.example.graphqlpractice.data

import com.example.graphqlpractice.LaunchListQuery
import com.example.graphqlpractice.domain.model.Launch
import com.example.graphqlpractice.domain.model.LaunchList

fun LaunchListQuery.Launches.toLaunchList(): LaunchList {
    return LaunchList(
        cursor = this.cursor,
        list = this.launches.mapNotNull { it?.toLaunch() },
        hasMore = this.hasMore
    )
}

fun LaunchListQuery.Launch.toLaunch(): Launch {
    return Launch(
        id = this.id,
        name = this.mission?.name ?: "No name",
        site = this.site,
        imageUrl = this.mission?.missionPatch
    )
}