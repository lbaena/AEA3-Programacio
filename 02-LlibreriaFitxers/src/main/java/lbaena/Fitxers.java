package lbaena;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.*;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;


public class Fitxers {

    // vars
    public String ruta;

    // constructors
    public Fitxers(String ruta) {
        this.ruta = ruta;
    }

    // getters i setters
    public String getRuta() {
        return ruta;
    }
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public boolean existeix(){
        Path path = Paths.get(ruta);
        return Files.exists(path);
    }

    public void creaDirectori() throws IOException {
        Path path = Paths.get(ruta);
        Files.createDirectories(path);
    }

    public String propietariFitxer() throws IOException {
        Path path = Paths.get(ruta);
        return Files.getOwner(path).getName();
    }

    public void eliminarFitxerDirectori() throws IOException {
        Path path = Paths.get(ruta);
        Files.deleteIfExists(path);
    }

    public void copiarFitxerDirectori(String origen, String desti) throws IOException {
        Path pathOrigen = Paths.get(origen);
        Path pathDesti = Paths.get(desti);
        Files.copy(pathOrigen, pathDesti);
    }

    public void moureFitxerDirectori(String origen, String desti) throws IOException {
        Path pathOrigen = Paths.get(origen);
        Path pathDesti = Paths.get(desti);
        Files.move(pathOrigen, pathDesti);
    }

    public String metadadesFitxer() throws IOException {
        Path path = Paths.get(ruta);
        String csv;
        String headers = "tamany;data_creacio;data_modificacio;permisos";

        BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);

        csv = Files.size(path) + ";" +
              attrs.creationTime() + ";" +
              attrs.lastModifiedTime() + ";" +
              Files.getPosixFilePermissions(path).toString();

        return csv;
    }

    public void escriuFitxerText(String text, boolean afegir) throws IOException {
        Path path = Paths.get(ruta);
        Files.writeString(path, text);
    }

    public List<String> retornaContingutFitxerLlista(String charset) throws IOException, InterruptedException {
        Path path = Paths.get(ruta);
        return Files.readAllLines(path, java.nio.charset.Charset.forName(charset));
    }

    public List<String> retornaContingutFitxerLlista() throws IOException, InterruptedException {
        Path path = Paths.get(ruta);
        return Files.readAllLines(path);
    }

    public void mostraContingutFitxer() throws IOException, InterruptedException {
        Path path = Paths.get(ruta);
        System.out.println(Files.readString(path));
    }
}