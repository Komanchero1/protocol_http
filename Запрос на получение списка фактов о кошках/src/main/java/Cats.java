import com.fasterxml.jackson.annotation.JsonProperty;

public class Cats {
    public final String id;
    public final String text;
    public final String type;
    public final String user;
    public final Integer upvotes;

    //пустой конструктор для десериализации
    public Cats() {
        this.id = null;
        this.text = null;
        this.type = null;
        this.user = null;
        this.upvotes = null;
    }

    //конструктор с параметрами
    //помечаем переменные анотацией @JsonProperty
    //чтобы каждое поле в классе Cats  сопоставлялось
    // с таким же полем lson
    public Cats(@JsonProperty String id, @JsonProperty String text,
                @JsonProperty String type, @JsonProperty String user,
                @JsonProperty Integer upvotes
    ) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public String getUser() {
        return user;
    }

    public Integer getUpvotes() {
        return upvotes;
    }


}
