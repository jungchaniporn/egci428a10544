package com.egci428a10544

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.city_item.view.*
import kotlin.random.Random

private class City(val name: String, val description: String, val part: String, val image: String){
    override fun toString(): String {
        return name
    }
    fun getData():ArrayList<City>{
        return cityList
    }
    private val cityList = arrayListOf<City>(
        City("Phuket", "Phuket, a rainforested, mountainous island in the Andaman Sea, has some of Thailand’s most popular beaches, mainly situated along the clear waters of the western shore. The island is home to many high-end seaside resorts, spas and restaurants. Phuket City, the capital, has old shophouses and busy markets. ", "Southern part","phuket"),
        City("Bangkok", "Bangkok, Thailand’s capital, is a large city known for ornate shrines and vibrant street life. The boat-filled Chao Phraya River feeds its network of canals, flowing past the Rattanakosin royal district, home to opulent Grand Palace and its sacred Wat Phra Kaew Temple. Nearby is Wat Pho Temple with an enormous reclining Buddha and, on the opposite shore, Wat Arun Temple with its steep steps and Khmer-style spire.", "Central part","bangkok"),
        City("Chiang Mai", "Chiang Mai is a city in mountainous northern Thailand. Founded in 1296, it was capital of the independent Lanna Kingdom until 1558. Its Old City area still retains vestiges of walls and moats from its history as a cultural and religious center. It’s also home to hundreds of elaborate Buddhist temples, including 14th-century Wat Phra Singh and 15th-century Wat Chedi Luang, adorned with carved serpents.", "Northern part","chiangmai"),
        City("Pattaya", "Pattaya is a city on Thailand’s eastern Gulf coast known for its beaches. A quiet fishing village as recently as the 1960s, it’s now lined with resort hotels, high-rise condos, shopping malls, cabaret bars and 24-hour clubs. Nearby, hillside Wat Phra Yai Temple features an 18m-tall golden Buddha. The area also features several designer golf courses, some with views of Pattaya Bay.", "Central part","pattaya")
    )
}
class MainActivity : AppCompatActivity() {
    private var data:ArrayList<City>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        data = DataProvider.getData()
        val cityArrayAdapter = CityArrayAdapter(this,data!!)
        list.setAdapter(cityArrayAdapter)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_main,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val position = Random.nextInt(0,3)
        val id = item.getItemId()
        if(id==R.id.add_city){
            data!!.add(data!![position])
            val cityArrayAdapter = CityArrayAdapter(this,data!!)
            list.setAdapter(cityArrayAdapter)
        }

        return super.onOptionsItemSelected(item)
    }
    private class CityArrayAdapter(var context:Context,var objects:ArrayList<City>):BaseAdapter(){
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val city = objects[position]
            val view:View

            if(convertView==null){
                val layoutInflater = LayoutInflater.from(parent!!.context)
                view = layoutInflater.inflate(R.layout.city_item,parent,false)
                val viewHolder = ViewHolder(view.titleText,view.locationText,view.ImgCity)
                view.tag = viewHolder
            }else{
                view = convertView
            }

            val viewHolder = view.tag as ViewHolder
            viewHolder.titleText.text = city.name
            viewHolder.locationText.text = "Location: "+city.part
            val res = context.resources.getIdentifier(city.image,"drawable",context.packageName)
            viewHolder.ImgCity.setImageResource(res)
            if(position%2==0){
                view.setBackgroundColor(Color.parseColor("#D3D3D3"))
            }
            else{
                view.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }

            view.setOnClickListener {
                val intent = Intent(context,DetailPage::class.java)
                intent.putExtra("name",city.name)
                intent.putExtra("description",city.description)
                intent.putExtra("part",city.part)
                intent.putExtra("image",city.image)
                context.startActivity(intent)

            }
            return view
        }

        private class ViewHolder(val  titleText:TextView, val locationText:TextView, val ImgCity:ImageView)

        override fun getItem(position: Int): Any {
            return objects[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return objects.size
        }

    }



}
