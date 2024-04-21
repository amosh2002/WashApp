package com.example.washapp.carwashes

import com.example.washapp.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CarwashesViewModel : BaseViewModel() {

    // Supports the stream of info between the ViewModel and UI
    private val _carwashesState: MutableStateFlow<CarwashState> =
        MutableStateFlow(CarwashState(loading = true))

    // Exposes the state of the carwashes to the UI -  an Immutable version of the _carwashesState
    val carwashesState: StateFlow<CarwashState> get() = _carwashesState

    // Initialize the ViewModel and fetch the carwashes
    init {
        getCarwashes()
    }

    // Fetch carwashes from the API
    private fun getCarwashes() {
        // Launch a coroutine to fetch the carwashes
        scope.launch {
            // Simulate a delay
            delay(1500)

            // Simulate fetching carwashes
            val fetchedCarwashes = fetchCarwashes()

            // Update the state of the carwashes
            _carwashesState.emit(CarwashState(carwashesLoaded = fetchedCarwashes))
        }
    }

    // Mock function to fetch carwashes
    private suspend fun fetchCarwashes(): List<Carwash> = mockCarwashes

    // Mock carwashes to play with
    private val mockCarwashes = listOf(
        Carwash(
            name = "Ararat Auto Spa",
            address = "Կողբացի 64",
            rating = 4.5f,
            ratingCount = 8,
            workHours = "11:00 - 00:00",
            imageUrl = "https://www.treehugger.com/thmb/nzRvmJ0mm74oRku8JSCA7LwppTo=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/__opt__aboutcom__coeus__resources__content_migration__mnn__images__2019__07__commercial_car_wash-1d8498e0ea964ffc91eeab5b48c1265b.jpg",
        ),
        Carwash(
            name = "Boulevard Car Wash",
            address = "Սարյան 3",
            rating = 4f,
            ratingCount = 2,
            workHours = "10:00 - 23:00",
            imageUrl = "https://quicksetautoglass.com/wp-content/uploads/freshizer/e1abe7d378fed5a2c8b9cf3eb31628e4_automatic-car-wash-basics-950-c-90.jpg",
        ),
        Carwash(
            name = "Moskovyan 24",
            address = "Մոսկովյան 24",
            workHours = "11:00 - 22:00",
            imageUrl = "https://centralca.cdn-anvilcms.net/media/images/2022/03/15/images/Car_wash_pix_3-16-22.max-1200x675.jpg",
        ),
        Carwash(
            name = "Gayane",
            address = "Կողբացի 4",
            rating = 4.3f,
            ratingCount = 8,
            workHours = "11:00 - 00:00",
            imageUrl = "https://www.treehugger.com/thmb/nzRvmJ0mm74oRku8JSCA7LwppTo=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/__opt__aboutcom__coeus__resources__content_migration__mnn__images__2019__07__commercial_car_wash-1d8498e0ea964ffc91eeab5b48c1265b.jpg",
        ),
        Carwash(
            name = "Prime Salon",
            address = "Սարյան 6",
            rating = 4f,
            ratingCount = 2,
            workHours = "10:00 - 23:00",
            imageUrl = "https://quicksetautoglass.com/wp-content/uploads/freshizer/e1abe7d378fed5a2c8b9cf3eb31628e4_automatic-car-wash-basics-950-c-90.jpg",
        ),
    )

}