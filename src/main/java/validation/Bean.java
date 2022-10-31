package validation;

import java.util.UUID;

public class Bean {

    @Required(error="MISSING_ID", type="", value=Enum.NULL)
    public Long id;
    @Required(error="MISSING_TYPE", type="", value=Enum.NULL)
    public Enum type;
    @Required(error="MISSING_FIRST", type="", value=Enum.NULL)
    public String first;
    @Required(error="MISSING_SECOND", type="type", value=Enum.SECOND)
    public String second;
    public UUID uuid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Enum getType() {
        return type;
    }

    public void setType(Enum type) {
        this.type = type;
    }

}
