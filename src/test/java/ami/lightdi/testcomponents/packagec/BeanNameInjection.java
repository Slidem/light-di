package ami.lightdi.testcomponents.packagec;

import ami.lightdi.annotations.Component;
import ami.lightdi.annotations.Inject;

/**
 * @author Mihai Alexandru
 * @date 15.09.2018
 */
@Component
public class BeanNameInjection {

    @Inject("dummyClassC")
    private DummyClass dummyClass;

    public DummyClass getDummyClass() {
        return dummyClass;
    }
}
