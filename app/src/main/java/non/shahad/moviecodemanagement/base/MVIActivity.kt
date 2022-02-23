package non.shahad.moviecodemanagement.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import org.orbitmvi.orbit.viewmodel.observe

abstract class MVIActivity<V: ViewDataBinding,VM: MVIViewModel<VS,SE>,VS: Any,SE: Any>: ViewBindingActivity<V>() {
    abstract val viewModel: VM
    abstract fun handleSideEffect(sideEffect: SE)
    abstract fun render(state: VS)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.observe(
            lifecycleOwner = this,
            sideEffect = ::handleSideEffect,
            state = ::render
        )
    }
}