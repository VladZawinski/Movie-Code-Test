package non.shahad.domain.flow

/**
 * Flow wrapper Loading, Data, Error
 */
sealed class LDEFlow<out D: Any> {
    data class Data<out G: Any>(val data: G): LDEFlow<G>()
    data class Error(val message: String,val cause: Exception? = null): LDEFlow<Nothing>()
    object Loading: LDEFlow<Nothing>()
}