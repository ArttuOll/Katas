/**
 * Tehtävänanto
 * Muuta annettu RGB-arvo lyhentämättömään heksadesimaalimuotoon. Jos RGB-arvoista jokin on yli tai
 * alle RGB-asteikon [0, 255], niin pyöristä se lähimpään pätevään arvoon.
 *
 * Aikavaativuus
 * O(1), koska arvoja on vain kolme ja jokaiselle tehdään ainoastaan vakioaikaisia
 * muunnosoperaatioita.
 */
class RgbToHex {
    /**
     * Muuttaa annetut RGB-kokonaisluvut (parametrit `r, g ja b`) merkkijonotyyppiseksi,
     * lyhentämättömäksi heksadesimaaliesitykseksi.
     */
    fun rgb(r: Int, g: Int, b: Int): String {
        val red = r.coerceIn(0..255)
        val green = g.coerceIn(0..255)
        val blue = b.coerceIn(0..255)
        return (formatRgbToHex(red) + formatRgbToHex(green) + formatRgbToHex(blue)).toUpperCase()
    }

    private fun formatRgbToHex(rgb: Int): String = String.format("%02x", rgb.toByte())
}