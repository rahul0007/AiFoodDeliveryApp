package org.aifooddelivery.app.presentation.chat.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.aifooddelivery.app.data.model.ChatItem
import org.aifooddelivery.app.data.model.ChatListUiState

class ChatListViewModel : ViewModel() {

    private val _state = MutableStateFlow(ChatListUiState())
    val state: StateFlow<ChatListUiState> = _state

    init {
        _state.value = ChatListUiState(
            chats = listOf(
                ChatItem("Geopart Etdsien", "Your Order Just Arrived!", "13.47", true),
                ChatItem("Stevano Clirover", "Your Order Just Arrived!", "11.23", true),
                ChatItem("Elisia Justin", "Your Order Just Arrived!", "11.23", false),
                ChatItem("Geopart Etdsien", "Your Order Just Arrived!", "13.47", true),
                ChatItem("Stevano Clirover", "Your Order Just Arrived!", "11.23", true),
                ChatItem("Elisia Justin", "Your Order Just Arrived!", "11.23", false),
                ChatItem("Geopart Etdsien", "Your Order Just Arrived!", "13.47", true),
                ChatItem("Stevano Clirover", "Your Order Just Arrived!", "11.23", true),
                ChatItem("Elisia Justin", "Your Order Just Arrived!", "11.23", false),
                ChatItem("Geopart Etdsien", "Your Order Just Arrived!", "13.47", true),
                ChatItem("Stevano Clirover", "Your Order Just Arrived!", "11.23", true),
                ChatItem("Elisia Justin", "Your Order Just Arrived!", "11.23", false),


                // ...add more
            )
        )
    }
}