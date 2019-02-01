package test;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class MyExtension implements BeforeEachCallback, AfterEachCallback {

    long ts;

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        ts = System.currentTimeMillis();
        System.out.println("MyExtension.beforeEach()");
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        System.out.println(context.getTestMethod().get().getName() + " : " + (System.currentTimeMillis() - ts) + " ms");
    }

}
