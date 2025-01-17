package com.touchin.prosto.feature.detail

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import com.anadolstudio.core.viewbinding.viewBinding
import com.touchin.prosto.R
import com.touchin.prosto.base.bottom.BaseContentBottom
import com.touchin.prosto.databinding.FragmentOfferDetailBinding
import com.touchin.prosto.di.viewmodel.assistedViewModel
import com.touchin.prosto.util.GradientDrawable

@Suppress("TooManyFunctions")
class OfferDetailFragment : BaseContentBottom<OfferDetailState, OfferDetailViewModel, OfferDetailController>(
    R.layout.fragment_offer_detail
) {

    private val binding by viewBinding { FragmentOfferDetailBinding.bind(it) }
    protected val args: OfferDetailFragmentArgs by navArgs()

    override fun createViewModelLazy() = assistedViewModel {
        getSharedComponent()
            .offerDetailViewModelFactory()
            .create(args.offer)
    }

    override fun render(state: OfferDetailState, controller: OfferDetailController) {
        with(binding) {
            gradientBackground.background = GradientDrawable(
                state.offer.backgroundFirstColor,
                state.offer.backgroundSecondColor
            )
            mainInfo.initView(state.offer)
            headerView.initView(state.offer) {
                controller.onClickFavorite()
                setFragmentResult(REQUEST_KEY, bundleOf(getString(R.string.key_offer) to state.offer))
            }
            offerName.text = state.offer.name
            offerLongDescription.text = state.offer.longDescription
        }
    }

    companion object {
        const val REQUEST_KEY = "OFFER_DETAIL"
    }
}
