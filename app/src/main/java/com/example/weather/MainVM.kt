package com.example.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val api: MainApi
): ViewModel() {

    val liveData = MutableLiveData<AlbumState>()

    fun coroutine(city: String){
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val result = api.response("b46c05df37ea4cc793a142330230511",city, 7)

                if (result.isSuccessful){
                        liveData.postValue(AlbumState.Success(result.body()))

                }else {
                    liveData.postValue(AlbumState.Error(result.errorBody().toString()))
                }

            }catch (e: Exception){
                liveData.postValue(AlbumState.Error("Məlumat tapılmadı"))
            }


        }

    }

}