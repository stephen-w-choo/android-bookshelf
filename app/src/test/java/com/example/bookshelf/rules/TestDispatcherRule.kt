package com.example.bookshelf.rules

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description



class TestDispatcherRule @OptIn(ExperimentalCoroutinesApi::class) constructor(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
): TestWatcher() {
    // Test rule to replace Dispatchers.Main with Dispatchers.Unconfined for testing
    // will run before each test
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}