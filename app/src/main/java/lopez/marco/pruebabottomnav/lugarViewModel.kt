package lopez.marco.pruebabottomnav

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lopez.marco.pruebabottomnav.repository.lugarRepository

class lugarViewModel : ViewModel() {

    private val repository : lugarRepository
    private val _allLugares = MutableLiveData<List<Lugar>>()
    val allLugares : LiveData<List<Lugar>> = _allLugares

    init {
        repository = lugarRepository().getInstance()
        repository.loadLugares(_allLugares)
    }


}