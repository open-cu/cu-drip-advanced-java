package example3.plugins.v2;

public class PluginImpl {
    public void run() {
        System.out.println("Plugin v2 running! loader=" + this.getClass().getClassLoader());
    }
}
