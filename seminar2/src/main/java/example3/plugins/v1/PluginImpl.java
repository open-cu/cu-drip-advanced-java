package example3.plugins.v1;

public class PluginImpl {
    public void run() {
        System.out.println("Plugin v1 running! loader=" + this.getClass().getClassLoader());
    }
}
