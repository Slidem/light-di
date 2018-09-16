package ami.lightdi.testcomponents.packaged;

import ami.lightdi.annotations.Component;
import ami.lightdi.annotations.Inject;

/**
 * @author Mihai Alexandru
 * @date 26.08.2018
 */
@Component
public class CircularC {

    @Inject
    private CircularA circularA;
}
