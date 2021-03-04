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
public class SecurityClearance {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int securityClearanceId;
    @Column(nullable = false)
    @Size(max = 50, message="Security Clearance name must be less than 50 characters.")
    private String name;

    
}
