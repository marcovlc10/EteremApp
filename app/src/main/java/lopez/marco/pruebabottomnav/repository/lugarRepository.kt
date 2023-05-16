package lopez.marco.pruebabottomnav.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import lopez.marco.pruebabottomnav.Lugar

class lugarRepository {

    private val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("Restaurante")

    @Volatile private var INSTANCE : lugarRepository ?= null

    fun getInstance(): lugarRepository{
        return INSTANCE ?: synchronized(this){

            val instance = lugarRepository()
            INSTANCE = instance
            instance
        }
    }

    fun loadLugares(lugarList : MutableLiveData<List<Lugar>>){

        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val _lugarList : List<Lugar> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(Lugar::class.java)!!
                    }

                    lugarList.postValue(_lugarList)

                } catch ( e: Exception){

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}