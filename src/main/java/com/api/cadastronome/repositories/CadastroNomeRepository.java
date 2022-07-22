package com.api.cadastronome.repositories;



import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.cadastronome.models.CadastrarNomeModel;

@Repository
public interface CadastroNomeRepository extends JpaRepository<CadastrarNomeModel, UUID> {

}
