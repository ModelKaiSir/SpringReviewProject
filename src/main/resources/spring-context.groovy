import domain.Car
import org.apache.taglibs.standard.extra.spath.Step
import org.springframework.core.io.ClassPathResource

import javax.xml.bind.annotation.XmlNs

beans {

    xmlns context: "http://www.springframework.org/schema/context"

    context.'component-scan'('base-package':"domain"){

    }



    def stream;
    def config = new Properties();

    try {
        stream = new ClassPathResource("").inputStream
        config.load(stream)
    } finally {

        if(null != stream){

            stream.close()
        }
    }

    car(Car){

        bean->
            bean.scope = "prototype"
    }
}