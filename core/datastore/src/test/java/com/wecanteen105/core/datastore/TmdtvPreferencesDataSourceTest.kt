package com.wecanteen105.core.datastore

import com.wecanteen105.core.datastore.test.testUserPreferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TmdtvPreferencesDataSourceTest {
    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var subject:TmdtvPreferencesDataSource

    @get:Rule
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    @Before
    fun setUp() {
        subject = TmdtvPreferencesDataSource(
            tmpFolder.testUserPreferencesDataStore(testScope)
        )
    }

//     Note: nia project testing methods are so funny, because it test some logic
//     changes and look `shouldHideOnBoarding` changes whether it's value as we expected,
//     then assertTrue or assertFalse.

    @Test
    fun test_shouldHideOnboarding_isFalse_byDefault() = testScope.runTest {
        assertFalse(subject.userDataFlow.first().shouldHideOnboarding)
    }

    @Test
    fun test_shouldHideOnboarding_isTrue_WhenSet() = testScope.runTest {
        subject.setShouldHideOnboarding(true)
        assertTrue(subject.userDataFlow.first().shouldHideOnboarding)
    }

    @Test
    fun test_shouldHideOnboarding_unfollowLastCrew_shouldHideOnboarding_isFalse() = testScope.runTest {
        // Given: user completes onboarding by selecting several casts.
        subject.setCrewIdFollowed(1, true)
        subject.setShouldHideOnboarding(true)

        // When: they unfollow their last cast.
        subject.setCrewIdFollowed(1, false)

        // Then: onboarding should be shown again.
        assertFalse(subject.userDataFlow.first().shouldHideOnboarding)
    }

    @Test
    fun test_shouldHideOnboarding_unfollowLastCast_shouldHideOnboarding_isFalse() = testScope.runTest {
        // Given: user completes onboarding by selecting several casts.
        subject.setCastIdFollowed(1, true)
        subject.setShouldHideOnboarding(true)

        // When: they unfollow their last cast.
        subject.setCastIdFollowed(1, false)

        // Then: onboarding should be shown again.
        assertFalse(subject.userDataFlow.first().shouldHideOnboarding)
    }

    @Test
    fun test_shouldHideOnboarding_unfollowsAllCasts_shouldHideOnboarding_isFalse() = testScope.runTest {
        // Given: user completes onboarding by selecting several crews.
        subject.setFollowedCastIds(setOf(1, 2, 3))
        subject.setShouldHideOnboarding(true)

        // When: they unfollow those crews.
        subject.setFollowedCastIds(emptySet())

        // Then: onboarding should be shown again.
        assertFalse(subject.userDataFlow.first().shouldHideOnboarding)
    }

    @Test
    fun test_shouldHideOnboarding_unfollowsAllCrews_shouldHideOnboarding_isFalse() = testScope.runTest {
        // Given: user completes onboarding by selecting several crews.
        subject.setFollowedCrewIds(setOf(1, 2, 3))
        subject.setShouldHideOnboarding(true)

        // When: they unfollow those crews.
        subject.setFollowedCrewIds(emptySet())

        // Then: onboarding should be shown again.
        assertFalse(subject.userDataFlow.first().shouldHideOnboarding)
    }

    @Test
    fun test_shouldUseDynamicColorFalseByDefault()  = testScope.runTest {
        assertFalse { subject.userDataFlow.first().useDynamicColor }
    }

    @Test
    fun test_shouldUseDynamicColorIsTrueWhenSet() = testScope.runTest {
        subject.setDynamicColorPreference(true)
        assertTrue { subject.userDataFlow.first().useDynamicColor }
    }
}