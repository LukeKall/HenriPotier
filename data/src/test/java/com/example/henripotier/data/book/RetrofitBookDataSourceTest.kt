package com.example.henripotier.data.book

import com.example.henripotier.data.network.client.RetrofitClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import java.net.HttpURLConnection
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@OptIn(ExperimentalCoroutinesApi::class)
class RetrofitBookDataSourceTest {

    private var mockWebServer = MockWebServer()

    private lateinit var retrofitBookDataSource: RetrofitBookDataSource

    @Before
    fun setup() {
        mockWebServer.start()
        retrofitBookDataSource = RetrofitBookDataSource(RetrofitClient.create(mockWebServer.url("/")))
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getBooks should return list of books dto returned by server`() = runTest {
        configGetBooksResponse(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(
                    this.javaClass.classLoader?.getResource("books_response_body.json")
                        ?.readText() ?: ""
                )
        )

        val books = retrofitBookDataSource.getBooks().first()

        assertEquals(booksDto, books)
    }

    @Test
    fun `getBooks should throw an exception when request fails`() = runTest {
        configGetBooksResponse(MockResponse().setResponseCode(HttpURLConnection.HTTP_NOT_FOUND))

        assertFailsWith(HttpException::class) {
            retrofitBookDataSource.getBooks().first()
        }
    }

    private fun configGetBooksResponse(response: MockResponse) {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse =
                if (request.path == "/books" && request.method == "GET") {
                    response
                } else {
                    MockResponse().setResponseCode(404)
                }
        }
    }
}