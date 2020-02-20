package RPIS72.Voronina.wdad.utils;

public class BindedObject {
    private String name;
    private String className;

    public BindedObject(String name, String className) {
        this.name = name;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
