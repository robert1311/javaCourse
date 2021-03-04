package corbos.fieldagent.data;

import corbos.fieldagent.entities.SecurityClearance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityClearanceRepository
        extends JpaRepository<SecurityClearance, Integer> {

}
