package com.dcapp.creds_keeper.utils

import android.util.Log

object Logger {
    val tag = "LOGGER"

    fun message(msg : Any){
        Log.d(tag,msg.toString())
    }

    fun error(err: Any){
        if(err is Exception){
            Log.e(tag,"${err.stackTrace[0].className} -> ${err.stackTrace[0].methodName} : \n $err")
        }else{
            Log.e(tag,"$err")
        }
    }
}