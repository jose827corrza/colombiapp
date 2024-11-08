package com.josedev.colombiapp.client

import com.josedev.colombiapp.client.responses.Colombia
import com.josedev.colombiapp.client.responses.Region
import com.josedev.colombiapp.utils.Constants
import com.josedev.colombiapp.utils.Resource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.gson.*

class GreetingImpl: Greeting {

    private val client = HttpClient {
        install(ContentNegotiation) {
            gson()
        }
    }

    override suspend fun greeting(): Resource<String> {
        val response = try {
            client.get(Constants.GREETING)
        } catch (e: Exception){
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response.bodyAsText())
    }

    override suspend fun colombia(): Resource<Colombia> {
        val response = try {
            client.get(Constants.COUNTRY)
        }catch (e: Exception){
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response.body())
    }

    override suspend fun region(): Resource<List<Region>> {
        val response = try {
            client.get(Constants.REGION)
        } catch (e: Exception){
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response.body())
    }
}
