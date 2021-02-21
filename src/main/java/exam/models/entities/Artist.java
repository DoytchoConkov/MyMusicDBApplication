package exam.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "artists")
public class Artist extends BaseEntity{
    private ArtistName name;
    private String carrierInformation;

    public Artist() {
    }

    public Artist(ArtistName name) {
        this.name = name;
        this.carrierInformation = carrierInformation;
    }

    @Enumerated(EnumType.STRING)
    public ArtistName getName() {
        return name;
    }

    public void setName(ArtistName name) {
        this.name = name;
    }
@Column(name = "carrier_information",columnDefinition = "TEXT")
    public String getCarrierInformation() {
        return carrierInformation;
    }

    public void setCarrierInformation(String carrierInformation) {
        this.carrierInformation = carrierInformation;
    }
}
