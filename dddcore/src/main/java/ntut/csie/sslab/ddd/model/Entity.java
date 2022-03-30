package ntut.csie.sslab.ddd.model;

import java.io.Serializable;
import java.util.UUID;

public abstract class Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String name;
    protected final String id;

    public Entity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Entity(String name) {
        this(UUID.randomUUID().toString(), name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getId() {
        return id;
    }
}
