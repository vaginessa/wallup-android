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
package com.dawnimpulse.wallup.utils

import android.content.Context
import android.net.Uri
import androidx.core.content.ContextCompat
import com.dawnimpulse.wallup.R
import com.dawnimpulse.wallup.pojo.IconsPojo
import com.dawnimpulse.wallup.pojo.LibraryPojo

/**
 * @info -
 *
 * @author - Saksham
 * @note Last Branch Update - master
 *
 * @note Created on 2018-12-15 by Saksham
 * @note Updates :
 */
object Arrays {
    val LIBRARIES = listOf(
            LibraryPojo("Glide", "An image loading and caching library for Android focused on smooth scrolling.", "https://github.com/bumptech/glide"),
            LibraryPojo("Retrofit", "Type-safe HTTP client for Android and Java by Square, Inc.", "https://github.com/square/retrofit"),
            LibraryPojo("RoundedImageView", "A fast ImageView that supports rounded corners, ovals, and circles.", "https://github.com/vinc3m1/RoundedImageView"),
            LibraryPojo("Calligraphy", "Custom fonts in Android the easy way...", "https://github.com/chrisjenx/Calligraphy"),
            /*LibraryPojo("AndroidImageCropper", "Image Cropping Library for Android, optimized for Camera / Gallery.", "https://github.com/ArthurHub/Android-Image-Cropper"),*/
            LibraryPojo("PhotoView", "Implementation of ImageView for Android that supports zooming, by various touch gestures.", "https://github.com/chrisbanes/PhotoView"),
            LibraryPojo("Permissions", "Simple permission handling in Android.", "https://github.com/DawnImpulse/permissions-android"),
            LibraryPojo("EasyPrefs", "A small library containing a wrapper/helper for the Shared Preferences of android.", "https://github.com/Pixplicity/EasyPrefs"),
            LibraryPojo("AutoFitTextView", "A TextView that automatically resize text to fit perfectly within its bounds.", "https://github.com/grantland/android-autofittextview"),
            LibraryPojo("EventBus", "Event bus for Android and Java that simplifies communication between Activities, Fragments, Threads, Services, etc. Less code, better quality.", "https://github.com/greenrobot/EventBus"),
            LibraryPojo("MPAndroidChart", "A powerful \uD83D\uDE80 Android chart view / graph view library, supporting line- bar- pie- radar- bubble- and candlestick charts as well as scaling, dragging and animations.", "https://github.com/PhilJay/MPAndroidChart"),
            LibraryPojo("Apache CommonsIO", "Commons IO is a library of utilities to assist with developing IO functionality.", "https://commons.apache.org/proper/commons-io/"),
            LibraryPojo("TapTargetView", "An implementation of tap targets from the Material Design guidelines for feature discovery", "https://github.com/KeepSafe/TapTargetView")
    )
    val downloadIds = ArrayList<Long>() // long for ref id

    // list of icon license
    fun icons(context: Context): List<IconsPojo> {
        val library = ContextCompat.getDrawable(context, R.drawable.vd_update)
        val changelog = ContextCompat.getDrawable(context, R.drawable.vd_changelog)
        val license = ContextCompat.getDrawable(context, R.drawable.vd_license)
        val privacy = ContextCompat.getDrawable(context, R.drawable.vd_privacy)
        val terms = ContextCompat.getDrawable(context, R.drawable.vd_terms)
        val icons = ContextCompat.getDrawable(context, R.drawable.vd_vector)
        val search = ContextCompat.getDrawable(context, R.drawable.vd_search_outline)
        val random = ContextCompat.getDrawable(context, R.drawable.vd_shuffle)
        val paypal = ContextCompat.getDrawable(context, R.drawable.vd_paypal_2)
        val heart = ContextCompat.getDrawable(context, R.drawable.vd_heart)
        val col = ContextCompat.getDrawable(context, R.drawable.vd_coll)
        val user = ContextCompat.getDrawable(context, R.drawable.vd_talent)

        return listOf(
                IconsPojo(library!!, "Dmitri13", "flaticon.com", "https://www.flaticon.com/free-icon/reload_813310"),
                IconsPojo(changelog!!, "Roundicons", "flaticon.com", "https://www.flaticon.com/free-icon/rotate_190262"),
                IconsPojo(license!!, "Freepik", "flaticon.com", "https://www.flaticon.com/free-icon/license_326613"),
                IconsPojo(privacy!!, "Freepik", "flaticon.com", "https://www.flaticon.com/free-icon/person_1047682"),
                IconsPojo(terms!!, "Freepik", "flaticon.com", "https://www.flaticon.com/free-icon/law-book_927283"),
                IconsPojo(icons!!, "Icons8", "icons8.com", "https://icons8.com"),
                IconsPojo(search!!, "Katarina Stefanikova", "flaticon.com", "https://www.flaticon.com/free-icon/search-magnifier-outline_59200"),
                IconsPojo(random!!, "Freepik", "flaticon.com", "https://www.flaticon.com/free-icon/shuffle_456232"),
                IconsPojo(paypal!!, "Pixel Perfect", "flaticon.com", "https://www.flaticon.com/free-icon/paypal_888920"),
                IconsPojo(heart!!, "Pixel Buddha", "flaticon.com", "https://www.flaticon.com/free-icon/heart_214309"),
                IconsPojo(col!!, "Freepik", "flaticon.com", "https://www.flaticon.com/free-icon/layout_747843"),
                IconsPojo(user!!, "Freepik", "flaticon.com", "https://www.flaticon.com/free-icon/talent_1152844")
        )
    }


}