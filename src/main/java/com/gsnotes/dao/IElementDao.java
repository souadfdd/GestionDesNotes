package com.gsnotes.dao;

import com.gsnotes.bo.Element;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IElementDao extends JpaRepository<Element,Long> {
}
