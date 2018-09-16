package ami.lightdi.testcomponents.packagec;

import ami.lightdi.annotations.Component;
import ami.lightdi.annotations.Inject;

import java.util.List;

/**
 * @author Mihai Alexandru
 * @date 27.08.2018
 */
@Component
public class ListDependencyConstructor {

    private List<DummyClass> dummies;

    @Inject
    public ListDependencyConstructor(List<DummyClass> dummies) {
        this.dummies = dummies;
    }
}
