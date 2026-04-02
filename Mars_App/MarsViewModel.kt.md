ui/screens/MarsViewModel.kt

import androidx.lifecycle.viewModelScope
import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.network.MarsApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

Add this above the MarsViewModel class.

sealed interface MarsUiState {
    data class Success(val photos: String) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}

var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading) //



fun getMarsPhotos() {
        viewModelScope.launch {
            marsUiState = MarsUiState.Loading
            marsUiState = try {
                val listResult = MarsApi.retrofitService.getPhotos()
                MarsUiState.Success(
                    "Success: ${listResult.size} Mars photos retrieved"
                )
            } catch (e: IOException) {
                MarsUiState.Error
            } catch (e: HttpException) {
                MarsUiState.Error
            }
        }
    }

