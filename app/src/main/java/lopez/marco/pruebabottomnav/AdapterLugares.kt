package lopez.marco.pruebabottomnav

import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AdapterLugares (private val listaLugares : ArrayList<Lugar>) : RecyclerView.Adapter<AdapterLugares.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_lugar,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = listaLugares[position]

        holder.descripcion.text=currentitem.descripcion
        holder.precio.text=currentitem.precio
        holder.rating.rating= currentitem.valoracion.toFloat()
        holder.ubicacion.text=currentitem.ubicacion
        Picasso.get()
            .load(currentitem.imagen).fit().centerCrop()
            .placeholder(R.drawable.imagedefault)
            .into(holder.imagen)

    }

    override fun getItemCount(): Int {
        return listaLugares.size
    }

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){

        var descripcion: TextView = view.findViewById(R.id.descripcion_lugar)
        var precio: TextView = view.findViewById(R.id.precio)
        var rating: RatingBar = view.findViewById(R.id.ratingBar)
        var ubicacion: TextView = view.findViewById(R.id.text_ubicacion)
        var imagen: ImageView = view.findViewById(R.id.imagen_detalles_lugar)

    }

}