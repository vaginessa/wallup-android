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
package com.dawnimpulse.wallup.activities

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.LinearLayoutManager
import com.dawnimpulse.wallup.R
import com.dawnimpulse.wallup.adapters.ArtistPhotosAdapter
import com.dawnimpulse.wallup.handlers.ColorHandler
import com.dawnimpulse.wallup.handlers.ImageHandler
import com.dawnimpulse.wallup.models.UnsplashModel
import com.dawnimpulse.wallup.pojo.CollectionPojo
import com.dawnimpulse.wallup.pojo.ImagePojo
import com.dawnimpulse.wallup.utils.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_collection.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @author Saksham
 *
 * @note Last Branch Update - master
 * @note Created on 2018-09-08 by Saksham
 *
 * @note Updates :
 * Saksham - 2018 11 28 - master - Connection handling
 * Saksham - 2018 12 19 - master - unsplash handling
 */
class CollectionActivity : AppCompatActivity(), View.OnClickListener {
    private var NAME = "CollectionActivity"
    private lateinit var details: CollectionPojo
    private lateinit var model: UnsplashModel
    private lateinit var type: String
    private var color = 0

    // on create
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection)

        type = intent.extras!!.getString(C.TYPE)!!
        if (type == C.WALLUP)
            type = C.FEATURED
        model = UnsplashModel(lifecycle)
        details = Gson().fromJson(intent.extras.getString(C.COLLECTION), CollectionPojo::class.java)
        setDetails()

        when (type) {
            C.FEATURED -> model.collectionPhotos(details.id, 1, 8) { e, r ->
                e?.let {
                    L.d(NAME, e.toString())
                    toast("error fetching images")
                }
                r?.let {
                    var adapter = ArtistPhotosAdapter(this, lifecycle, r as List<ImagePojo?>)
                    colRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                    colRecycler.adapter = adapter
                    colRecycler.clipToPadding = false
                }
            }
            C.CURATED -> model.curatedCollectionPhotos(details.id, 1, 8) { e, r ->
                e?.let {
                    L.d(NAME, e.toString())
                    toast("error fetching images")
                }
                r?.let {
                    var adapter = ArtistPhotosAdapter(this, lifecycle, r as List<ImagePojo?>)
                    colRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                    colRecycler.adapter = adapter
                    colRecycler.clipToPadding = false
                }
            }
        }

        colMore.setOnClickListener(this)
        colUserImage.setOnClickListener(this)
        colUserImageL.setOnClickListener(this)
        colBack.setOnClickListener(this)
        colUnsplash.setOnClickListener(this)
    }

    // on start
    override fun onStart() {
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this)
        super.onStart()
    }

    // on destroy
    override fun onDestroy() {
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

    // on click
    override fun onClick(v: View) {
        when (v.id) {
            colMore.id -> {
                var intent = Intent(this, GeneralImagesActivity::class.java)
                intent.putExtra(C.TYPE, C.COLLECTION)
                intent.putExtra(C.COLLECTION, type)
                intent.putExtra(C.ID, details.id)
                startActivity(intent)
            }
            colUserImage.id, colUserImageL.id -> {
                var intent = Intent(this, ArtistProfileActivity::class.java)
                intent.putExtra(C.USERNAME, details.user!!.username)
                startActivity(intent)
            }
            colBack.id -> finish()
            colUnsplash.id -> F.startWeb(this,details!!.links!!.html + C.UTM)
        }
    }

    // on message event
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: Event) {
        if (event.obj.has(C.TYPE)) {
            if (event.obj.getString(C.TYPE) == C.NETWORK) {
                runOnUiThread {
                    if (event.obj.getBoolean(C.NETWORK)) {
                        colConnLayout.setBackgroundColor(Colors(this).GREEN)
                        colConnText.text = "Back Online"
                        GlobalScope.launch {
                            delay(1500)
                            runOnUiThread {
                                colConnLayout.visibility = View.GONE
                            }
                        }
                    } else {
                        colConnLayout.visibility = View.VISIBLE
                        colConnLayout.setBackgroundColor(Colors(this).LIKE)
                        colConnText.text = "No Internet"
                    }
                }
            }
        }
    }

    // setting details in views
    private fun setDetails() {
        var name = F.capWord(details.user!!.name)
        color = Colors(this).GREY_500
        details.cover_photo?.let {
            color = Color.parseColor(details.cover_photo!!.color!!)
        }
        name = "<font color=\"#ffffff\">${F.firstWord(name)}</font> ${name.replace(F.firstWord(name), "")}"
        colUserName.setTextColor(color)
        colTitle.text = F.capWord(details.title)
        colDescription.text = details.description
        colUserName.setText(F.fromHtml(name), TextView.BufferType.SPANNABLE)
        colImageC.setText(F.fromHtml("${details.total_photos} <font color=\"#ffffff\">photos</font>"), TextView.BufferType.SPANNABLE)
        colImageUpdated.setText(F.fromHtml("<font color=\"#ffffff\">updated on</font> ${F.dateConvert(details.updated_at)}"), TextView.BufferType.SPANNABLE)
        colPublished.setText(F.fromHtml("<font color=\"#ffffff\">published on</font> ${F.dateConvert(details.published_at)}"), TextView.BufferType.SPANNABLE)

        details.cover_photo?.let {
            ImageHandler.getImageAsBitmap(lifecycle, this, details.cover_photo!!.urls?.full + Config.IMAGE_LIST_QUALITY) {
                val color = ColorHandler.getNonDarkColor(Palette.from(it).generate(), this)
                colImage.setImageBitmap(it)
                setColor(color)
            }
        }
        ImageHandler.setImageInView(lifecycle, colUserImage, details.user?.profile_image!!.large)

        setColor(color)
    }

    // set color on views
    private fun setColor(color: Int) {
        colTitle.setTextColor(color)
        colImageC.setTextColor(color)
        //colDescription.setTextColor(color)
        colUserName.setTextColor(color)
        colImageUpdated.setTextColor(color)
        colPublished.setTextColor(color)

        (colBack.background.current as GradientDrawable).setColor(color)
    }
}
