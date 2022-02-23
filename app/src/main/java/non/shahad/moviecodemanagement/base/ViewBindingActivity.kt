package non.shahad.moviecodemanagement.base

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.hilt.android.AndroidEntryPoint

abstract class ViewBindingActivity<VB: ViewDataBinding>: InnocentActivity() {

    @get:LayoutRes
    abstract val layoutRes: Int

    lateinit var viewBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        makeStatusBarWhite()
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this,layoutRes)

    }

    private fun makeStatusBarWhite(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

}

@AndroidEntryPoint
abstract class InnocentActivity: AppCompatActivity() {

}