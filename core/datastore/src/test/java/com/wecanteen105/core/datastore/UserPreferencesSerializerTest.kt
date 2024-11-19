package com.wecanteen105.core.datastore

import androidx.datastore.core.CorruptionException
import kotlinx.coroutines.test.runTest
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import kotlin.test.Test
import kotlin.test.assertEquals

class UserPreferencesSerializerTest {

    private val userPreferencesSerializer = UserPreferencesSerializer()

    @Test
    fun test_defaultUserPreferences_isEmpty() {
        assertEquals(
            userPreferences {
                // Default value
            },
            userPreferencesSerializer.defaultValue,
        )
    }

    @Test
    fun test_writingAndReadingUserPreferences_outputCorrectValue() = runTest {
       val expectedUserPreferences = userPreferences {
           likedMovieIds.put(1, true)
           likedMovieIds.put(2, true)
       }

        val outputStream = ByteArrayOutputStream()

        expectedUserPreferences.writeTo(outputStream)

        val inputStream = ByteArrayInputStream(outputStream.toByteArray())

        val actualUserPreferences = userPreferencesSerializer.readFrom(inputStream)

        assertEquals(
            expectedUserPreferences,
            actualUserPreferences,
        )
    }

    @Test(expected = CorruptionException::class)
    fun test_readingInvalidUserPreferences_throwsCorruptionException() = runTest {
        userPreferencesSerializer.readFrom(ByteArrayInputStream(byteArrayOf(0)))
    }
}