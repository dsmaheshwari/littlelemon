package com.example.network

import com.example.littlelemon.R
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.HttpRequest
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.withTimeout
import kotlinx.serialization.Serializable
import kotlin.collections.set

class CustomResponseException(request: HttpRequest, response: HttpResponse, cachedResponseText: String) :
    ResponseException(response, cachedResponseText) {

    private val errorBody:HttpResponse = response

    override val message: String = "Error for ${request.url} : ${request.method} \nRecieved ${response.status} \n Error is ::::\n $cachedResponseText"

    fun getError(): HttpResponse {
        return errorBody
    }
}


object NetworkCall {
    private fun instantiateClient(): HttpClient {
        val client = HttpClient(OkHttp) {
            expectSuccess = true
            install(ContentNegotiation) {
                json(
                    contentType = ContentType("text", "plain")
//                    contentType = ContentType("application", "json"),
//                    json = Json {
//                        prettyPrint = true
//                        ignoreUnknownKeys = true
//                    }
                )
            }

            install(Logging) {
                level = LogLevel.ALL
                logger = Logger.DEFAULT
            }

            HttpResponseValidator {
                handleResponseExceptionWithRequest { exception, request ->
                    val clientException = exception as? ClientRequestException ?: return@handleResponseExceptionWithRequest
                    val exceptionResponse = clientException.response
                    if(exceptionResponse.status.value !in 200..299) {
                        val exceptionResponseText = exceptionResponse.bodyAsText()
                        println("Inside handleResponseExceptionWithRequest ---------> \n\n ")

                        throw CustomResponseException(request, exceptionResponse, exceptionResponseText)
                    }
                }
            }
        }

        return client
    }

    private val client = instantiateClient()

    /**
     * @param httpResponse The response received from the Network Call
     */
    private fun completeHandler(httpResponse: HttpResponse): HttpResponse {

        return httpResponse
    }

    private fun appendHeaders(
        headers: Map<String, String>?,
        isBatchCall: Boolean = false
    ): Map<String, String> {
        val appendedHeader = mutableMapOf<String, String>("Content-type" to "application/json")

        if (!isBatchCall) {
            headers?.forEach {
                appendedHeader[it.key] = it.value
            }
        }

        return appendedHeader.toMap()
    }

    /**
     * @param urlString [String] The URL to make the API call
     * @param pathParams [Map] A Map<String, String> of path parameters to replace in the URL. For example, in the URL "/user/{id}/{lang}", the map should be {"id" to "123", "lang", "en"}.
     * @param queryParams [Map] A Map<String, String> of query parameters to be added in the URL. The map should be {KEY to VALUE}.
     * @param headersMap [Map] A Map<String, String> of additional headers to include in the request.
     *
     * @return A [HttpResponse] containing the response from the server.
     */
    suspend fun fetch(
        urlString: String,
        pathParams: Map<String, String>? = mapOf(),
        queryParams: Map<String, String>? = mapOf(),
        headersMap: Map<String, String>? = mapOf()
    ): HttpResponse {
        lateinit var httpResponse: HttpResponse

        var urlWithParams = urlString

        pathParams?.forEach {
            urlWithParams = urlString.replace("{${it.key}}", it.value)
        }

        if (queryParams != null) {
            urlWithParams =
                "$urlString?${queryParams.entries.joinToString("&") { "${it.key}=${it.value}" }}"
        }

        withTimeout(10_000L) {
            httpResponse = client.request(urlWithParams) {
                method = HttpMethod.Get
                appendHeaders(headersMap).forEach {
                    headers.append(it.key, it.value)
                }
            }
        }

        return completeHandler(httpResponse)
    }

    /**
     * @param urlString [String] The URL to make the API call
     * @param body [Serializable] The Serializable object to be set as Body
     * @param pathParams [Map] A Map<String, String> of path parameters to replace in the URL.
     *          For example, in the URL "/user/{id}/{lang}", the map should be {"id" to "123", "lang" to "en"}. Resulting URl would be "/user/123/en"
     * @param queryParams [Map] A Map<String, String> of query parameters to be added in the URL.
     *          For example, in the URL "/user" The map should be {"id" to "123", "lang" to "en"}. Resulting URl would be "/url?id=123&lang=en"
     * @param headersMap [Map] A Map<String, String> of additional headers to include in the request.
     *
     * @return A [HttpResponse] containing the response from the server.
     */
    suspend fun add(
        urlString: String,
        body: Any?,
        pathParams: Map<String, String>? = mapOf(),
        queryParams: Map<String, String>? = mapOf(),
        headersMap: Map<String, String>? = mapOf()
    ): HttpResponse {
        lateinit var httpResponse: HttpResponse

        var urlWithParams = urlString

        pathParams?.forEach {
            urlWithParams = urlString.replace("{${it.key}}", it.value)
        }

        if (queryParams != null) {
            urlWithParams =
                "$urlString?${queryParams.entries.joinToString("&") { "${it.key}=${it.value}" }}"
        }

        withTimeout(R.integer.network_timeout.toLong()) {
            httpResponse = client.request(urlWithParams) {
                method = HttpMethod.Post
                appendHeaders(headersMap).forEach {
                    headers.append(it.key, it.value)
                }
                setBody(body)
            }
        }

        return completeHandler(httpResponse)
    }
}