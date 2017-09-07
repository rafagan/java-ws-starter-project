package vetorlog.model.util.relational;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrapperStaging implements IEntityManagerWrapper {
    @PersistenceContext(name="mysql_staging")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        em = entityManager;
    }
}
