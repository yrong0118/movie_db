package com.example.movie_db.Util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class Util {

    companion object {
        @JvmStatic
        fun getdate(tag:String,day:Int): String {
            //2020-01-15
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            //Getting current date
            val cal = Calendar.getInstance()
            //Number of Days to add/substract
            cal.add(Calendar.DAY_OF_MONTH, day)
            //Date after adding/subtract the days to the current date
            val newDate = sdf.format(cal.getTime())
            //Displaying the new Date after addition of Days to current date
            Log.d(tag,"Date after Addition: $newDate")
            return newDate
        }



        fun isStringEmpty(str:String?):Boolean{
            return (str == null || str.length == 0)
        }
    }
}