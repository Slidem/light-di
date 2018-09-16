package ami.lightdi.testcomponents.packagec;

import ami.lightdi.annotations.Component;

/**
 * @author Mihai Alexandru
 * @date 26.08.2018
 */
@Component
public class DummyClassB implements DummyClass {
    @Override
    public String getText() {
        return "dummyB";
    }
}
