package lopez.marco.pruebabottomnav

import android.media.Image
import android.net.Uri

data class Lugar(val nombre:String, val descripcion: String, val precio: String, val valoracion: String, val ubicacion: String,
                 val imagen:String?=null)
