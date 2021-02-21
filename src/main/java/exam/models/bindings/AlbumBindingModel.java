package exam.models.bindings;

import exam.models.entities.Artist;
import exam.models.entities.ArtistName;
import exam.models.entities.GenreName;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AlbumBindingModel {
    private String name;
    private String imgUrl;
    private String description;
    private Integer copies;
    private BigDecimal price;
    private LocalDate releaseDate;
    private String producer;
    private GenreName genre;
    private ArtistName artist;

    public AlbumBindingModel() {
    }

    @NotBlank(message = "Album name can not be empty.")
    @Size(min = 3, max = 20, message = "Album name length must be between three and  twenty characters.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank(message = "ImageUrl can not be empty.")
    @Size(min = 5, message = "ImageUrl length must be minimum five characters.")
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @NotBlank(message = "Description can not be empty.")
    @Size(min = 5, message = "Description length must be minimum five characters.")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    @Min(10)
    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    @NotNull
    @DecimalMin("0")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @NotNull(message = "Please , choose date.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Date must be in past or present.")
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @NotNull(message = "Please, choose Genre!")
    public GenreName getGenre() {
        return genre;
    }

    public void setGenre(GenreName genre) {
        this.genre = genre;
    }

    @NotNull(message = "Please, choose Artist!")
    public ArtistName getArtist() {
        return artist;
    }

    public void setArtist(ArtistName artist) {
        this.artist = artist;
    }
}
