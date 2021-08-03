package cake.api.model;

import java.util.Objects;

public class CakeSummary {

    private String slug;
    private String title;

    public CakeSummary() {
        // no-op
    }

    public CakeSummary(CakeEntity entity) {
        this.slug = entity.getSlug();
        this.title = entity.getTitle();
    }

    public CakeSummary(String slug, String title) {
        this.slug = slug;
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CakeSummary that = (CakeSummary) o;
        return Objects.equals(slug, that.slug)
                && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slug, title);
    }

    @Override
    public String toString() {
        return "CakeSummary{" +
                "slug='" + slug + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
