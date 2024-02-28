package com.touchin.prosto.feature.detail

import android.content.Context
import com.anadolstudio.core.presentation.event.SingleMessageSnack
import com.touchin.prosto.R
import com.touchin.prosto.base.viewmodel.BaseContentViewModel
import com.touchin.prosto.base.viewmodel.navigateUp
import com.touchin.prosto.feature.model.OfferUi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@Suppress("TooManyFunctions", "LongParameterList")
class OfferDetailViewModel @AssistedInject constructor(
    @Assisted offerUi: OfferUi,
    context: Context
) : BaseContentViewModel<OfferDetailState>(
    initState = OfferDetailState(
        offer = offerUi
    )
), OfferDetailController {

    override fun onRetryClicked() = showTodo()

    override fun onBackClicked() = _navigationEvent.navigateUp()

    init {
        if (_stateLiveData.value?.offer?.isActive == false)
            showEvent(SingleMessageSnack.Short(context.getString(R.string.offer_error)))
    }

    override fun onClickFavorite() {
        _stateLiveData.value?.let {
            val newOffer = it.offer.copy(isFavorite = !it.offer.isFavorite)
            _stateLiveData.value = _stateLiveData.value!!.copy(offer = newOffer)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(offerUi: OfferUi): OfferDetailViewModel
    }
}
