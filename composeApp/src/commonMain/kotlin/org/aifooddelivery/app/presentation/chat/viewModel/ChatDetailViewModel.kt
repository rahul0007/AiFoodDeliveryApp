package org.aifooddelivery.app.presentation.chat.viewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.aifooddelivery.app.presentation.chat.ChatItems
import org.aifooddelivery.app.presentation.chat.ChatUiState

class ChatDetailViewModel : ViewModel() {
    private val _state = MutableStateFlow(ChatUiState())
    val state: StateFlow<ChatUiState> = _state


    private val _userName = MutableStateFlow("Stevano Clirover")
    val userName = _userName.asStateFlow()

    private val _callDuration = MutableStateFlow("03:45")
    val callDuration = _callDuration.asStateFlow()


    fun onMessageChange(newValue: String) {
        _state.update { it.copy(currentMessage = newValue) }
    }

    fun onSendClick() {
        if (_state.value.currentMessage.isNotBlank()) {
            _state.update {
                it.copy(
                    chatItems = it.chatItems + ChatItems.Sender(it.currentMessage, "Now"),
                    currentMessage = ""
                )
            }
        }
    }
}