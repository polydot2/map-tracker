
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

/**
 * Creates a [LatLngBounds] object from a collection of [LatLng] objects.  The collection must
 * contain at least one LatLng.
 */
fun Collection<LatLng>.toLatLngBounds() : LatLngBounds {
  if (isEmpty()) error("Cannot create a LatLngBounds from an empty list")

  return LatLngBounds.builder().apply {
      for (latLng in this@toLatLngBounds) {
        include(latLng)
      }
    }.build()
}
