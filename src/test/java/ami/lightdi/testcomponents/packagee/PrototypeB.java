package ami.lightdi.testcomponents.packagee;

import ami.lightdi.annotations.Component;
import ami.lightdi.annotations.Inject;
import ami.lightdi.annotations.Scope;
import ami.lightdi.testcomponents.packagee.prototypes.ProtA;
import ami.lightdi.testcomponents.packagee.prototypes.ProtB;

/**
 * @author Mihai Alexandru
 * @date 08.09.2018
 */
@Component(scope = Scope.PROTOTYPE)
public class PrototypeB {

    private ProtA protA;

    private ProtB protB;

    @Inject
    public PrototypeB(ProtA protA, ProtB protB) {
        this.protA = protA;
        this.protB = protB;
    }

    public ProtA getProtA() {
        return protA;
    }

    public ProtB getProtB() {
        return protB;
    }
}
