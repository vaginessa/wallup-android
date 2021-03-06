/*
ISC License

Copyright 2018-2019, Saksham (DawnImpulse)

Permission to use, copy, modify, and/or distribute this software for any purpose with or without fee is hereby granted,
provided that the above copyright notice and this permission notice appear in all copies.

THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS,
WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE
OR PERFORMANCE OF THIS SOFTWARE.*/
package com.dawnimpulse.wallup.pojo

import com.google.gson.annotations.SerializedName

/**
 * @info -
 *
 * @author - Saksham
 * @note Last Branch Update - master
 *
 * @note Created on 2018-12-09 by Saksham
 * @note Updates :
 */
data class ImageStatsPojo(
        @SerializedName("id") val id: String,
        @SerializedName("views") val views: ImageStatsInfo,
        @SerializedName("downloads") val downloads: ImageStatsInfo,
        @SerializedName("likes") val likes: ImageStatsInfo
)

data class ImageStatsInfo(
        @SerializedName("total") val total: Int,
        @SerializedName("historical") val historical: ImageStatsHistorical
)

data class ImageStatsHistorical(
        @SerializedName("change") val change: Int,
        @SerializedName("quantity") val quantity: Int,
        @SerializedName("values") val values: List<ImageStatsData>
)

data class ImageStatsData(
        @SerializedName("data") val date: String,
        @SerializedName("value") val value: Int
)