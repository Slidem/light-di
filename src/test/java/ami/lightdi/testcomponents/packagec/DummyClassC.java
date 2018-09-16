package ami.lightdi.testcomponents.packagec;

import ami.lightdi.annotations.Component;

/**
 * @author Mihai Alexandru
 * @date 26.08.2018
 */
@Component(name = "dummyClassC")
public class DummyClassC implements DummyClass {
    @Override
    public String getText() {
        return "dummyC";
    }
}
