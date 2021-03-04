package corbos.fieldagent.entities;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
public class Agent {

    @Id
    @NotBlank
    @Size(max = 25, message="Identifier must be less than 25 characters.")
    private String identifier;
    
    @NotBlank
    @Size(max = 25, message="First name must be less than 25 characters.")
    private String firstName;
    
    
    @Size(max = 25, message="Middle name must be less than 25 characters.")
    private String middleName;
    
    @NotBlank
    @Size(max = 25, message="Last name must be less than 25 characters.")
    private String lastName;
    
    
    @Size(max = 255, message="Picture URL must be less than 255 characters.")
    private String pictureUrl;
    
    @NotNull(message="Needs valid date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    
    @NotNull(message="Can't be blank")
    @Range(min=36, max=96, message="Height must be between 36 and 96 inches")
    private int height;
    
    @NotNull(message="Needs valid date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate activationDate;
    
    
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    @NotNull(message = "Agency must be selected")
    private Agency agency;

    @ManyToOne
    @JoinColumn(name = "security_clearance_id")
    @NotNull(message ="Security Clearance must be selected")
    private SecurityClearance securityClearance;

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
