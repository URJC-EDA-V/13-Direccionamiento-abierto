import es.urjc.grafo.EDA.mapas.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TestPerformance {

    int initialValues = 50000;
    int updates = 50000;
    int seed = 1234;

    @Test
    @Order(1)
    void timeConsumptionOfMapaEncadenamientoSeparado() {
        Mapa<Integer, Integer> freq = new MapaEncadenamientoSeparado<>(initialValues);
        timeConsumptionOfMapa(freq);
    }

    @Test
    @Order(2)
    void timeConsumptionOfSimpleMapa() {
        Mapa<Integer, Integer> freq = new SimpleMapa<>(initialValues);
        timeConsumptionOfMapa(freq);
    }

    @Test
    @Order(3)
    void timeConsumptionOfHashMap() {
        Random random = new Random(seed);
        Map<Integer, Integer> freq = new HashMap<>(initialValues);
        Instant start = Instant.now();
        for (int i = 0; i < initialValues; i++) {
            freq.put(random.nextInt(), random.nextInt());
        }

        for (int i = 0; i < updates; i++) {
            int key = random.nextInt();
            if (!freq.containsKey(key)) {
                freq.put(key, random.nextInt());
                freq.remove(key);
            } else {
                freq.put(key, random.nextInt());
            }
        }
        Duration duration = Duration.between(start, Instant.now());
        System.out.printf("With HashMap, the test took %d.%d seconds%n", duration.toSecondsPart(), duration.toMillisPart());
    }

    @Test
    @Order(4)
    void timeConsumptionOfUnsortedArrayMap() {
        Mapa<Integer, Integer> freq = new UnsortedArrayMap<>(initialValues);
        timeConsumptionOfMapa(freq);
    }

    @Test
    @Order(5)
    void timeConsumptionOfMapaDireccionamientoAbiertoLP() {
        Mapa<Integer, Integer> freq = new MapaDireccionamientoAbiertoPruebaLineal<>(initialValues);
        timeConsumptionOfMapa(freq);
    }

    @Test
    @Order(6)
    void timeConsumptionOfMapaDireccionamientoAbiertoQP() {
        Mapa<Integer, Integer> freq = new MapaDireccionamientoAbiertoPruebaCuadratica<>(initialValues);
        timeConsumptionOfMapa(freq);
    }

    @Test
    @Order(7)
    void timeConsumptionOfMapaDireccionamientoAbiertoDH() {
        Mapa<Integer, Integer> freq = new MapaDireccionamientoAbiertoDobleHash<>(initialValues);
        timeConsumptionOfMapa(freq);
    }

    void timeConsumptionOfMapa(Mapa<Integer, Integer> freq) {
        Random random = new Random(seed);
        Instant start = Instant.now();
        for (int i = 0; i < initialValues; i++) {
            freq.put(random.nextInt(), random.nextInt());
        }

        for (int i = 0; i < updates; i++) {
            int key = random.nextInt();
            if (!freq.containsKey(key)) {
                freq.put(key, random.nextInt());
                freq.remove(key);
            } else {
                freq.put(key, random.nextInt());
            }
        }
        Duration duration = Duration.between(start, Instant.now());
        System.out.printf("With %s, the test took %d.%d seconds%n", freq.getClass().getSimpleName(), duration.toSecondsPart(), duration.toMillisPart());
    }

}
