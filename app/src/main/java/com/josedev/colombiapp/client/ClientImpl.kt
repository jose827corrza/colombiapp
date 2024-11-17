package com.josedev.colombiapp.client

import android.util.Log
import com.josedev.colombiapp.client.responses.Colombia
import com.josedev.colombiapp.client.responses.Department
import com.josedev.colombiapp.client.responses.President
import com.josedev.colombiapp.client.responses.Region
import com.josedev.colombiapp.utils.Constants
import com.josedev.colombiapp.utils.Resource
import dagger.Module
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.gson.*


class ClientImpl: Client {

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

    override suspend fun statesByRegionId(id: String): Resource<Region> {
        val response = try {
            client.get(Constants.REGION +"/$id" +Constants.DEPARTMENT)
        } catch (e: Exception){
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response.body())
    }

    override suspend fun stateDetailById(id: String): Resource<Department> {
        val response = try {
            client.get(Constants.BASE_URL + Constants.DEPARTMENT_WITH_ID + "/$id")
        }catch (e: Exception){
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response.body())
    }

    override suspend fun listOfPresidents(): Resource<List<President>> {
        val response = try {
            client.get(Constants.PRESIDENTS)
        }catch (e: Exception){
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response.body())
    }

    override suspend fun presidentDetailById(id: String): Resource<President> {
        val response = try {
            client.get(Constants.PRESIDENTS + "/$id")
        }catch (e: Exception){
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response.body())
    }
}
