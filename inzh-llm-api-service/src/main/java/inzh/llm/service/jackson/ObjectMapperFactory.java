package inzh.llm.service.jackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author Jean-Raffi Nazareth
 */
public class ObjectMapperFactory {
    
    
    public static ObjectMapper create(){
        ObjectMapper m = new ObjectMapper();
        m.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        m.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        m.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        
        m.enable(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
        m.setSerializationInclusion(Include.NON_NULL);
        
        m.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        m.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
        return m;
    }
}
