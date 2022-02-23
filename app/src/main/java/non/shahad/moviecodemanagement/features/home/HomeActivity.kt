package non.shahad.moviecodemanagement.features.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import non.shahad.moviecodemanagement.R
import non.shahad.moviecodemanagement.base.MVIActivity
import non.shahad.moviecodemanagement.databinding.ActivityHomeBinding
import timber.log.Timber


class HomeActivity(
    override val layoutRes: Int = R.layout.activity_home
) : MVIActivity<ActivityHomeBinding,HomeViewModel,HomeState, HomeSideEffect>() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override val viewModel: HomeViewModel
        by viewModels()

    override fun handleSideEffect(sideEffect: HomeSideEffect) {
        when(sideEffect){
            is HomeSideEffect.ShowSnackBar -> {

            }
        }
    }

    override fun render(state: HomeState) {
        if (state.isLoading){

        } else {

        }

        Timber.d("$state")
    }
}