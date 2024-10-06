package net.ikiazar;

import org.rauschig.jarchivelib.ArchiveFormat;
import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;
import org.rauschig.jarchivelib.CompressionType;

import java.io.File;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        File dirFile = new File(FileSystems.getDefault().getPath("").toAbsolutePath().toString());
        FileSystems.getDefault().getPath(".").toAbsolutePath();
        List<File> files = new ArrayList<>();
        for (File fileEntry : dirFile.listFiles()) {
            if (fileEntry.getName().endsWith("tar.gz")) {
                files.add(fileEntry);
            }
        }
        for (File tarFile : files) {
            try {
                System.out.println("Processing file: " + tarFile.getName());
                Archiver archiver = ArchiverFactory.createArchiver(ArchiveFormat.TAR, CompressionType.GZIP);
                archiver.extract(tarFile, dirFile);
            } catch (Exception e) {
                System.out.println("Error processing file: " + tarFile.getName() + " code: " + e.getLocalizedMessage());
            } finally {
                System.out.println("File processed: " + tarFile.getName());
            }
        }
    }

}