package corbos.fieldagent.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Entity
@Data
public class Country {

    @Id
    @Range(min=0, max = 4)
    private String countryCode;
    @Column(nullable = false)
    @Size(max = 100, message="Country Name must be less than 100 characters.")
    private String name;

}
