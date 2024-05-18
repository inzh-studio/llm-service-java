package inzh.llm.service.parameter;

import de.kherud.llama.InferenceParameters;
import de.kherud.llama.ModelParameters;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class ConfigurationGenerator {
    
    private Method getSetter(Class c, Field f){
        String name = "set" + f.getName().substring(6).toLowerCase().replaceAll("_", "");
        for(Method m : c.getDeclaredMethods()){
            if(m.getName().toLowerCase().equals(name)){
                return m;
            }
        }
        
        switch(f.getName()){
            case "PARAM_DYNATEMP_RANGE":
                name = "setDynamicTemperatureRange";
                break;
            case "PARAM_DYNATEMP_EXPONENT":
                name = "setDynamicTemperatureExponent";
                break;
            case "PARAM_LOGIT_BIAS":
                name = "setTokenIdBias";
                break;
            case "PARAM_STOP":
                name = "setStopStrings";
                break;
                
                
            case "PARAM_MODEL":
                name = "setModelFilePath";
                break;
            case "PARAM_DEFRAG_THOLD":
                name = "setDefragmentationThreshold";
                break;
            case "PARAM_CONT_BATCHING":
                name = "setContinuousBatching";
                break;
        }
        
        for(Method m : c.getDeclaredMethods()){
            if(m.getName().equals(name)){
                return m;
            }
        }
        
        return null;
    }

    private Class getType(Method m) {
        return m.getParameterTypes()[0];
    }
    
    private List<Method> getMethods(Class c){
        List<Method> ms = new ArrayList<>();
        for(Field f : c.getDeclaredFields()){
            if (java.lang.reflect.Modifier.isStatic(f.getModifiers())) {
                if(f.getName().startsWith("PARAM_")){
                    Method m = getSetter(c, f);
                    if(m != null){
                        ms.add(m);
                    }
                }
            } 
        }
        return ms;
    }
    
    private String getPropertyName(Method m){
        String name = m.getName();
        name = name.substring(3);
        name = name.substring(0, 1).toLowerCase() + name.substring(1);
        return name;
    }
    
    private String getTypeName(Class c){
        String name = c.getSimpleName();
        switch(name){
            default:
                if(name.contains("[]")){
                    return name;
                }
                return name.substring(0,1).toUpperCase() + name.substring(1);
            case "int":
                return "Integer";
        }
    }
    
    
    private void printFields(List<Method> methods){
        for(Method m : methods){
            Class t = getType(m);
            String tn = getTypeName(t);
            String name = getPropertyName(m);
            System.out.println("private " + tn + " " + name + ";");
        }
    }
    
    private void printSetter(List<Method> methods, String targetType){
        
        for(Method m : methods){
            Class t = getType(m);
            String tn = getTypeName(t);
            String name = getPropertyName(m);
            System.out.println("public " + targetType + " " + m.getName() + "(" + tn + " " + name + "){");
            System.out.println("this." + name + "=" + name + ";");
            System.out.println("return this;");
            System.out.println("}");
        }
    }

    private void printToParameters(List<Method> methods){
        Class c = methods.get(0).getDeclaringClass();
        System.out.println("public " + c.getSimpleName() + " toParameters() {");
        System.out.println(c.getSimpleName() + " p = new " + c.getSimpleName() +"();");
        for(Method m : methods){
            String name = getPropertyName(m);
            System.out.println("if (this." + name + " != null){");
            System.out.println("    p." + m.getName() + "(this." + name + ");");
            System.out.println("}");
        }
        System.out.println("return p;");
        System.out.println("}");
    }
    
    private void onType(Class c, String targetType){
        List<Method> methods = getMethods(c);
        printFields(methods);
        printSetter(methods, targetType);
        printToParameters(methods);
    }
    
    public void printParams(){
        onType(InferenceParameters.class, "InferenceConfiguration");
        onType(ModelParameters.class, "ModelConfiguration");
    }
}
