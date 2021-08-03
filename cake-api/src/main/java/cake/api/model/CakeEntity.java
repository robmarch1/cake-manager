package cake.api.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cake")
public class CakeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer cakeId;

    @Column(name = "slug", unique = true, nullable = false, length = 20)
    private String slug;

    @Column(name = "title", unique = true, nullable = false, length = 100)
    private String title;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @Column(name = "image", nullable = false, length = 300)
    private String image;

    public CakeEntity() {
        // no-op
    }

    public CakeEntity(String slug,
                      String title,
                      String description,
                      String image) {
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public Integer getCakeId() {
        return cakeId;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSlug() {
        return slug;
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
        CakeEntity that = (CakeEntity) o;
        return Objects.equals(cakeId, that.cakeId)
                && Objects.equals(slug, that.slug)
                && Objects.equals(title, that.title)
                && Objects.equals(description, that.description)
                && Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cakeId, slug, title, description, image);
    }

    @Override
    public String toString() {
        return "CakeEntity{" +
                "cakeId=" + cakeId +
                ", slug='" + slug + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
