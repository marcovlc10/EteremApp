package lopez.marco.pruebabottomnav

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterLugares : RecyclerView.Adapter<AdapterLugares.MyViewHolder>() {

    private val listaLugares = ArrayList<Lugar>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_lugar,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = listaLugares[position]

        holder.descripcion.text=currentitem.descripcion
        holder.precio.text=currentitem.precio
        holder.rating.rating=currentitem.valoracion
        holder.ubicacion.text=currentitem.ubicacion

    }

    override fun getItemCount(): Int {
        return listaLugares.size
    }

    fun updateLugaresList(lugaresList: List<Lugar>){
        this.listaLugares.clear()
        this.listaLugares.addAll(listaLugares)
        notifyDataSetChanged()
    }

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){

        var descripcion: TextView = view.findViewById(R.id.descripcion_lugar)
        var precio: TextView = view.findViewById(R.id.precio)
        var rating: RatingBar = view.findViewById(R.id.ratingBar)
        var ubicacion: TextView = view.findViewById(R.id.text_ubicacion)

    }

}