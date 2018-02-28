package mymethod;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;


public class IndexOfMethod implements TemplateMethodModelEx{

    @Override
    public Object exec(List arguments) throws TemplateModelException {

        if(arguments==null||arguments.size()!=2){
            throw new TemplateModelException("Wrong arguments.");
        }


        int index = arguments.get(1).toString().indexOf(arguments.get(0).toString());

        return index;
    }

}
