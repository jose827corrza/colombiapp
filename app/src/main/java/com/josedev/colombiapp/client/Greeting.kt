package com.josedev.colombiapp.client

import com.josedev.colombiapp.client.responses.Colombia
import com.josedev.colombiapp.client.responses.Region
import com.josedev.colombiapp.utils.Resource

interface Greeting {

    suspend fun greeting(): Resource<String>
    suspend fun colombia(): Resource<Colombia>
    suspend fun region(): Resource<List<Region>>
}