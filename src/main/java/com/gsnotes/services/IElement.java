package com.gsnotes.services;



import com.gsnotes.bo.Element;

import java.util.List;

public interface IElement {
    public void addElement(Element m);

    public void updateElement(Element m);

    public List<Element> getAllElement();

    public void deleteElement(Long id);

    public Element getElementById(Long id);

}
