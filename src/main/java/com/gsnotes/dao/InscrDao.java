package com.gsnotes.dao;

import com.gsnotes.bo.InscriptionAnnuelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InscrDao extends JpaRepository<InscriptionAnnuelle, Long> {

}
