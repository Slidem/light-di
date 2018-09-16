package ami.lightdi.testcomponents.packageb;

import ami.lightdi.annotations.Component;
import ami.lightdi.annotations.Inject;

/**
 * @author Mihai Alexandru
 * @date 26.08.2018
 */
@Component(name = "myDummyComponentD")
public class DummyComponentD {

    private DummyComponentA ca;

    private DummyComponentB cb;

    private DummyComponentC cc;

    @Inject
    public DummyComponentD(DummyComponentA ca, DummyComponentB cb, DummyComponentC cc) {
        this.ca = ca;
        this.cb = cb;
        this.cc = cc;
    }

    public DummyComponentA getCa() {
        return ca;
    }

    public DummyComponentB getCb() {
        return cb;
    }

    public DummyComponentC getCc() {
        return cc;
    }
}
