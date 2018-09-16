package ami.lightdi.testcomponents.packagee.singletons;

import ami.lightdi.annotations.Component;
import ami.lightdi.annotations.Inject;
import ami.lightdi.annotations.Scope;
import ami.lightdi.testcomponents.packagee.PrototypeA;
import ami.lightdi.testcomponents.packagee.PrototypeB;

/**
 * @author Mihai Alexandru
 * @date 08.09.2018
 */
@Component(scope = Scope.SINGLETON)
public class SingletonA {

    private PrototypeA prototypeA;

    private PrototypeB prototypeB;

    @Inject
    public SingletonA(PrototypeA prototypeA, PrototypeB prototypeB) {
        this.prototypeA = prototypeA;
        this.prototypeB = prototypeB;
    }

    public PrototypeA getPrototypeA() {
        return prototypeA;
    }

    public PrototypeB getPrototypeB() {
        return prototypeB;
    }
}
