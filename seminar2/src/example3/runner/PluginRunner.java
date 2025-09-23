package runner;

import java.lang.ref.WeakReference;
import java.nio.file.Path;
import loader.FileSystemClassLoader;

public class PluginRunner {
    public static void main(String[] args) throws Exception {
        WeakReference<ClassLoader> r1 = loadAndRun(Path.of(""), "plugins.v1.PluginImpl");
        WeakReference<ClassLoader> r2 = loadAndRun(Path.of(""), "plugins.v2.PluginImpl");

        // Попытка выгрузить
        System.out.println("Request GC...");
        System.gc();
        Thread.sleep(200);

        System.out.println("ref1: " + r1.get());
        System.out.println("ref2: " + r2.get());
    }

    static WeakReference<ClassLoader> loadAndRun(Path pluginDir, String className) throws Exception {
        FileSystemClassLoader loader = new FileSystemClassLoader(pluginDir);
        Class<?> cls = loader.loadClass(className); // если класс без пакета
        Object plugin = cls.getDeclaredConstructor().newInstance();
        cls.getMethod("run").invoke(plugin);

        WeakReference<ClassLoader> ref = new WeakReference<>(loader);
        // Удаляем сильные ссылки (для демонстрации)
        loader = null;
        cls = null;
        plugin = null;
        return ref;
    }
}