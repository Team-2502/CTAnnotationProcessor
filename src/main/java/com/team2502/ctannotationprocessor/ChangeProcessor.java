package com.team2502.ctannotationprocessor;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.*;

public class ChangeProcessor extends AbstractProcessor
{
    static StringBuilder BUILDER = new StringBuilder();
    static List<Name> NAME_LIST = new ArrayList<>();

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv)
    {
        for(TypeElement typeElement : annotations)
        {
//            processingEnv.getMessager().printMessage(Diagnostic.Kind.OTHER, "hello change");
            for(Element element : roundEnv.getElementsAnnotatedWith(typeElement))
            {
                Change annotation = element.getAnnotation(Change.class);
                BUILDER.setLength(0);
                Element e = element.getEnclosingElement();

                NAME_LIST.clear();

                while(e != null)
                {
                    NAME_LIST.add(e.getSimpleName());
                    e = e.getEnclosingElement();
                }

                Collections.reverse(NAME_LIST);

                for(Name n : NAME_LIST)
                {
                    BUILDER.append(n).append('.');
                }

                BUILDER.append(element.getSimpleName());
                processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, "[Change]: `" + BUILDER.toString() + "` needs to be changed for the following reason: " + annotation.reason());

            }
        }
        return false;
    }

    @Override
    public SourceVersion getSupportedSourceVersion()
    {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes()
    {
        return new HashSet<>(Collections.singletonList(Change.class.getName()));
    }
}
