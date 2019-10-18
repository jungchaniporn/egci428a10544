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

class MainActivity : AppCompatActivity() {
    protected var data:ArrayList<City>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        data = DataProvider.getData()
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
