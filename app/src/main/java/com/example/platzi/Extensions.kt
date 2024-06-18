package com.example.platzi

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import java.io.ByteArrayOutputStream

// Extensión para convertir Drawable a Bitmap
fun Drawable.toBitmap(): Bitmap {
    return (this as BitmapDrawable).bitmap
}

// Función para convertir Bitmap a ByteArray
fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
    return stream.toByteArray()
}