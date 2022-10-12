package com.kk.dicoverynewsapp.Utils

object WebserviceUtil {

    fun isValidWebCall(queryString:String,queryFrom :String,querySortBy :String,queryAPIKey :String) :Boolean
    {
        val existingFilter = listOf("popularity", "language")
        if(queryString.isEmpty() || queryFrom.isEmpty() ||
            querySortBy.isEmpty()|| queryAPIKey.isEmpty())
        {
            return false
        }
        if(queryString.count{it.isLetterOrDigit()} <2)
        {
            return false
        }
        if(querySortBy !in existingFilter)
        {
            return false
        }

        return true
    }

}