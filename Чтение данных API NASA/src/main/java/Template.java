import com.fasterxml.jackson.annotation.JsonProperty;

public class Template {
    public final String copyright;
    public final String date;
    public final String explanation;
    public final String hdurl;
    public final String media_type;
    public final String service_version;
    public final String title;
    public final String url;

    public Template() {
        this.copyright = null;
        this.date = null;
        this.explanation = null;
        this.hdurl = null;
        this.media_type = null;
        this.service_version = null;
        this.title = null;
        this.url = null;
    }

    public Template(@JsonProperty String copyright, @JsonProperty String date,
                    @JsonProperty String explanation, @JsonProperty String hdurl,
                    @JsonProperty String media_type, @JsonProperty String service_version,
                    @JsonProperty String title, @JsonProperty String url
    ) {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.media_type = media_type;
        this.service_version = service_version;
        this.title = title;
        this.url = url;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getDate() {
        return date;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getHdurl() {
        return hdurl;
    }

    public String getMedia_type() {
        return media_type;
    }

    public String getService_version() {
        return service_version;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
