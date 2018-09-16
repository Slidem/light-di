package ami.lightdi.testcomponents.packageb;

import ami.lightdi.annotations.Component;
import ami.lightdi.annotations.Inject;

/**
 * @author Mihai Alexandru
 * @date 26.08.2018
 */
@Component
public class DummyComponentC {

    @Inject
    private DummyComponentA dummyComponentA;
}
