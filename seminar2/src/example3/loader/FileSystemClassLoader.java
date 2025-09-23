package loader;

import java.nio.file.*;
import java.io.IOException;

public class FileSystemClassLoader extends ClassLoader {
    private final Path dir;

    public FileSystemClassLoader(Path dir) {
        super(null);
        this.dir = dir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Path path = dir.resolve(name.replace('.', '/') + ".class");
        System.out.println("path: " + path);
        if (!Files.exists(path)) {
            throw new ClassNotFoundException(name + " not found in " + dir);
        }
        try {
            byte[] bytes = Files.readAllBytes(path);
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Failed to read class file for " + name, e);
        }
    }
}