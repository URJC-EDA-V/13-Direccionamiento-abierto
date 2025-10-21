import es.urjc.grafo.EDA.mapas.AbstractMapaDireccionamientoAbierto;
import es.urjc.grafo.EDA.mapas.MapaDireccionamientoAbiertoDobleHash;
import es.urjc.grafo.EDA.mapas.MapaDireccionamientoAbiertoPruebaCuadratica;
import es.urjc.grafo.EDA.mapas.MapaDireccionamientoAbiertoPruebaLineal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Random;

public class OpenAddressingTests {

    final boolean printDebug = false;
    int initialValues = 50000;
    int updates = 1000000;

    @Test
    public void testLPHashMap() {
        AbstractMapaDireccionamientoAbierto<Integer, Integer> ownImplementation = new MapaDireccionamientoAbiertoPruebaLineal<>(10);
        testGeneral(ownImplementation);
    }

    @Test
    public void testQPHashMap() {
        AbstractMapaDireccionamientoAbierto<Integer, Integer> ownImplementation = new MapaDireccionamientoAbiertoPruebaCuadratica<>(10);
        testGeneral(ownImplementation);
    }

    @Test
    public void testDHHashMap() {
        AbstractMapaDireccionamientoAbierto<Integer, Integer> ownImplementation = new MapaDireccionamientoAbiertoDobleHash<>(10);
        testGeneral(ownImplementation);
    }

    public void testGeneral(AbstractMapaDireccionamientoAbierto<Integer, Integer> ownImplementation) {
        Assertions.assertTrue(ownImplementation.isEmpty());

        Random random = new Random(1234);
        HashMap<Integer, Integer> hashMapImplementation = new HashMap<>(this.initialValues * 2);

        for (int i = 0; i < initialValues; i++) {
            int key = random.nextInt(initialValues);
            int value = random.nextInt();
            if (printDebug) {
                System.out.println("PUT " + key + " -> " + value);
            }
            Assertions.assertEquals(hashMapImplementation.put(key, value), ownImplementation.put(key, value));
            Assertions.assertEquals(hashMapImplementation.size(), ownImplementation.size());
        }

        for (int i = 0; i < updates; i++) {
            int key = random.nextInt(initialValues);
            int value = random.nextInt();
            int operation = random.nextInt(3);
            switch (operation) {
                case 0:
                    if (printDebug) {
                        System.out.println("DEBUG BREAKPOINT");
                        System.out.println("PUT " + key + " -> " + value);
                    }
                    Assertions.assertEquals(hashMapImplementation.put(key, value), ownImplementation.put(key, value));
                    Assertions.assertEquals(hashMapImplementation.size(), ownImplementation.size());
                    break;
                case 1:
                    if (printDebug) {
                        System.out.println("DEBUG BREAKPOINT");
                        System.out.println("GET " + key);
                    }

                    Assertions.assertEquals(hashMapImplementation.get(key), ownImplementation.get(key));
                    Assertions.assertEquals(hashMapImplementation.size(), ownImplementation.size());
                    break;
                case 2:
                    if (printDebug) {
                        System.out.println("DEBUG BREAKPOINT");
                        System.out.println("REMOVE " + key);
                    }
                    Assertions.assertEquals(hashMapImplementation.remove(key), ownImplementation.remove(key));
                    Assertions.assertEquals(hashMapImplementation.size(), ownImplementation.size());
                    break;
            }
        }
    }

}
