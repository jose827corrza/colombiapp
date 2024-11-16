package com.josedev.colombiapp.client

import com.josedev.colombiapp.client.responses.Colombia
import com.josedev.colombiapp.client.responses.Department
import com.josedev.colombiapp.client.responses.Region
import com.josedev.colombiapp.utils.Resource

interface Client {

    suspend fun greeting(): Resource<String>
    suspend fun colombia(): Resource<Colombia>
    suspend fun region(): Resource<List<Region>>
    suspend fun statesByRegionId(id: String): Resource<Region>
    suspend fun stateDetailById(id: String): Resource<Department>

}