package com.andrescermeno.examensosafe.ui.detailsFragment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.andrescermeno.examensosafe.common.ScopedViewModel
import com.andrescermeno.examensosafe.data.model.FavoritesEntity
import com.andrescermeno.examensosafe.data.model.ReviewsEntity
import com.andrescermeno.examensosafe.domain.Repo
import com.andrescermeno.examensosafe.remote.Review
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Creada por Andrés Cermeño el 2/9/2020
 */
class DetailsViewModel @ViewModelInject constructor(private var repo: Repo) : ScopedViewModel() {

    var placeId = MutableLiveData<String>()

    var search = MutableLiveData<Boolean>()

    var addFavorite = MutableLiveData<Boolean>()

    private val _favoritePlace = MutableLiveData<FavoritesEntity>()
    val favoritePlace: LiveData<FavoritesEntity> get() = _favoritePlace

    private val _reviews = MutableLiveData<List<ReviewsEntity>>()

    private var changeToReview= MutableLiveData<List<Review>>()

    private val _allReviews = MutableLiveData<List<Review>>()
    val allReviews: LiveData<List<Review>> get() = _allReviews

    init {
        addFavorite.value = false
        initScope()
    }

    val detailsPlace = search.distinctUntilChanged().switchMap {

        liveData(Dispatchers.IO) {
            emit(
                repo.getDetailsPlace(
                    placeId.value.toString(),
                    "name,rating,formatted_address,rating,geometry,reviews,photos",
                    "AIzaSyAh_bAfEqdromedJ2yOECCzkggzolKoI4E"
                )
            )
        }
    }

    fun deleteFavorite() {
        launch {
            repo.deleteFavoritesPlace(_favoritePlace.value!!)
            repo.deleteAllReviewsForPlace(placeId.value!!)
        }
        addFavorite.value = false
    }

    fun insertFavorite(favoritePlace: FavoritesEntity) {
        launch {
            repo.insertFavoritesPlace(favoritePlace)
        }
        addFavorite.value = true
    }

    fun insertReviews(reviews: List<Review>) {
        for (review in reviews) {
            launch {
                repo.insertReview(
                    ReviewsEntity(
                        0L,
                        placeId.value!!,
                        review.profile_photo_url,
                        review.author_name,
                        review.rating,
                        review.relative_time_description,
                        review.text
                    )
                )
            }
        }
    }

    fun verifyData() {
        launch {
            _favoritePlace.value = repo.getFavoritePlace(placeId.value!!)
        }
    }

    fun getReviews() {
        launch {
            _reviews.value = repo.getAllReviewsForPlace(placeId.value!!)
            _allReviews.value = withContext(Dispatchers.Default) {
                val convertList : MutableList<Review> = mutableListOf()
                for (review in _reviews.value!!) {
                    convertList.add(
                        Review(
                            review.nameAuthor,
                            review.imgAuthor,
                            review.rating,
                            review.time,
                            review.opinion
                        )
                    )
                }
                convertList
        }

        }
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }


}