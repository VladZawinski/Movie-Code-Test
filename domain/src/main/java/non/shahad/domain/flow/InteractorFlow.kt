package non.shahad.domain.flow

sealed class InteractorFlow {
    data class Error(val message: String,val cause: Exception? = null): InteractorFlow()
    object JobStarted: InteractorFlow()
    object Done: InteractorFlow()
}