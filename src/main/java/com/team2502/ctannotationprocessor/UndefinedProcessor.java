package com.team2502.ctannotationprocessor;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class UndefinedProcessor extends AbstractProcessor
{
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv)
    {
        for(TypeElement typeElement : annotations)
        {
            for(Element element : roundEnv.getElementsAnnotatedWith(typeElement))
            {
                Undefined annotation = element.getAnnotation(Undefined.class);
                if(annotation.safe())
                {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, "Element `" + element.getSimpleName() + "` is set to `UNDEFINED`. This may be ok for this variable.");
                }
                else
                {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Element `" + element.getSimpleName() + "` is set to `UNDEFINED`. It is highly probable that this will cause a failure on the robot.");
                }
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
        return new HashSet<>(Collections.singletonList(Undefined.class.getName()));
    }
}
