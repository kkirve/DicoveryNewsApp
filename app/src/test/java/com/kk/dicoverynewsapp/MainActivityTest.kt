package com.kk.dicoverynewsapp


import com.google.common.truth.Truth.assertThat
import com.kk.dicoverynewsapp.Utils.WebserviceUtil

import org.junit.Test

class MainActivityTest{

    @Test
    fun empty_search_query_return_false()
    {
        val result = WebserviceUtil.isValidWebCall(
            "",
             "2022-10-10",
            "popularity",
            "22bb22af9cdd4fc5ab21d3eefc387b09"
                )
        assertThat(result).isFalse()
    }

    @Test
    fun empty_query_from_return_false()
    {
        val result = WebserviceUtil.isValidWebCall(
            "Canada",
            "",
            "popularity",
            "22bb22af9cdd4fc5ab21d3eefc387b09"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun empty_query_sortby_return_false()
    {
        val result = WebserviceUtil.isValidWebCall(
            "Canada",
            "2022-10-10",
            "",
            "22bb22af9cdd4fc5ab21d3eefc387b09"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun empty_query_api_key_return_false()
    {
        val result = WebserviceUtil.isValidWebCall(
            "Canada",
            "2022-10-10",
            "popularity",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun valid_all_input_query_return_true()
    {
        val result = WebserviceUtil.isValidWebCall(
            "Canada",
            "2022-10-10",
            "popularity",
            "22bb22af9cdd4fc5ab21d3eefc387b09"
        )
        assertThat(result).isTrue()
    }
}