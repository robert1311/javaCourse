package corbos.fieldagent.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int assignmentId;  
    
    @Column(nullable = false)
    @NotNull(message="Must enter a valid Start Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    
    @Column(nullable = false)
    @NotNull(message="Must enter a valid Projected End Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate projectedEndDate;
    
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate actualEndDate;
    
    @Column
    private String notes;

    @NotNull(message="Must select a country")
    @ManyToOne
    @JoinColumn(name = "country_code")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "identifier")  
    private Agent agent;

}
