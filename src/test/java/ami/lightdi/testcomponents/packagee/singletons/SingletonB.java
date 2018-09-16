package ami.lightdi.testcomponents.packagee.singletons;

import ami.lightdi.annotations.Component;
import ami.lightdi.annotations.Inject;
import ami.lightdi.beans.framework.PrototypeFactory;
import ami.lightdi.testcomponents.packagee.PrototypeA;
import ami.lightdi.testcomponents.packagee.PrototypeB;

/**
 * @author Mihai Alexandru
 * @date 08.09.2018
 */
@Component
public class SingletonB {

    private PrototypeFactory<PrototypeA> prototypeFactoryA;

    private PrototypeFactory<PrototypeB> prototypeFactoryB;

    @Inject
    public SingletonB(PrototypeFactory<PrototypeA> prototypeFactoryA, PrototypeFactory<PrototypeB> prototypeFactoryB) {
        this.prototypeFactoryA = prototypeFactoryA;
        this.prototypeFactoryB = prototypeFactoryB;
    }

    public PrototypeA getPrototypeFactoryA() {
        return prototypeFactoryA.get();
    }

    public PrototypeB getPrototypeFactoryB() {
        return prototypeFactoryB.get();
    }
}
