package com.example.platzi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.platzi.R
import com.example.platzi.model.Delinquent

class DelinquentAdapter(context: Context, private val morososList: List<Delinquent>) : ArrayAdapter<Delinquent>(context, 0, morososList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.list_item_delinquent, parent, false)
        }

        val moroso = getItem(position) as Delinquent

        val nombreTextView = listItemView?.findViewById<TextView>(R.id.nombre)
        val documentoTextView = listItemView?.findViewById<TextView>(R.id.documento)
        val cuotasTextView = listItemView?.findViewById<TextView>(R.id.cuotas)
        val totalAdeudadoTextView = listItemView?.findViewById<TextView>(R.id.total_adeudado)

        nombreTextView?.text = "${moroso.nombre} ${moroso.apellido}"
        documentoTextView?.text = "${moroso.tipoDocumento}: ${moroso.numeroDocumento}"
        cuotasTextView?.text = "Cuotas adeudadas: ${moroso.cuotasAdeudadas}"
        totalAdeudadoTextView?.text = "Total adeudado: $${moroso.totalAdeudado}"

        return listItemView!!
    }
}
