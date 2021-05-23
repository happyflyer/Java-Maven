package org.example.java_maven;

import org.junit.Assert;
import org.junit.Test;

public class HelloTest {
    @Test
    public void sayHelloTest() {
        Assert.assertEquals("Hello World!", new Hello().sayHello());
    }
}
