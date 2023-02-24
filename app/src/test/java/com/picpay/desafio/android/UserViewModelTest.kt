package com.picpay.desafio.android

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.domain.contract.UserUseCase
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.presentation.viewmodel.MainViewModel
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UserViewModelTest {

    @Mock
    lateinit var userUseCase: UserUseCase

    @InjectMocks
    lateinit var mainViewModel: MainViewModel

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @After
    fun after(){
        RxAndroidPlugins.reset()
    }

    @Test
    fun shouldSuccessIntoApiResponse() {
        whenever(userUseCase.getUsers()).thenReturn(Single.just(getUsersMockedList()))

        mainViewModel.getUsers()

        assertEquals(
            getUsersMockedList(),
            mainViewModel.successLiveData.value
        )
    }

    @Test
    fun shouldSuccessAndEmptyIntoApiResponse() {
        whenever(userUseCase.getUsers()).thenReturn(Single.just(emptyList()))

        mainViewModel.getUsers()

        assertEquals(emptyList<User>(), mainViewModel.successLiveData.value)
    }

    @Test
    fun shouldErrorIntoApiResponse() {
        whenever(userUseCase.getUsers()).thenReturn(Single.error(Exception()))

        mainViewModel.getUsers()

        assertEquals(true, mainViewModel.failureLiveData.value)
    }

    private fun getUsersMockedList() = listOf(
        User("https://randomuser.me/api/portraits/men/1.jpg", "Sandrine Spinka", 1, "Tod86"),
        User("https://randomuser.me/api/portraits/men/2.jpg", "Carli Carroll", 2, "Constantin_Sawayn"),
        User("https://randomuser.me/api/portraits/men/3.jpg", "Annabelle Reilly", 3, "Lawrence_Nader62")
    )

}