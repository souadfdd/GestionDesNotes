package com.gsnotes.services.impl;

import com.gsnotes.bo.Element;
import com.gsnotes.bo.Module;
import com.gsnotes.dao.IModuleDao;
import com.gsnotes.dao.IUtilisateurDao;
import com.gsnotes.services.Imodule;
import com.gsnotes.utils.export.ExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ModuleServiceImpl implements Imodule {
    @Autowired
    private IModuleDao ImoduleDao;


    @Override
    public void addModule(Module m) {
        ImoduleDao.save(m);

    }

    @Override
    public void updateModule(Module m) {
        ImoduleDao.save(m);
    }

    @Override
    public List<Module> getAllModule() {
        return ImoduleDao.findAll();
    }

    @Override
    public void deleteModule(Long id) {
        ImoduleDao.deleteById(id);
    }

    @Override
    public Module getModuleById(Long id) {
        return ImoduleDao.getById(id);
    }
}
