package corbos.fieldagent.service;

import corbos.fieldagent.data.AgencyRepository;
import corbos.fieldagent.data.AgentRepository;
import corbos.fieldagent.data.AssignmentRepository;
import corbos.fieldagent.data.CountryRepository;
import corbos.fieldagent.data.SecurityClearanceRepository;
import corbos.fieldagent.entities.Agency;
import corbos.fieldagent.entities.Agent;
import corbos.fieldagent.entities.Assignment;
import corbos.fieldagent.entities.Country;
import corbos.fieldagent.entities.SecurityClearance;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class LookupService {

    private final AgentRepository agentRepo;
    private final AssignmentRepository assignmentRepo;
    private final AgencyRepository agencyRepo;
    private final CountryRepository countryRepo;
    private final SecurityClearanceRepository securityRepo;

    public LookupService(AgentRepository agentRepo, AssignmentRepository assignmentRepo, AgencyRepository agencyRepo,
            CountryRepository countryRepo,
            SecurityClearanceRepository securityRepo) {
        this.assignmentRepo = assignmentRepo;
        this.agentRepo = agentRepo;
        this.agencyRepo = agencyRepo;
        this.countryRepo = countryRepo;
        this.securityRepo = securityRepo;
    }

    public List<Agent> findAllAgents() {
        return agentRepo.findAll();
    }

    public List<Agency> findAllAgencies() {
        return agencyRepo.findAll();
    }

    public Optional<Agent> findAgentbyId(String id) {
        return agentRepo.findById(id);
    }

    public Agency findAgencyById(int agencyId) {
        return agencyRepo.findById(agencyId)
                .orElse(null);
    }

    public List<Country> findAllCountries() {
        return countryRepo.findAll();
    }

    public Country findCountryByCode(String countryCode) {
        return countryRepo.findById(countryCode)
                .orElse(null);
    }

    public List<SecurityClearance> findAllSecurityClearances() {
        return securityRepo.findAll();
    }

    public SecurityClearance findSecurityClearanceById(int securityClearanceId) {
        return securityRepo.findById(securityClearanceId)
                .orElse(null);
    }

    public BindingResult addAgentErrorValidation(Agent agent, BindingResult result, HttpServletRequest request) {

        LocalDate minDate = LocalDate.parse("1900-01-01");
        LocalDate maxDate = LocalDate.now().minusYears(10);

        if (agent.getBirthDate() != null) {
            if (agent.getBirthDate().isBefore(minDate) || agent.getBirthDate().isAfter(maxDate)) {
                FieldError error = new FieldError("agent", "birthDate", "BirthDate must be after 01/01/1900 and at least 10 years old from today");
                result.addError(error);
            }
        }
        if (agent.getActivationDate() != null) {
            if (agent.getActivationDate().isBefore(agent.getBirthDate())) {
                FieldError error = new FieldError("agent", "activationDate", "Activation Date must be at least 10 years after birthdate");
                result.addError(error);
            }
        }
        
        List<Agent> currentAgents = findAllAgents();
        if(agent.getIdentifier() !=null){
            for(Agent currentAgent : currentAgents){
                if(currentAgent.getIdentifier().equals(agent.getIdentifier())){
                    FieldError error = new FieldError("agent", "identifier", "Identifier must be unique");
                    result.addError(error);
                    break;
                } else{
                    
                }
            }
        }
        
        return result;

    }
    
    public BindingResult editAgentErrorValidation(Agent agent, BindingResult result, HttpServletRequest request) {

        LocalDate minDate = LocalDate.parse("1900-01-01");
        LocalDate maxDate = LocalDate.now().minusYears(10);

        if (agent.getBirthDate() != null) {
            if (agent.getBirthDate().isBefore(minDate) || agent.getBirthDate().isAfter(maxDate)) {
                FieldError error = new FieldError("agent", "birthDate", "BirthDate must be after 01/01/1900 and at least 10 years old from today");
                result.addError(error);
            }
        }
        if (agent.getActivationDate() != null) {
            if (agent.getActivationDate().isBefore(agent.getBirthDate())) {
                FieldError error = new FieldError("agent", "activationDate", "Activation Date must be at least 10 years after birthdate");
                result.addError(error);
            }
        }
        
        return result;

    }
    
    

    public Agent addAgent(Agent agent) {
        agentRepo.save(agent);
        return agent;
    } 

    public List<Assignment> getAssignmentsByAgentId(String identifier) {
        return assignmentRepo.findByAgentIdentifier(identifier);
    }

    public void deleteAgent(String identifier) {
        agentRepo.deleteById(identifier);

    }

    public BindingResult addAssignmentValidateDates(Assignment assignment, BindingResult result, HttpServletRequest request) {
        List<Assignment> assignments = assignmentRepo.findByAgentIdentifier(request.getParameter("agent"));

        if (assignments.size() == 0) {
            return result;
        }

        LocalDate start = assignment.getStartDate();
        LocalDate end = assignment.getProjectedEndDate();
        LocalDate actualEnd = assignment.getActualEndDate();

        if (assignment.getStartDate() != null) {

            for (Assignment current : assignments) {
                if (!current.equals(assignment) | (current.getAgent()).equals(assignment.getAgent())) {
                    if (start.isAfter(current.getStartDate()) & start.isBefore(current.getProjectedEndDate()) & !(current.getAssignmentId() == assignment.getAssignmentId())) {
                        FieldError error = new FieldError("assignment", "startDate", "Start date can't occur within another assignments date range");
                        result.addError(error);
                        break;
                    } else if (start.equals(current.getStartDate()) & !(current.getAssignmentId() == assignment.getAssignmentId())) {
                        FieldError error = new FieldError("assignment", "startDate", "Start date can't have the same start date as another assignment");
                        result.addError(error);
                        break;
                    }
                }
            }

            if (start.isAfter(end)) {
                FieldError error = new FieldError("assignment", "projectedEndDate", "Projected End Date can't be before start date");
                result.addError(error);
            }
            if (actualEnd != null && actualEnd.isBefore(start)) {
                FieldError error = new FieldError("assignment", "actualEndDate", "Actual End Date can't be before start date");
                result.addError(error);
            }
        }

        if (assignment.getProjectedEndDate() != null) {

            for (Assignment current : assignments) {
                if (!current.equals(assignment) | (current.getAgent()).equals(assignment.getAgent())) {
                    if (end.isAfter(current.getStartDate())
                            & end.isBefore(current.getProjectedEndDate()) 
                            & !(current.getAssignmentId() == assignment.getAssignmentId())) {
                        FieldError error = new FieldError("assignment", "projectedEndDate", "Projected End Date can't occur within another assignments date range");
                        result.addError(error);
                        break;
                    } else if (end.equals(current.getProjectedEndDate()) 
                            & !(current.getAssignmentId() == assignment.getAssignmentId())) {
                        FieldError error = new FieldError("assignment", "projectedEndDate", "Projected End Date can't have the same projected End date as another assignment");
                        result.addError(error);
                        break;
                    }
                }
            }

        }

        return result;
    }

    public Assignment addAssignment(Assignment assignment) {

        return assignmentRepo.save(assignment);
    }

    public void deleteAssignment(int id) {
        assignmentRepo.deleteById(id);
    }

    public Assignment getAssignmentById(int id) {
        return assignmentRepo.getOne(id);
    }

}
