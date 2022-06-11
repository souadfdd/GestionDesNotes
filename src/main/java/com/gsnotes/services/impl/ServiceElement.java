package com.gsnotes.services.impl;

import com.gsnotes.bo.Element;
import com.gsnotes.dao.IElementDao;
import com.gsnotes.dao.IModuleDao;
import com.gsnotes.services.IElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ServiceElement implements IElement {
    @Autowired
    private IElementDao Dao;

    @Override
    public void addElement(Element m) {
        Dao.save(m);

    }

    @Override
    public void updateElement(Element m) {
       Dao.save(m);
    }

    @Override
    public List<Element> getAllElement() {
        return Dao.findAll();
    }

    @Override
    public void deleteElement(Long id) {
        Dao.deleteById(id);

    }

    @Override
    public Element getElementById(Long id) {
        return Dao.getById(id);
    }
}
