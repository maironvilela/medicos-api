package med.voll.api;

import med.voll.api.domain.medico.modelos.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepositorio  extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByIsAtivoTrue(Pageable pageable);
}
