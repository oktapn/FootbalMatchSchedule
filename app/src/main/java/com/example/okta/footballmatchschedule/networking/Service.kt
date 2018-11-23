package com.example.okta.footballmatchschedule.networking

import com.example.okta.applicationkade.model.TeamResponse
import com.example.okta.footballmatchschedule.model.detailevent.DetailEventResponse
import com.example.okta.footballmatchschedule.model.detailteam.DetailTeamResponse
import com.example.okta.footballmatchschedule.model.eventnextleague.EventNextLeagueResponse
import com.example.okta.footballmatchschedule.model.eventpastleague.EventPastLeagueResponse
import com.example.okta.footballmatchschedule.model.player.PlayerResponse

import rx.Observable
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class Service(private val networkService: NetworkService) {


    fun getLastMatch(id: String, getPastEventCallback: GetPastEventCallback): Subscription {
        return networkService.getLastMatch(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext { throwable -> Observable.error(throwable) }
            .subscribe(object : Subscriber<EventPastLeagueResponse>() {
                override fun onCompleted() {

                }

                override fun onError(e: Throwable) {
                    getPastEventCallback.onError(NetworkError(e))
                }

                override fun onNext(responseBarcode: EventPastLeagueResponse) {
                    getPastEventCallback.onSuccess(responseBarcode)
                }
            })
    }

    interface GetPastEventCallback {
        fun onSuccess(pastLeagueResponse: EventPastLeagueResponse)

        fun onError(networkError: NetworkError)
    }

    fun getNextMatch(id: String, getNextEventCallback: GetNextEventCallback): Subscription {
        return networkService.getNextMatch(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext { throwable -> Observable.error(throwable) }
            .subscribe(object : Subscriber<EventNextLeagueResponse>() {
                override fun onCompleted() {

                }

                override fun onError(e: Throwable) {
                    getNextEventCallback.onError(NetworkError(e))
                }

                override fun onNext(responseBarcode: EventNextLeagueResponse) {
                    getNextEventCallback.onSuccess(responseBarcode)
                }
            })
    }

    interface GetNextEventCallback {
        fun onSuccess(nextLeagueResponse: EventNextLeagueResponse)

        fun onError(networkError: NetworkError)
    }

    fun getTeamDetail(team: String, getDetailTeamCallback: GetDetailTeamCallback): Subscription {
        return networkService.getDetailTeam(team)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext { throwable -> Observable.error(throwable) }
            .subscribe(object : Subscriber<DetailTeamResponse>() {
                override fun onCompleted() {

                }

                override fun onError(e: Throwable) {
                    getDetailTeamCallback.onError(NetworkError(e))
                }

                override fun onNext(detailTeamResponse: DetailTeamResponse) {
                    getDetailTeamCallback.onSuccess(detailTeamResponse)
                }
            })
    }

    interface GetDetailTeamCallback {
        fun onSuccess(detailTeamResponse: DetailTeamResponse)

        fun onError(networkError: NetworkError)
    }

    fun getTeamEvent(id: String, getDetailEventCallback: GetDetailEventCallback): Subscription {
        return networkService.getDetailEvent(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext { throwable -> Observable.error(throwable) }
            .subscribe(object : Subscriber<DetailEventResponse>() {
                override fun onCompleted() {

                }

                override fun onError(e: Throwable) {
                    getDetailEventCallback.onError(NetworkError(e))
                }

                override fun onNext(detailEventResponse: DetailEventResponse) {
                    getDetailEventCallback.onSuccess(detailEventResponse)
                }
            })
    }

    interface GetDetailEventCallback {
        fun onSuccess(detailEventResponse: DetailEventResponse)

        fun onError(networkError: NetworkError)
    }

    fun getTeamData(league: String, getTeamDataCallback: GetTeamDataCallback): Subscription {
        return networkService.getTeam(league)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext { throwable -> Observable.error(throwable) }
            .subscribe(object : Subscriber<TeamResponse>() {
                override fun onCompleted() {

                }

                override fun onError(e: Throwable) {
                    getTeamDataCallback.onError(NetworkError(e))
                }

                override fun onNext(teamResponse: TeamResponse) {
                    getTeamDataCallback.onSuccess(teamResponse)
                }
            })

    }

    interface GetTeamDataCallback {
        fun onSuccess(teamResponse: TeamResponse)

        fun onError(networkError: NetworkError)
    }

    fun getTeamDatabyID(league: String?, getTeamDataByIDCallback: GetTeamDataByIDCallback): Subscription {
        return networkService.getDetailTeambyID(league)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext { throwable -> Observable.error(throwable) }
            .subscribe(object : Subscriber<DetailTeamResponse>() {
                override fun onCompleted() {

                }

                override fun onError(e: Throwable) {
                    getTeamDataByIDCallback.onError(NetworkError(e))
                }

                override fun onNext(teamResponse: DetailTeamResponse) {
                    getTeamDataByIDCallback.onSuccess(teamResponse)
                }
            })

    }

    interface GetTeamDataByIDCallback {
        fun onSuccess(teamResponse: DetailTeamResponse)

        fun onError(networkError: NetworkError)
    }

    fun getPlayers(id: String?, getPlayersDataByIDCallback: GetPlayersDataByIDCallback): Subscription {
        return networkService.getPlayersbyTeamID(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext { throwable -> Observable.error(throwable) }
            .subscribe(object : Subscriber<PlayerResponse>() {
                override fun onCompleted() {

                }

                override fun onError(e: Throwable) {
                    getPlayersDataByIDCallback.onError(NetworkError(e))
                }

                override fun onNext(playerResponse: PlayerResponse) {
                    getPlayersDataByIDCallback.onSuccess(playerResponse)
                }
            })

    }

    interface GetPlayersDataByIDCallback {
        fun onSuccess(playerResponse: PlayerResponse)

        fun onError(networkError: NetworkError)
    }


}
