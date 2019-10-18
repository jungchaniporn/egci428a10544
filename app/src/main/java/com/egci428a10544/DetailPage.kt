package com.egci428a10544

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail_page.*
import kotlinx.android.synthetic.main.city_item.*

class DetailPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)

        val actionbr = supportActionBar
        actionbr!!.setDisplayHomeAsUpEnabled(true)
        actionbr!!.setDisplayHomeAsUpEnabled(true)

        val name:String?
        val description:String?
        val part:String?
        val image:String?
        val extra = intent.extras
        if(extra!=null){
            name = extra.getString("name")
            nameTxt.text = name
            description = extra.getString("description")
            detailTxt.text = description
            part = extra.getString("part")
            locationTxt.text = "Location: " + part
            image = extra.getString("image")
            val res = this.resources.getIdentifier(image,"drawable",this.packageName)
            this.placeImg.setImageResource(res)

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
