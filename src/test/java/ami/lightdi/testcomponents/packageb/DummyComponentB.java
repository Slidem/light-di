package ami.lightdi.testcomponents.packageb;

import ami.lightdi.annotations.Component;
import ami.lightdi.annotations.Inject;

/**
 * @author Mihai Alexandru
 * @date 26.08.2018
 */
@Component(name = "myDummyComponentB")
public class DummyComponentB {

    @Inject
    private DummyComponentA componentA;

    @Inject
    private DummyComponentC componentC;

    public DummyComponentA getComponentA() {
        return componentA;
    }

    public DummyComponentC getComponentC() {
        return componentC;
    }
}
