package mc.com.pedago.tools

import android.content.Context
import android.content.Intent

object Tools {

    fun openActivity(context: Context, activityClass: Class<*>) {
        val i = Intent(context, activityClass)
        context.startActivity(i)
    }

}