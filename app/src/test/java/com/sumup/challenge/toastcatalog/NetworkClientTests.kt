package com.sumup.challenge.toastcatalog

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.sumup.challenge.toastcatalog.data.ItemResponse
import com.sumup.challenge.toastcatalog.network.provideOkHttpClient
import com.sumup.challenge.toastcatalog.network.provideRetrofit
import com.sumup.challenge.toastcatalog.network.provideService
import com.sumup.challenge.toastcatalog.network.Service
import kotlinx.coroutines.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClientTests {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var service: Service

    private val expectedResponseData = """
[
  {
    "name": "Avocado Toast",
    "price": "5.99",
    "id": 1,
    "currency": "EUR",
    "last_sold": "2020-11-28T15:14:22Z"
  },
  {
    "name": "Bacon Toast",
    "id": 2,
    "price": "1.99",
    "currency": "EUR",
    "last_sold": "2021-01-30T02:24:04Z"
  },
  {
    "name": "Crunchy Toast",
    "id": 3,
    "price": "0.99",
    "currency": "EUR",
    "last_sold": "2021-03-17T03:45:47Z"
  },
  {
    "name": "Egg in the basket",
    "id": 4,
    "price": "2.99",
    "currency": "EUR",
    "last_sold": "2020-07-12T02:43:38Z"
  },
  {
    "name": "French Toast",
    "id": 5,
    "price": "3.99",
    "currency": "EUR",
    "last_sold": "2021-04-11T14:45:28Z"
  },
  {
    "name": "Milk Toast",
    "id": 6,
    "price": "1.99",
    "currency": "EUR",
    "last_sold": "2020-08-05T13:39:11Z"
  },
  {
    "name": "Ogura Toast",
    "id": 7,
    "price": "4.99",
    "currency": "EUR",
    "last_sold": "2020-09-11T23:59:58Z"
  },
  {
    "name": "Prawn Sesame Toast",
    "id": 8,
    "price": "5.99",
    "currency": "EUR",
    "last_sold": "2021-06-20T23:13:28Z"
  },
  {
    "name": "Toast Hawaii",
    "id": 9,
    "price": "3.99",
    "currency": "EUR",
    "last_sold": "2020-12-28T21:37:20Z"
  },
  {
    "name": "Hummus Toast",
    "id": 10,
    "price": "1.99",
    "currency": "EUR",
    "last_sold": "2022-03-23T12:38:26Z"
  },
  {
    "name": "Whipped Ricotta Toast",
    "id": 11,
    "price": "2.99",
    "currency": "EUR",
    "last_sold": "2022-07-30T09:13:34Z"
  },
  {
    "name": "Sweet Potato Toast",
    "id": 12,
    "price": "3.99",
    "currency": "EUR",
    "last_sold": "2022-01-15T07:03:09Z"
  },
  {
    "name": "Tuna Toast",
    "id": 13,
    "price": "2.99",
    "currency": "EUR",
    "last_sold": "2022-03-16T10:55:17Z"
  }
]
""".trimIndent()

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val okHttpClient = provideOkHttpClient()
        val retrofit = provideRetrofit(okHttpClient).newBuilder()
            .baseUrl(mockWebServer.url("/"))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = provideService(retrofit)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }


    @Test
    fun testEndpoint() = runBlocking {
        val expected = GsonBuilder().create().fromJson<List<ItemResponse>>(
            expectedResponseData, object : TypeToken<List<ItemResponse>>() {}.type
        )

        val actual = service.getItems()

        Assert.assertTrue(
            "Resposta não contém todos os itens esperados",
            actual.containsAll(expected)
        )
    }

    @Test
    fun testEndpointError() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(500)
                .setBody("Internal Server Error")
        )

        try {
            service.getItems()
            Assert.fail("Esperava uma exceção ao fazer a chamada com status 500")
        } catch (e: Exception) {
            println("Exceção capturada: ${e::class.simpleName} - ${e.message}")
            Assert.assertTrue(
                "Esperava HttpException",
                e is retrofit2.HttpException
            )
            Assert.assertEquals(500, (e as retrofit2.HttpException).code())
        }
    }

}
