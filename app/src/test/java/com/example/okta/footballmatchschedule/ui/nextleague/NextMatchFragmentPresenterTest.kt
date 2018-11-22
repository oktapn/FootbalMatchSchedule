package com.example.okta.footballmatchschedule.ui.nextleague

import com.example.okta.footballmatchschedule.model.eventnextleague.EventNextLeagueResponse
import com.example.okta.footballmatchschedule.networking.NetworkError
import com.example.okta.footballmatchschedule.networking.Service
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

class NextMatchFragmentPresenterTest {

    @Mock
    private
    lateinit var view: NextMatchFragmentView

    @Mock
    @Inject
    lateinit var service: Service
    private lateinit var presenter: NextMatchFragmentPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NextMatchFragmentPresenter(service, view)
    }

    @Test
    fun getid() {
        val subscriptions= CompositeSubscription()
        var league = "4328"
        GlobalScope.launch {
            Mockito.`when`(
                service.getNextMatch(league, object : Service.GetNextEventCallback {
                    override fun onSuccess(detailTeamResponse: EventNextLeagueResponse) {
                        Mockito.verify(view).getResponse(detailTeamResponse)
                    }

                    override fun onError(networkError: NetworkError) {
                        Mockito.verify(view).onFailure(networkError.appErrorMessage)
                    }
                })
            ).thenReturn(subscriptions)
            presenter.getNextMatch(league)
            Mockito.verify(view).showWait()
        }
    }
}