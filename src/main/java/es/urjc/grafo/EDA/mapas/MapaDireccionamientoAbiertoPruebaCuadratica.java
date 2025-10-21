package es.urjc.grafo.EDA.mapas;

import es.urjc.grafo.EDA.utils.Pair;

import java.util.Map;

public class MapaDireccionamientoAbiertoPruebaCuadratica<K, V> extends AbstractMapaDireccionamientoAbierto<K, V> {

    private final int constante1 = 7;
    private final int constante2 = 3;

    public MapaDireccionamientoAbiertoPruebaCuadratica() {
        super();
    }

    public MapaDireccionamientoAbiertoPruebaCuadratica(int capacity) {
        super(capacity);
    }

    public MapaDireccionamientoAbiertoPruebaCuadratica(int capacity, double loadFactor) {
        super(capacity, loadFactor);
    }

    public MapaDireccionamientoAbiertoPruebaCuadratica(double loadFactor) {
        super(loadFactor);
    }

    @Override
    protected void rehash(int desiredCapacity) {
        MapaDireccionamientoAbiertoPruebaCuadratica<K, V> nuevoMapa = new MapaDireccionamientoAbiertoPruebaCuadratica<>(desiredCapacity);
        for (Map.Entry<K, V> entry : this.entries()) {
            nuevoMapa.put(entry.getKey(), entry.getValue());
        }
        this.table = nuevoMapa.table;
        this.size = nuevoMapa.size;
        this.a = nuevoMapa.a;
        this.b = nuevoMapa.b;
        this.prime = nuevoMapa.prime;
    }

    /**
     * Busca la entrada correspondiente a la clave key. Si la encuentra, devuelve un par (true, índice) donde índice es la posición
     * en la tabla hash donde se encuentra la entrada. Si no la encuentra, devuelve un par (false, índice) donde índice es la
     * primera posición disponible (vacía o desactivada) donde se podría insertar una nueva entrada con dicha clave.
     *
     * @param key la clave a buscar
     * @return un par (boolean, int) indicando si se encontró la clave y el índice en el que se encuentra
     * (si el boolean es true) o se podría insertar (si el boolean es false)
     */
    @Override
    protected Pair<Boolean, Integer> findEntry(K key) {
        // TODO
        throw new UnsupportedOperationException("Not implemented yet.");
    }

}
