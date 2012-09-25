package bieberfever.compositeservice;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * JAXB class - task-element
 * @author BieberFever (based on codesnippet by rao)
 */
    @XmlRootElement(name = "task")
    public class Task implements Serializable{

        @XmlID
        @XmlAttribute
        public String id;
        
        @XmlAttribute
        public String name;
        
        @XmlAttribute
        public String date;
        
        @XmlAttribute
        public String status;
        
        @XmlElement
        public String description;
        
        @XmlElement
        public String attendants;
               
        
        
    }