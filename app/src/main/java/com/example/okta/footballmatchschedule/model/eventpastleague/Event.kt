package com.example.okta.footballmatchschedule.model.eventpastleague

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Event() : Parcelable{

    @SerializedName("dateEvent")
    var dateEvent: String? = null
    @SerializedName("idAwayTeam")
    var idAwayTeam: String? = null
    @SerializedName("idEvent")
    var idEvent: String? = null
    @SerializedName("idHomeTeam")
    var idHomeTeam: String? = null
    @SerializedName("idLeague")
    var idLeague: String? = null
    @SerializedName("idSoccerXML")
    var idSoccerXML: String? = null
    @SerializedName("intAwayScore")
    var intAwayScore: Any? = null
    @SerializedName("intAwayShots")
    var intAwayShots: Any? = null
    @SerializedName("intHomeScore")
    var intHomeScore: Any? = null
    @SerializedName("intHomeShots")
    var intHomeShots: Any? = null
    @SerializedName("intRound")
    var intRound: String? = null
    @SerializedName("intSpectators")
    var intSpectators: Any? = null
    @SerializedName("strAwayFormation")
    var strAwayFormation: Any? = null
    @SerializedName("strAwayGoalDetails")
    var strAwayGoalDetails: Any? = null
    @SerializedName("strAwayLineupDefense")
    var strAwayLineupDefense: Any? = null
    @SerializedName("strAwayLineupForward")
    var strAwayLineupForward: Any? = null
    @SerializedName("strAwayLineupGoalkeeper")
    var strAwayLineupGoalkeeper: Any? = null
    @SerializedName("strAwayLineupMidfield")
    var strAwayLineupMidfield: Any? = null
    @SerializedName("strAwayLineupSubstitutes")
    var strAwayLineupSubstitutes: Any? = null
    @SerializedName("strAwayRedCards")
    var strAwayRedCards: Any? = null
    @SerializedName("strAwayTeam")
    var strAwayTeam: String? = null
    @SerializedName("strAwayYellowCards")
    var strAwayYellowCards: Any? = null
    @SerializedName("strBanner")
    var strBanner: Any? = null
    @SerializedName("strCircuit")
    var strCircuit: Any? = null
    @SerializedName("strCity")
    var strCity: Any? = null
    @SerializedName("strCountry")
    var strCountry: Any? = null
    @SerializedName("strDate")
    var strDate: String? = null
    @SerializedName("strDescriptionEN")
    var strDescriptionEN: Any? = null
    @SerializedName("strEvent")
    var strEvent: String? = null
    @SerializedName("strFanart")
    var strFanart: Any? = null
    @SerializedName("strFilename")
    var strFilename: String? = null
    @SerializedName("strHomeFormation")
    var strHomeFormation: Any? = null
    @SerializedName("strHomeGoalDetails")
    var strHomeGoalDetails: Any? = null
    @SerializedName("strHomeLineupDefense")
    var strHomeLineupDefense: Any? = null
    @SerializedName("strHomeLineupForward")
    var strHomeLineupForward: Any? = null
    @SerializedName("strHomeLineupGoalkeeper")
    var strHomeLineupGoalkeeper: Any? = null
    @SerializedName("strHomeLineupMidfield")
    var strHomeLineupMidfield: Any? = null
    @SerializedName("strHomeLineupSubstitutes")
    var strHomeLineupSubstitutes: Any? = null
    @SerializedName("strHomeRedCards")
    var strHomeRedCards: Any? = null
    @SerializedName("strHomeTeam")
    var strHomeTeam: String? = null
    @SerializedName("strHomeYellowCards")
    var strHomeYellowCards: Any? = null
    @SerializedName("strLeague")
    var strLeague: String? = null
    @SerializedName("strLocked")
    var strLocked: String? = null
    @SerializedName("strMap")
    var strMap: Any? = null
    @SerializedName("strPoster")
    var strPoster: Any? = null
    @SerializedName("strResult")
    var strResult: Any? = null
    @SerializedName("strSeason")
    var strSeason: String? = null
    @SerializedName("strSport")
    var strSport: String? = null
    @SerializedName("strTVStation")
    var strTVStation: Any? = null
    @SerializedName("strThumb")
    var strThumb: Any? = null
    @SerializedName("strTime")
    var strTime: String? = null

    constructor(parcel: Parcel) : this() {
        dateEvent = parcel.readString()
        idAwayTeam = parcel.readString()
        idEvent = parcel.readString()
        idHomeTeam = parcel.readString()
        idLeague = parcel.readString()
        idSoccerXML = parcel.readString()
        intRound = parcel.readString()
        strAwayTeam = parcel.readString()
        strDate = parcel.readString()
        strEvent = parcel.readString()
        strFilename = parcel.readString()
        strHomeTeam = parcel.readString()
        strLeague = parcel.readString()
        strLocked = parcel.readString()
        strSeason = parcel.readString()
        strSport = parcel.readString()
        strTime = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(dateEvent)
        parcel.writeString(idAwayTeam)
        parcel.writeString(idEvent)
        parcel.writeString(idHomeTeam)
        parcel.writeString(idLeague)
        parcel.writeString(idSoccerXML)
        parcel.writeString(intRound)
        parcel.writeString(strAwayTeam)
        parcel.writeString(strDate)
        parcel.writeString(strEvent)
        parcel.writeString(strFilename)
        parcel.writeString(strHomeTeam)
        parcel.writeString(strLeague)
        parcel.writeString(strLocked)
        parcel.writeString(strSeason)
        parcel.writeString(strSport)
        parcel.writeString(strTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Event> {
        override fun createFromParcel(parcel: Parcel): Event {
            return Event(parcel)
        }

        override fun newArray(size: Int): Array<Event?> {
            return arrayOfNulls(size)
        }
    }

}
