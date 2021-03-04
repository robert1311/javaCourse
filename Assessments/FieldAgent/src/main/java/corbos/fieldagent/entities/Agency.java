package corbos.fieldagent.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Agency {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int agencyId;
   
    @Size(max = 100, message="Agency name must be less than 100 characters")
    private String name;

    
}
