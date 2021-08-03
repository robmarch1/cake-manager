package cake.api.model;

import java.util.Objects;

public class Cake {

    private String slug;
    private String title;
    private String description;
    private String image;

    public Cake() {
        // no-op
    }

    public Cake(CakeEntity entityModel) {
        this(entityModel.getSlug(), entityModel.getTitle(), entityModel.getDescription(), entityModel.getImage());
    }

    public Cake(String slug,
                String title,
                String description,
                String image) {
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.image = image;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cake cake = (Cake) o;
        return Objects.equals(slug, cake.slug)
                && Objects.equals(title, cake.title)
                && Objects.equals(description, cake.description)
                && Objects.equals(image, cake.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slug, title, description, image);
    }

    @Override
    public String toString() {
        return "Cake{" +
                "slug='" + slug + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
