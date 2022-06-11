package com.gsnotes.services;

import com.gsnotes.bo.Module;
import com.gsnotes.utils.export.ExcelExporter;

import java.util.List;

public interface Imodule {
    public void addModule(Module m);

    public void updateModule(Module m);

    public List<Module> getAllModule();

    public void deleteModule(Long id);

    public Module getModuleById(Long id);

    //public ExcelExporter prepareModuleExport(Module module);
}
