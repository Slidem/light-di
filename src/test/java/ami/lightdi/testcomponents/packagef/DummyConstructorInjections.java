package ami.lightdi.testcomponents.packagef;

import ami.lightdi.annotations.Component;
import ami.lightdi.annotations.Inject;

/**
 * @author Mihai Alexandru
 * @date 21.10.2018
 */
@Component
public class DummyConstructorInjections {

    @Inject
    public DummyConstructorInjections(Dummy dummy) {
    }

}
