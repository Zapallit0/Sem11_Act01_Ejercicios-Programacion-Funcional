import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String banco = "abcdefghijkmnlopqrstuvwxyzABCDEFGHIJKMNOPKRSTUVWYXZ0123456789 ";
        List<String>cadenasAleatorias=Stream.generate(()->{
        int longitud = numeroAleatorioEnRango(1, 10);
        return Stream.generate(() -> {
                    int index = numeroAleatorioEnRango(0, banco.length() - 1);
                    return banco.charAt(index);
                }).limit(longitud)
                .map(Object::toString)
                .collect(Collectors.joining());
    }).limit(10).map(Object::toString).toList();

        System.out.println(cadenasAleatorias);
        //Para cadenas vacias
        long cantidadCadenasVacias = cadenasAleatorias.stream()
                .filter(String::isEmpty)
                .count();

        System.out.println("Cantidad de cadenas vacías: " + cantidadCadenasVacias);
        //Para cadenas con mas de 5 de longitud
        long cantidadCadenasLongitud5 = cadenasAleatorias.stream()
                .filter(a -> a.length() > 5)
                .count();

        System.out.println("Cantidad de cadenas con longitud superior a 5: " + cantidadCadenasLongitud5);

        //Contar los strings que inicien con s
        long cantidadCadenasConS = cadenasAleatorias.stream()
                .filter(s -> s.startsWith("s"))
                .count();

        System.out.println("Cantidad de cadenas que comienzan con 's': " + cantidadCadenasConS);

        //Para primero eliminar los vacios y luego solo filtrar los que tengan mas de 5 caracteres
        List<String> cadenasSinVacios = cadenasAleatorias.stream()
                .filter(s -> !s.isEmpty())
                .toList();
        List<String> cadenasConMasDe5Caracteres = cadenasSinVacios.stream()
                .filter(s -> s.length() > 5)
                .collect(Collectors.toList());
        System.out.println("Lista sin cadenas vacías y con más de 5 caracteres: ");
        System.out.println(cadenasConMasDe5Caracteres);

        //Para convertir palabras a mayusculas y concatenar usando coma
        String cadenaMayusyConcatenar = cadenasConMasDe5Caracteres.stream()
                .map(String::toUpperCase)
                .collect(Collectors.joining(", "));

        System.out.println("Palabras en mayúsculas separadas por comas: ");
        System.out.println(cadenaMayusyConcatenar);


        // Para implementar IntSummaryStatics
        List<Integer> numeros = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        IntSummaryStatistics stats = numeros.stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics();

        System.out.println("Recuento: " + stats.getCount());
        System.out.println("Mín: " + stats.getMin());
        System.out.println("Máx: " + stats.getMax());
        System.out.println("Sum: " + stats.getSum());
        System.out.println("Prom: " + stats.getAverage());
    }



    public static int numeroAleatorioEnRango(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}